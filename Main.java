import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// instance 2D array with empty string
		ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 3; i++) {
			board.add(new ArrayList<String>());
			for (int j = 0; j < 3; j++) {
				board.get(i).add(Integer.toString(j + i * 3));
			}
		}
		String[] player = { "X", "O" };
		int round = 1;
		String status = "active";
		while (status == "active") {
			printHeader(round);
			printBoard(board);
			printIntroduction(round, player);

			boolean validIn = true;
			do {
				Scanner in = new Scanner(System.in);
				try {
					int placeIndex = in.nextInt();
					if (placeIndex < 0 || placeIndex > 8)
						throw new ArithmeticException("must be between 0 and 8.");
					if (board.get(placeIndex / 3).get(placeIndex % 3) == player[0]
							|| board.get(placeIndex / 3).get(placeIndex % 3) == player[1])
						throw new ArithmeticException("there is already a nought there");
					board.get(placeIndex / 3).set(placeIndex % 3, player[round % 2]);
					// passes all check
					round++;
					validIn = true;
					status = checkStatus(board, player, round);
				} catch (Exception e) {
					System.out.println(e);
					validIn = false;
				}
			} while (validIn == false);
		}
		printBoard(board);
		System.out.println(String.format("%s", status));

	}

	static String checkStatus(ArrayList<ArrayList<String>> board, String[] player, int round) {
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (board.get(j).get(0).equals(player[i]) && //
						board.get(j).get(1).equals(player[i]) && //
						board.get(j).get(2).equals(player[i])) {
					return String.format("player %s wins!", player[i]);
				} //
				else if (board.get(0).get(j).equals(player[i]) && //
						board.get(1).get(j).equals(player[i])&& //
						board.get(2).get(j).equals(player[i])) {
					return String.format("player %s wins!", player[i]);
				}
			}
			
			if (board.get(0).get(0).equals(player[i]) && //
					board.get(1).get(1).equals(player[i])&& //
					board.get(2).get(2).equals(player[i])) {
				return String.format("player %s wins!", player[i]);
			} //
			else if (board.get(0).get(2).equals(player[i]) && //
					board.get(1).get(1).equals(player[i])&& //
					board.get(2).get(0).equals(player[i])) {
				return String.format("player %s wins!", player[i]);
			}
		}
//		check tied game
		if (round == 10) {
			return "game tied";
		}
		return "active";
	}

	static void printHeader(int round) {
		System.out.println(String.format("[Tic - tac - toe] (Round %d)", round));
	}

	static void printIntroduction(int round, String[] player) {
		System.out.print(
				String.format("%s's Round, type the index to place:\n> ", round % 2 == 0 ? player[0] : player[1]));
	}

	static void printBoard(ArrayList<ArrayList<String>> board) {
		int boxPadding = 5;
		int boxWidth = boxPadding * 2 + 1;
		int wallLength = 4 + boxWidth * 3;
		int modOp = boxWidth + 1;
		int index = 0;

		String material = "■ ";
		String space = "  ";
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
}
