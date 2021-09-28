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
	void fly() {

		System.out.println("Rotated Wing");
	}

}
