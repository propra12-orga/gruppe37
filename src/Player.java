import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JPanel implements KeyListener {

	public static void main(String[] args) {
		// new Player();

	}

	private static final long serialVersionUID = 1L;
	private final JFrame frame = new JFrame();
	private final ImageIcon solidBlock = new ImageIcon("HardBlock.png");
	private final ImageIcon brkbleBlock = new ImageIcon("breakstone.jpg");
	private final ImageIcon grndBlock = new ImageIcon("ground.jpg");
	private final ImageIcon player = new ImageIcon("Player.png");
	private final JPanel panel1 = new JPanel();
	private final JLabel fblock[][] = new JLabel[15][15];
	private final int blockStatus[][] = new int[15][15];
	public int m = 0, n = 0, x = 1, y = 1;

	public boolean moveRight = false;
	public boolean moveLeft = false;
	public boolean moveDown = false;
	public boolean moveUp = false;
	public boolean bomb = false;

	public void testfeld() {
		/***********************************
		 * Es folgt das obere Rahmenst�ck. *
		 ***********************************/

		for (m = 0; m < 15; m++) {
			blockStatus[m][0] = 1;
		}

		/***************************
		 * Das untere Rahmenst�ck. *
		 ***************************/

		for (m = 0; m < 15; m++) {
			blockStatus[m][14] = 1;
		}

		/***************************
		 * Das linke Rahmenst�ck. *
		 ***************************/

		for (n = 1; n < 14; n++) {
			blockStatus[0][n] = 1;
		}

		/***************************
		 * Das rechte Rahmenst�ck. *
		 ***************************/

		for (n = 1; n < 14; n++) {
			blockStatus[14][n] = 1;
		}

		/*************************************************************
		 * Innenfeld - Reihen der Spielfeld Bl�cke. *
		 *************************************************************/

		/************
		 * 1. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][1] = 2;
		}

		/************
		 * 2. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][2] = 2;
			m++;
			blockStatus[m][2] = 1;
		}

		/************
		 * 3. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][3] = 2;
		}

		/************
		 * 4. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][4] = 2;
			m++;
			blockStatus[m][4] = 1;
		}

		/************
		 * 5. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][5] = 2;
		}

		/************
		 * 6. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][6] = 2;
			m++;
			blockStatus[m][6] = 1;
		}

		/************
		 * 7. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][7] = 2;
		}

		/************
		 * 8. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][8] = 2;
			m++;
			blockStatus[m][8] = 1;
		}

		/************
		 * 9. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][9] = 2;
		}

		/************
		 * 10. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][10] = 2;
			m++;
			blockStatus[m][10] = 1;
		}

		/************
		 * 11. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][11] = 2;
		}

		/************
		 * 12. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][12] = 2;
			m++;
			blockStatus[m][12] = 1;
		}

		/************
		 * 13. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][13] = 2;
		}

		/********************
		 * Ecken freimachen *
		 ********************/

		// Oben links
		blockStatus[1][1] = 4; // <-- aktuelle spieler start posi
		blockStatus[1][2] = 0;
		blockStatus[2][1] = 0;
		// Oben rechts
		blockStatus[13][1] = 0;
		blockStatus[13][2] = 0;
		blockStatus[12][1] = 0;
		// Unten links
		blockStatus[1][13] = 0;
		blockStatus[1][12] = 0;
		blockStatus[2][13] = 0;
		// Unten rechts
		blockStatus[13][13] = 0;
		blockStatus[13][12] = 0;
		blockStatus[12][13] = 0;
	}

	public void zeichnen() {
		panel1.removeAll();
		for (m = 0; m < 15; m++) {
			for (n = 0; n < 15; n++) {
				if (blockStatus[m][n] == 0) {
					fblock[m][n] = new JLabel(grndBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == 1) {
					fblock[m][n] = new JLabel(solidBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == 2) {
					fblock[m][n] = new JLabel(brkbleBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}
				// player

				else if (blockStatus[m][n] == 4) {
					fblock[m][n] = new JLabel(player);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
			}
		}

	}

	public Player() {
		frame.getContentPane().add(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);
		panel1.setLayout(null);
		panel1.setBounds(200, 200, 450, 450);
		frame.addKeyListener(this);

		/********************
		 * Spieler setzen *
		 ********************/
		testfeld();
		zeichnen();
		blockStatus[x][y] = 4;

		/*************************************************
		 * Abfragen der Blockarten und setzen der Blöcke *
		 * ***********************************************
		 */
		testfeld();
		zeichnen();

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
		}

		// move player down with DOWN-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_DOWN)) {
			moveUp = false;
			moveDown = true;
		}
		// move player left with LEFT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_LEFT)) {
			moveLeft = true;
			moveRight = false;
		}
		// move player right with RIGHT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_RIGHT)) {
			moveRight = true;
			moveLeft = false;

		}
		// bomb with CONTROL key
		else if ((e.getKeyCode()) == (KeyEvent.VK_CONTROL)) {
			bomb = true;
		}
		if (moveRight == true && blockStatus[x + 1][y] != 1) {
			blockStatus[x][y] = 0;
			x++;
			blockStatus[x][y] = 4;
			zeichnen();
		}
		if (moveLeft == true && blockStatus[x - 1][y] != 1) {
			blockStatus[x][y] = 0;
			x--;
			blockStatus[x][y] = 4;
			zeichnen();
		}
		if (moveUp == true && blockStatus[x][y - 1] != 1) {
			blockStatus[x][y] = 0;
			y--;
			blockStatus[x][y] = 4;
			zeichnen();
		}
		if (moveDown == true && blockStatus[x][y + 1] != 1) {
			blockStatus[x][y] = 0;
			y++;
			blockStatus[x][y] = 4;
			zeichnen();
		}
	}

}