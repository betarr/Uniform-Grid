package app;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import model.DenseMatrix;
import model.Pair;
import model.PairManagerable;
import model.UGObject;

public class ProgramLogicTimerTask extends TimerTask {

	private ProgramLogic programLogic;

	private PairManagerable pairManagement;
	private DenseMatrix denseMatrix;
	private Dimension gridPanelSize;
	private int gridSize;
	
	private long timeTaken;
	private long stepsTaken;

	private List<UGObject> objects;

	private double iterationsCounter = 0.0;

	public ProgramLogicTimerTask(ProgramLogic programLogic,
			PairManagerable pairManagement, DenseMatrix denseMatrix,
			Dimension gridPanelSize, int gridSize, List<UGObject> objects,
			long timeTaken, long stepsTaken) {
		this.programLogic = programLogic;
		this.pairManagement = pairManagement;
		this.denseMatrix = denseMatrix;
		this.gridPanelSize = gridPanelSize;
		this.gridSize = gridSize;
		this.objects = objects;
		this.timeTaken = timeTaken;
		this.stepsTaken = stepsTaken;
	}

	@Override
	public void run() {
		long startTime = Calendar.getInstance().getTimeInMillis();
		this.removeObjectsFromDenseMatrix();
		this.removeObjectsFromPairManagement();
		this.moveObjects(this.gridPanelSize);
		this.addObjectsToPairManagement();
		this.addObjectsToDenseMatrix();
		this.iterationsCounter += ProgramLogic.ITERATION_STEP;
		if (this.iterationsCounter >= 1) {
			this.iterationsCounter = 0.0;
			this.programLogic.step();
		}
		long endTime = Calendar.getInstance().getTimeInMillis();
		this.timeTaken += (endTime - startTime);
		this.stepsTaken++;
	}

	private void moveObjects(Dimension gridPanelSize) {
		for (UGObject object : this.objects) {
			object.makeStep(gridPanelSize);
			object.setAABB();
		}
	}

	private void removeObjectsFromDenseMatrix() {
		for (UGObject object : this.objects) {
			this.denseMatrix.removeBox(object);
		}
	}

	private void addObjectsToDenseMatrix() {
		for (UGObject object : this.objects) {
			this.denseMatrix.addBox(object);
		}
	}

	private void addObjectsToPairManagement() {
		for (UGObject actualObject : this.objects) {
			for (UGObject otherObject : this.objects) {
				if (actualObject.getId() != otherObject.getId()) {
					if (this.areInConfict(actualObject, otherObject)) {
						Pair pair = new Pair(actualObject.getId(), otherObject.getId());
						this.pairManagement.addPair(pair);
					}
				}
			}
		}
	}
	
	private void removeObjectsFromPairManagement() {
		for (UGObject actualObject : this.objects) {
			for (UGObject otherObject : this.objects) {
				if (actualObject.getId() != otherObject.getId()) {
					if (this.areInConfict(actualObject, otherObject)) {
						Pair pair = new Pair(actualObject.getId(), otherObject.getId());
						this.pairManagement.removePair(pair);
					}
				}
			}
		}
	}

	private boolean areInConfict(UGObject actualObject, UGObject otherObject) {
		return this.areInConflictX(actualObject, otherObject)
				&& this.areInConflictY(actualObject, otherObject);
	}

	private boolean areInConflictX(UGObject actualObject, UGObject otherObject) {
		int minXIndex1 = actualObject.getAabb().getxMin() / this.gridSize;
		int maxXIndex1 = actualObject.getAabb().getxMax() / this.gridSize;
		int minXIndex2 = otherObject.getAabb().getxMin() / this.gridSize;
		int maxXIndex2 = otherObject.getAabb().getxMax() / this.gridSize;
		
		return (minXIndex2 >= minXIndex1 && minXIndex2 <= maxXIndex1) || (maxXIndex2 >= minXIndex1 && maxXIndex2 <= maxXIndex1);
	}

	private boolean areInConflictY(UGObject actualObject, UGObject otherObject) {
		int minYIndex1 = actualObject.getAabb().getyMin() / this.gridSize;
		int maxYIndex1 = actualObject.getAabb().getyMax() / this.gridSize;
		int minYIndex2 = otherObject.getAabb().getyMin() / this.gridSize;
		int maxYIndex2 = otherObject.getAabb().getyMax() / this.gridSize;
		
		return (minYIndex2 >= minYIndex1 && minYIndex2 <= maxYIndex1) || (maxYIndex2 >= minYIndex1 && maxYIndex2 <= maxYIndex1);
	}

	public PairManagerable getPairManagement() {
		return pairManagement;
	}

	public DenseMatrix getDenseMatrix() {
		return denseMatrix;
	}

	public List<UGObject> getObjects() {
		return objects;
	}

	public long getTimeTaken() {
		return timeTaken;
	}

	public long getStepsTaken() {
		return stepsTaken;
	}

}
