package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SettingsDialog extends JDialog {

	public static final Color INPUT_GOOD_BG_COLOR = Color.WHITE;
	public static final Color INPUT_WRONG_BG_COLOR = new Color(255, 160, 122);

	private static final String DIALOG_NAME = "Settings";
	private static final Dimension DIALOG_SIZE = new Dimension(300, 200);
	
	private JLabel gridSizeLabel = new JLabel(" Grid Size");
	private JLabel objectNumberLabel = new JLabel(" Objects Number");
	private JLabel objectVelocityLabel = new JLabel(" Objects Velocity");
	private JLabel dataStructureLabel = new JLabel(" Pair Management");
	
	private JTextField gridSizeTextField = new JTextField("10");
	private JTextField objectNuberTextField = new JTextField("10");
	private JTextField objectVelocityTextField = new JTextField("15");
	
	private ButtonGroup dataStructureButtonGroup = new ButtonGroup();
	private JRadioButton matrixStructureRadioButton = new JRadioButton("Matrix");
	private JRadioButton hashStructureRadioButton = new JRadioButton("Hash Map");
	
	private JButton confirmButton = new JButton("Confirm");
	private JButton cancelButton = new JButton("Cancel");

	public SettingsDialog() {
		this.buildDialog();
	}

	private void buildDialog() {
		this.setTitle(SettingsDialog.DIALOG_NAME);
		this.setSize(SettingsDialog.DIALOG_SIZE);
		
		this.setLayout(new BorderLayout());
		
		this.add(this.buildContentPanel(), BorderLayout.CENTER);
		this.add(this.buildConfirmPanel(), BorderLayout.SOUTH);
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);
	}

	private JPanel buildContentPanel() {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(4, 2));
		
		contentPanel.add(this.gridSizeLabel);
		contentPanel.add(this.gridSizeTextField);
		
		contentPanel.add(this.objectNumberLabel);
		contentPanel.add(this.objectNuberTextField);
		
		contentPanel.add(this.objectVelocityLabel);
		contentPanel.add(this.objectVelocityTextField);
		
		this.dataStructureButtonGroup.add(this.matrixStructureRadioButton);
		this.dataStructureButtonGroup.add(this.hashStructureRadioButton);
		contentPanel.add(this.dataStructureLabel);
		JPanel dataStructurePanel = new JPanel();
		dataStructurePanel.setLayout(new GridLayout(2, 1));
		this.matrixStructureRadioButton.setSelected(true);
		dataStructurePanel.add(this.matrixStructureRadioButton);
		dataStructurePanel.add(this.hashStructureRadioButton);
		contentPanel.add(dataStructurePanel);
		return contentPanel;
	}

	private JPanel buildConfirmPanel() {
		JPanel confirmPanel = new JPanel();
		confirmPanel.add(this.confirmButton);
		confirmPanel.add(this.cancelButton);
		return confirmPanel;
	}
	
	public JButton getConfirmButton() {
		return confirmButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JTextField getGridSizeTextField() {
		return gridSizeTextField;
	}

	public JTextField getObjectNuberTextField() {
		return objectNuberTextField;
	}
	
	public JTextField getObjectVelocityTextField() {
		return objectVelocityTextField;
	}

	public JRadioButton getMatrixStructureRadioButton() {
		return matrixStructureRadioButton;
	}

	public JRadioButton getHashStructureRadioButton() {
		return hashStructureRadioButton;
	}
	
	
}
