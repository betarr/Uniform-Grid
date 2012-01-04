package controller;

import model.PairManagerable;
import view.InformationPanel;

public class InformationPanelController {

	private InformationPanel informationPanel;

	public InformationPanelController() {
	}

	public void setPanel(InformationPanel informationPanel) {
		this.informationPanel = informationPanel;
	}

	public void repaint(PairManagerable pairManagement, long averageTimeTaken) {
		int numberOfCollisions = pairManagement.getPairs().size(); 
		this.informationPanel.getCollisionNumber().setText(String.valueOf(numberOfCollisions));
		this.informationPanel.getAverageStepTime().setText(String.valueOf(averageTimeTaken) + " ms");
	}
	
	
}
