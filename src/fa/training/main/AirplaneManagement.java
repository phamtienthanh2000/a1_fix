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
		AirportService airportService = new AirportServiceImplement();
		FixedWingService fixedWingService = new FixedWingServiceImplement();
		HelicopterService helicopterService = new HelicopterServiceImplement();
		List<Airport> allAirports = airportService.getAll();
		Collections.sort(allAirports, new Comparator<Airport>() {

			@Override
			public int compare(Airport o1, Airport o2) {
				// TODO Auto-generated method stub
				return o1.getID().compareTo(o2.getID());
			}

		});
		List<FixedWing> allFixedWings = fixedWingService.getAll();
		List<Helicopter> allHelicopters = helicopterService.getAll();
		System.out.println("Choose role user or admin");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();

		switch (choice) {

		case "user":
			Scanner sc1 = new Scanner(System.in);

			while (true) {
				displayUI();
				int chosenFunction = Integer.parseInt(scanner.nextLine());
				switch (chosenFunction) {
				case 1:

					for (Airport airport : allAirports) {
						System.out.println(airport);

					}
					break;
				case 2:
//					Scanner scanner = new Scanner(System.in);
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
					// System.out.println(allFixedWings.size());
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
							System.out.println(helicopter + "airport ID : " + correctAirport.getID()
									+ " airport name : " + correctAirport.getName());
						} else {
							System.out.println(helicopter + "airport ID : " + null + " airport name : " + null);

						}

					}
					break;
				case 0:
					System.exit(0);

				}
			}

		case "admin":
			Scanner sc2 = new Scanner(System.in);

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
					System.out.println("Choose fixed wing by ID");
					for (FixedWing fx : allFixedWings) {
						System.out.println(fx);
					}
					String chosenFixedWingID = sc2.nextLine();
					System.out.println("Choose airport by ID");
					for (Airport airport : allAirports) {
						System.out.println(airport);
					}
					String chosenAirportID = sc2.nextLine();

					FixedWing chosenFx = null;
					for (FixedWing fx : allFixedWings) {
						if (fx.getID().equals(chosenFixedWingID)) {
							chosenFx = fx;
							break;
						}
					}
					Airport chosenAirport = null;
					for (Airport airport : allAirports) {
						if (airport.getID().equals(chosenAirportID)) {
							chosenAirport = airport;
							break;
						}
					}

					if (chosenAirport == null || chosenFx == null) {
						System.out.println("Input incorrect values");
						return;
					}
					try {
						airportService.addFixedWing(chosenFx, chosenAirport);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}

					break;
				// fixing
				case 3:
					System.out.println("Choose fixed wings by Ids , seperate by \",\" ");
					List<FixedWing> fixedWings = new ArrayList<FixedWing>();
					for (FixedWing fx : allFixedWings) {
						System.out.println(fx);
					}
					String[] fxIDs = scanner.nextLine().split(",");
					for (FixedWing fx : allFixedWings) {
						for (int i = 0; i < fxIDs.length; i++) {
							String id = fxIDs[i];
							if (id.equals(fx.getID())) {
								fixedWings.add(fx);
								break;
							}

						}
					}
					System.out.println("Choose airport by Id");
					allAirports.forEach(e -> {
						System.out.println(e.toString());

					});
					String chosenAirportId = sc2.nextLine();
					Airport chosenAp = null;
					for (Airport airport : allAirports) {
						if (airport.getID().equals(chosenAirportId)) {
							chosenAp = airport;
							break;
						}

					}
					try {
						airportService.addFixedWing(fixedWings, chosenAp);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println(e1.getMessage());
					}

					break;
				case 4:
					System.out.println("Choose Airport by Id");
					allAirports.forEach(e -> System.out.println(e.getID()));
					String apId = sc2.nextLine();

					Airport ap = null;
					for (Airport a : allAirports) {
						if (a.getID().equals(apId)) {
							ap = a;
							break;
						}

					}

					System.out.println("Choose fixed wing by Id");
					ap.getFixedWingIDs().forEach(e -> System.out.println(e));

					String choseFxId = sc2.nextLine();
					FixedWing removeFixedWing = null;
					for (FixedWing fx : allFixedWings) {
						if (fx.getID().equals(choseFxId)) {
							removeFixedWing = fx;
							break;
						}
					}

					airportService.removeFixedWing(removeFixedWing, ap);

					break;
				case 5:
					System.out.println("Choose Airport by Id");
					allAirports.forEach(e -> System.out.println(e.getID()));
					String airportId = sc2.nextLine();

					Airport airport = null;
					for (Airport a : allAirports) {
						if (a.getID().equals(airportId)) {
							airport = a;
							break;
						}

					}
					List<FixedWing> removeFixedWings = new ArrayList<FixedWing>();

					System.out.println("Choose fixed wings by Id seperated by \",\"");
					airport.getFixedWingIDs().forEach(e -> System.out.println(e));
					String[] removeIds = sc2.nextLine().split(",");

					for (FixedWing fx : allFixedWings) {

						for (int i = 0; i < removeIds.length; i++) {
							if (fx.getID().equals(removeIds[i])) {
								removeFixedWings.add(fx);
								break;

							}
						}

					}

					airportService.removeFixedWing(removeFixedWings, airport);

					break;

				case 6:
					System.out.println("Choose helicopter by Id");
					allHelicopters
							.forEach(e -> System.out.println(e.getID() + " is participated : " + e.isParticipated()));
					String addHelicopterId = sc2.nextLine();

					Helicopter addHelicopter = null;
					for (Helicopter h : allHelicopters) {
						if (h.getID().equals(addHelicopterId)) {
							addHelicopter = h;
							break;

						}

					}
					System.out.println("Choose airport by Id");
					allAirports.forEach(e -> System.out.println(e.getID()));
					String addAirportId = sc2.nextLine();
					Airport addHelicopterAirport = null;
					for (Airport a : allAirports) {
						if (a.getID().equals(addAirportId)) {
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
				case 7:
					System.out.println("Choose helicopters by Id ,seperated by \",\"");
					allHelicopters
							.forEach(e -> System.out.println(e.getID() + " is participated : " + e.isParticipated()));
					String[] addHelicopterIds = sc2.nextLine().split(",");
					List<Helicopter> addHelicopters = new ArrayList<Helicopter>();
					for (Helicopter h : allHelicopters) {
						for (int i = 0; i < addHelicopterIds.length; i++) {
							if (h.getID().equals(addHelicopterIds[i])) {
								addHelicopters.add(h);
								break;
							}

						}
					}

					System.out.println("Choose airport by Id");
					allAirports.forEach(e -> System.out.println(e.getID()));
					String addHelicopterAirportId = sc2.nextLine();

					Airport chosenAddHelicopterAirport = null;
					for (Airport a : allAirports) {
						if (a.getID().equals(addHelicopterAirportId)) {
							chosenAddHelicopterAirport = a;
							break;
						}

					}
					try {
						airportService.addHelicopter(addHelicopters, chosenAddHelicopterAirport);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				case 8:
					System.out.println("choose airport by Id");
					allAirports.forEach(e -> System.out.println(e.getID()));
					String removeAirportId = sc2.nextLine();
					Airport removeAirport = null;
					for (Airport a : allAirports) {
						if (a.getID().equals(removeAirportId)) {
							removeAirport = a;
							break;
						}

					}
					System.out.println("Choose helicopter by Id");
					removeAirport.getHelicopterIDs().forEach(e -> System.out.println(e));
					String removeHelicopterId = sc2.nextLine();
					Helicopter removeHelicopter = null;
					for (Helicopter h : allHelicopters) {
						if (h.getID().equals(removeHelicopterId)) {
							removeHelicopter = h;
							break;
						}

					}

					airportService.removeHelicopter(removeHelicopter, removeAirport);
					break;
				case 9:
					System.out.println("Choose airport by Id");
					allAirports.forEach(e -> System.out.println(e.getID()));
					String removeHelicoptersAirportId = sc2.nextLine();
					Airport removeHelicoptersAirport = null;
					for (Airport a : allAirports) {
						if (a.getID().equals(removeHelicoptersAirportId)) {
							removeHelicoptersAirport = a;
							break;
						}

					}
					System.out.println("Choose helicopters by Id , seperated by \",\" ");
					removeHelicoptersAirport.getHelicopterIDs().forEach(e -> System.out.println(e));
					String[] removeHelicopterIds = sc2.nextLine().split(",");
					List<Helicopter> removeHelicopters = new ArrayList<Helicopter>();
					for (Helicopter h : allHelicopters) {
						for (int i = 0; i < removeHelicopterIds.length; i++) {
							if (h.getID().equals(removeHelicopterIds[i])) {
								removeHelicopters.add(h);
								break;
							}
						}

					}
					airportService.removeHelicopter(removeHelicopters, removeHelicoptersAirport);
					break;
				case 10:
					System.out.println("Choose a fixed wing airplane by id :");
					allFixedWings.forEach(e -> System.out.println(e.getID() + " type : " + e.getPlaneType()
							+ " runway size : " + e.getMinNeededRunwaySize()));
					String editFixedWingId = sc2.nextLine();
					FixedWing editFixedWing = null;
					for (FixedWing fx : allFixedWings) {
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
		System.out.println("2. Add a fixed wing to an airport");
		System.out.println("3. Add Fixed wings to an airport");
		System.out.println("4. Remove a fixed wing from an airport");
		System.out.println("5. Remove fixed wings from an airport");
		System.out.println("6. Add a helicopter to an airport");
		System.out.println("7. Add helicopters to an airport");
		System.out.println("8. Remove a helicopter form an airport");
		System.out.println("9. Remove helicopters from an airport");
		System.out.println("10. Change type and min runway size of an fixed wing airplane");
		System.out.println("0. CLOSE PROGRAM");

	}

}
