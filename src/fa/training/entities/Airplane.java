package fa.training.entities;

import java.io.Serializable;

public abstract class Airplane implements Serializable {
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private String ID;
	private String model;
	private float cruiseSpeed;
	private float emptyWeight;
	private float maxTakeoffWeight;
	private boolean isParticipated;

	public Airplane() {
		// TODO Auto-generated constructor stub
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getCruiseSpeed() {
		return cruiseSpeed;
	}

	public void setCruiseSpeed(float cruiseSpeed) {
		this.cruiseSpeed = cruiseSpeed;
	}

	public float getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(float emptyWeight) {
		this.emptyWeight = emptyWeight;
	}

	public float getMaxTakeoffWeight() {
		return maxTakeoffWeight;
	}

	public void setMaxTakeoffWeight(float maxTakeoffWeight) throws Exception {
		this.maxTakeoffWeight = maxTakeoffWeight;
	}

	public boolean isParticipated() {
		return isParticipated;
	}

	public void setParticipate(boolean isParticipate) {
		this.isParticipated = isParticipate;
	}

	abstract public void fly();

	@Override
	public String toString() {
		return "Airplane [ID=" + ID + ", model=" + model + ", cruiseSpeed=" + cruiseSpeed + ", emptyWeight="
				+ emptyWeight + ", maxTakeoffWeight=" + maxTakeoffWeight + ", isParticipated=" + isParticipated + "]";
	}

}
