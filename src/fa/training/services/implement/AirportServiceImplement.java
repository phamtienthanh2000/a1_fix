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
//		System.out.println("Enter airport name");
//		String airportName = scanner.nextLine();
//		System.out.println("Enter airport runway size");
//		float runwaySize = Float.parseFloat(scanner.nextLine());
//		System.out.println("Enter number of fixed wing slot ");
//		int fixedWingSlot = Integer.parseInt(scanner.nextLine());
//		System.out.println("Enter number of Helicopter slot ");
//		int helicopterSlot = Integer.parseInt(scanner.nextLine());
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

	/**
	 * ĐOẠN NÀY EM CHƯA LÀM ĐƯỢC Ạ
	 * 
	 */
	@Override
	public void addFixedWing(FixedWing fixedWing, Airport airport) throws Exception {

		if (!BusinessRule.validateAddFixedWingToAirport(fixedWing, airport)) {

			return;

		}

		// tìm fixedWing trong các fixed wing lấy từ file
		FixedWing chosenFixedWing = null;
		for (FixedWing f : this.fixedWings) {
			if (f.getID().equals(fixedWing.getID())) {
				f = fixedWing;
				chosenFixedWing = f;
				break;
			}

		}

		// tìm airport trong các airport lấy từ file
		Airport chosenAirport = null;
		for (Airport a : this.airports) {
			// System.out.println(a);
			if (a.getID().equals(airport.getID())) {
				a = airport;
				chosenAirport = a;
				// đoạn này em check xem 2 biến a với choseAirport có reference đến cùng 1
				// object không , nó return true
				System.out.println(a == chosenAirport);
				break;
			}

		}
		// đến đoạn này em check lại thì nó lại ko reference đến cùng một object nữa
		for (Airport a : this.airports) {
			System.out.println(a.getID());
			System.out.println(chosenAirport.getID());
			if (a == chosenAirport) {

				System.out.println("Choose correct");
			} else {
				System.out.println("Not choose");
			}
		}

		chosenAirport.getFixedWingIDs().add(chosenFixedWing.getID());
		System.out.println(chosenAirport);
		chosenFixedWing.setParticipate(true);
		System.out.println(chosenFixedWing);

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
		for (Airport ap : this.airports) {
			if (ap.getID().equals(airport.getID())) {
				ap = airport;
				chosenAirport = ap;

			}

		}

		for (FixedWing fx : this.fixedWings) {
			for (FixedWing addFixedWing : fixedWings) {
				if (fx.getID().equals(addFixedWing.getID())) {
					fx = addFixedWing;
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
		for (Airport ap : this.airports) {
			if (ap.getID().equals(airport.getID())) {
				chosenAirport = ap;

			}

		}

		FixedWing chosenFixedWing = null;
		for (FixedWing fx : this.fixedWings) {
			if (fx.getID().equals(fixedWing.getID())) {
				fx = fixedWing;
				chosenFixedWing = fx;
				break;
			}

		}

		for (int i = 0; i < airport.getFixedWingIDs().size(); i++) {
			String id = airport.getFixedWingIDs().get(i);
			if (chosenFixedWing.getID().equals(id)) {
				airport.getFixedWingIDs().remove(id);
				chosenFixedWing.setParticipate(false);
				break;
			}

		}
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(fixedWings, FixedWingService.FILE_NAME);

	}

	@Override
	public void removeFixedWing(List<FixedWing> fixedWing, Airport airport) {
		Airport chosenAirport = null;
		for (Airport ap : this.airports) {
			if (ap.getID().equals(airport.getID())) {
				ap = airport;
				chosenAirport = ap;
				break;
			}

		}

		for (FixedWing fx : this.fixedWings) {
			for (FixedWing removeFixedWing : fixedWing) {
				if (fx.getID().equals(removeFixedWing.getID())) {
					fx = removeFixedWing;
					break;

				}

			}

		}

		for (int i = 0; i < fixedWing.size(); i++) {
			String id = fixedWing.get(i).getID();
			chosenAirport.getFixedWingIDs().remove(id);
			fixedWing.get(i).setParticipate(false);

		}
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(fixedWings, FixedWingService.FILE_NAME);

	}

	@Override
	public void addHelicopter(Helicopter helicopter, Airport airport) throws Exception {
		if (!BusinessRule.validateAddHelicopterToAirport(helicopter, airport)) {
			return;
		}

		Airport chosenAirport = null;
		for (Airport ap : this.airports) {
			if (ap.getID().equals(airport.getID())) {
				ap = airport;
				chosenAirport = ap;

			}
		}
		Helicopter chosenHelicopter = null;
		for (Helicopter addhelicopter : this.helicopters) {
			if (addhelicopter.getID().equals(helicopter.getID())) {
				addhelicopter = helicopter;
				chosenHelicopter = addhelicopter;
			}
		}

		chosenAirport.getHelicopterIDs().add(chosenHelicopter.getID());
		chosenHelicopter.setParticipate(true);

		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(helicopters, HelicopterService.FILE_NAME);

	}

	@Override
	public void addHelicopter(List<Helicopter> helicopters, Airport airport) throws Exception {
		if (!BusinessRule.validateAddFixedWingToAirport(fixedWings, airport)) {
			return;
		}

		Airport chosenAirport = null;
		for (Airport ap : this.airports) {
			if (ap.getID().equals(airport.getID())) {
				ap = airport;
				chosenAirport = ap;
			}
		}

		for (Helicopter helicopter : this.helicopters) {
			for (Helicopter addHelicopter : helicopters) {
				if (helicopter.getID().equals(addHelicopter.getID())) {
					helicopter = addHelicopter;
					break;
				}
			}

		}

		for (Helicopter addHelicopter : helicopters) {
			chosenAirport.getHelicopterIDs().add(addHelicopter.getID());
			addHelicopter.setParticipate(true);
		}

		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(this.helicopters, HelicopterService.FILE_NAME);
	}

	@Override
	public void removeHelicopter(Helicopter helicopter, Airport airport) {
		Airport chosenAirport = null;
		for (Airport ap : this.airports) {
			if (ap.getID().equals(airport.getID())) {
				ap = airport;
				chosenAirport = ap;
			}

		}

		Helicopter chosenHelicopter = null;
		for (Helicopter removeHelicopter : this.helicopters) {
			if (removeHelicopter.getID().equals(helicopter.getID())) {
				removeHelicopter = helicopter;
				chosenHelicopter = removeHelicopter;
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

	}

	@Override
	public void removeHelicopter(List<Helicopter> helicopters, Airport airport) {
		Airport chosenAirport = null;
		for (Airport ap : this.airports) {
			if (ap.getID().equals(airport.getID())) {
				ap = airport;
				chosenAirport = ap;
				break;

			}

		}

		for (Helicopter h : this.helicopters) {
			for (Helicopter removeHelicopter : helicopters) {
				if (h.getID().equals(removeHelicopter.getID())) {
					h = removeHelicopter;
					break;
				}

			}

		}

		for (int i = 0; i < helicopters.size(); i++) {
			String removeID = helicopters.get(i).getID();
			chosenAirport.getHelicopterIDs().remove(removeID);
			helicopters.get(i).setParticipate(false);
		}
		FileIO.writeToFile(airports, FILE_NAME);
		FileIO.writeToFile(this.helicopters, HelicopterService.FILE_NAME);

	}

}
