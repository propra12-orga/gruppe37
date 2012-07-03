package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Gui.Main;
import Spielfeld.Spielfeld;

public class Spieler implements KeyListener {

	public int[][] controlSets = {
			{ KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D,
					KeyEvent.VK_SPACE },
			{ KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,
					KeyEvent.VK_RIGHT, KeyEvent.VK_CONTROL } };

	public final int playerNR;

	/**
         *
         */
	/**
	 * Klasse für die Steuerung der Spielfigur mit Übergabe der Tastendrücke
	 * 
	 * @author Gruppe 37
	 * @version 1
	 * 
	 */
	/** Bewegung nach rechts */
	public static boolean moveRight = false;
	/** Bewegung nach links */
	public static boolean moveLeft = false;
	/** Bewegung nach unten */
	public static boolean moveDown = false;
	/** Bewegung nach oben */
	public static boolean moveUp = false;
	/** Bombe legen */
	public static boolean bomb = false;

	private final Main window;

	/** Steuerungseingabe wird in Mainpanel implementiert */
	public Spieler(Main parent, int controlType) {
		playerNR = controlType;
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
	/** Loslassen einer Taste */
	public void keyReleased(KeyEvent e) {
		if ((e.getKeyCode()) == (controlSets[playerNR][0]))
			moveUp = false;
		else if ((e.getKeyCode()) == (controlSets[playerNR][1]))
			moveDown = false;

		else if ((e.getKeyCode()) == (controlSets[playerNR][2]))
			moveLeft = false;

		else if ((e.getKeyCode()) == (controlSets[playerNR][3]))
			moveRight = false;

	}

	@Override
	/** Drücken (und Halten) einer Taste */
	public void keyPressed(KeyEvent e) {
		// move player up with UP-ARROW key
		if ((e.getKeyCode()) == (controlSets[playerNR][0])) {
			moveUp = true;
			moveDown = false;
			moveLeft = false;
			moveRight = false;
		}

		// move player down with DOWN-ARROW key
		else if ((e.getKeyCode()) == (controlSets[playerNR][1])) {
			moveUp = false;
			moveDown = true;
			moveLeft = false;
			moveRight = false;
		}
		// move player left with LEFT-ARROW key
		else if ((e.getKeyCode()) == (controlSets[playerNR][2])) {
			moveLeft = true;
			moveRight = false;
			moveUp = false;
			moveDown = false;
		}
		// move player right with RIGHT-ARROW key
		else if ((e.getKeyCode()) == (controlSets[playerNR][3])) {
			moveRight = true;
			moveLeft = false;
			moveUp = false;
			moveDown = false;

		}
		// bomb with CONTROL key
		else if ((e.getKeyCode()) == (controlSets[playerNR][4])
				&& Spielfeld.nextbomb[playerNR] == true) {
			bomb = true;
			delay.start();

		}
		window.gamepanel.control(playerNR);
		moveUp = false;
		moveDown = false;
		moveLeft = false;
		moveRight = false;
		bomb = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}