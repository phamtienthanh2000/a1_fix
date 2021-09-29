package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import fa.training.entities.Helicopter;
import fa.training.services.HelicopterService;
import fa.training.utils.BusinessRule;

public class HelicopterServiceImplement implements HelicopterService {
	private ArrayList<Helicopter> helicopters;

	public HelicopterServiceImplement() {

		this.helicopters = (ArrayList<Helicopter>) this.getAll();

	}

	@Override
	public void create(Helicopter helicopter) throws Exception {
		String lastID = null;
		if (this.helicopters.size() != 0) {
			lastID = helicopters.get(helicopters.size() - 1).getID();
			// RW12345
			int number = Integer.parseInt(lastID.substring(2, 7));
			lastID = lastID.substring(0, 2) + (number + 1);

		} else {
			lastID = "RW10000";
		}

		if (!BusinessRule.validateModelSize(helicopter.getModel())) {
			throw new Exception("Model size length > 40 characters");
		}

		if (!BusinessRule.validateHelicopterTakeoffWeight(helicopter)) {
			throw new Exception("Helicopter max takeoff weight exceeds 1.5 times empty weight");

		}

		helicopter.setID(lastID);
		helicopters.add(helicopter);
		FileIO.writeToFile(helicopters, FILE_NAME);
		System.out.println("Create helicopter finish");
	}

	@Override
	public List<Helicopter> getAll() {
		ArrayList<Helicopter> helicopters = new ArrayList<Helicopter>();
		FileIO.readToArray(helicopters, FILE_NAME);
		return helicopters;

	}

}
