
public class CowGenerator {
	private static final String cow = 
		" _$__\n" + 
		"< $t >\n" + 
		" -$--\n" + 
		"        \\   ^__^\n" + 
		"         \\  (oo)\\_______\n" + 
		"            (__)\\       )\\/\\\n" + 
		"                ||----w |\n" + 
		"                ||     ||\n";
	
	public static String say(String text) {
		int length = text.length();
		String underscores = "_".repeat(length);
		String dashes = "-".repeat(length);
		
		return cow
				.replaceFirst("\\$_", underscores)
				.replaceFirst("\\$t", text)
				.replaceFirst("\\$-", dashes);
	}
	
	public static void print(String text) {
		System.out.println(say(text));
	}
}
