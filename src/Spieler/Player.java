package Spieler;
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
	private final ImageIcon solidBlock = new ImageIcon("Images/HardBlock.png");
	private final ImageIcon brkbleBlock = new ImageIcon("Images/breakstone.jpg");
	private final ImageIcon grndBlock = new ImageIcon("Images/ground.jpg");
	private final ImageIcon player = new ImageIcon("Images/Player.png");
	private final ImageIcon bombe = new ImageIcon("Images/bomb.jpg");
	private final ImageIcon exp_h = new ImageIcon("Images/exp_h.jpg");
	private final ImageIcon exp_v = new ImageIcon("Images/exp_v.jpg");
	private final ImageIcon exp_m = new ImageIcon("Images/exp_m.jpg");
	private final ImageIcon playeronbomb = new ImageIcon("Images/Player.png");
	private final ImageIcon portal = new ImageIcon("Images/portal.gif");
	private final JPanel panel1 = new JPanel();
	private final JLabel fblock[][] = new JLabel[15][15];
	private final int blockStatus[][] = new int[15][15];
	public int m = 0, n = 0, x = 1, y = 1, a, b;
	public int ground = 0;
	public int solid = 1;
	public int breakblock = 2;
	public int bombesetzen = 3;
	public int spieler_bombe = 4;
	public int explosion_mitte = 5;
	public int explosion_horizontal = 6;
	public int explosion_vertikal = 7;
	public int spieler = 8;
	public int ausgang = 9;
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

					if (blockStatus[a][b] == spieler_bombe) {
						game_over.start();
					}
					if (blockStatus[a + 1][b] == spieler) {
						game_over.start();
					}
					if (blockStatus[a - 1][b] == spieler) {
						game_over.start();
					}
					if (blockStatus[a][b + 1] == spieler) {
						game_over.start();
					}
					if (blockStatus[a][b - 1] == spieler) {
						game_over.start();
					}

					if (blockStatus[a + 1][b] != solid
							&& blockStatus[a + 1][b] != ausgang) {
						blockStatus[a + 1][b] = explosion_horizontal;
					}
					if (blockStatus[a - 1][b] != solid
							&& blockStatus[a - 1][b] != ausgang) {
						blockStatus[a - 1][b] = explosion_horizontal;
					}
					if (blockStatus[a][b - 1] != solid
							&& blockStatus[a][b - 1] != ausgang) {
						blockStatus[a][b - 1] = explosion_vertikal;
					}
					if (blockStatus[a][b + 1] != solid
							&& blockStatus[a][b + 1] != ausgang) {
						blockStatus[a][b + 1] = explosion_vertikal;
					}

					blockStatus[a][b] = explosion_mitte;
					zeichnen();
					explosion_ende.start();

					explosion.stop();

				}
			});

	javax.swing.Timer explosion_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (blockStatus[a + 1][b] != solid
							&& blockStatus[a + 1][b] != ausgang) {
						blockStatus[a + 1][b] = ground;
					}
					if (blockStatus[a - 1][b] != solid
							&& blockStatus[a - 1][b] != ausgang) {
						blockStatus[a - 1][b] = ground;
					}
					if (blockStatus[a][b - 1] != solid
							&& blockStatus[a][b - 1] != ausgang) {
						blockStatus[a][b - 1] = ground;
					}
					if (blockStatus[a][b + 1] != solid
							&& blockStatus[a][b + 1] != ausgang) {
						blockStatus[a][b + 1] = ground;
					}
					blockStatus[a][b] = ground;
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
			blockStatus[m][0] = solid;
		}

		/***************************
		 * Das untere Rahmenst�ck. *
		 ***************************/

		for (m = 0; m < 15; m++) {
			blockStatus[m][14] = solid;
		}

		/***************************
		 * Das linke Rahmenst�ck. *
		 ***************************/

		for (n = 1; n < 14; n++) {
			blockStatus[0][n] = solid;
		}

		/***************************
		 * Das rechte Rahmenst�ck. *
		 ***************************/

		for (n = 1; n < 14; n++) {
			blockStatus[14][n] = solid;
		}

		/*************************************************************
		 * Innenfeld - Reihen der Spielfeld Bl�cke. *
		 *************************************************************/

		/************
		 * 1. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][1] = breakblock;
		}

		/************
		 * 2. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][2] = breakblock;
			m++;
			blockStatus[m][2] = solid;
		}

		/************
		 * 3. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][3] = breakblock;
		}

		/************
		 * 4. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][4] = breakblock;
			m++;
			blockStatus[m][4] = solid;
		}

		/************
		 * 5. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][5] = breakblock;
		}

		/************
		 * 6. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][6] = breakblock;
			m++;
			blockStatus[m][6] = solid;
		}

		/************
		 * 7. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][7] = breakblock;
		}

		/************
		 * 8. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][8] = breakblock;
			m++;
			blockStatus[m][8] = solid;
		}

		/************
		 * 9. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][9] = breakblock;
		}

		/************
		 * 10. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][10] = breakblock;
			m++;
			blockStatus[m][10] = solid;
		}

		/************
		 * 11. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][11] = breakblock;
		}

		/************
		 * 12. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][12] = breakblock;
			m++;
			blockStatus[m][12] = solid;
		}

		/************
		 * 13. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][13] = breakblock;
		}

		/********************
		 * Ecken freimachen *
		 ********************/

		// Oben links
		blockStatus[1][1] = ground; // <-- aktuelle spieler start posi
		blockStatus[1][2] = ground;
		blockStatus[2][1] = ground;
		// Oben rechts
		blockStatus[13][1] = ground;
		blockStatus[13][2] = ground;
		blockStatus[12][1] = ground;
		// Unten links
		blockStatus[1][13] = ground;
		blockStatus[1][12] = ground;
		blockStatus[2][13] = ground;
		// Unten rechts
		blockStatus[13][13] = ground;
		blockStatus[13][12] = ground;
		blockStatus[12][13] = ground;
	}

	public void zeichnen() {
		panel1.removeAll();
		for (m = 0; m < 15; m++) {
			for (n = 0; n < 15; n++) {
				if (blockStatus[m][n] == ground) {
					fblock[m][n] = new JLabel(grndBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == 1) {
					fblock[m][n] = new JLabel(solidBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == breakblock) {
					fblock[m][n] = new JLabel(brkbleBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}
				/*
				 * Bombe
				 */
				else if (blockStatus[m][n] == bombesetzen) {
					fblock[m][n] = new JLabel(bombe);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler_bombe) {
					fblock[m][n] = new JLabel(playeronbomb);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_mitte) {
					fblock[m][n] = new JLabel(exp_m);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_horizontal) {
					fblock[m][n] = new JLabel(exp_h);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_vertikal) {
					fblock[m][n] = new JLabel(exp_v);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
				/*
				 * Spieler
				 */
				else if (blockStatus[m][n] == spieler) {
					fblock[m][n] = new JLabel(player);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
				/*
				 * Ausgang
				 */
				else if (blockStatus[m][n] == ausgang) {
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
		blockStatus[1][1] = spieler;
		blockStatus[7][7] = ausgang;
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
				&& (blockStatus[x + 1][y] == ground || blockStatus[x + 1][y] == ausgang)) {
			if (blockStatus[x + 1][y] == ausgang) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == spieler_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			x++;
			blockStatus[x][y] = spieler;
			zeichnen();
		}
		if (moveLeft == true
				&& (blockStatus[x - 1][y] == ground || blockStatus[x - 1][y] == ausgang)) {
			if (blockStatus[x - 1][y] == ausgang) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == spieler_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			x--;
			blockStatus[x][y] = spieler;
			zeichnen();
		}
		if (moveUp == true
				&& (blockStatus[x][y - 1] == ground || blockStatus[x][y - 1] == ausgang)) {
			if (blockStatus[x][y - 1] == ausgang) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == spieler_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			y--;
			blockStatus[x][y] = spieler;
			zeichnen();
		}
		if (moveDown == true
				&& (blockStatus[x][y + 1] == ground || blockStatus[x][y + 1] == ausgang)) {
			if (blockStatus[x][y + 1] == ausgang) {
				frame.dispose();
				new Player();
			} else if (blockStatus[x][y] == spieler_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			y++;
			blockStatus[x][y] = spieler;
			zeichnen();
		}
		if (bomb == true) {
			blockStatus[x][y] = spieler_bombe;
			a = x;
			b = y;
			zeichnen();
			bomb = false;
			explosion.start();

		}
	}

}