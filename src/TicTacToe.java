import java.util.InputMismatchException;
import java.util.Scanner;

enum State { empty, x, o }

public class TicTacToe {
	private static Scanner scanner;
	private static final String dancingCowFile = "../dancingCow.animation.txt";
	
	public static void main(String[] args) {
		System.out.println(
			"Welcome to TicTacToe. I'm sure you already know the rules, if you don't,\n"
			+ "then, please, go get yourself a childhood.\n"
			+ "\n"
			+ "To play the CLI version, enter a number as indicated on the board.\n"
			+ "The board and numbers have been optimized for keypad users.\n"
			+ "If you lose, then know that you lost to a random number generator.\n"
			+ "\n"
			+ "Copyright (c) 2019 JaPNaA"
		);
		
		scanner = new Scanner(System.in);
		
		while (true) {
			new TicTacToe();
			CowGenerator.print("Play again?");
			System.out.print("(y/N) > ");
			if (scanner.next().toLowerCase().charAt(0) != 'y') {
				CowGenerator.print("NOOOOO What have you don--");
				break;
			}
		}
	}
	
	Board board;
	
	
	private TicTacToe() {
		board = new Board();
		
		while (true) {
			board.printer.print();
			try { mainLoopStep(); }
			catch (DoneException event) {
				Heatmap.print();
				playDancingCowSaying(board.getWinnerString() + " won this game");
				break;
			}
		}
	}
	
	
	private void playDancingCowSaying(String text) {
		try {
			String str = Util.readFileToString(dancingCowFile);
			AnimationPlayer.play(str, 500, (String frame) -> {
				CowGenerator.setCow(frame);
				CowGenerator.print(text);
			});
			CowGenerator.resetCow();
		} catch (Exception err) {
			err.printStackTrace();
			throw new Error("An error occured");
		}
	}
	
	
	private void mainLoopStep() throws DoneException {
		doPlayerMove();
		board.printer.print();
		board.checkDone();
		
		waitAMoment();
		
		doComputerMove();
		board.printer.print();
		board.checkDone();
	}
	
	
	private void doPlayerMove() {
		while (true) {
			int move = getPlayerMove();
			try {
				if (board.isEmptyAt(move)) {
					board.setStateAt(move, State.x);
					Heatmap.addPlayerMove(move);
					return;
				} else {
					CowGenerator.print("Cell not empty!");
				}
			} catch (ArrayIndexOutOfBoundsException err) {
				CowGenerator.print("Invalid number!");
			}
		}
	}
	
	private int getPlayerMove() {
		while (true) {
			System.out.print("1-9 > ");
			try {
				return scanner.nextInt() - 1;
			} catch (InputMismatchException err) {
				if (scanner.nextLine().toLowerCase().charAt(0) == 'q') {
					System.out.println("Quitting");
					System.exit(0);
				} else {
					CowGenerator.print("That's... not a number.");
				}
			}
		}
	}
	
	private void doComputerMove() {
		while (true) {
			int move = getComputerMove();
			if (board.isEmptyAt(move)) {
				Heatmap.addComputerMove(move);
				board.setStateAt(move, State.o);
				return;
			}
		}
	}
	
	private int getComputerMove() {
		return (int)(Math.random() * board.width * board.height);
	}
	
	
	private void waitAMoment() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
