package fa.training.utils;

import java.util.List;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;
import fa.training.services.AirportService;
import fa.training.services.implement.AirportServiceImplement;

public class BusinessRule<E> {
	// FixedWingService fixedWingService;

	public static boolean validateAddFixedWingToAirport(FixedWing fixedWing, Airport airport) throws Exception {
		boolean result = false;
		if (airport.getFixedWingIDs().size() >= airport.getMaxFixedWingParkingPlace()) {
			throw new Exception("Not enough place for fixed wing");
		}
		if (airport.getRunwaySize() < fixedWing.getMinNeededRunwaySize()) {
			throw new Exception("airport runway size is smaller than fixed wing min runway size");
		}
		if (fixedWing.isParticipated()) {
			throw new Exception("fixed wing is already parked");
		}

		result = true;

		return result;

	}

	public static boolean validateAddFixedWingToAirport(List<FixedWing> fixedWings, Airport airport) throws Exception {
		boolean result = false;
		int availableSlot = airport.getMaxFixedWingParkingPlace() - airport.getFixedWingIDs().size();
		if (availableSlot < airport.getMaxFixedWingParkingPlace()) {
			throw new Exception("Not enough place for fixed wings ");
		}
		for (FixedWing fx : fixedWings) {
			validateAddFixedWingToAirport(fx, airport);
		}
		result = true;
		return result;

	}

	public static boolean validateAddHelicopterToAirport(Helicopter helicopter, Airport airport) throws Exception {
		boolean result = false;
		if (airport.getHelicopterIDs().size() >= airport.getMaxFixedWingParkingPlace()) {
			throw new Exception("Not enough place for helicopter");
			// return false;
		}
		if (helicopter.isParticipated()) {
			throw new Exception("helicopter is already parked");
		}
		result = true;
		return result;
	}

	public static boolean validateAddHelicopterToAirport(List<Helicopter> helicopters, Airport airport)
			throws Exception {
		boolean result = false;

		int availableSlot = airport.getMaxRotatedWingParkingPlace() - airport.getHelicopterIDs().size();
		if (availableSlot < helicopters.size()) {
			throw new Exception("Not enough place for helicopters");
		}
		for (Helicopter helicopter : helicopters) {
			if (helicopter.isParticipated()) {
				throw new Exception("Helicopter : " + helicopter.getID() + " is already parked");
			}

		}

		result = true;
		return result;

	}

	public static boolean validateUpdateTypeAndRunwaySize(FixedWing fixedWing, String type, float runwaySize)
			throws Exception {
		boolean result = false;
		// validate type
		if ((type.equals("CAG")) || (type.equals("LGR")) || (type.equals("PRV"))) {
			result = true;
		} else {
			throw new Exception("Type is not valid : " + type);
		}

		if (fixedWing.isParticipated()) {
			AirportService airportService = new AirportServiceImplement();
			List<Airport> airports = airportService.getAll();

			for (Airport airport : airports) {
				for (String fxID : airport.getFixedWingIDs()) {
					if (fxID.equals(fixedWing.getID())) {
						if (airport.getRunwaySize() < runwaySize) {
							throw new Exception("update fixed wing runway size exceed airport runway size ");
						}
					}
				}

			}

		}

		return result;

	}

//	public static String generateID(E entity) {
//		String lastID = null;
//		if (entity instanceof FixedWing) {
//
//		}
//
//		return "";
//
//	}

	public static boolean validateModelSize(String modelSize) {
		return false;
	}

	public static boolean validateFixedWingType(String type) {
		return false;

	}

	public static boolean validateFixedRunwaySize(float runwaySize, float fixedWingRunwaySize) {

		return false;
	}

	public boolean validateTakeoffWeight(float takeoffWeight, float emptyWeight) {

		return false;
	}
}
