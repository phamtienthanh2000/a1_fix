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

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public float getMinNeededRunwaySize() {
		return minNeededRunwaySize;
	}

	public void setMinNeededRunwaySize(float minNeededRunwaySize) {
		this.minNeededRunwaySize = minNeededRunwaySize;
	}

	@Override
	void fly() {
		System.out.println("Fixed Wing");

	}

}
