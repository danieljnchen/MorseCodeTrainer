import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class Practice {
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException, InterruptedException {
		String directory = System.getProperty("user.dir") + "\\MorseCodeSounds\\";
		String morse[] = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
				".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
				"-.--", "--.."};

		for(int i = 0; i<26; ++i) {
			new Sound(new File(directory + Character.toString((char) ('a' + i)) + ".wav"), morse[i], Character.toString((char) ('a' + i)));
		}
		new Sound(new File(directory + "correct.wav"), "", "correct");
		new Sound(new File(directory + "incorrect.wav"), "", "incorrect");
		
		while(true) {
			Sound current = Sound.getSound(((int) (Math.random()*26)));
			System.out.println("What letter is this?" + "                                                " + current.getMorse());
			String in = null;
			do {
				current.playSound();
				in = System.console().readLine();
			} while(in.equals("repeat"));
			if(in.equals("exit")) {
				break;
			} else if(in.equals(current.getName())) {
				System.out.println("Correct");
				Sound.getSound("correct").playSound();
			} else {
				System.out.println("Incorrect. The correct letter was " + current.getName() + ".");
				Sound.getSound("incorrect").playSound();
			}
			TimeUnit.MILLISECONDS.sleep(100);
		}
	}
}