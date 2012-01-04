package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ControlPanel;

public class ControlPanelController {

	private AppController appController;
	private ControlPanel controlPanel;

	public ControlPanelController(AppController appController) {
		this.appController = appController;
	}

	public void setPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
	
	public void addListeners() {
		this.controlPanel.getStartButton().addActionListener(new StartListener(this));
		this.controlPanel.getRestartButton().addActionListener(new RestartListener(this));
		this.controlPanel.getPauseButton().addActionListener(new PauseListener(this));
	}
	
	public void setEnabledPauseButton(boolean enabled) {
		this.controlPanel.getPauseButton().setEnabled(enabled);
	}
	
	public void setEnabledRestartButton(boolean enabled) {
		this.controlPanel.getRestartButton().setEnabled(enabled);
	}
	
	public void onStartPressed() {
		this.appController.onStartButtonPressed();
	}
	
	public void onRestartPressed() {
		this.appController.onRestartButtonPressed();
	}
	
	public void onPausePressed() {
		this.appController.onPauseButtonPressed();
	}
	
	public class StartListener implements ActionListener {
		
		ControlPanelController controlPanelController;

		public StartListener(ControlPanelController controlPanelController) {
			this.controlPanelController = controlPanelController;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			this.controlPanelController.onStartPressed();
		}
	}
	
	public class RestartListener implements ActionListener {
		
		ControlPanelController controlPanelController;

		public RestartListener(ControlPanelController controlPanelController) {
			this.controlPanelController = controlPanelController;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.controlPanelController.onRestartPressed();
		}
		
		
	}
	
	public class PauseListener implements ActionListener {
		
		ControlPanelController controlPanelController;

		public PauseListener(ControlPanelController controlPanelController) {
			this.controlPanelController = controlPanelController;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.controlPanelController.onPausePressed();
		}
	}

	public void unpause() {
		this.controlPanel.getPauseButton().setText("Pause");
	}

	public void pause() {
		this.controlPanel.getPauseButton().setText("Unpause");
	}
}
