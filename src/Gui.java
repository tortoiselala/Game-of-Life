package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.CollationElementIterator;

//import javax.management.Descriptor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import gameOfLife.Board;

public class Gui {
	JFrame frame;
	Dimension screenSize;

	public Gui() throws Exception {
		Toolkit kiToolkit = Toolkit.getDefaultToolkit();
		screenSize = kiToolkit.getScreenSize();

		initGUI();
		addPanel();
		controlPanel();

	}

	public void initGUI() {
		frame = new JFrame("HelloWorldSwing");
		JFrame.setDefaultLookAndFeelDecorated(true);

		// 去除默认的标题栏，设置为无88
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		// 窗口最大化
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// 显示窗口
		// 获取屏幕大小..

		frame.setSize(screenSize);
		frame.setVisible(true);

	}

	public void addPanel() throws Exception {
		DividedPanel newPanel = new DividedPanel(frame.getWidth(), frame.getHeight());
		Board board = new Board();

		frame.add(newPanel);

//		board.setBoardCell(10, 10, 1);
//		board.setBoardCell(11, 11, 1);
//		board.setBoardCell(9, 12, 1);
//		board.setBoardCell(10, 12, 1);
//		board.setBoardCell(11, 12, 1);

		board.setBoardCell(9, 10, 1);
		board.setBoardCell(10, 9, 1);
		board.setBoardCell(10, 10, 1);
		board.setBoardCell(10, 11, 1);
		board.setBoardCell(11, 9, 1);

		board.setBoardCell(11, 11, 1);
		board.setBoardCell(12, 10, 1);

		int i = 100000;
		while (i > 0) {
			newPanel.setBoard(board.getBoard());
			board.updateBoard();
			Thread.currentThread();
			Thread.sleep(500);

			--i;
		}
	}

	public void controlPanel() {
		JPanel controlJpanel = new JPanel();
		// 設置背景色透明
		controlJpanel.setBackground(Color.WHITE);
		controlJpanel.setOpaque(false);
		//
		controlJpanel.setLocation(screenSize.height - 100, screenSize.width - 1000);
		
		frame.add(controlJpanel);
	}

}
