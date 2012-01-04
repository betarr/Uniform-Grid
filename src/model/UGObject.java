package model;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;

import app.ProgramLogic;

public abstract class UGObject {
	public static final int OBJECT_SQUARE = 0;
	public static final int OBJECT_RECTANGLE = 1;
	public static final int OBJECT_CIRCLE = 2;

	private static final int VELOCITY_INDEX_X = 0;
	private static final int VELOCITY_INDEX_Y = 1;

	protected int id;
	protected AABB aabb;
	protected Point gravityCentre;
	protected int[] velocity = new int[2];
	protected int size;

	// protected int velocity;

	public void setRandomGravityCentre(Dimension gridPanelSize) {
		Random r = new Random();
		this.gravityCentre = new Point(
				r.nextInt((int) gridPanelSize.getWidth() - 40) + 40,
				r.nextInt((int) gridPanelSize.getHeight() - 40) + 40);
	}

	public AABB getAabb() {
		return aabb;
	}

	public void setAabb(AABB aabb) {
		this.aabb = aabb;
	}

	public void setAABB() {
	}

	public void makeStep(Dimension gridSize) {
		if (this.isBorderCoolisionX(gridSize)) {
			this.velocity[UGObject.VELOCITY_INDEX_X] *= -1;
		}
		
		if (this.isBorderCollisionY(gridSize)) {
			this.velocity[UGObject.VELOCITY_INDEX_Y] *= -1;
		}

//		this.gravityCentre.x += this.velocity[UGObject.VELOCITY_INDEX_X];
//		this.gravityCentre.y += this.velocity[UGObject.VELOCITY_INDEX_Y];
		
		this.gravityCentre.x += (int)((double)this.velocity[UGObject.VELOCITY_INDEX_X] * ProgramLogic.ITERATION_STEP);
		this.gravityCentre.y += (int)((double)this.velocity[UGObject.VELOCITY_INDEX_Y] * ProgramLogic.ITERATION_STEP);
	}

	private boolean isBorderCollisionY(Dimension gridSize) {
		return this.aabb.getyMin() < 0 || this.aabb.getyMax() > gridSize.getHeight();
	}

	private boolean isBorderCoolisionX(Dimension gridSize) {
		return this.aabb.getxMin() < 0 || this.aabb.getxMax() > gridSize.getWidth();
	}

	public Point getGravityCentre() {
		return gravityCentre;
	}

	public void setGravityCentre(Point gravityCentre) {
		this.gravityCentre = gravityCentre;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setVelocityY(int deltaY) {
		this.velocity[UGObject.VELOCITY_INDEX_Y] = deltaY;
	}

	public void setVelocityX(int deltaX) {
		this.velocity[UGObject.VELOCITY_INDEX_X] = deltaX;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
