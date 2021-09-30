package fa.training.entities;

public class FixedWing extends Airplane {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5812853912912885255L;
	/**
	 * 
	 */
	//	private static final long serialVersionUID = 1L;
	private String planeType;
	private float minNeededRunwaySize;

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) throws Exception {
		if (!validatePlaneType(planeType)) {
			throw new Exception("In valid plane type");
		}
		this.planeType = planeType;
	}

	private boolean validatePlaneType(String planeType2) {
		boolean result = false;
		if (planeType2.equals("CAG") || planeType2.equals("LGR") || planeType2.equals("PRV")) {
			result = true;
		}
		return result;
	}

	public float getMinNeededRunwaySize() {
		return minNeededRunwaySize;
	}

	public void setMinNeededRunwaySize(float minNeededRunwaySize) {
		this.minNeededRunwaySize = minNeededRunwaySize;
	}

	@Override
	public void fly() {
		System.out.println("Fixed Wing");

	}

	@Override
	public String toString() {
		return "FixedWing [planeType=" + planeType + ", minNeededRunwaySize=" + minNeededRunwaySize + ", PlaneType()="
				+ getPlaneType() + ", MinNeededRunwaySize()=" + getMinNeededRunwaySize() + ", ID()=" + getID()
				+ ", Model()=" + getModel() + ", CruiseSpeed()=" + getCruiseSpeed() + ", EmptyWeight()="
				+ getEmptyWeight() + ", MaxTakeoffWeight()=" + getMaxTakeoffWeight() + ", isParticipated()="
				+ isParticipated() + "]";
	}

}
