package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;
import fa.training.services.AirportService;
import fa.training.services.FixedWingService;
import fa.training.services.HelicopterService;
import fa.training.utils.BusinessRule;

public class AirportServiceImplement implements AirportService {
	private ArrayList<Airport> airports;
	private ArrayList<FixedWing> fixedWings;
	private ArrayList<Helicopter> helicopters;
	private FixedWingService fixedWingService;
	private HelicopterService helicopterService;

	public AirportServiceImplement() {
		airports = (ArrayList<Airport>) this.getAll();
		fixedWingService = new FixedWingServiceImplement();
		fixedWings = (ArrayList<FixedWing>) fixedWingService.getAll();

		helicopterService = new HelicopterServiceImplement();
		helicopters = (ArrayList<Helicopter>) helicopterService.getAll();

	}

	@Override
	public void create(Airport airport) {
//		System.out.println("All airports");
//		for (Airport ap : airports) {
//			System.out.println(ap);
//		}
//		Scanner scanner = new Scanner(System.in);

//
//		Airport airport = new Airport(airportName, runwaySize, fixedWingSlot, helicopterSlot);
		String lastID = null;
		if (this.airports.size() != 0) {

			lastID = airports.get(airports.size() - 1).getID();
			// AP12345
			int number = Integer.parseInt(lastID.substring(2, 7));
			lastID = lastID.substring(0, 2) + (number + 1);
		} else {
			lastID = "AP10000";
		}
		airport.setID(lastID);
		airports.add(airport);
		FileIO.writeToFile(airports, AirportService.FILE_NAME);
		System.out.println("Create airport finished");

	}

	@Override
	public List<Airport> getAll() {
		ArrayList<Airport> airports = new ArrayList<Airport>();
		FileIO.readToArray(airports, FILE_NAME);
		return airports;

	}

	@Override
	public void addFixedWing(FixedWing fixedWing, Airport airport) throws Exception {

		if (!BusinessRule.validateAddFixedWingToAirport(fixedWing, airport)) {

			return;

		}

		// tìm fixedWing trong các fixed wing lấy từ file
		FixedWing chosenFixedWing = null;
		for (int i = 0; i < this.fixedWings.size(); i++) {
			if (this.fixedWings.get(i).getID().equals(fixedWing.getID())) {
				fixedWings.set(i, fixedWing);
				chosenFixedWing = fixedWings.get(i);
				break;
			}

		}

		// tìm airport trong các airport lấy từ file
		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			// System.out.println(a);
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);
				chosenAirport = this.airports.get(i);
				// đoạn này em check xem 2 biến a với choseAirport có reference đến cùng 1
				// object không , nó return true
				// System.out.println(a == chosenAirport);
				break;
			}

		}
		// đến đoạn này em check lại thì nó lại ko reference đến cùng một object nữa
