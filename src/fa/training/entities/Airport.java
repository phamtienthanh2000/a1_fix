package fa.training.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2442391834003227037L;
	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private String ID;
	private String name;
	private float runwaySize;
	private int maxFixedWingParkingPlace;
	private List<String> fixedWingIDs;
	private int maxRotatedWingParkingPlace;
	private List<String> helicopterIDs;

	public Airport(String name, float runwaySize, int maxFixedWingParkingPlace, int maxRotatedWingParkingPlace) {
		super();
		this.name = name;
		this.runwaySize = runwaySize;
		this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
		this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
		fixedWingIDs = new ArrayList<String>();
		helicopterIDs = new ArrayList<String>();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRunwaySize() {
		return runwaySize;
	}

	public void setRunwaySize(float runwaySize) {
		this.runwaySize = runwaySize;
	}

	public int getMaxFixedWingParkingPlace() {
		return maxFixedWingParkingPlace;
	}

	public void setMaxFixedWingParkingPlace(int maxFixedWingParkingPlace) {
		this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
	}

	public List<String> getFixedWingIDs() {
		return fixedWingIDs;
	}

	public void setFixedWingIDs(List<String> fixedWingIDs) {
		this.fixedWingIDs = fixedWingIDs;
	}

	public int getMaxRotatedWingParkingPlace() {
		return maxRotatedWingParkingPlace;
	}

	public void setMaxRotatedWingParkingPlace(int maxRotatedWingParkingPlace) {
		this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
	}

	public List<String> getHelicopterIDs() {
		return helicopterIDs;
	}

	public void setHelicopterIDs(List<String> helicopterIDs) {
		this.helicopterIDs = helicopterIDs;
	}

	@Override
	public String toString() {
		return "Airport [ID=" + ID + ", name=" + name + ", runwaySize=" + runwaySize + ", maxFixedWingParkingPlace="
				+ maxFixedWingParkingPlace + ", fixedWingIDs=" + fixedWingIDs + ", maxRotatedWingParkingPlace="
				+ maxRotatedWingParkingPlace + ", helicopterIDs=" + helicopterIDs + "]";
	}

}
