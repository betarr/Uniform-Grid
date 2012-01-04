package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {

	private static String APP_NAME = "Uniform Grid and Pair Management";
	private static Dimension APP_SIZE = new Dimension(640, 480);
	
	private JFrame appFrame;
	private ControlPanel controlPanel;
	private InformationPanel informationPanel;
	private GridPanel gridPanel;

	public App() {
	}

	public void buildGUI() {
		this.appFrame = new JFrame(App.APP_NAME);
		this.appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.appFrame.setPreferredSize(App.APP_SIZE);
		
		this.addPanels();
		
		this.appFrame.pack();
		this.appFrame.setLocationRelativeTo(null);
		this.appFrame.setVisible(true);		
	}

	private void addPanels() {
		this.appFrame.setLayout(new BorderLayout());
		
		this.controlPanel = new ControlPanel();
		this.informationPanel = new InformationPanel();
		this.gridPanel = new GridPanel();
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		northPanel.add(this.controlPanel);
		northPanel.add(this.informationPanel);
		this.appFrame.add(northPanel, BorderLayout.NORTH);
		this.appFrame.add(this.gridPanel, BorderLayout.CENTER);
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public InformationPanel getInformationPanel() {
		return informationPanel;
	}

	public GridPanel getGridPanel() {
		return gridPanel;
	}

	public JFrame getAppFrame() {
		return appFrame;
	}
}
