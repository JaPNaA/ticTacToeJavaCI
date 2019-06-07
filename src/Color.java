public class Color {
	public static String red = "31";
	public static String green = "32";
	
	public static String reset = "39;49";
	
	private static String beforeColor = "\u001b[";
	private static String afterColor = "m";
	
	private String color;
	
	public Color() {
		this.color = beforeColor + reset + afterColor;
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
