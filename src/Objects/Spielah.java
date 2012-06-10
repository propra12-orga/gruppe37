package Objects;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import TestKrempel.Player;


//Spieler
		if (moveDown) {
			int nextStatus = window.gamepanel.getBlockStatus(x, y + 1);
			int currentBlock = getBlockStatus(x, y);

			window.gamepanel.setBlockStatus(x, y, spieler_bombe);
		}


public class Spielah extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JFrame frame = new JFrame();
	private final ImageIcon solidBlock = new ImageIcon("HardBlock.png");
	private final JPanel panel1 = new JPanel();
	private final JLabel bild1 = new JLabel(solidBlock);
	private final int a[][] = new int[15][15];
	public int m = 0, n = 0;

	public Spielah() {
		frame.getContentPane().add(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		panel1.setLayout(null);
		for (m = 0; m < 15; m++) {
			a[m][0] = 1;
			if (a[m][n] == 1) {
				panel1.add(bild1);
				bild1.setBounds(m * 30, n * 30, 30, 30);
			}
		}
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
		if ((e.getKeyCode()) == (KeyEvent.VK_ESCAPE)) {
			frame.dispose();
			new Player();
		}
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
		else if ((e.getKeyCode()) == (KeyEvent.VK_CONTROL) && nextbomb == true) {
			bomb = true;
			nextbomb = false;
		}
	}

	public static void main(String[] args) {
		new Spielah();
	}
}