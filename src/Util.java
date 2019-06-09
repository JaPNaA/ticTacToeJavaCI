import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
	public static String readFileToString(String path) {
		try {
			byte[] enc = Files.readAllBytes(Paths.get(path));
			return new String(enc, Charset.forName("utf-8"));
		} catch (IOException err) {
			err.printStackTrace();
			throw new Error("IOException");
		}
	}
}
