
public class CowGenerator {
	private static final String originalCow =  
		" _$__\n" + 
		"< $t >\n" + 
		" -$--\n" + 
		"        \\   ^__^\n" + 
		"         \\  (oo)\\_______\n" + 
		"            (__)\\       )\\/\\\n" + 
		"                ||----w |\n" + 
		"                ||     ||\n";
	private static String cow = originalCow;
	
	public static String say(String text) {
		int length = text.length();
		String underscores = "_".repeat(length);
		String dashes = "-".repeat(length);
		
		return cow
				.replaceFirst("\\$_", underscores)
				.replaceFirst("\\$t", text)
				.replaceFirst("\\$-", dashes);
	}
	
	public static void setCow(String cow) {
		CowGenerator.cow = cow;
	}
	
	public static void resetCow() {
		CowGenerator.cow = originalCow;
	}
	
	public static void print(String text) {
		System.out.println(say(text));
	}
}
