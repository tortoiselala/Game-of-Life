package gameOfLife;

public class BitsArray {
	private int boardWidth;
	private int boardHeight;
	private byte[][] board;

	public BitsArray() {
		setBoardWidth(100000);
		setBoardHeight(100000);
		setBoard(new byte[boardHeight][boardWidth]);
	}

	public BitsArray(int boardWidth, int boardHeight) {
		setBoardWidth(boardWidth);
		setBoardHeight(boardHeight);
		setBoard(new byte[boardHeight][boardWidth]);
	}

	public int getBoardWidth() {
		return boardWidth * 8;
	}

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth / 8;
	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

	public byte[][] getBoard() {
		return board;
	}

	public void setBoard(byte[][] board) {
		if (board.length != boardWidth) {
			boardWidth = board[0].length;
		}
		if (board.length != boardHeight) {
			boardHeight = board.length;
		}

		this.board = board;
	}

	public int setValueAccordingIJ(int i, int j) {
		return 0;
	}
}
