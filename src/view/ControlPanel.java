package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
	
	private JButton startButton = new JButton("Start");
	private JButton restartButton = new JButton("Restart");
	private JButton pauseButton = new JButton("Pause");

	public ControlPanel() {
		this.buildControlPanel();
	}

	private void buildControlPanel() {
		this.setLayout(new FlowLayout());
		this.startButton.setPreferredSize(new Dimension(100, 50));
		this.add(this.startButton);
		this.restartButton.setPreferredSize(new Dimension(100, 50));
		this.add(this.restartButton);
		this.restartButton.setEnabled(false);
		this.pauseButton.setPreferredSize(new Dimension(100, 50));
		this.pauseButton.setEnabled(false);
		this.add(this.pauseButton);
	}

	public JButton getStartButton() {
		return this.startButton;
	}
	
	public JButton getRestartButton() {
		return restartButton;
	}

	public JButton getPauseButton() {
		return this.pauseButton;
	}
}
