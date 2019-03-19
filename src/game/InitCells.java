package game;

public class InitCells {
	private int[][] initCellArray;
	private int boardWidth;
	private int boardHeight;

	public InitCells(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}

	public int[][] getInitCellArray() {
		return initCellArray;
	}

	public void setInitCellArray(int[][] initCellArray) {
		this.initCellArray = initCellArray;
	}

	public void ensureInitCellArrayIsNull() throws Exception {
		if (initCellArray == null) {
			throw new Exception("must init the array,before use it!");
		}
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
		for (int m = 0, i = boardHeight / 2 - initCellArray.length / 2; m < initCellArray.length; i++, m++) {
			for (int n = 0, j = boardWidth / 2 - initCellArray[0].length / 2; n < initCellArray[0].length; j++, n++) {
				board[i][j] = initCellArray[m][n];
			}
		}
		return board;
	}
}
