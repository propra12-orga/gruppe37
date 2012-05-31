package Objects;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import Spielfeld.Spielfeld;

public class Spieler implements KeyListener {

	private static final long serialVersionUID = 1L;
	private final JFrame frame = new JFrame();
	public boolean moveRight = false;
	public boolean moveLeft = false;
	public boolean moveDown = false;
	public boolean moveUp = false;
	public boolean bomb = false;
	public boolean nextbomb = true;

	public Spieler() {
		frame.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

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
		if ((e.getKeyCode()) == (KeyEvent.VK_ESCAPE))
			System.exit(0);

		else if ((e.getKeyCode()) == (KeyEvent.VK_UP)) {
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
		else if ((e.getKeyCode()) == (KeyEvent.VK_CONTROL) && nextbomb == true) {
			bomb = true;
			nextbomb = false;
		}
		if (moveRight == true
				&& (blockstatus[x + 1][y] == ground || blockstatus[x + 1][y] == ausgang)) {
			if (blockstatus[x + 1][y] == ausgang) {
				frame.dispose();
				// new Player();
			} else if (blockstatus[x][y] == spieler_bombe) {
				blockstatus[x][y] = bombesetzen;
			} else {
				blockstatus[x][y] = ground;
			}
			x++;
			blockstatus[x][y] = spieler;
			Spielfeld.zeichnen();
		}
		if (moveLeft == true
				&& (blockstatus[x - 1][y] == ground || blockstatus[x - 1][y] == ausgang)) {
			if (blockstatus[x - 1][y] == ausgang) {
				frame.dispose();
				// new Player();
			} else if (blockstatus[x][y] == spieler_bombe) {
				blockstatus[x][y] = bombesetzen;
			} else {
				blockstatus[x][y] = ground;
			}
			x--;
			blockstatus[x][y] = spieler;

			Spielfeld.zeichnen();
		}
		if (moveUp == true
				&& (blockstatus[x][y - 1] == ground || blockstatus[x][y - 1] == ausgang)) {
			if (blockstatus[x][y - 1] == ausgang) {
				frame.dispose();
				// new Player();
			} else if (blockstatus[x][y] == spieler_bombe) {
				blockstatus[x][y] = bombesetzen;
			} else {
				blockstatus[x][y] = ground;
			}
			y--;
			blockstatus[x][y] = spieler;
			Spielfeld.zeichnen();
		}
		if (moveDown == true
				&& (blockstatus[x][y + 1] == ground || blockstatus[x][y + 1] == ausgang)) {
			if (blockstatus[x][y + 1] == ausgang) {
				frame.dispose();
				// new Player();
			} else if (blockstatus[x][y] == spieler_bombe) {
				blockstatus[x][y] = bombesetzen;
			} else {
				blockstatus[x][y] = ground;
			}
			y++;
			blockstatus[x][y] = spieler;
			Spielfeld.zeichnen();
		}
		if (bomb == true) {
			blockstatus[x][y] = spieler_bombe;
			a = x;
			b = y;
			Spielfeld.zeichnen();
			bomb = false;
			// explosion.start();

		}
	}

}