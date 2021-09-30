package fa.training.entities;

public class Helicopter extends Airplane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4285455114889168833L;
	/**
	 * 
	 */
	//	private static final long serialVersionUID = 1L;

	private float range;

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	@Override
	public void fly() {

		System.out.println("Rotated Wing");
	}

	@Override
	public void setMaxTakeoffWeight(float maxTakeoffWeight) throws Exception {
		if (!(maxTakeoffWeight <= (1.5) * this.getEmptyWeight())) {
			throw new Exception("Max take off weight exceeds 1.5 times empty weight");
		}
		super.setMaxTakeoffWeight(maxTakeoffWeight);

	}

	@Override
	public String toString() {
		return "Helicopter [range=" + range + ", getID()=" + getID() + ", getModel()=" + getModel()
				+ ", getCruiseSpeed()=" + getCruiseSpeed() + ", getEmptyWeight()=" + getEmptyWeight()
				+ ", getMaxTakeoffWeight()=" + getMaxTakeoffWeight() + ", isParticipated()=" + isParticipated() + "]";
	}

}
