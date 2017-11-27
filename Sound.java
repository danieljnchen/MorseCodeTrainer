import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

import java.util.ArrayList;

public class Sound {
	private File file;
	private AudioInputStream audioStream;
	private Clip audioClip;
	private String morse, name;
	
	private static ArrayList<Sound> sounds = new ArrayList<>();

	public Sound(File fileIn, String morseIn, String nameIn) {
		file = fileIn;
		morse = morseIn;
		name = nameIn;
		sounds.add(this);
	}
	
	public static Sound getSound(String name) {
		Sound out = null;
		for(Sound s : sounds) {
			if(s.getName().equals(name)) {
				out = s;
			}
		}
		return out;
	}
	
	public static Sound getSound(int number) {
		return sounds.get(number);
	}
	
	public void playSound() {
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.start();
		} catch(IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public String getMorse() {
		return morse;
	}
	
	public String getName() {
		return name;
	}
}