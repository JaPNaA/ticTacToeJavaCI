public class Color {
	public static String red = "31";
	public static String green = "32";

	private static String beforeColor = "\u001b[";
	private static String afterColor = "m";
	
	public static String resetString = "39;49";
	public static Color resetColor = new Color(resetString);
	
	private String color;
	
	public Color() {
		this.color = beforeColor + resetString + afterColor;
	}
	
	public Color(String color) {
		this.color = beforeColor + color + afterColor;
	}
	
	public String toString() {
		return this.color;
	}
	
	public boolean isSameAs(Color other) {
		return other.color == this.color;
	}
}
