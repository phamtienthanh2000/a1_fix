package fa.training.services;

import java.util.List;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.entities.Helicopter;

public interface AirportService extends Service<Airport> {
	public static final String FILE_NAME = "Airport.txt";

	void addFixedWing(FixedWing fixedWing, Airport airport) throws Exception;

	void addFixedWing(List<FixedWing> fixedWings, Airport airport) throws Exception;

	void removeFixedWing(FixedWing fixedWing, Airport airport);

	void removeFixedWing(List<FixedWing> fixedWing, Airport airport);

	void addHelicopter(Helicopter helicopter, Airport airport) throws Exception;

	void addHelicopter(List<Helicopter> helicopters, Airport airport) throws Exception;

	void removeHelicopter(Helicopter helicopter, Airport airport);

	void removeHelicopter(List<Helicopter> helicopters, Airport airport);

}
