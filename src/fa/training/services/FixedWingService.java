package fa.training.services;

import fa.training.entities.FixedWing;

public interface FixedWingService extends Service<FixedWing> {
	public static final String FILE_NAME = "FixedWing.txt";

	void changeTypeAndMinRunwaySize(FixedWing fixedWing, String type, float runwaySize) throws Exception;

}
