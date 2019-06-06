
public class BoardPrinter {

	private static final char[][] largeX = {
		{ ' ', '\\', ' ', '/',  ' ' },
		{ ' ', ' ' , 'X', ' ',  ' ' },
		{ ' ', '/' , ' ', '\\', ' ' }
	};
	
	private static final char[][] largeO = {
		{ '┌', '─', '─', '─', '┐'},
		{ '│', ' ', ' ', ' ', '│'},
		{ '└', '─', '─', '─', '┘'}
	};
	
	private static final char vertical = '|';
	private static final char horizontal = '-';
	
	private static final int largeCharWidth = 5;
	private static final int largeCharHeight = 3;
	private static final int padding = 1;
	private static final int border = 1;
	private static final int cellWidth = largeCharWidth + padding * 2;
	private static final int cellHeight = largeCharHeight + padding * 2;
	
	private Board board;
	private char[][] buffer;
	private int bufferWidth;
	private int bufferHeight;
	
	
	BoardPrinter(Board board) {
		bufferWidth = board.width * cellWidth + border * (board.width + 1);
		bufferHeight = board.height * cellHeight + border * (board.height + 1);
		buffer = new char[bufferHeight][bufferWidth];
		clearBuffer();
		
		this.board = board;
	}
	
	public void print() {
		for (int y = 0; y < board.height; y++) {
			for (int x = 0; x < board.width; x++) {
				State state = board.getStateAt(x, y);
				int drawingYPos = board.height - y - 1;
				if (state == State.x) {
					drawItem(largeX, x, drawingYPos);
				} else if (state == State.o) {
					drawItem(largeO, x, drawingYPos);
				} else {
					writeChar(String.valueOf(y * board.width + x + 1).charAt(0), x, drawingYPos);
				}
			}
		}
		
		printBuffer();
	}
	
	public void printBuffer() {
		StringBuilder builder = new StringBuilder();
		for (int y = 0; y < bufferHeight; y++) {
			for (int x = 0; x < bufferWidth; x++) {
				builder.append(buffer[y][x]);
			}
			builder.append('\n');
		}
		System.out.println(builder.toString());
	}
	
	private void clearBuffer() {
		for (int y = 0; y < bufferHeight; y++) {
			for (int x = 0; x < bufferWidth; x++) {
				buffer[y][x] = ' ';
			}
		}
		
		drawBufferBorders();
	}
	
	private void drawBufferBorders() {
		for (int y = 0; y < bufferHeight; y += cellHeight + 1) {
			for (int x = 0; x < bufferWidth; x++) {
				buffer[y][x] = horizontal;
			}
		}
		
		for (int x = 0; x < bufferWidth; x += cellWidth + 1) {
			for (int y = 0; y < bufferHeight; y++) {
				buffer[y][x] = vertical;
			}
		}
	}
	
	private void drawItem(char[][] item, int boardX, int boardY) {
		int startX = boardX * (cellWidth + border) + border + padding;
		int startY = boardY * (cellHeight + border) + border + padding;
		
		for (int x = 0; x < largeCharWidth; x++) {
			for (int y = 0; y < largeCharHeight; y++) {
				buffer[startY + y][startX + x] = item[y][x];
			}
		}
	}
	
	private void writeChar(char text, int boardX, int boardY) {
		int startX = boardX * (cellWidth + border) + border + padding;
		int startY = boardY * (cellHeight + border) + border + padding;
		
		buffer[startY + largeCharHeight / 2][startX + largeCharWidth / 2] = text;
	}
}
