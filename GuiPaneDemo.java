package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.net.ssl.SSLException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import gameOfLife.Board;

public class GuiPaneDemo extends JPanel implements ActionListener {

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
	private static final long serialVersionUID = -7914818778131354949L;

	private String[] actionCommand = { "start_pause", "once", "ten_timse", "one_hundred_times" };
	private Dimension guiPaneSize;
	private Board gameBoard;
	private JPanel controlPane;
	private DividedPanel paintPanel;
	private JLabel initText;
	private JComboBox<String> initCheckBox;
	private JButton startPauseButton;
	private JButton onceButton;
	private JButton tenTimesButton;
	private JButton hundredTimesButton;
	private JLabel speedLabel;
	private JSlider speedSlider;
	private JSpinner speedSpinner;
	// state =0 代表停止 state = 1代表运行
	private int state;

	public GuiPaneDemo(Board gameBoard) {
		this.gameBoard = gameBoard;
		setPaneSize();

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(guiPaneSize);

		layeredPane.add(createControlPanel(), new Integer(2), 0);
		layeredPane.add(createPaintPanel(), new Integer(1), 0);

		add(Box.createRigidArea(new Dimension(0, 10)));
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);

		setState(0);

	}

	public JPanel createControlPanel() {
		controlPane = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		controlPane.setLayout(layout);

		controlPane.setBackground(Color.RED);

		controlPane.setOpaque(false);

		controlPane.setBounds((int) (getPaneWidth() - getPaneWidth() / 4), 0, (int) (getPaneWidth() / 4),
				(int) (getHeight() / 3));

		initText = new JLabel("预  置");
		initText.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPane.add(initText, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

		initCheckBox = new JComboBox<String>();
		initCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 20));
		initCheckBox.addItem("Pulsar");
		initCheckBox.addItem("Glider");
		initCheckBox.addItem("Gospel Gliding Gun");
		controlPane.add(initCheckBox, new GridBagConstraints(2, 0, 6, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		startPauseButton = new JButton("start/pause");
		startPauseButton.setActionCommand(actionCommand[0]);
		startPauseButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		startPauseButton.addActionListener(this);
		controlPane.add(startPauseButton, new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

		onceButton = new JButton("1次");
		onceButton.setActionCommand(actionCommand[1]);
		onceButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPane.add(onceButton, new GridBagConstraints(2, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		tenTimesButton = new JButton("10次");
		tenTimesButton.setActionCommand(actionCommand[2]);
		tenTimesButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPane.add(tenTimesButton, new GridBagConstraints(4, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		hundredTimesButton = new JButton("100次");
		hundredTimesButton.setActionCommand(actionCommand[3]);
		hundredTimesButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPane.add(hundredTimesButton, new GridBagConstraints(6, 1, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		speedLabel = new JLabel("速  度");
		speedLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		controlPane.add(speedLabel, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 0), 0, 0));

		speedSlider = new JSlider(1, 50, 20);
		controlPane.add(speedSlider, new GridBagConstraints(2, 2, 5, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		SpinnerNumberModel speedModel = new SpinnerNumberModel(20, 1, 50, 2);
		speedSpinner = new JSpinner(speedModel);
		controlPane.add(speedSpinner, new GridBagConstraints(7, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 0), 0, 0));

		return controlPane;
	}

	public JPanel createPaintPanel() {
		paintPanel = new DividedPanel(getPaneWidth(), getPaneHeight());
		paintPanel.setBoard(gameBoard.getBoard());
		return paintPanel;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(actionCommand[0])) {
			SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

				@Override
				protected Void doInBackground() throws Exception {
					for (int i = 0; i < 30; i++) {
						gameBoard.updateBoard();
						paintPanel.setBoard(gameBoard.getBoard());
						System.out.println("Test:" + Integer.toString(i));
						Thread.sleep(100);
					}
					return null;
				}
			};
			worker.execute();

		} else if (cmd.equals(actionCommand[1])) {

		} else if (cmd.equals(actionCommand[2])) {

		} else if (cmd.equals(actionCommand[3])) {

		} else {

		}

	}

	private void setState(int state) {
		this.state = state;
	}

	private int getState() {
		return state;
	}

	private class StartRun extends Thread {
		private int runTimes;
		private int speed;
		private boolean isStop;

		public StartRun(int runTimes, int speed) {
			setRunTimes(runTimes);
			System.out.println("test");
			setSpeed(speed);
			setIsStop(false);
			setDaemon(true);
		}

		/*
		 * 查到swing是线程不安全的，所以这边不能用独立的线程去完成界面的绘制
		 * 
		 */
		@Override
		public void run() {
			while (getRunTimes() > 0 && !getIsStop()) {
				try {   
					gameBoard.updateBoard();
					paintPanel.setBoard(gameBoard.getBoard());
					setRunTimes(getRunTimes() - 1);
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public boolean getIsStop() {
			return isStop;
		}

		public void setIsStop(boolean isStop) {
			this.isStop = isStop;
		}

		public int getRunTimes() {
			return runTimes;
		}

		public void setRunTimes(int runTimes) {
			this.runTimes = runTimes;
		}

		public int getSpeed() {
			return speed;
		}

		public void setSpeed(int speed) {
			this.speed = speed;
		}
	}

	private class DividedPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2197929693344108982L;
		private final double minWidthOfCell = 10;
		private double screenWidth;
		private double screenHeight;
		private int localWidthNumber;
		private int localHeightNumber;
		private int widthNumber;
		private int heightNumber;
		private int[][] board;

		public DividedPanel(double screenWidth, double screenHeight) {
			this.screenWidth = screenWidth;

			this.screenHeight = screenHeight;
			this.localWidthNumber = (int) (screenWidth / minWidthOfCell);
			this.localHeightNumber = (int) (screenHeight / minWidthOfCell);
			this.setBackground(Color.BLACK);
			setSize(getPreferredSize());
			this.setVisible(true);
		}

		public void setBoard(int[][] board) {
			this.board = board;
			this.widthNumber = board[0].length;
			this.heightNumber = board.length;
			this.repaint();
		}

		@Override
		public void paintComponent(Graphics g) {
			if (this.board == null) {
				return;
			}
			if (localWidthNumber < widthNumber || localHeightNumber < heightNumber) {
				try {
					throw new Exception("too large map");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Graphics2D g2 = (Graphics2D) g;

			ArrayList<Line2D> line2dWidth = new ArrayList<Line2D>();
			ArrayList<Line2D> line2dHeight = new ArrayList<Line2D>();

			for (int i = 0; i < localHeightNumber; i++) {
				line2dWidth.add(new Line2D.Double(0, i * minWidthOfCell, screenWidth, i * minWidthOfCell));
				g2.draw(line2dWidth.get(i));
			}

			for (int i = 0; i < localWidthNumber; i++) {
				line2dHeight.add(new Line2D.Double(i * minWidthOfCell, 0, i * minWidthOfCell, screenHeight));
				g2.draw(line2dHeight.get(i));
			}
			g2.setPaint(Color.RED);

			for (int i = 0; i < heightNumber; i++) {
				for (int j = 0; j < widthNumber; j++) {
					if (board[i][j] > 0) {
						g2.fill(new Rectangle2D.Double(j * minWidthOfCell, i * minWidthOfCell, minWidthOfCell,
								minWidthOfCell));
					}

				}
			}

		}

		@Override
		public Dimension getPreferredSize() {
			// TODO Auto-generated method stub
			return new Dimension((int) screenWidth, (int) screenHeight);
		}
	}

	public double getPaneWidth() {
		return getWidth();
	}

	public void setPaneSize() {
		guiPaneSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(guiPaneSize);
	}

	public double getPaneHeight() {
		return getHeight();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((int) (getWidth()), (int) (getHeight()));
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	static int[][] GospelGlidingGun = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1,
					1, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1,
					1, 0, 0, 0, 0, },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, } };

	private static void createAndShowGUI() throws Exception {
		// Create and set up the window.
		JFrame frame = new JFrame("LayeredPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		Board board = new Board();
		InitCells initCells = new InitCells(board.getBoardWidth(), board.getBoardHeight());
		initCells.setInitCellArray(GospelGlidingGun);
		board.setBoard(initCells.getBoard());
		JComponent newContentPane = new GuiPaneDemo(board);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

}
