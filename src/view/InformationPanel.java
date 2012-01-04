package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationPanel extends JPanel {

	private JLabel collisionNumberLabel = new JLabel("Collisions Number: ");
	private JLabel collisionNumber = new JLabel("0");
	
	private JLabel averageStepTimeLabel = new JLabel("Average Step Time: ");
	private JLabel averageStepTime = new JLabel("0 ms");

	public InformationPanel() {
		this.buildInformationPanel();
	}

	private void buildInformationPanel() {
		this.setLayout(new GridLayout(2, 2));
		
		this.add(this.collisionNumberLabel);
		this.add(this.collisionNumber);
		
		this.add(this.averageStepTimeLabel);
		this.add(this.averageStepTime);
	}

	public JLabel getCollisionNumber() {
		return collisionNumber;
	}

	public JLabel getAverageStepTime() {
		return averageStepTime;
	}
	
	
	
	
}
