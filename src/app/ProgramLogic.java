package app;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import model.DenseMatrix;
import model.HashPairManagement;
import model.MatrixPairManagement;
import model.ObjectCircle;
import model.ObjectRectangle;
import model.ObjectSquare;
import model.PairManagerable;
import model.Settings;
import model.UGObject;
import controller.AppController;

public class ProgramLogic {
	public static final double ITERATION_STEP = 0.2;

	private AppController appController;

	private Settings settings;
	private Dimension gridPanelSize;

	private PairManagerable pairManagement;
	private DenseMatrix denseMatrix;

	private List<UGObject> objects;
	private Random random = new Random();
	
	private Timer timer;
	private ProgramLogicTimerTask timerTask;
	
	private long timeTaken = 0;
	private long stepsTaken = 0;

	public ProgramLogic(AppController appController, Settings settings,
			Dimension gridPanelSize) {
		this.appController = appController;
		this.settings = settings;
		this.gridPanelSize = gridPanelSize;
		this.init();
		long averageTimeTaken = (stepsTaken == 0) ? 0 : this.timeTaken / this.stepsTaken;
		this.appController.step(this.denseMatrix, this.objects, this.pairManagement, averageTimeTaken);
	}

	private void init() {
		this.createObjects();
		this.setPairManagement();
		this.createDenseMatrix();

	}

	private void createObjects() {
		this.objects = new ArrayList<UGObject>();
		int numOfObjects = this.settings.getObjectNumber();
		int objectsVelocity = this.settings.getObjectVelocity();
		for (int i = 0; i < numOfObjects; i++) {
			UGObject obj = this.createObject();
			obj.setRandomGravityCentre(this.gridPanelSize);
			
			int deltaX = this.random.nextInt(objectsVelocity-5)+5;
			if (this.random.nextInt(2) == 0) deltaX *= -1;
			int deltaY = this.random.nextInt(objectsVelocity-5)+5;
			if (this.random.nextInt(2) == 0) deltaY *= -1;
			obj.setVelocityX(deltaX);
			obj.setVelocityY(deltaY);
			
			obj.setSize(30);
			obj.setId(i);
			
			if (obj instanceof ObjectRectangle) {
				ObjectRectangle rect = (ObjectRectangle) obj;
				rect.setLocation(this.random.nextInt(2));
				rect.setAABB();
				this.objects.add(rect);
				continue;
			}
			obj.setAABB();
			this.objects.add(obj);
		}
	}

	private void setPairManagement() {
		int pairManagement = this.settings.getPairManagement();
		if (pairManagement == Settings.PAIR_MANAGEMENT_MATRIX) {
			this.pairManagement = new MatrixPairManagement();
		} else if (pairManagement == Settings.PAIR_MANAGEMENT_HASH) {
			this.pairManagement = new HashPairManagement();
		} else {
			this.pairManagement = null;
		}
	}

	private void createDenseMatrix() {
		this.denseMatrix = new DenseMatrix(this.gridPanelSize,
				this.settings.getGridSize());
	}

	private UGObject createObject() {
		UGObject result;
		switch (this.random.nextInt(3)) {
			case UGObject.OBJECT_SQUARE: {
				result = new ObjectSquare();
				break;
			}
			case UGObject.OBJECT_RECTANGLE: {
				result = new ObjectRectangle();
				break;
			}
			case UGObject.OBJECT_CIRCLE: {
				result = new ObjectCircle();
				break;
			}
			default: {
				result = null;
				break;
			}
		}
		return result;
	}

	public void start() {
		this.timer = new Timer();
		this.timerTask = new ProgramLogicTimerTask(
				this,
				this.pairManagement,
				this.denseMatrix,
				this.gridPanelSize,
				this.settings.getGridSize(),
				this.objects, 
				this.timeTaken,
				this.stepsTaken);
		this.timer.schedule(this.timerTask, 0, 20);
	}

	public void unpause() {
		this.start();
	}

	public void pause() {
		this.updateVars();
		this.timer.cancel();
	}

	public void step() {
		this.updateVars();
		long averageTimeTaken = (stepsTaken == 0) ? 0 : this.timeTaken / this.stepsTaken;
		this.appController.step(this.denseMatrix, this.objects, this.pairManagement, averageTimeTaken);
	}
	
	public void stop() {
		if (this.timer != null) {
			this.timer.cancel();
			this.timer.purge();
		}
	}

	private void updateVars() {
		this.pairManagement = this.timerTask.getPairManagement();
		this.denseMatrix = this.timerTask.getDenseMatrix();
		this.objects = this.timerTask.getObjects();
		this.timeTaken = this.timerTask.getTimeTaken();
		this.stepsTaken = this.timerTask.getStepsTaken();
	}
	
	

}
