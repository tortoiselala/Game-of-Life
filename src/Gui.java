package gameOfLife;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JRootPane;

import gameOfLife.Board;
import gameOfLife.ControlPanel;

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
	ControlPanel controlPanel;

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

		controlPanel = new ControlPanel();

		setDefaultLookAndFeelDecorated(true);
		
		// 去除默认的标题栏，设置为无
		//setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		// 窗口最大化
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// 显示窗口
		// 获取屏幕大小..

		setVisible(true);
		add(controlPanel);
		add(paintPanel);
		

	}

	/*
	 * 
	 */
	public void updateBoard() {
		gameBoard.updateBoard();
		paintPanel.setBoard(gameBoard.getBoard());
	}

}
