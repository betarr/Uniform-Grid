package model;

public class Settings {

	public static final int PAIR_MANAGEMENT_MATRIX = 0;
	public static final int PAIR_MANAGEMENT_HASH = 1;
	
	private int gridSize = 10;
	private int objectNumber = 2;
	private int objectVelocity = 5;
	private int pairManagement = Settings.PAIR_MANAGEMENT_MATRIX;
	
	public int getGridSize() {
		return gridSize;
	}
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	public int getObjectNumber() {
		return objectNumber;
	}
	public void setObjectNumber(int objectNumber) {
		this.objectNumber = objectNumber;
	}
	public int getObjectVelocity() {
		return objectVelocity;
	}
	public void setObjectVelocity(int objectVelocity) {
		this.objectVelocity = objectVelocity;
	}
	public int getPairManagement() {
		return pairManagement;
	}
	public void setPairManagement(int pairManagement) {
		this.pairManagement = pairManagement;
	}
}
