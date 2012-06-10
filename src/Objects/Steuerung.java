package Objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gui.Main;

public class Steuerung implements KeyListener {

	public static boolean moveRight = false;
	public static boolean moveLeft = false;
	public static boolean moveDown = false;
	public static boolean moveUp = false;
	public static boolean bomb = false;
	public static boolean nextbomb = true;

	private final Main window;

	public Steuerung(Main parent) {
		window = parent;
		window.addKeyListener(this);
	}

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
		if ((e.getKeyCode()) == (KeyEvent.VK_ESCAPE)) {
			window.dispose();
		}
		if ((e.getKeyCode()) == (KeyEvent.VK_UP)) {
			moveUp = true;
			moveDown = false;
			moveLeft = false;
			moveRight = false;
			System.out.println("blaaaa");
		}

		// move player down with DOWN-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_DOWN)) {
			moveUp = false;
			moveDown = true;
			moveLeft = false;
			moveRight = false;
			System.out.println("blaaaa");
		}
		// move player left with LEFT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_LEFT)) {
			moveLeft = true;
			moveRight = false;
			moveUp = false;
			moveDown = false;
			System.out.println("blaaaa");
		}
		// move player right with RIGHT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_RIGHT)) {
			moveRight = true;
			moveLeft = false;
			moveUp = false;
			moveDown = false;
			System.out.println("blaaaa");

		}
		// bomb with CONTROL key
		else if ((e.getKeyCode()) == (KeyEvent.VK_CONTROL) && nextbomb == true) {
			bomb = true;
			nextbomb = false;
		}
		window.gamepanel.control();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}