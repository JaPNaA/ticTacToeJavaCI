
public class BoardPrinter {

	private static final char[][] largeX = {
		{ ' ', ' ', '\\', ' ', '/',  ' ', ' ' },
		{ ' ', ' ', ' ' , 'X', ' ',  ' ', ' ' },
		{ ' ', ' ', '/' , ' ', '\\', ' ', ' ' }
	};
	
	private static final char[][] largeO = {
		{ ' ', 0x250c, 0x2500, 0x2500, 0x2500, 0x2510, ' '},
		{ ' ', 0x2502,    ' ',    ' ',    ' ', 0x2502, ' '},
		{ ' ', 0x2514, 0x2500, 0x2500, 0x2500, 0x2518, ' '}
	};
	
	private static final Color colorX = new Color(Color.green);
	private static final Color colorO = new Color(Color.red);
	
	private static final char vertical = '│';
	private static final char horizontal = '─';
	
	private static final int largeCharWidth = 7;
	private static final int largeCharHeight = 3;
	private static final int padding = 1;
	private static final int border = 1;
	private static final int cellWidth = largeCharWidth + padding * 2;
	private static final int cellHeight = largeCharHeight + padding * 2;
	
	private Board board;
	private char[][] buffer;
	private Color[][] colorBuffer;
	private int bufferWidth;
	private int bufferHeight;
	
	
	BoardPrinter(Board board) {
		bufferWidth = board.width * cellWidth + border * (board.width + 1);
		bufferHeight = board.height * cellHeight + border * (board.height + 1);
		buffer = new char[bufferHeight][bufferWidth];
		colorBuffer = new Color[bufferHeight][bufferWidth];
		clearBuffer();
		
		this.board = board;
	}
	
	public void print() {
		for (int y = 0; y < board.height; y++) {
			for (int x = 0; x < board.width; x++) {
				drawStateFor(x, y);
			}
		}
		
		printBuffer();
	}
	
	private void drawStateFor(int x, int y) {
		State state = board.getStateAt(x, y);
		int drawingYPos = board.height - y - 1;
		if (state == State.x) {
			drawX(x, drawingYPos);
		} else if (state == State.o) {
			drawO(x, drawingYPos);
		} else {
			writeChar(String.valueOf(y * board.width + x + 1).charAt(0), x, drawingYPos);
		}
	}
	
	private void printBuffer() {
		StringBuilder builder = new StringBuilder();
		Color lastColor = null;
		
		for (int y = 0; y < bufferHeight; y++) {
			for (int x = 0; x < bufferWidth; x++) {
				Color newColor = colorBuffer[y][x];
				builder.append(getColorChangeString(lastColor, newColor));
				builder.append(buffer[y][x]);
				lastColor = newColor;
			}
			builder.append('\n');
		}
		System.out.println(builder.toString());
	}
	
	private String getColorChangeString(Color lastColor, Color newColor) {
		if (lastColor == null) {
			if (newColor != null) {
				return newColor.toString();
			}
		} else {
			if (newColor == null) {
				return Color.resetColor.toString();
			} else if (!lastColor.equals(newColor)) {
				return newColor.toString();
			}	
		}
		
		return "";
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
	
	private void drawX(int boardX, int boardY) {
		drawItem(largeX, boardX, boardY, colorX);
	}
	
	private void drawO(int boardX, int boardY) {
		drawItem(largeO, boardX, boardY, colorO);
	}
	
	private void drawItem(char[][] item, int boardX, int boardY, Color color) {
		int startX = boardX * (cellWidth + border) + border + padding;
		int startY = boardY * (cellHeight + border) + border + padding;
		
		for (int x = 0; x < largeCharWidth; x++) {
			for (int y = 0; y < largeCharHeight; y++) {
				int currY = startY + y;
				int currX = startX + x;
				buffer[currY][currX] = item[y][x];
				colorBuffer[currY][currX] = color;
			}
		}
	}
	
	private void writeChar(char text, int boardX, int boardY) {
		int startX = boardX * (cellWidth + border) + border + padding;
		int startY = boardY * (cellHeight + border) + border + padding;
		
		buffer[startY + largeCharHeight / 2][startX + largeCharWidth / 2] = text;
	}
}
