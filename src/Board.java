
public class Board {
	public final int width = 3;
	public final int height = 3;
	
	public BoardPrinter printer;
	
	private State winner = State.empty;
	private State board[][];

	private final int middleX = width / 2;
	private final int middleY = height / 2;
	
	Board() {
		board = new State[width][height];
		printer = new BoardPrinter(this);
		this.fillBoard();
	}


	
	public char getCharAt(int i) {
		return stateToChar(getStateAt(i));
	}
	
	public char getCharAt(Vector vec) {
		return stateToChar(getStateAt(vec));
	}
	
	public char getCharAt(int x, int y) {
		return stateToChar(getStateAt(x, y));
	}

	public State getStateAt(int i) {
		Vector vec = indexToVec(i);
		return getStateAt(vec);
	}
	
	public State getStateAt(Vector vec) {
		return board[vec.y][vec.x];
	}
	
	public State getStateAt(int x, int y) {
		return board[y][x];
	}

	public void setStateAt(int i, State state) {
		Vector vec = indexToVec(i);
		board[vec.y][vec.x] = state;
	}
	
	public void setStateAt(Vector vec, State state) {
		board[vec.y][vec.x] = state;
	}
	
	public void setStateAt(int x, int y, State state) {
		board[y][x] = state;
	}
	
	public boolean isEmpty(int x, int y) {
		return getStateAt(x, y) == State.empty;
	}
	
	public boolean isEmptyAt(int i) {
		return getStateAt(i) == State.empty;
	}
	
	public boolean isEmpty(Vector vec) {
		return getStateAt(vec) == State.empty;
	}
	
	public State getWinner() {
		if (winner == State.empty) {
			return null;
		} else {
			return winner;
		}
	}
	
	public String getWinnerString() {
		if (winner == State.empty) {
			return "no one";
		} else {
			return String.valueOf(stateToChar(winner));
		}
	}
	

	public void checkDone() throws DoneException {
		try {
			for (int x = 0; x < width; x++) {
				checkDoneInColumn(x);
			}
			
			for (int y = 0; y < height; y++) {
				checkDoneInRow(y);
			}
			
			checkDoneDiagonal();
			checkBoardFull();
		} catch (DoneException event) {
			winner = event.winner;
			throw event;
		}
	}

	private void fillBoard() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				board[y][x] = State.empty;
			}
		}
	}
	
	private void checkDoneInColumn(int x) throws DoneException {
		State first = board[0][x];
		if (first == State.empty) { return; }
		for (int y = 1; y < height; y++) {
			if (board[y][x] != first) {
				return;
			}
		}
		
		throw new DoneException(first);
	}

	private void checkDoneInRow(int y) throws DoneException {
		State first = board[y][0];
		if (first == State.empty) { return; }
		for (int x = 1; x < width; x++) {
			if (board[y][x] != first) {
				return;
			}
		}
		
		throw new DoneException(first);
	}
	
	private void checkDoneDiagonal() throws DoneException {
		State middle = board[middleY][middleX];
		if (middle == State.empty) { return; }
		checkDiagonal1();
		checkDiagonal2();
		
	}
	
	private void checkDiagonal1() throws DoneException {
		State middle = board[middleY][middleX];
		
		for (int i = 0; i < width; i++) {
			if (board[i][width - i - 1] != middle) {
				return;
			}
		}
		
		throw new DoneException(middle);
	}
	
	private void checkDiagonal2() throws DoneException {
		State middle = board[middleY][middleX];

		for (int i = 0; i < width; i++) {
			if (board[i][i] != middle) {
				return;
			}
		}
		
		throw new DoneException(middle);
	}
	
	private void checkBoardFull() throws DoneException {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (board[y][x] == State.empty) {
					return;
				}
			}
		}
		
		throw new DoneException(State.empty);
	}
	
	private char stateToChar(State state) {
		switch(state) {
		case x:
			return 'x';
		case o:
			return 'o';
		case empty:
			return ' ';
		default:
			throw new Error();
		}
	}
	
	
	private Vector indexToVec(int index) {
		return new Vector(
			index % 3,
			index / 3
		);
	}
}
