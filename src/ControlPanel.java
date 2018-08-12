package gameOfLife;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.TextArea;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.tools.OptionChecker;

public class ControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7869229682601225660L;
	private double screenWidth;
	private double screenHeight;

	public ControlPanel() {

		setScreenHeight();
		setScreenWidth();

		setBackground(Color.RED);

		setOpaque(false);

		setBounds((int) (getScreenWidth() - getScreenWidth() / 4), 0, (int) (getScreenWidth() / 4),
				(int) (getScreenHeight() / 2));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		addYAxisBox();

		setVisible(true);

	}

	public void addYAxisBox() {
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		GridBagConstraints container = new GridBagConstraints();
		
		 
		
		JPanel initPanel = new JPanel();

		JLabel initText = new JLabel("预置");
		initText.setFont(new Font("宋体", Font.BOLD, 20));

		JComboBox<String> initCheckBox = new JComboBox<String>();

		initCheckBox.addItem("Pulsar");
		initCheckBox.addItem("Glider");
		initCheckBox.addItem("Gospel Gliding Gun");
		initCheckBox.setOpaque(true);

		initPanel.add(initText);
		initPanel.add(initCheckBox);

		initPanel.setOpaque(false);
		initPanel.setVisible(true);

		initPanel.setFont(new Font("宋体", Font.BOLD, 100));
		add(initPanel);

		JPanel timesButtonPanel = new JPanel();

		JButton startAndPauseButton = new JButton("start/pause");
		JButton onceButton = new JButton("once");
		JButton tenTimesButton = new JButton("ten Times");
		JButton oneHundredTimesButton = new JButton("one hundred times");

		timesButtonPanel.add(startAndPauseButton);
		timesButtonPanel.add(onceButton);
		timesButtonPanel.add(tenTimesButton);
		timesButtonPanel.add(oneHundredTimesButton);

		tenTimesButton.setOpaque(true);
		initPanel.setVisible(true);
		add(timesButtonPanel);
		
		

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
}
