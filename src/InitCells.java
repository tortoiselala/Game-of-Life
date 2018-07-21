package gameOfLife;

public class InitCells {
	private int[][] InitCellArray;
	private int boardWidth;
	private int boardHeight;

	public InitCells(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}

	public int[][] getInitCellArray() {
		return InitCellArray;
	}

	public void setInitCellArray(int[][] initCellArray) {
		InitCellArray = initCellArray;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	public int[][] getBoard() {
		int[][] board = new int[boardHeight][boardWidth];
		for (int m = 0, i = boardHeight / 2 - InitCellArray.length / 2; i < InitCellArray.length; i++, m++) {
			for (int n = 0, j = boardWidth / 2 - InitCellArray[0].length; j < InitCellArray[0].length; j++, n++) {
				board[i][j] = InitCellArray[m][n];
			}
		}
		return board;
	}
}
