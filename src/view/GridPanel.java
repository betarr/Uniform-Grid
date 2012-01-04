package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import model.AABB;
import model.DenseMatrix;
import model.ObjectCircle;
import model.ObjectRectangle;
import model.ObjectSquare;
import model.UGObject;

public class GridPanel extends JPanel {
	
	private static final Color COLOR_GRID = Color.GRAY;
	private static final Color COLOR_OBJECTS_ID = Color.WHITE;
	private static final Color COLOR_OBJECTS_AABB = Color.WHITE;
	private static final Color COLOR_RECTANGLE = new Color(51, 255, 102);
	private static final Color COLOR_SQUERE = new Color(204, 0, 204);
	private static final Color COLOR_CIRCLE = new Color(51, 102, 255);
	private static final Color FILL_SQUERE_CONFLICTED1 = new Color(192, 192, 192);
	private static final Color FILL_SQUERE_CONFLICTED2 = new Color(168, 168, 168);
	private static final Color FILL_SQUERE_CONFLICTED3 = new Color(72, 72, 72);
	private static final Color FILL_SQUERE_CONFLICTED4 = new Color(0, 0, 0);
	private static final Color FILL_SQUERE_CONFLICTED_MORE = new Color(0, 0, 0);

	private DenseMatrix denseMatrix = null;
	private List<UGObject> objects = null;
	private int gridSize;

	public GridPanel() {
		this.buildGridPanel();
	}

	private void buildGridPanel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.WHITE);
		if (this.denseMatrix != null) {
			this.paintGrid(g);
			this.fillConflictedSqueres(g);
			this.paintObjects(g);
			//this.paintConfictedJunctor(g);
		}
	}

	private void paintGrid(Graphics g) {
		g.setColor(GridPanel.COLOR_GRID);
		int width = this.getWidth();
		int height = this.getHeight();
		for (int i = 0; i < width; i = i + this.gridSize) {
			g.drawLine(i, 0, i, height);
		}
		for (int i = 0; i < height; i = i + this.gridSize) {
			g.drawLine(0, i, width, i);
		}
	}
	
	private void fillConflictedSqueres(Graphics g) {
		for (int row = 0; row < this.denseMatrix.getRowsCount(); row++) {
			for (int column = 0; column < this.denseMatrix.getColumnsCount(row); column++) {
				int objectsOnSquere = this.denseMatrix.getGridSquere(row, column).getNumberOfIds();
				if (objectsOnSquere > 0) {
					Color c = null;
					if (objectsOnSquere == 1) {
						c = GridPanel.FILL_SQUERE_CONFLICTED1;
					} else if (objectsOnSquere == 2) {
						c = GridPanel.FILL_SQUERE_CONFLICTED2;
					} else if (objectsOnSquere == 3) {
						c = GridPanel.FILL_SQUERE_CONFLICTED3;
					} else if (objectsOnSquere == 4) {
						c = GridPanel.FILL_SQUERE_CONFLICTED4;
					} else {
						c = GridPanel.FILL_SQUERE_CONFLICTED_MORE;
					}
					this.fillSquere(g, c, row, column);
				}
			}
		}
	}
	
	private void fillSquere(Graphics g, Color color, int row, int column) {
		int squereX = column * this.gridSize;
		int squereY = row * this.gridSize;
		g.setColor(color);
		g.fillRect(squereX, squereY, this.gridSize, this.gridSize);
	}

	private void paintObjects(Graphics g) {
		if (this.objects != null) {
			for (UGObject object : this.objects) {
				this.paintObject(g, object);
				this.paintAABB(g, object.getAabb());
			}
		}
	}

	private void paintObject(Graphics g, UGObject object) {
		if (object != null) {
			if (object instanceof ObjectSquare) {
				paintSquare(g, (ObjectSquare) object);
			} else if (object instanceof ObjectRectangle) {
				paintRectangle(g, (ObjectRectangle) object);
			} else if (object instanceof ObjectCircle) {
				paintCircle(g, (ObjectCircle) object);
			}
			g.setColor(GridPanel.COLOR_OBJECTS_ID);
			g.drawString(String.valueOf(object.getId()), object.getGravityCentre().x-5, object.getGravityCentre().y+5);
		}
	}

	private void paintSquare(Graphics g, ObjectSquare object) {
		g.setColor(GridPanel.COLOR_SQUERE);
		int x = object.getGravityCentre().x - (object.getSize() / 2);
		int y = object.getGravityCentre().y - (object.getSize() / 2);
		g.fillRect(x, y, object.getSize(), object.getSize());
	}

	private void paintRectangle(Graphics g, ObjectRectangle object) {
		g.setColor(GridPanel.COLOR_RECTANGLE);
		int x = object.getAabb().getxMin();
		int y = object.getAabb().getyMin();
		int width = object.getAabb().getxMax() - x;
		int height = object.getAabb().getyMax() - y;
		g.fillRect(x, y, width, height);
	}

	private void paintCircle(Graphics g, ObjectCircle object) {
		g.setColor(GridPanel.COLOR_CIRCLE);
		int x = object.getGravityCentre().x - (object.getSize() / 2);
		int y = object.getGravityCentre().y - (object.getSize() / 2);
		g.fillOval(x, y, object.getSize(), object.getSize());
	}
	
	private void paintAABB(Graphics g, AABB aabb) {
		g.setColor(GridPanel.COLOR_OBJECTS_AABB);
		int x = aabb.getxMin();
		int y = aabb.getyMin();
		int width = aabb.getxMax() - aabb.getxMin();
		int height = aabb.getyMax() - aabb.getyMin();
		g.drawRect(x, y, width, height);
	}
	
//	private void paintConfictedJunctor(Graphics g) {
//		for (int row = 0; row < this.denseMatrix.getRowsCount(); row++) {
//			for (int column = 0; column < this.denseMatrix.getColumnsCount(row); column++) {
//				GridSquere gridSquere = this.denseMatrix.getGridSquere(row, column);
//				if (gridSquere.getNumberOfIds() >= 2) {
//					this.paintJunctor(g, gridSquere);
//				}
//			}
//		}
//	}
//	
//	private void paintJunctor(Graphics g, GridSquere gridSquere) {
//		g.setColor(Color.BLACK);
//		List<Integer> ids = gridSquere.getIds();
//		for (int actualId : ids) {
//			UGObject actualObject = this.getObjectWithId(actualId);
//			for (int otherId : ids) {
//				if (actualId != otherId) {
//					UGObject otherObject = this.getObjectWithId(otherId);
//					Point p1 = actualObject.getGravityCentre();
//					Point p2 = otherObject.getGravityCentre();
//					g.drawLine(p1.x, p1.y, p2.x, p2.y);
//				}
//			}
//		}
//	}
//	
//	private UGObject getObjectWithId(int id) {
//		for (UGObject object : this.objects) {
//			if (object.getId() == id) {
//				return object;
//			}
//		}
//		return null;
//	}

	public void setDenseMatrix(DenseMatrix denseMatrix) {
		this.denseMatrix = denseMatrix;
	}

	public void setObjects(List<UGObject> objects) {
		this.objects = objects;
	}

	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
}
