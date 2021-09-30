package fa.training.services.implement;

import java.util.ArrayList;
import java.util.List;

import fa.training.entities.FixedWing;
import fa.training.services.FixedWingService;
import fa.training.utils.BusinessRule;

public class FixedWingServiceImplement implements FixedWingService {

	private ArrayList<FixedWing> fixedWings;

	public FixedWingServiceImplement() {
		fixedWings = (ArrayList<FixedWing>) this.getAll();
	}

	@Override
	public void create(FixedWing fixedWing) throws Exception {

		String lastID = null;
		if (this.fixedWings.size() != 0) {
			lastID = fixedWings.get(fixedWings.size() - 1).getID();
			// AP12345
			int number = Integer.parseInt(lastID.substring(2, 7));
			lastID = lastID.substring(0, 2) + (number + 1);

		} else {
			lastID = "FW10000";
		}

		if (!BusinessRule.validateModelSize(fixedWing.getModel())) {
			throw new Exception("model size > 40 charracters");
		}
		fixedWing.setID(lastID);
		fixedWings.add(fixedWing);
		FileIO.writeToFile(fixedWings, this.FILE_NAME);
		System.out.println("Create fixed wing finished");

	}

	@Override
	public List<FixedWing> getAll() {
		ArrayList<FixedWing> fixedWings = new ArrayList<FixedWing>();
		FileIO.readToArray(fixedWings, FILE_NAME);
		return fixedWings;

	}

	@Override
	public void changeTypeAndMinRunwaySize(FixedWing fixedWing, String type, float runwaySize) throws Exception {
		if (!BusinessRule.validateUpdateTypeAndRunwaySize(fixedWing, type, runwaySize)) {
			return;
		}

		FixedWing chosenFixedWing = null;
		for (int i = 0; i < this.fixedWings.size(); i++) {
			if (this.fixedWings.get(i).getID().equals(fixedWing.getID())) {
				this.fixedWings.set(i, fixedWing);

				chosenFixedWing = this.fixedWings.get(i);
				break;
			}

		}
		chosenFixedWing.setPlaneType(type);
		chosenFixedWing.setMinNeededRunwaySize(runwaySize);

		FileIO.writeToFile(this.fixedWings, FILE_NAME);
		System.out.println("Change fixed wing type and min runway size finished");

	}

}
