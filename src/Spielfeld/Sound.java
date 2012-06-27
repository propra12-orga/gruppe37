package Spielfeld;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static void soundeffekt(String AudioDatei) {

		try {
			AudioInputStream EffektStream = AudioSystem
					.getAudioInputStream(new File(AudioDatei).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(EffektStream);
			clip.start();
		} catch (Exception ex) {

		}

	}

	public static void hintergrundmusik() {

		try {
			AudioInputStream BackgroundStream = AudioSystem
					.getAudioInputStream(new File("Audio/background.au")
							.getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(BackgroundStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception ex) {

		}
		return;
	}
}
