package game;

public class Board {
	private int boardWidth;
	private int boardHeight;
	private int[][] board;
	private int[] liveRules;
	private int[] deadRules;

	public Board() throws Exception {
		setBoardWidth(100);
		setBoardHeight(100);
		setBoard(new int[boardHeight][boardWidth]);
		setLiveRules(new int[] { 2, 3 });
		setDeadRules(new int[] { 3 });
	}

	public Board(int boardWidth, int boardHeight, int[] liveRules, int[] deadRules) throws Exception {
		setBoardWidth(boardWidth);
		setBoardHeight(boardHeight);
		setBoard(new int[boardHeight][boardWidth]);
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

	public void setBoard(int[][] board) throws Exception {
		if (board.length != boardHeight) {
			setBoardHeight(board.length);
		}
		if (board[0].length != boardWidth) {
			setBoardWidth(board[0].length);
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
		int[][] temp = new int[boardHeight][boardWidth];
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				int neighbourNum = 0;
				if ((i - 1 >= 0) && (j - 1 >= 0) && (board[i - 1][j - 1] == 1)) {
					neighbourNum++;
				}
				if ((i - 1 >= 0) && (board[i - 1][j] == 1)) {
					neighbourNum++;
				}
				if ((i - 1 >= 0) && (j + 1 < boardWidth - 1) && (board[i - 1][j + 1] == 1)) {
					neighbourNum++;
				}
				if ((j - 1 >= 0) && (board[i][j - 1] == 1)) {
					neighbourNum++;
				}
				if ((j + 1 < boardWidth - 1) && (board[i][j + 1] == 1)) {
					neighbourNum++;
				}
				if ((i + 1 < boardHeight - 1) && (j - 1 >= 0) && (board[i + 1][j - 1] == 1)) {
					neighbourNum++;
				}
				if ((i + 1 < boardHeight - 1) && (board[i + 1][j] == 1)) {
					neighbourNum++;
				}
				if ((i + 1 < boardHeight - 1) && (j + 1 < boardWidth - 1) && (board[i + 1][j + 1] == 1)) {
					neighbourNum++;
				}
//			*	当前细胞为死亡状态时，当周围有3个存活细胞时，该细胞变成存活状态。 （模拟繁殖）
//				当前细胞为存活状态时，当周围低于2个（不包含2个）存活细胞时， 该细胞变成死亡状态。（模拟人口稀少）
//			*	当前细胞为存活状态时，当周围有2个或3个存活细胞时， 该细胞保持原样。
//				当前细胞为存活状态时，当周围有3个以上的存活细胞时，该细胞变成死亡状态。（模拟过度拥挤）
//				if ((board[i][j] == 1) && ensureNumInRules(liveRules, neighbourNum)) {
//					temp[i][j] = board[i][j];
//				} else if ((board[i][j] == 0) && ensureNumInRules(deadRules, neighbourNum)) {
//					temp[i][j] = 1;
//				}
				if (board[i][j] == 0 && neighbourNum == 3) {
					temp[i][j] = 1;
				} else if (board[i][j] > 0 && neighbourNum < 2) {
					temp[i][j] = 0;
				} else if (board[i][j] > 0 && (neighbourNum == 2 || neighbourNum == 3)) {
					temp[i][j] = board[i][j];
				} else if (board[i][j] > 0 && neighbourNum > 3) {
					temp[i][j] = 0;
				}
			}
		}
		this.board = temp;
	}

	public void setBoardCell(int i, int j, int value) throws Exception {
		if (i >= 0 && j >= 0 && i < boardHeight && j < boardWidth && value >= 0) {
			board[i][j] = value;
		} else {
			throw new Exception("Wrong index or value!");
		}
	}
}
