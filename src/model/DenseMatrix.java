package model;

import java.awt.Dimension;

public class DenseMatrix {

	private Dimension gridPanelSize;
	private int gridSize;

	private GridSquere[][] gridSquereMatrix;
	
	public DenseMatrix(Dimension gridPanelSize, int gridSize) {
		this.gridPanelSize = gridPanelSize;
		this.gridSize = gridSize;
		this.initMatrix();
	}

	private void initMatrix() {
		int columns = (int) this.gridPanelSize.getWidth() / this.gridSize;
		int rows = (int) this.gridPanelSize.getHeight() / this.gridSize;

		this.gridSquereMatrix = new GridSquere[rows+1][columns+1];
		for (int row = 0; row <= rows; row++) {
			for (int column = 0; column <= columns; column++) {
				this.gridSquereMatrix[row][column] = new GridSquere();
			}
		}
	}
	
	private void addCollision(int row, int column, int id) {
		if (row < this.gridSquereMatrix.length) {
			if (column < this.gridSquereMatrix[row].length) {
				this.gridSquereMatrix[row][column].addId(id);
			}
		}
	}

	public void addBox(UGObject object) {
		int minColumnIndex = object.getAabb().getxMin() / this.gridSize;
		int maxColumnIndex = object.getAabb().getxMax() / this.gridSize;
		int minRowIndex = object.getAabb().getyMin() / this.gridSize;
		int maxRowIndex = object.getAabb().getyMax() / this.gridSize;
		
		for (int row = minRowIndex; row <= maxRowIndex; row++) {
			for (int column = minColumnIndex; column <= maxColumnIndex; column++) {
				this.addCollision(Math.abs(row), Math.abs(column), object.getId());
			}
		}
	}

	private void removeCollision(int row, int column, int id) {
		if (row < this.gridSquereMatrix.length) {
			if (column < this.gridSquereMatrix[row].length) {
				if (this.gridSquereMatrix[row][column].containsId(id)) {
					this.gridSquereMatrix[row][column].removeId(id);
				}
			}
		}
	}

	public void removeBox(UGObject object) {
		int minColumnIndex = object.getAabb().getxMin() / this.gridSize;
		int maxColumnIndex = object.getAabb().getxMax() / this.gridSize;
		int minRowIndex = object.getAabb().getyMin() / this.gridSize;
		int maxRowIndex = object.getAabb().getyMax() / this.gridSize;

		for (int row = minRowIndex; row <= maxRowIndex; row++) {
			for (int column = minColumnIndex; column <= maxColumnIndex; column++) {
				this.removeCollision(row, column, object.getId());
			}
		}		
	}
	
	public int getRowsCount() {
		return this.gridSquereMatrix.length;
	}
	
	public int getColumnsCount(int row) {
		return this.gridSquereMatrix[row].length;
	}
	
	public GridSquere getGridSquere(int row, int column) {
		return this.gridSquereMatrix[row][column];
	}

	@Override
	public String toString() {
		String result = "";
		for (GridSquere[] row : this.gridSquereMatrix) {
			for (GridSquere gridSquere : row) {
				result += gridSquere + " ";
			}
			result += "\n";
		}
		return result;
	}

	public GridSquere[][] getGridSquereMatrix() {
		return gridSquereMatrix;
	}
	
	
}