//		for (Airport a : this.airports) {
//			System.out.println(a.getID());
//			System.out.println(chosenAirport.getID());
//			if (a == chosenAirport) {
//
//				System.out.println("Choose correct");
//			} else {
//				System.out.println("Not choose");
//			}
//		}

		chosenAirport.getFixedWingIDs().add(chosenFixedWing.getID());

		chosenFixedWing.setParticipate(true);
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(fixedWings, FixedWingService.FILE_NAME);

		// System.out.println("add fixed wing success");

	}

	@Override
	public void addFixedWing(List<FixedWing> fixedWings, Airport airport) throws Exception {
		if (!BusinessRule.validateAddFixedWingToAirport(fixedWings, airport)) {
			return;
		}

		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);
				chosenAirport = this.airports.get(i);

			}

		}

		for (int i = 0; i < this.fixedWings.size(); i++) {
			for (FixedWing addFixedWing : fixedWings) {
				if (this.fixedWings.get(i).getID().equals(addFixedWing.getID())) {
					this.fixedWings.set(i, addFixedWing);
					break;

				}
			}

		}

		for (FixedWing addFixedWing : fixedWings) {
			chosenAirport.getFixedWingIDs().add(addFixedWing.getID());
			addFixedWing.setParticipate(true);
		}

		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(this.fixedWings, FixedWingService.FILE_NAME);

	}

	@Override
	public void removeFixedWing(FixedWing fixedWing, Airport airport) {
		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);
				chosenAirport = this.airports.get(i);
				break;

			}

		}

		FixedWing chosenFixedWing = null;
		for (int i = 0; i < this.fixedWings.size(); i++) {
			if (fixedWings.get(i).getID().equals(fixedWing.getID())) {
				fixedWings.set(i, fixedWing);
				chosenFixedWing = fixedWings.get(i);
				break;
			}

		}

		for (int i = 0; i < chosenAirport.getFixedWingIDs().size(); i++) {
			String id = chosenAirport.getFixedWingIDs().get(i);
			if (chosenFixedWing.getID().equals(id)) {
				chosenAirport.getFixedWingIDs().remove(id);
				chosenFixedWing.setParticipate(false);
				break;
			}

		}
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(fixedWings, FixedWingService.FILE_NAME);

	}

	@Override
	public void removeFixedWing(List<FixedWing> removeFixedWings, Airport airport) {
		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);

				chosenAirport = this.airports.get(i);
				break;
			}

		}

		for (int i = 0; i < this.fixedWings.size(); i++) {
			for (FixedWing removeFixedWing : removeFixedWings) {
				if (this.fixedWings.get(i).getID().equals(removeFixedWing.getID())) {
					this.fixedWings.set(i, removeFixedWing);
					break;

				}

			}

		}

		for (int i = 0; i < removeFixedWings.size(); i++) {
			String id = removeFixedWings.get(i).getID();
			chosenAirport.getFixedWingIDs().remove(id);
			removeFixedWings.get(i).setParticipate(false);

		}
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(this.fixedWings, FixedWingService.FILE_NAME);

	}

	@Override
	public void addHelicopter(Helicopter helicopter, Airport airport) throws Exception {
		if (!BusinessRule.validateAddHelicopterToAirport(helicopter, airport)) {
			return;
		}

		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);
				chosenAirport = this.airports.get(i);
				break;
			}
		}
		Helicopter chosenHelicopter = null;
		for (int i = 0; i < this.helicopters.size(); i++) {
			if (this.helicopters.get(i).getID().equals(helicopter.getID())) {
				this.helicopters.set(i, helicopter);
				chosenHelicopter = this.helicopters.get(i);
				break;
			}
		}

		chosenAirport.getHelicopterIDs().add(chosenHelicopter.getID());
		chosenHelicopter.setParticipate(true);

		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(helicopters, HelicopterService.FILE_NAME);
		System.out.println("add helicopter finished");

	}

	@Override
	public void addHelicopter(List<Helicopter> addHelicopters, Airport airport) throws Exception {
		if (!BusinessRule.validateAddHelicopterToAirport(addHelicopters, airport)) {
			return;
		}

		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);
				chosenAirport = this.airports.get(i);
				break;
			}
		}

		for (int i = 0; i < this.helicopters.size(); i++) {
			for (Helicopter addHelicopter : addHelicopters) {
				if (this.helicopters.get(i).getID().equals(addHelicopter.getID())) {
					this.helicopters.set(i, addHelicopter);

					break;
				}
			}

		}

		for (Helicopter addHelicopter : addHelicopters) {
			chosenAirport.getHelicopterIDs().add(addHelicopter.getID());
			addHelicopter.setParticipate(true);
		}

		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(this.helicopters, HelicopterService.FILE_NAME);
		System.out.println("Add helicopters finished");
	}

	@Override
	public void removeHelicopter(Helicopter removeHelicopter, Airport airport) {
		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);
				chosenAirport = this.airports.get(i);
			}

		}

		Helicopter chosenHelicopter = null;
		for (int i = 0; i < this.helicopters.size(); i++) {
			if (this.helicopters.get(i).getID().equals(removeHelicopter.getID())) {
				this.helicopters.set(i, removeHelicopter);
				chosenHelicopter = this.helicopters.get(i);
				break;
			}
		}

		for (int i = 0; i < chosenAirport.getHelicopterIDs().size(); i++) {
			String removeID = chosenAirport.getHelicopterIDs().get(i);
			if (removeID.equals(chosenHelicopter.getID())) {
				chosenAirport.getHelicopterIDs().remove(removeID);
				break;
			}

		}
		chosenHelicopter.setParticipate(false);
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(this.helicopters, HelicopterService.FILE_NAME);
		System.out.println("Remove helicopter finished");

	}

	@Override
	public void removeHelicopter(List<Helicopter> removeHelicopters, Airport airport) {
		Airport chosenAirport = null;
		for (int i = 0; i < this.airports.size(); i++) {
			if (this.airports.get(i).getID().equals(airport.getID())) {
				this.airports.set(i, airport);
				chosenAirport = this.airports.get(i);
				break;

			}

		}

		for (int i = 0; i < this.helicopters.size(); i++) {
			for (Helicopter removeHelicopter : removeHelicopters) {
				if (this.helicopters.get(i).getID().equals(removeHelicopter.getID())) {
					this.helicopters.set(i, removeHelicopter);

					break;
				}

			}

		}

		for (int i = 0; i < removeHelicopters.size(); i++) {
			String removeID = removeHelicopters.get(i).getID();
			chosenAirport.getHelicopterIDs().remove(removeID);
			removeHelicopters.get(i).setParticipate(false);
		}
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(this.helicopters, HelicopterService.FILE_NAME);
		System.out.println("Remove helicopters finished");

	}

}
