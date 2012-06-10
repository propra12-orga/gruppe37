package Objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gui.Main;

public class Steuerung2 implements KeyListener {

	public static boolean moveRechts = false;
	public static boolean moveLinks = false;
	public static boolean moveRunter = false;
	public static boolean moveHoch = false;
	public static boolean bomb = false;
	public static boolean nextbomb = true;

	private final Main window;

	public Steuerung2(Main parent) {
		window = parent;
		window.addKeyListener(this);
	}

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
		if ((e.getKeyCode()) == (KeyEvent.VK_ESCAPE)) {
			window.dispose();
		}
		// move player2 up with W
		if ((e.getKeyCode()) == (KeyEvent.VK_W)) {
			moveHoch = true;
			moveRunter = false;
			moveLinks = false;
			moveRechts = false;
			System.out.println("blaaaa");
		}

		// move player2 down with S
		else if ((e.getKeyCode()) == (KeyEvent.VK_S)) {
			moveHoch = false;
			moveRunter = true;
			moveLinks = false;
			moveRechts = false;
			System.out.println("blaaaa");
		}
		// move player2 left with A
		else if ((e.getKeyCode()) == (KeyEvent.VK_A)) {
			moveLinks = true;
			moveRechts = false;
			moveHoch = false;
			moveRunter = false;
			System.out.println("blaaaa");
		}
		// move player2 right with D
		else if ((e.getKeyCode()) == (KeyEvent.VK_D)) {
			moveRechts = true;
			moveLinks = false;
			moveHoch = false;
			moveRunter = false;
			System.out.println("blaaaa");

		}
		// set bomb with player2 with SPACE
		else if ((e.getKeyCode()) == (KeyEvent.VK_SPACE) && nextbomb == true) {
			bomb = true;
			nextbomb = false;
		}
		window.gamepanel.control2();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}