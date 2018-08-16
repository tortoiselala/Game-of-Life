package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import gameOfLife.Board;

public class Gui extends JFrame {
	/**
	 * <h1>主界面</h1>
	 * <p>
	 * 主界面程序创建一个主界面，其中包含了游戏主面板DividedPanel与控制面板ControlPanel
	 * </p>
	 * 
	 * @author tortoiselala
	 * @version 1.0
	 * @since 2018-7
	 */
	private static final long serialVersionUID = 3260292374030718185L;

	Dimension screenSize;
	Board gameBoard;
	DividedPanel paintPanel;
	JPanel controlPanel;

	/*
	 * @param gameBoard 游戏逻辑处理对象
	 */
	public Gui(Board gameBoard) throws Exception {
		this.gameBoard = gameBoard;

		Toolkit kiToolkit = Toolkit.getDefaultToolkit();
		screenSize = kiToolkit.getScreenSize();

		setSize(screenSize);

		paintPanel = new DividedPanel(screenSize.width, screenSize.height);
		paintPanel.setBoard(gameBoard.getBoard());
		//未添加
		setDefaultLookAndFeelDecorated(true);

		// 去除默认的标题栏，设置为无
		// setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		// 窗口最大化
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// 显示窗口
		// 获取屏幕大小..
		
		JLayeredPane layerPane = new JLayeredPane();
		layerPane.add(paintPanel , new Integer(200), 0);
		setTitle("生命游戲");
		
		add(layerPane);
		
		setVisible(true);
		
		
		
		//addControlPanel();
		
		//add(paintPanel);

	}

	public void addControlPanel() {

		controlPanel = new JPanel();

		GridBagLayout layout = new GridBagLayout();
		controlPanel.setLayout(layout);

		controlPanel.setBackground(Color.RED);

		controlPanel.setOpaque(true);

		controlPanel.setBounds((int) (getScreenWidth() - getScreenWidth() / 4), 0, (int) (getScreenWidth() / 4),
				(int) (getScreenHeight() / 3));

		JLabel initText = new JLabel("预  置");
		initText.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPanel.add(initText, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

		JComboBox<String> initCheckBox = new JComboBox<String>();
		initCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		initCheckBox.addItem("Pulsar");
		initCheckBox.addItem("Glider");
		initCheckBox.addItem("Gospel Gliding Gun");
		controlPanel.add(initCheckBox, new GridBagConstraints(2, 0, 6, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		JButton startPauseButton = new JButton("start/pause");
		startPauseButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		// startPauseButton.addActionListener(1);
		controlPanel.add(startPauseButton, new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

		JButton onceButton = new JButton("1次");
		onceButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPanel.add(onceButton, new GridBagConstraints(2, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		JButton tenTimesButton = new JButton("10次");
		tenTimesButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPanel.add(tenTimesButton, new GridBagConstraints(4, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		JButton hundredTimesButton = new JButton("100次");
		hundredTimesButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPanel.add(hundredTimesButton, new GridBagConstraints(6, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		JLabel speedLabel = new JLabel("速  度");
		speedLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPanel.add(speedLabel, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

		JSlider speedSlider = new JSlider(1, 50, 20);
		controlPanel.add(speedSlider, new GridBagConstraints(2, 2, 5, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		SpinnerNumberModel speedModel = new SpinnerNumberModel(20, 1, 50, 2);
		JSpinner speedSpinner = new JSpinner(speedModel);
		controlPanel.add(speedSpinner, new GridBagConstraints(7, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));
		add(controlPanel);
	}

	/*
	 * 
	 */
	public void updateBoard() {
		gameBoard.updateBoard();
		paintPanel.setBoard(gameBoard.getBoard());
	}

	public class runTimesButtonListener implements ActionListener {

		public runTimesButtonListener() {
		}

		@Override
		public void actionPerformed(ActionEvent event) {

		}
	}

	public double getScreenWidth() {
		return screenSize.getWidth();
	}

	public void setScreenWidth() {
		Toolkit kiToolkit = Toolkit.getDefaultToolkit();
		this.screenSize = kiToolkit.getScreenSize();
	}

	public double getScreenHeight() {
		return screenSize.getHeight();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (getScreenWidth()), (int) (getScreenHeight()));
	}

}
