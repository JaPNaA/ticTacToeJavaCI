import java.util.Scanner;

enum State { empty, x, o }

public class TicTacToe {
	private static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		while (true) {
			new TicTacToe();
			System.out.println("Play again? (y/N) > ");
			if (scanner.next().toLowerCase().charAt(0) != 'y') {
				break;
			}
		}
	}
	
	Board board;
	
	
	private TicTacToe() {
		board = new Board();
		
		while (true) {
			board.printBoard();
			try { mainLoopStep(); }
			catch (DoneException event) {
				 System.out.println(board.getWinnerString() + " won this game");
				 break;
			}
		}
	}
	
	
	private void mainLoopStep() throws DoneException {
		doPlayerMove();
		board.printBoard();
		board.checkDone();
		
		waitAMoment();
		
		doComputerMove();
		board.printBoard();
		board.checkDone();
	}
	
	
	private void doPlayerMove() {
		while (true) {
			int move = getPlayerMove();
			try {
				if (board.isEmptyAt(move)) {
					board.setStateAt(move, State.x);
					return;
				} else {
					System.out.println("Cell not empty!");
				}
			} catch (ArrayIndexOutOfBoundsException err) {
				System.out.println("Invalid move!");
			}
		}
	}
	
	private int getPlayerMove() {
		System.out.print("1-9 > ");
		return scanner.nextInt() - 1;
	}
	
	private void doComputerMove() {
		while (true) {
			int move = getComputerMove();
			if (board.isEmptyAt(move)) {
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
