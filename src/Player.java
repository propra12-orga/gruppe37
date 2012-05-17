import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private final ImageIcon bombe = new ImageIcon("bomb.jpg");
	private final ImageIcon exp_h = new ImageIcon("exp_h.jpg");
	private final ImageIcon exp_v = new ImageIcon("exp_v.jpg");
	private final ImageIcon exp_m = new ImageIcon("exp_m.jpg");
	private final ImageIcon playeronbomb = new ImageIcon("Player.png");
	private final ImageIcon portal = new ImageIcon("portal.gif");
	private final JPanel panel1 = new JPanel();
	private final JLabel fblock[][] = new JLabel[15][15];
	private final int blockStatus[][] = new int[15][15];
	public int m = 0, n = 0, x = 1, y = 1, a, b;

	public boolean moveRight = false;
	public boolean moveLeft = false;
	public boolean moveDown = false;
	public boolean moveUp = false;
	public boolean bomb = false;
	public boolean nextbomb = true;

	/*
	 * Timer / Bombe
	 */
	javax.swing.Timer explosion = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (blockStatus[a][b] == 4) {
						game_over.start();
					}
					if (blockStatus[a + 1][b] == 8) {
						game_over.start();
					}
					if (blockStatus[a - 1][b] == 8) {
						game_over.start();
					}
					if (blockStatus[a][b + 1] == 8) {
						game_over.start();
					}
					if (blockStatus[a][b - 1] == 8) {
						game_over.start();
					}

					if (blockStatus[a + 1][b] != 1
							&& blockStatus[a + 1][b] != 9) {
						blockStatus[a + 1][b] = 6;
					}
					if (blockStatus[a - 1][b] != 1
							&& blockStatus[a - 1][b] != 9) {
						blockStatus[a - 1][b] = 6;
					}
					if (blockStatus[a][b - 1] != 1
							&& blockStatus[a][b - 1] != 9) {
						blockStatus[a][b - 1] = 7;
					}
					if (blockStatus[a][b + 1] != 1
							&& blockStatus[a][b + 1] != 9) {
						blockStatus[a][b + 1] = 7;
					}

					blockStatus[a][b] = 5;
					zeichnen();
					explosion_ende.start();

					explosion.stop();

				}
			});

	javax.swing.Timer explosion_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (blockStatus[a + 1][b] != 1
							&& blockStatus[a + 1][b] != 9) {
						blockStatus[a + 1][b] = 0;
					}
					if (blockStatus[a - 1][b] != 1
							&& blockStatus[a - 1][b] != 9) {
						blockStatus[a - 1][b] = 0;
					}
					if (blockStatus[a][b - 1] != 1
							&& blockStatus[a][b - 1] != 9) {
						blockStatus[a][b - 1] = 0;
					}
					if (blockStatus[a][b + 1] != 1
							&& blockStatus[a][b + 1] != 9) {
						blockStatus[a][b + 1] = 0;
					}
					blockStatus[a][b] = 0;
					zeichnen();

					explosion_ende.stop();
					nextbomb = true;

				}
			});

	/*
	 * Timer neues Spiel
	 */
	javax.swing.Timer game_over = new javax.swing.Timer(600,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					new Player();
					game_over.stop();
				}
			});

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
		blockStatus[1][1] = 0; // <-- aktuelle spieler start posi
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
				/*
				 * Bombe
				 */
				else if (blockStatus[m][n] == 3) {
					fblock[m][n] = new JLabel(bombe);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == 4) {
					fblock[m][n] = new JLabel(playeronbomb);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == 5) {
					fblock[m][n] = new JLabel(exp_m);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == 6) {
					fblock[m][n] = new JLabel(exp_h);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == 7) {
					fblock[m][n] = new JLabel(exp_v);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
				/*
				 * Spieler
				 */
				else if (blockStatus[m][n] == 8) {
					fblock[m][n] = new JLabel(player);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
				/*
				 * Ausgang
				 */
				else if (blockStatus[m][n] == 9) {
					fblock[m][n] = new JLabel(portal);
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
		frame.setResizable(false);

		/********************
		 * Spieler setzen *
		 ********************/
		testfeld();
		blockStatus[1][1] = 8;
		blockStatus[7][7] = 9;
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
		else if ((e.getKeyCode()) == (KeyEvent.VK_CONTROL) && nextbomb == true) {
			bomb = true;
			nextbomb = false;
		}
		if (moveRight == true
				&& (blockStatus[x + 1][y] == 0 || blockStatus[x + 1][y] == 9)) {
			if (blockStatus[x + 1][y] == 9) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == 4) {
				blockStatus[x][y] = 3;
			} else {
				blockStatus[x][y] = 0;
			}
			x++;
			blockStatus[x][y] = 8;
			zeichnen();
		}
		if (moveLeft == true
				&& (blockStatus[x - 1][y] == 0 || blockStatus[x - 1][y] == 9)) {
			if (blockStatus[x - 1][y] == 9) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == 4) {
				blockStatus[x][y] = 3;
			} else {
				blockStatus[x][y] = 0;
			}
			x--;
			blockStatus[x][y] = 8;
			zeichnen();
		}
		if (moveUp == true
				&& (blockStatus[x][y - 1] == 0 || blockStatus[x][y - 1] == 9)) {
			if (blockStatus[x][y - 1] == 9) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == 4) {
				blockStatus[x][y] = 3;
			} else {
				blockStatus[x][y] = 0;
			}
			y--;
			blockStatus[x][y] = 8;
			zeichnen();
		}
		if (moveDown == true
				&& (blockStatus[x][y + 1] == 0 || blockStatus[x][y + 1] == 9)) {
			if (blockStatus[x][y + 1] == 9) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == 4) {
				blockStatus[x][y] = 3;
			} else {
				blockStatus[x][y] = 0;
			}
			y++;
			blockStatus[x][y] = 8;
			zeichnen();
		}
		if (bomb == true) {
			blockStatus[x][y] = 4;
			a = x;
			b = y;
			zeichnen();
			bomb = false;
			explosion.start();

		}
	}

}