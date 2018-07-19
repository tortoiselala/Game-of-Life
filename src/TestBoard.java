package gameOfLife;

public class TestBoard {
	public static void main(String[] args) throws Exception {
		Board test = null;
		try {
			test = new Board(2, 2, new int[] { 2, 3 }, new int[] { 3 });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(test.getBoardWidth());
		System.out.println(test.getBoardHeight());
		int[][] testBoard = new int[100][100];
		test.setBoard(testBoard);
		System.out.println(test.getBoardWidth());
		System.out.println(test.getBoardHeight());
		test.updateBoard();
		test.setBoardElem(10, 10, 1);
	}
}
