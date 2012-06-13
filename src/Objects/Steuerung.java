package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gui.Main;

public class Steuerung implements KeyListener {

	public static boolean moveRight = false;
	public static boolean moveLeft = false;
	public static boolean moveDown = false;
	public static boolean moveUp = false;
	public static boolean bomb = false;

	private final Main window;

	public Steuerung(Main parent) {
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
			bomb = false;
			delay.stop();
		}
	});

	/*
	 * Methoden zum erkennen der Tastendrücke für die Steuerung des ersten
	 * Spielers
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode()) == (KeyEvent.VK_UP))
			moveUp = false;
		else if ((e.getKeyCode()) == (KeyEvent.VK_DOWN))
			moveDown = false;

		else if ((e.getKeyCode()) == (KeyEvent.VK_LEFT))
			moveLeft = false;

		else if ((e.getKeyCode()) == (KeyEvent.VK_RIGHT))
			moveRight = false;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// move player up with UP-ARROW key
		if ((e.getKeyCode()) == (KeyEvent.VK_UP)) {
			moveUp = true;
			moveDown = false;
			moveLeft = false;
			moveRight = false;
		}

		// move player down with DOWN-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_DOWN)) {
			moveUp = false;
			moveDown = true;
			moveLeft = false;
			moveRight = false;
		}
		// move player left with LEFT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_LEFT)) {
			moveLeft = true;
			moveRight = false;
			moveUp = false;
			moveDown = false;
		}
		// move player right with RIGHT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_RIGHT)) {
			moveRight = true;
			moveLeft = false;
			moveUp = false;
			moveDown = false;

		}
		// bomb with CONTROL key
		else if ((e.getKeyCode()) == (KeyEvent.VK_CONTROL)
				&& Spielfeld.Spielfeld.nextbomb1 == true) {
			bomb = true;
			delay.start();

		}
		window.gamepanel.control();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}