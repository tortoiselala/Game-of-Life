package gameOfLife;

import java.util.Date;

public class Board {
	private int boardWidth;
	private int boardHeight;
	private int[][] board;
	private int[] liveRules;
	private int[] deadRules;

	public Board() throws Exception {
		setBoardWidth(10000);
		setBoardHeight(10000);
		setBoard(new int[boardWidth][boardHeight]);
		setLiveRules(new int[] { 2, 3 });
		setDeadRules(new int[] { 3 });
	}

	public Board(int boardWidth, int boardHeight, int[] liveRules, int[] deadRules) throws Exception {
		setBoardWidth(boardWidth);
		setBoardWidth(boardWidth);
		setBoard(new int[boardWidth][boardHeight]);
		setLiveRules(liveRules);
		setDeadRules(deadRules);
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(int boardWidth) throws Exception {
		if (boardWidth <= 0) {
			throw new Exception("No-positive integer is not allowed! Set Failed!");
		} else {
			this.boardWidth = boardWidth;
		}

	}

	public int getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(int boardHeight) throws Exception {
		if (boardHeight <= 0) {
			throw new Exception("No-positive integer is not allowed! Set Failed!");
		} else {
			this.boardHeight = boardHeight;
		}
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		if (board.length != boardHeight) {
			this.boardHeight = board.length;
		}
		if (board[0].length != boardWidth) {
			this.boardWidth = board[0].length;
		}
		this.board = board;
	}

	public int[] getLiveRules() {
		return liveRules;
	}

	public void setLiveRules(int[] liveRules) {
		this.liveRules = liveRules;
	}

	public int[] getDeadRules() {
		return deadRules;
	}

	public void setDeadRules(int[] deadRules) {
		this.deadRules = deadRules;
	}

	public void updateBoard() {
		int[][] temp = new int[boardWidth][boardHeight];
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < boardHeight; j++) {
				int neighbourNum = 0;
				if ((i - 1 >= 0) && (j - 1 >= 0) && (board[i - 1][j - 1] == 1)) {
					neighbourNum++;
				}
				if ((i - 1 >= 0) && (board[i - 1][j] == 1)) {
					neighbourNum++;
				}
				if ((i - 1 >= 0) && (j + 1 <= boardWidth - 1) && (board[i - 1][j + 1] == 1)) {
					neighbourNum++;
				}
				if ((j - 1 >= 0) && (board[i][j - 1] == 1)) {
					neighbourNum++;
				}
				if ((j + 1 <= boardHeight - 1) && (board[i][j + 1] == 1)) {
					neighbourNum++;
				}
				if ((i + 1 <= boardHeight - 1) && (j + 1 <= boardWidth - 1) && (board[i + 1][j + 1] == 1)) {
					neighbourNum++;
				}
				if ((i + 1 <= boardHeight - 1) && (board[i + 1][j] == 1)) {
					neighbourNum++;
				}
				if ((i + 1 <= boardHeight - 1) && (j + 1 <= boardWidth - 1) && (board[i + 1][j + 1] == 1)) {
					neighbourNum++;
				}
				if ((board[i][j] == 1) && ensureNumInRules(liveRules, neighbourNum)) {
					temp[i][j] = 1;
				} else if ((board[i][j] == 0) && ensureNumInRules(deadRules, neighbourNum)) {
					temp[i][j] = 1;
				}
			}
		}

		this.board = temp;
	}

	public void setBoardElem(int i, int j, int value) throws Exception {
		if (i >= 0 && j >= 0 && i < boardHeight && j < boardWidth && value >= 0) {
			board[i][j] = value;
		} else {
			throw new Exception("Wrong index or value!");
		}
	}

	private boolean ensureNumInRules(int[] rules, int neighbourNum) {
		for (int rule : rules) {
			if (neighbourNum == rule) {
				return true;
			}
		}
		return false;
	}
}
