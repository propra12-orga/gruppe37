package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gui.Main;

public class Steuerung2 implements KeyListener {

	public static boolean moveRechts = false;
	public static boolean moveLinks = false;
	public static boolean moveRunter = false;
	public static boolean moveHoch = false;
	public static boolean bomb2 = false;

	private final Main window;

	public Steuerung2(Main parent) {
		window = parent;
		window.addKeyListener(this);
	}

	/*
	 * Timer mit 1sec um zu verhindern, dass der spieler durchgehend neue Bomben
	 * legt
	 */
	javax.swing.Timer delay = new javax.swing.Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			bomb2 = false;
			delay.stop();
		}
	});

	/*
	 * Methoden zum erkennen der Tastendrücke für die Steuerung des zweiten
	 * Spielers
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode()) == (KeyEvent.VK_W))
			moveHoch = false;
		else if ((e.getKeyCode()) == (KeyEvent.VK_S))
			moveRunter = false;

		else if ((e.getKeyCode()) == (KeyEvent.VK_A))
			moveLinks = false;

		else if ((e.getKeyCode()) == (KeyEvent.VK_D))
			moveRechts = false;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// move player2 up with W
		if ((e.getKeyCode()) == (KeyEvent.VK_W)) {
			moveHoch = true;
			moveRunter = false;
			moveLinks = false;
			moveRechts = false;
		}

		// move player2 down with S
		else if ((e.getKeyCode()) == (KeyEvent.VK_S)) {
			moveHoch = false;
			moveRunter = true;
			moveLinks = false;
			moveRechts = false;
		}
		// move player2 left with A
		else if ((e.getKeyCode()) == (KeyEvent.VK_A)) {
			moveLinks = true;
			moveRechts = false;
			moveHoch = false;
			moveRunter = false;
		}
		// move player2 right with D
		else if ((e.getKeyCode()) == (KeyEvent.VK_D)) {
			moveRechts = true;
			moveLinks = false;
			moveHoch = false;
			moveRunter = false;

		}
		// set bomb with player2 with SPACE
		else if ((e.getKeyCode()) == (KeyEvent.VK_SPACE)
				&& Spielfeld.Spielfeld.nextbomb2 == true) {
			bomb2 = true;
			delay.start();
		}
		window.gamepanel.control2();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}