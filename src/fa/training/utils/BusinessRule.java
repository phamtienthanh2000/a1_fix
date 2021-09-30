package fa.training.utils;

import java.util.List;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;
import fa.training.services.AirportService;
import fa.training.services.implement.AirportServiceImplement;

public class BusinessRule<E> {
	// FixedWingService fixedWingService;
	/**
	 * validate parameters value when add a fixed wing to an airport
	 * if the airport is full || airport runway size < fixed wing runway size || fixed wing is parked 
	 * throw exceptions
	 * 
	 * 
	 * @param fixedWing : the fixed wing added to the airport
	 * @param airport : the airport to add fixed wing
	 * @return
	 * if the parameter satisfied the business rules
	 * return true
	 * else 
	 * return false
	 * @throws Exception
	 */
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

	/**
	 * validate paramters values when add many fixed wings to an airport 
	 * if not enought slot for fixed wing || fixed wing  is already parked
	 * throw exception
	 * 
	 * @param fixedWings : the fixed wings to add to the airport
	 * @param airport : the airport to add fixed wings
	 * @return 
	 * if the parameter satisfied the business rules
	 * return true
	 * else 
	 * return false
	 * @throws Exception
	 */
	public static boolean validateAddFixedWingToAirport(List<FixedWing> fixedWings, Airport airport) throws Exception {
		boolean result = false;
		int availableSlot = airport.getMaxFixedWingParkingPlace() - airport.getFixedWingIDs().size();
		if (availableSlot < fixedWings.size()) {
			throw new Exception("Not enough place for fixed wings ");
		}
		for (FixedWing fx : fixedWings) {
			validateAddFixedWingToAirport(fx, airport);
		}
		result = true;
		return result;

	}

	/**
	 * validate parameters values when add aa helicopter to the airport
	 * if not enough slot for helicopter  || helicopter is parked
	 * throw exception
	 * @param helicopter
	 * @param airport
	 * @return
	 * if the parameter satisfied the business rules
	 * return true
	 * else 
	 * return false
	 * @throws Exception
	 */
	public static boolean validateAddHelicopterToAirport(Helicopter helicopter, Airport airport) throws Exception {
		boolean result = false;
		if (airport.getHelicopterIDs().size() >= airport.getMaxRotatedWingParkingPlace()) {
			throw new Exception("Not enough place for helicopter");
			// return false;
		}
		if (helicopter.isParticipated()) {
			throw new Exception("helicopter is already parked");
		}
		result = true;
		return result;
	}

	/**
	 * validate paramters values when add helicopters to an airport
	 * if not enough place for the helicopters || one of the helicopters is parked
	 *  throw exception
	 * @param helicopters
	 * @param airport
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * validate the update type and runway size values 
	 * if type is not one of specified types 
	 * throw exception
	 * if fixed wing airplane is already parked /\ update runway size > fixed wing current parked airport runway size
	 * throw exception 
	 * 
	 * @param fixedWing
	 * @param type
	 * @param runwaySize
	 * @return
	 * @throws Exception
	 */
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
						break;
					}
				}

			}

		}
		result = true;

		return result;

	}

	/**
	 * validate the model size
	 * @param modelSize
	 *@return
	 * if model length <= 40 
	 * return true
	 * else
	 * return false
	 */
	public static boolean validateModelSize(String modelSize) {
		return modelSize.length() <= 40;
	}

	/**
	 * validate the helicopter take off weight
	 * @param helicopter
	 * @return
	 * if takeoffWeight <= 1.5 times the helicopter empty weight
	 */
	public static boolean validateHelicopterTakeoffWeight(Helicopter helicopter) {

		return helicopter.getMaxTakeoffWeight() <= (1.5) * helicopter.getEmptyWeight();
	}
}
