import java.util.ArrayList;
import java.util.Scanner;

public class RefactoredTicTacToe {

	static ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
	static String[] player = { "X", "O" };
	static int round = 1;
	static String gameStatus = "active";
	// board visual config
	static int boxPadding = 2;
	static String material = "■ ";
	static String space = "  ";

	public static void main(String[] args) {
		instanceGame();
		while (gameStatus == "active") {
			printHeader();
			printBoard();
			printGameStats();

			boolean validIn = true;
			do {
				Scanner in = new Scanner(System.in);
				try {
					int placeIndex = in.nextInt();
					validateMove(placeIndex);
					// if doesn't throw err then
					round++;
					validIn = true;
					checkStatus();
					System.out.println(String.format("%s", gameStatus));

				} catch (Exception e) {
					System.out.println(e);
					validIn = false;
				}
			} while (validIn == false);
		}
		printBoard();
		System.out.println(String.format("%s", gameStatus));

	}

	static void instanceGame(){
		board = new ArrayList<ArrayList<String>>();
		round = 1;
		gameStatus = "active";
		for (int i = 0; i < 3; i++) {
			board.add(new ArrayList<String>());
			for (int j = 0; j < 3; j++) {
				board.get(i).add(Integer.toString(j + i * 3));
			}
		}
	}

	static void printHeader() {
		System.out.println(String.format("[Tic - tac - toe] (Round %d)", round));
	}

	static void printGameStats() {
		System.out.print(
				String.format("%s's Round, type the index to place:\n> ", round % 2 == 0 ? player[0] : player[1]));
	}

	static void printBoard() {
		int boxWidth = boxPadding * 2 + 1;
		int wallLength = 4 + boxWidth * 3;
		int modOp = boxWidth + 1;
		int index = 0;

		String printWall = space.repeat(boxWidth) + material;

		for (int i = 0; i < wallLength; i++) {

			if (i % modOp == 0) {
				System.out.println(material.repeat(wallLength));
			} //
			else if (i % modOp == boxPadding + 1) {
				System.out.print(material);
				for (int j = 0; j < 3; j++) {
					System.out.print(space.repeat(boxWidth / 2) + board.get(index / 3).get(index % 3) + " "
							+ space.repeat(boxWidth / 2) + material);
					index++;
				}
				System.out.println();
			} //
			else {
				System.out.println(material + printWall.repeat(3));
			}
		}

	}

	static void checkStatus() {
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.get(j).get(0).equals(player[i]) && //
						board.get(j).get(1).equals(player[i]) && //
						board.get(j).get(2).equals(player[i])) {
					gameStatus = String.format("player %s wins!", player[i]);
					return;
				} //
				else if (board.get(0).get(j).equals(player[i]) && //
						board.get(1).get(j).equals(player[i])&& //
						board.get(2).get(j).equals(player[i])) {
					gameStatus = String.format("player %s wins!", player[i]);
					return;
				}
			}
			
			if (board.get(0).get(0).equals(player[i]) && //
					board.get(1).get(1).equals(player[i])&& //
					board.get(2).get(2).equals(player[i])) {
				gameStatus = String.format("player %s wins!", player[i]);
				return;
			} //
			else if (board.get(0).get(2).equals(player[i]) && //
					board.get(1).get(1).equals(player[i])&& //
					board.get(2).get(0).equals(player[i])) {
				gameStatus = String.format("player %s wins!", player[i]);
				return;
			}
		}
		//check tied game
		if (round == 10) {
			gameStatus = "game tied";
			return;
		}
		gameStatus = "active";
		return;
	}

	static void validateMove(int placeIndex) {
		// check if valid index
		if (placeIndex < 0 || placeIndex > 8)
			throw new ArithmeticException("must be between 0 and 8.");
		// check if nought is already placed there
		if (board.get(placeIndex / 3).get(placeIndex % 3) == player[0]
				|| board.get(placeIndex / 3).get(placeIndex % 3) == player[1])
			throw new ArithmeticException("there is already a nought there");
		board.get(placeIndex / 3).set(placeIndex % 3, player[round % 2]);
	}
}
