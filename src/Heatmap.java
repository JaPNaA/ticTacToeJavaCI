
public class Heatmap {
	static int[] computerMoves = new int[9];
	static int[] playerMoves = new int[9];
	
	public static void init() {
		int[][] moves = { computerMoves, playerMoves };
		for (int[] move : moves) {
			for (int i = 0; i < 3; i++) {
				move[i] = 0;
			}
		}
	}
	
	public static void addComputerMove(int i) {
		computerMoves[i]++;
	}
	
	public static void addPlayerMove(int i) {
		playerMoves[i]++;
	}
	
	public static void print() {
		System.out.println("Computer moves heatmap:");
		printHeatmap(computerMoves);
		
		System.out.println("\nPlayer moves heatmap:");
		printHeatmap(playerMoves);
	}
	
	private static void printHeatmap(int[] heatmap) {
		int max = getMax(heatmap);
		
		for (int x = 9 - 3; x >= 0; x -= 3) {
			for (int i = 0; i < 3; i++) {
				float brightness = (float)heatmap[i + x] / max;
				System.out.print(createColor(brightness));
				System.out.print(roundToOneDecimalPlace(brightness));
				System.out.print(", ");
			}
			System.out.print("\n");
		}
		
		System.out.print(Color.resetColor.toString());
	}
	
	private static int getMax(int[] arr) {
		int max = 0;
		
		for (int i : arr) {
			if (i > max) {
				max = i;
			}
		}
		
		return max;
	}
	
	private static String createColor(float brightness) {
		int num = (int)Math.floor(brightness * 255);
		return "\u001b[38;2;" + num + ";" + num + ";" + num + "m";
	}
	
	private static float roundToOneDecimalPlace(float brightness) {
		return (float)Math.floor(brightness * 10) / 10;
	}
}
