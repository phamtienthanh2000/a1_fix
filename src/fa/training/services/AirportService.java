package fa.training.services;

import java.util.List;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;

/**
 * This class delcares functions implements functional requirements related to
 * airport
 * 
 * @author Admin
 *
 */
public interface AirportService extends Service<Airport> {
	public static final String FILE_NAME = "Airport.txt";

	/**
	 * add a fixed wing to an airport 
	 * if the parameters passed violate the business
	 * rule throw Exception
	 * 
	 * @param fixedWing the fixed wing airplane to add
	 * @param airport the airport to add fixed wing airplane
	 * @throws Exception
	 */
	void addFixedWing(FixedWing fixedWing, Airport airport) throws Exception;

	/**
	 * 
	 * add many fixed wing type airplane to an airport
	 *  if the business rules are violated
	 *  throw exception
	 * 
	 * @param fixedWings the list of fixed wing type airplane
	 * @param airport the airport to add many fixed wing airplanes
	 * @throws Exception
	 */

	void addFixedWing(List<FixedWing> fixedWings, Airport airport) throws Exception;

	/**
	 * remove a fixed wing airplane from an airport
	 * if parameters violate the business rules
	 * throw exception  
	 * 
	 * @param fixedWing the fixed wing airplane to remove from the airport
	 * @param airport the airport to remove the fixed wing airplane
	 */
	void removeFixedWing(FixedWing fixedWing, Airport airport);

	/**
	 * remove many fixed wing airplanes from an airport
	 *	if parameters violate the business rules
	 * 		throw exception
	 * 
	 * 
	 * @param fixedWings the list of fixed wing type airplane be removed from the airport
	 * @param airport : the airport to remove the fixed wing airplane
	 */
	void removeFixedWing(List<FixedWing> fixedWings, Airport airport);

	/**
	 * add a helicopter to an airport
	 * if parameters violate the business rules
	 * throw exception
	 * 
	 * @param helicopter : the helicopter to add to the airport
	 * @param airport : the airport to add the helicopter
	 * @throws Exception
	 */
	void addHelicopter(Helicopter helicopter, Airport airport) throws Exception;

	/**
	 * add many helicopters to an airport 
	 * if parameters violate the business rules
	 * throw exception
	 * 
	 * @param helicopters : the list of many helicopters be added to the airport
	 * @param airport  : the airport to add many helicopters
	 * @throws Exception
	 */

	void addHelicopter(List<Helicopter> helicopters, Airport airport) throws Exception;

	/**
	 * remove a helicopter from an airport
	 * if parameters violate the business rules
	 * throw exception
	 * @param helicopter : the helicopter to remove from the airport
	 * @param airport : the airport to remove the helicopter
	 */
	void removeHelicopter(Helicopter helicopter, Airport airport);

	/**
	 * remove many helicopter from an airport
	 * if parameters violate the business rules
	 * throw exception
	 * @param helicopters : list of helicopters be removed from the airport
	 * @param airport : the airport to remove many helicopters
	 */
	void removeHelicopter(List<Helicopter> helicopters, Airport airport);

}
