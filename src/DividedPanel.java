package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class DividedPanel extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	}

	public void setBoard(int[][] board) {
		this.board = board;
		this.widthNumber = board[0].length;
		this.heightNumber = board.length;
		this.repaint();
	}

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
}
