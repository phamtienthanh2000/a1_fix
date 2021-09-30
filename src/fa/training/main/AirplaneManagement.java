package fa.training.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;
import fa.training.services.AirportService;
import fa.training.services.FixedWingService;
import fa.training.services.HelicopterService;
import fa.training.services.implement.AirportServiceImplement;
import fa.training.services.implement.FixedWingServiceImplement;
import fa.training.services.implement.HelicopterServiceImplement;

public class AirplaneManagement {
	public static void main(String[] args) {
		System.out.println("Choose role user or admin");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		if (choice.equals("user")) {
			ImplementUserFunction();
		} else {
			ImplementAdminFunction();
		}

	}

	private static void ImplementAdminFunction() {

		Scanner sc2 = new Scanner(System.in);
		AirportService airportService = new AirportServiceImplement();
		FixedWingService fixedWingService = new FixedWingServiceImplement();
		HelicopterService helicopterService = new HelicopterServiceImplement();
		while (true) {
			displayAI();
			int chosenFunction = Integer.parseInt(sc2.nextLine());
			switch (chosenFunction) {
			case 1:

				try {
					System.out.println("Enter airport name");
					String airportName = sc2.nextLine();
					System.out.println("Enter airport runway size");
					float runwaySize = Float.parseFloat(sc2.nextLine());
					System.out.println("Enter number of fixed wing slot ");
					int fixedWingSlot = Integer.parseInt(sc2.nextLine());
					System.out.println("Enter number of Helicopter slot ");
					int helicopterSlot = Integer.parseInt(sc2.nextLine());
					Airport airport = new Airport(airportName, runwaySize, fixedWingSlot, helicopterSlot);
					airportService.create(airport);
				} catch (Exception e) {
					System.out.println(e.getMessage());

				}
				break;
			//
			case 2:
				//Create new fixed wing
				Scanner sc = new Scanner(System.in);
				System.out.println("Insert fixed wing model : ");
				String fixedWingModel = sc.nextLine();
				System.out.println("Insert fixed wing cruise speed : ");
				float fixedWingCruiseSpeed = Float.parseFloat(sc.nextLine());
				System.out.println("Insert fixed wing empty weight");
				float fixedWingEmptyWeight = Float.parseFloat(sc.nextLine());
				System.out.println("Insert max takeoff weight");
				float fixedWingMaxTakeoffWeight = Float.parseFloat(sc.nextLine());
				System.out.println("Insert plane type ");
				String fixedWingPlaneType = sc.nextLine();
				System.out.println("Insert min runway size ");
				float minRunwaySize = Float.parseFloat(sc.nextLine());
				FixedWing newFixedWing = new FixedWing();
				newFixedWing.setModel(fixedWingModel);
				newFixedWing.setCruiseSpeed(fixedWingCruiseSpeed);
				newFixedWing.setEmptyWeight(fixedWingEmptyWeight);
				try {
					newFixedWing.setMaxTakeoffWeight(fixedWingMaxTakeoffWeight);
					newFixedWing.setPlaneType(fixedWingPlaneType);
					newFixedWing.setMinNeededRunwaySize(minRunwaySize);
					fixedWingService.create(newFixedWing);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					System.out.println(e2.getMessage());
				}

				break;
			case 3:
				//create new helicopter
				Scanner scanner = new Scanner(System.in);
				System.out.println("Insert helicopter model : ");
				String helicopterModel = scanner.nextLine();
				System.out.println("Insert fixed helicopter speed : ");
				float helicopterCruiseSpeed = Float.parseFloat(scanner.nextLine());
				System.out.println("Insert helicopter empty weight");
				float helicopterEmptyWeight = Float.parseFloat(scanner.nextLine());
				System.out.println("Insert max takeoff weight");
				float helicopterMaxTakeoffWeight = Float.parseFloat(scanner.nextLine());
				System.out.println("Insert range");
				float helicopterRange = Float.parseFloat(scanner.nextLine());
				Helicopter helicopter = new Helicopter();
				helicopter.setModel(helicopterModel);
				helicopter.setCruiseSpeed(helicopterCruiseSpeed);
				helicopter.setEmptyWeight(helicopterEmptyWeight);
				try {
					helicopter.setMaxTakeoffWeight(helicopterMaxTakeoffWeight);
					helicopter.setRange(helicopterRange);
					helicopterService.create(helicopter);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					System.out.println(e2.getMessage());
				}
				break;
			case 4:
				List<FixedWing> allFixedWingCase4 = fixedWingService.getAll();
				List<Airport> allAirportCase4 = airportService.getAll();

				System.out.println("Choose fixed wing by ID");
				for (FixedWing fx : allFixedWingCase4) {
					System.out.println(fx + "is participated : " + fx.isParticipated());
				}
				String addFixedWingId = sc2.nextLine();

				System.out.println("Choose airport by ID");
				for (Airport airport : allAirportCase4) {
					System.out.println("Airport ID : " + airport.getID());
				}
				String addFixedWingAirportID = sc2.nextLine();

				FixedWing addFixedWing = null;
				for (FixedWing fx : allFixedWingCase4) {
					if (fx.getID().equals(addFixedWingId)) {
						addFixedWing = fx;
						break;
					}
				}

				Airport addFixedWingAirport = null;
				for (Airport airport : allAirportCase4) {
					if (airport.getID().equals(addFixedWingAirportID)) {
						addFixedWingAirport = airport;
						break;
					}
				}

				if (addFixedWing == null || addFixedWingAirport == null) {
					System.out.println("Input incorrect values");
					return;
				}
				try {
					airportService.addFixedWing(addFixedWing, addFixedWingAirport);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}

				break;
			// 5. Add Fixed wings to an airport
			case 5:
				List<FixedWing> allFixedWingCase5 = fixedWingService.getAll();
				List<Airport> allAirportCase5 = airportService.getAll();
				System.out.println("Choose fixed wings by Ids , seperate by \",\" ");
				List<FixedWing> addfixedWings = new ArrayList<FixedWing>();
				for (FixedWing fx : allFixedWingCase5) {
					System.out.println(fx + " is participated : " + fx.isParticipated());
				}
				String[] addFixedWingIDs = sc2.nextLine().split(",");
				for (FixedWing fx : allFixedWingCase5) {
					for (int i = 0; i < addFixedWingIDs.length; i++) {
						String id = addFixedWingIDs[i];
						if (id.equals(fx.getID())) {
							addfixedWings.add(fx);
							break;
						}

					}
				}
				System.out.println("Choose airport by Id");
				allAirportCase5.forEach(e -> {
					System.out.println("Airport ID :" + e.getID());

				});
				String addFixedWingsAirportID = sc2.nextLine();
				Airport addFixedWingsAirport = null;
				for (Airport airport : allAirportCase5) {
					if (airport.getID().equals(addFixedWingsAirportID)) {
						addFixedWingsAirport = airport;
						break;
					}

				}
				try {
					airportService.addFixedWing(addfixedWings, addFixedWingsAirport);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}

				break;
			case 6:
				//Remove a fixed wing from an airport
				List<FixedWing> allFixedWingCase6 = fixedWingService.getAll();
				List<Airport> allAirportCase6 = airportService.getAll();
				System.out.println("Choose Airport by Id");
				allAirportCase6.forEach(e -> System.out.println(e.getID()));
				String removeFixedWingAirportID = sc2.nextLine();

				Airport removeFixedWingAirport = null;
				for (Airport a : allAirportCase6) {
					if (a.getID().equals(removeFixedWingAirportID)) {
						removeFixedWingAirport = a;
						break;
					}

				}

				System.out.println("Choose fixed wing by Id");
				removeFixedWingAirport.getFixedWingIDs().forEach(e -> System.out.println(e));

				String removeFixedWingID = sc2.nextLine();
				FixedWing removeFixedWing = null;
				for (FixedWing fx : allFixedWingCase6) {
					if (fx.getID().equals(removeFixedWingID)) {
						removeFixedWing = fx;
						break;
					}
				}

				airportService.removeFixedWing(removeFixedWing, removeFixedWingAirport);

				break;
			case 7:
				// Remove fixed wings from an airport
				List<FixedWing> allFixedWingCase7 = fixedWingService.getAll();
				List<Airport> allAirportCase7 = airportService.getAll();
				System.out.println("Choose Airport by Id");
				allAirportCase7.forEach(e -> System.out.println(e.getID()));
				String removeFixedWingsAirportId = sc2.nextLine();

				Airport removeFixedWingsAirport = null;
				for (Airport a : allAirportCase7) {
					if (a.getID().equals(removeFixedWingsAirportId)) {
						removeFixedWingsAirport = a;
						break;
					}

				}
				List<FixedWing> removeFixedWings = new ArrayList<FixedWing>();

				System.out.println("Choose fixed wings by Id seperated by \",\"");
				removeFixedWingsAirport.getFixedWingIDs().forEach(e -> System.out.println(e));
				String[] removeFixedWingIDs = sc2.nextLine().split(",");

				for (FixedWing fx : allFixedWingCase7) {

					for (int i = 0; i < removeFixedWingIDs.length; i++) {
						if (fx.getID().equals(removeFixedWingIDs[i])) {
							removeFixedWings.add(fx);
							break;

						}
					}

				}

				airportService.removeFixedWing(removeFixedWings, removeFixedWingsAirport);

				break;

			case 8:
				//Add a helicopter to an airport
				List<Helicopter> allHelicopterCase8 = helicopterService.getAll();
				List<Airport> allAirportCase8 = airportService.getAll();
				System.out.println("Choose helicopter by Id");
				allHelicopterCase8
						.forEach(e -> System.out.println(e.getID() + " is participated : " + e.isParticipated()));
				String addHelicopterId = sc2.nextLine();

				Helicopter addHelicopter = null;
				for (Helicopter h : allHelicopterCase8) {
					if (h.getID().equals(addHelicopterId)) {
						addHelicopter = h;
						break;

					}

				}
				System.out.println("Choose airport by Id");
				allAirportCase8.forEach(e -> System.out.println(e.getID()));
				String addHelicopterAirportId = sc2.nextLine();
				Airport addHelicopterAirport = null;
				for (Airport a : allAirportCase8) {
					if (a.getID().equals(addHelicopterAirportId)) {
						addHelicopterAirport = a;
						break;

					}

				}
				try {
					airportService.addHelicopter(addHelicopter, addHelicopterAirport);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 9:
				//Add helicopters to an airport
				List<Helicopter> allHelicopterCase9 = helicopterService.getAll();
				List<Airport> allAirportCase9 = airportService.getAll();
				System.out.println("Choose helicopters by Id ,seperated by \",\"");
				allHelicopterCase9
						.forEach(e -> System.out.println(e.getID() + " is participated : " + e.isParticipated()));
				String[] addHelicopterIds = sc2.nextLine().split(",");
				List<Helicopter> addHelicopters = new ArrayList<Helicopter>();
				for (Helicopter h : allHelicopterCase9) {
					for (int i = 0; i < addHelicopterIds.length; i++) {
						if (h.getID().equals(addHelicopterIds[i])) {
							addHelicopters.add(h);
							break;
						}

					}
				}

				System.out.println("Choose airport by Id");
				allAirportCase9.forEach(e -> System.out.println(e.getID()));
				String addHelicoptersAirportId = sc2.nextLine();

				Airport addHelicoptersAirport = null;
				for (Airport a : allAirportCase9) {
					if (a.getID().equals(addHelicoptersAirportId)) {
						addHelicoptersAirport = a;
						break;
					}

				}
				try {
					airportService.addHelicopter(addHelicopters, addHelicoptersAirport);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case 10:
				//10. Remove a helicopter form an airport
				List<Helicopter> allHelicopterCase10 = helicopterService.getAll();
				List<Airport> allAirportCase10 = airportService.getAll();
				System.out.println("choose airport by Id");
				allAirportCase10.forEach(e -> System.out.println(e.getID()));
				String removeHeilcopterAirportId = sc2.nextLine();
				Airport removeHeilcopterAirport = null;
				for (Airport a : allAirportCase10) {
					if (a.getID().equals(removeHeilcopterAirportId)) {
						removeHeilcopterAirport = a;
						break;
					}

				}
				System.out.println("Choose helicopter by Id");
				removeHeilcopterAirport.getHelicopterIDs().forEach(e -> System.out.println(e));
				String removeHelicopterId = sc2.nextLine();
				Helicopter removeHelicopter = null;
				for (Helicopter h : allHelicopterCase10) {
					if (h.getID().equals(removeHelicopterId)) {
						removeHelicopter = h;
						break;
					}

				}

				airportService.removeHelicopter(removeHelicopter, removeHeilcopterAirport);
				break;
			case 11:
				//Remove helicopters from an airport
				List<Helicopter> allHelicopterCase11 = helicopterService.getAll();
				List<Airport> allAirportCase11 = airportService.getAll();
				System.out.println("Choose airport by Id");
				allAirportCase11.forEach(e -> System.out.println(e.getID()));
				String removeHelicoptersAirportId = sc2.nextLine();
				Airport removeHelicoptersAirport = null;
				for (Airport a : allAirportCase11) {
					if (a.getID().equals(removeHelicoptersAirportId)) {
						removeHelicoptersAirport = a;
						break;
					}

				}
				System.out.println("Choose helicopters by Id , seperated by \",\" ");
				removeHelicoptersAirport.getHelicopterIDs().forEach(e -> System.out.println(e));
				String[] removeHelicopterIds = sc2.nextLine().split(",");
				List<Helicopter> removeHelicopters = new ArrayList<Helicopter>();
				for (Helicopter h : allHelicopterCase11) {
					for (int i = 0; i < removeHelicopterIds.length; i++) {
						if (h.getID().equals(removeHelicopterIds[i])) {
							removeHelicopters.add(h);
							break;
						}
					}

				}
				airportService.removeHelicopter(removeHelicopters, removeHelicoptersAirport);
				break;
			case 12:
				//Change type and min runway size of an fixed wing airplane
				List<FixedWing> allFixedWingCase12 = fixedWingService.getAll();
				//List<Airport> allAirportCase12 = airportService.getAll();
				System.out.println("Choose a fixed wing airplane by id :");
				allFixedWingCase12.forEach(e -> System.out.println(
						e.getID() + " type : " + e.getPlaneType() + " runway size : " + e.getMinNeededRunwaySize()));
				String editFixedWingId = sc2.nextLine();
				FixedWing editFixedWing = null;
				for (FixedWing fx : allFixedWingCase12) {
					if (fx.getID().equals(editFixedWingId)) {
						editFixedWing = fx;
						break;
					}
				}
				System.out.println("Insert fixed wing type of set [CAG,LGR,PRV] : ");
				String updateType = sc2.nextLine();
				System.out.println("Insert new runway size : ");
				float updateRunwaySize = Float.parseFloat(sc2.nextLine());
				// scanner.nextLine();
				try {
					fixedWingService.changeTypeAndMinRunwaySize(editFixedWing, updateType, updateRunwaySize);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage());
				}
				break;
			case 0:
				System.exit(0);
			}
		}

	}

	private static void ImplementUserFunction() {
		// TODO Auto-generated method stub
		AirportService airportService = new AirportServiceImplement();
		FixedWingService fixedWingService = new FixedWingServiceImplement();
		HelicopterService helicopterService = new HelicopterServiceImplement();
		List<Airport> allAirports = airportService.getAll();
		List<FixedWing> allFixedWings = fixedWingService.getAll();
		List<Helicopter> allHelicopters = helicopterService.getAll();
		Scanner sc1 = new Scanner(System.in);
		while (true) {
			displayUI();
			int chosenFunction = Integer.parseInt(sc1.nextLine());
			switch (chosenFunction) {
			case 1:
				Collections.sort(allAirports, new Comparator<Airport>() {

					@Override
					public int compare(Airport o1, Airport o2) {
						// TODO Auto-generated method stub
						return o1.getID().compareTo(o2.getID());
					}
				});
				for (Airport a : allAirports) {
					System.out.println(a);
				}

				break;
			case 2:

				System.out.println("choose an airport by Id");
				// List<Airport> airports = airportService.getAll();
				for (Airport airport : allAirports) {
					System.out.println(airport.getID());
				}
				String chosenID = sc1.nextLine();
				for (Airport airport : allAirports) {
					if (airport.getID().equals(chosenID)) {
						System.out.println(airport);
						break;
					}

				}

				break;
			case 3:

				for (FixedWing fx : allFixedWings) {

					Airport correctAirport = null;
					for (Airport airport : allAirports) {
						boolean existAirport = false;
						for (String id : airport.getFixedWingIDs()) {
							if (id.equals(fx.getID())) {
								correctAirport = airport;
								existAirport = true;
								break;
							}

						}
						if (existAirport == true) {
							break;
						}
					}
					if (correctAirport != null) {
						System.out.println(fx + "airport ID : " + correctAirport.getID() + " airport name : "
								+ correctAirport.getName());
					} else {
						System.out.println(fx + "airport ID : " + null + " airport name : " + null);

					}

				}
				break;
			case 4:
				for (Helicopter helicopter : allHelicopters) {

					Airport correctAirport = null;
					for (Airport airport : allAirports) {
						boolean existAirport = false;
						for (String id : airport.getHelicopterIDs()) {
							if (id.equals(helicopter.getID())) {
								correctAirport = airport;
								existAirport = true;
								break;
							}

						}
						if (existAirport == true) {
							break;
						}
					}
					if (correctAirport != null) {
						System.out.println(helicopter + "airport ID : " + correctAirport.getID() + " airport name : "
								+ correctAirport.getName());
					} else {
						System.out.println(helicopter + "airport ID : " + null + " airport name : " + null);

					}

				}
				break;
			case 0:
				System.exit(0);

			}

		}
	}

	public static void displayUI() {
		System.out.println("Choose one of the following functions : ");
		System.out.println("AIRPORT MANAGEMENT");
		System.out.println("1. Display all airport information ,sorted by ID ");
		System.out.println("2 Display the status of one airport by airport ID");
		System.out.println("FIXED WING AIRPLANE MANAGEMENT");
		System.out.println("3. Display all fixed wing airplane with parking airport and name");
		System.out.println("HELICOPTER MANAGEMENT GROUP");
		System.out.println("4. Display all helicopters with parking airport ID and name");
		System.out.println("0. CLOSE PROGRAM");

	}

	public static void displayAI() {
		System.out.println("Choose one of the following functions : ");
		System.out.println("1. Create new airport");
		System.out.println("2. Create new fixed wing");
		System.out.println("3. create new helicopter");
		System.out.println("4. Add a fixed wing to an airport");
		System.out.println("5. Add Fixed wings to an airport");
		System.out.println("6. Remove a fixed wing from an airport");
		System.out.println("7. Remove fixed wings from an airport");
		System.out.println("8. Add a helicopter to an airport");
		System.out.println("9. Add helicopters to an airport");
		System.out.println("10. Remove a helicopter form an airport");
		System.out.println("11. Remove helicopters from an airport");
		System.out.println("12. Change type and min runway size of an fixed wing airplane");
		System.out.println("0. CLOSE PROGRAM");

	}

}
