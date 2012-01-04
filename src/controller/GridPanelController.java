package controller;

import java.util.List;

import model.DenseMatrix;
import model.Settings;
import model.UGObject;
import view.GridPanel;

public class GridPanelController {

	private GridPanel gridPanel;
	private Settings settings;

	public GridPanelController() {
	}
	
	public void repaint(DenseMatrix denseMatrix, List<UGObject> objects) {
		this.gridPanel.setDenseMatrix(denseMatrix);
		this.gridPanel.setObjects(objects);
		this.gridPanel.setGridSize(this.settings.getGridSize());
		this.gridPanel.repaint();
	}

	public void setPanel(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	
}
