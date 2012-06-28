package Spielfeld;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;

public class Sound {

	static Clip clip = null;

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

	public static void hintergrundmusik(String BGM) {

		String BGMFile = "Audio/" + BGM + ".au";
		try {
			clip = AudioSystem.getClip();
			AudioInputStream BackgroundStream = AudioSystem
					.getAudioInputStream(new File(BGMFile).getAbsoluteFile());

			clip.open(BackgroundStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception ex) {

		}
		return;
	}

	public static void stoppen() {
		if (clip != null)
			clip.stop();
	}

	public static void loopen() {
		if (clip != null)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static void mutean() {
		BooleanControl muteControl = (BooleanControl) clip
				.getControl(BooleanControl.Type.MUTE);
		muteControl.setValue(true);
	}
}
