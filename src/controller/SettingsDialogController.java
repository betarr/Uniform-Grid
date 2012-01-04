package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Settings;
import view.SettingsDialog;

public class SettingsDialogController {
	
	private AppController appController;
	private SettingsDialog settingsDialog;
	private Settings settings;

	public SettingsDialogController(AppController appController) {
		this.appController = appController;
	}

	public void showSettingsDialog() {
		this.settingsDialog = new SettingsDialog();
		this.addListeners();
		this.settingsDialog.setLocationRelativeTo(this.appController.getApp().getAppFrame());
		this.settingsDialog.setVisible(true);
	}

	public AppController getAppController() {
		return appController;
	}
	
	public void onCancelButtonPressed() {
		this.settingsDialog.dispose();
	}
	
	public void onConfirmButtonPressed() {
		Settings settings = this.getSettingsFromDialog();
		if ( settings != null ) {
			this.settings = settings;
			this.settingsDialog.dispose();
			this.appController.start(settings);
		}
	}
	
	public Settings getSettingsFromDialog() {
		Settings settings = new Settings();
		String gridSizeString = this.settingsDialog.getGridSizeTextField().getText();
		String objectsNumberString = this.settingsDialog.getObjectNuberTextField().getText();
		String objectVelocityString = this.settingsDialog.getObjectVelocityTextField().getText();
		int pairManagement = this.settingsDialog.getMatrixStructureRadioButton().isSelected() ? Settings.PAIR_MANAGEMENT_MATRIX : Settings.PAIR_MANAGEMENT_HASH;
		
		boolean areDataOk = true;
		try {
			settings.setGridSize(Integer.parseInt(gridSizeString));
			this.settingsDialog.getGridSizeTextField().setBackground(SettingsDialog.INPUT_GOOD_BG_COLOR);
		} catch (NumberFormatException nfe) {
			areDataOk = false;
			this.settingsDialog.getGridSizeTextField().setBackground(SettingsDialog.INPUT_WRONG_BG_COLOR);
		}
		
		try {
			settings.setObjectNumber(Integer.parseInt(objectsNumberString));
			this.settingsDialog.getObjectNuberTextField().setBackground(SettingsDialog.INPUT_GOOD_BG_COLOR);
		} catch (NumberFormatException nfe) {
			areDataOk = false;
			this.settingsDialog.getObjectNuberTextField().setBackground(SettingsDialog.INPUT_WRONG_BG_COLOR);
		}
		
		try {
			settings.setObjectVelocity(Integer.parseInt(objectVelocityString));
			this.settingsDialog.getObjectVelocityTextField().setBackground(SettingsDialog.INPUT_GOOD_BG_COLOR);
		} catch (NumberFormatException nfe) {
			areDataOk = false;
			this.settingsDialog.getObjectVelocityTextField().setBackground(SettingsDialog.INPUT_WRONG_BG_COLOR);
		}
		
		settings.setPairManagement(pairManagement);
		
		return areDataOk ? settings : null;
	}
	
	private void addListeners() {
		this.settingsDialog.getConfirmButton().addActionListener(new ConfirmSettingsDialogListener(this));
		this.settingsDialog.getCancelButton().addActionListener(new CloseSettingsDialogListener(this));
	}
	
	public Settings getLastSettings() {
		return this.settings;
	}
	
	public class CloseSettingsDialogListener implements ActionListener {
		
		private SettingsDialogController settingsDialogController;

		public CloseSettingsDialogListener(SettingsDialogController settingsDialogController) {
			this.settingsDialogController = settingsDialogController;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.settingsDialogController.onCancelButtonPressed();
		}
	}
	
	public class ConfirmSettingsDialogListener implements ActionListener {
		
		private SettingsDialogController settingsDialogController;

		public ConfirmSettingsDialogListener(SettingsDialogController settingsDialogController) {
			this.settingsDialogController = settingsDialogController;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.settingsDialogController.onConfirmButtonPressed();
		}
		
		
	}

	

}
