package gameOfLife;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import gameOfLife.Board;

public class Gui {
	public void createAndShowGUI() throws Exception {
		Board board = new Board();
		board.setBoardElem(10, 10, 1);
		board.setBoardElem(11, 11, 1);
		board.setBoardElem(9, 12, 1);
		board.setBoardElem(10, 12, 1);
		board.setBoardElem(11, 12, 1);
		
		
		
		JFrame.setDefaultLookAndFeelDecorated(true);

		// 创建及设置窗口
		JFrame frame = new JFrame("HelloWorldSwing");
		// 去除默认的标题栏，设置为无
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		// 窗口最大化
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// 显示窗口
		// 获取屏幕大小
		Toolkit kiToolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kiToolkit.getScreenSize();

		frame.setSize(screenSize);
		DividedPanel newPanel = new DividedPanel(frame.getWidth(), frame.getHeight());
		frame.add(newPanel);
		frame.setVisible(true);
		int i =100000;
		while (i>0) {
			newPanel.setBoard(board.getBoard());
			board.updateBoard();
			Thread.currentThread();
			Thread.sleep(1000);
			--i;
		}

	}

}
