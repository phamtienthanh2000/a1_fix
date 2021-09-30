package fa.training.services;

import fa.training.entities.FixedWing;

public interface FixedWingService extends Service<FixedWing> {
	public static final String FILE_NAME = "FixedWing.txt";

	/**
	 * change the type and min needed runway size of a fixed wing airplane type
	 * if the parameters violate the business rules
	 * throw exception
	 * @param fixedWing : the fixed wing airplane to change attribute values
	 * @param type : the update type value
	 * @param runwaySize : the update runway size value
	 * @throws Exception 
	 */
	void changeTypeAndMinRunwaySize(FixedWing fixedWing, String type, float runwaySize) throws Exception;

}
