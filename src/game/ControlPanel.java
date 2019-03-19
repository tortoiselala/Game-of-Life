package game;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7869229682601225660L;
	private double screenWidth;
	private double screenHeight;
	private GridBagLayout layout;
	final static int minbuttonWidth = 100;
	final static int minbuttonHeight = 100;

	public ControlPanel() {

		setScreenHeight();
		setScreenWidth();

		setBackground(Color.RED);

		setOpaque(true);

		setBounds((int) (getScreenWidth() - getScreenWidth() / 4), 0, (int) (getScreenWidth() / 4),
				(int) (getScreenHeight() / 2));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addYAxisBox();

		setVisible(true);

	}

	public void addYAxisBox() {
		layout = new GridBagLayout();
		setLayout(layout);

		JLabel initText = new JLabel("预置:");
		initText.setFont(new Font("微软雅黑", Font.BOLD, 20));

		JComboBox<String> initCheckBox = new JComboBox<String>();
		initCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		initCheckBox.addItem("Pulsar");
		initCheckBox.addItem("Glider");
		initCheckBox.addItem("Gospel Gliding Gun");

		addComponent(0, 0, 2, 1, 0, 0, initText, GridBagConstraints.NONE, GridBagConstraints.EAST);
		addComponent(2, 0, 6, 1, 0, 0, initCheckBox, GridBagConstraints.NONE, GridBagConstraints.CENTER);
		addComponent(0, 1, 2, 1, 0, 0, createButton("start/pause"), GridBagConstraints.NONE,
				GridBagConstraints.CENTER);
		addComponent(2, 1, 2, 1, 0, 0, createButton("  1次"), GridBagConstraints.NONE, GridBagConstraints.CENTER);
		addComponent(4, 1, 2, 1, 0, 0, createButton(" 10次"), GridBagConstraints.NONE, GridBagConstraints.CENTER);
		addComponent(6, 1, 2, 1, 0, 0, createButton("100次"), GridBagConstraints.NONE, GridBagConstraints.CENTER);

	}

	private void addComponent(int gridx, int gridy, int gridwidth, int gridheight, int weightx, int weighty,
			Component comp, int fill, int anchor) {
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.gridx = gridx;
		constraint.gridy = gridy;
		constraint.gridwidth = gridwidth;
		constraint.gridheight = gridheight;
		constraint.weightx = weightx;
		constraint.weighty = weighty;
		constraint.fill = fill;
		constraint.anchor = anchor;
		add(comp, constraint);

	}

	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("微软雅黑", Font.BOLD, 15));
		return button;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth() {
		Toolkit kiToolkit = Toolkit.getDefaultToolkit();
		this.screenWidth = kiToolkit.getScreenSize().getWidth();
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight() {
		Toolkit kiToolkit = Toolkit.getDefaultToolkit();
		this.screenHeight = kiToolkit.getScreenSize().getHeight();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (getScreenWidth() / 4), (int) (getScreenHeight() / 2));
	}
}
