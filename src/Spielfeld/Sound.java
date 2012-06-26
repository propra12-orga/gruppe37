package Spielfeld;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static void soundeffekt(String AudioDatei) {

		try {
			AudioInputStream InputStream = AudioSystem
					.getAudioInputStream(new File(AudioDatei).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(InputStream);
			clip.start();
		} catch (Exception ex) {

		}
	}
}
