package controller;

import java.awt.Dimension;
import java.util.List;

import model.DenseMatrix;
import model.PairManagerable;
import model.Settings;
import model.UGObject;
import view.App;
import app.ProgramLogic;

public class AppController {

	private SettingsDialogController settingsController;
	private ControlPanelController controlPanelController;
	private InformationPanelController informationPanelController;
	private GridPanelController gridPanelController;

	private App app;

	private ProgramLogic programLogic;

	private boolean paused = false;

	public AppController() {
		this.settingsController = new SettingsDialogController(this);
		this.controlPanelController = new ControlPanelController(this);
		this.informationPanelController = new InformationPanelController();
		this.gridPanelController = new GridPanelController();
	}

	public void buildApplication() {
		this.app = new App();
		this.app.buildGUI();

		this.controlPanelController.setPanel(this.app.getControlPanel());
		this.controlPanelController.addListeners();
		this.informationPanelController
				.setPanel(this.app.getInformationPanel());
		this.gridPanelController.setPanel(this.app.getGridPanel());
	}

	public void start(Settings settings) {
		this.controlPanelController.setEnabledPauseButton(true);
		this.controlPanelController.setEnabledRestartButton(true);
		this.gridPanelController.setSettings(settings);
		this.paused = false;
		this.controlPanelController.unpause();
		Dimension gridPanelSize = this.app.getGridPanel().getSize();
		if (this.programLogic != null)
			this.programLogic.stop();
		this.programLogic = new ProgramLogic(this, settings, gridPanelSize);
		this.programLogic.start();
	}

	public void step(DenseMatrix denseMatrix, List<UGObject> objects,
			PairManagerable pairManagement, long averageTimeStep) {
		this.gridPanelController.repaint(denseMatrix, objects);
		this.informationPanelController
				.repaint(pairManagement, averageTimeStep);
	}

	public SettingsDialogController getSettingsController() {
		return settingsController;
	}

	public ControlPanelController getControlPanelController() {
		return controlPanelController;
	}

	public InformationPanelController getInformationPanelController() {
		return informationPanelController;
	}

	public GridPanelController getGridPanelController() {
		return gridPanelController;
	}

	public App getApp() {
		return app;
	}

	public void onStartButtonPressed() {
		this.settingsController.showSettingsDialog();
	}

	public void onRestartButtonPressed() {
		this.start(this.settingsController.getLastSettings());
	}

	public void onPauseButtonPressed() {
		if (this.paused) {
			this.paused = false;
			this.controlPanelController.unpause();
			this.programLogic.unpause();
		} else {
			this.paused = true;
			this.controlPanelController.pause();
			this.programLogic.pause();
		}
	}

}
