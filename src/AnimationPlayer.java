import java.io.IOException;

public class AnimationPlayer {
	public static void play(String animation) throws IOException, InterruptedException {
		play(animation, 500);
	}
	
	public static void play(String animation, int speed) throws IOException, InterruptedException {
		play(animation, speed, (String frame) -> {
			System.out.println(frame);
		});
	}
	
	public static void play(String animation, int speed, ICallback<String> callback) throws IOException, InterruptedException {		
		String[] frames = animation.split("\n\n");
		
		for (String frame : frames) {
			callback.run(frame);
			Thread.sleep(speed);
		}
	}
}
