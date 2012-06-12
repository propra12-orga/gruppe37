package TestKrempel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player2 extends JPanel implements KeyListener {

	public static void main(String[] args) {

	}

	private static final long serialVersionUID = 1L;
	private final JFrame frame = new JFrame();
	private final ImageIcon solidBlock = new ImageIcon("Images/HardBlock.png");
	private final ImageIcon brkbleBlock = new ImageIcon("Images/breakstone.jpg");
	private final ImageIcon grndBlock = new ImageIcon("Images/ground.jpg");
	private final ImageIcon player = new ImageIcon("Images/Player.png");
	private final ImageIcon player2 = new ImageIcon("Images/Player.png");
	private final ImageIcon bombe = new ImageIcon("Images/bomb.jpg");
	private final ImageIcon exp_h = new ImageIcon("Images/exp_h.jpg");
	private final ImageIcon exp_v = new ImageIcon("Images/exp_v.jpg");
	private final ImageIcon exp_m = new ImageIcon("Images/exp_m.jpg");
	private final ImageIcon playeronbomb = new ImageIcon(
			"Images/Playeronbomb.png");
	private final ImageIcon portal = new ImageIcon("Images/portal.gif");
	private final JPanel panel1 = new JPanel();
	private final JPanel panel2 = new JPanel();
	public int Feldgroesse_x = 15;
	public int Feldgroesse_y = 15;
	private final JLabel fblock[][] = new JLabel[Feldgroesse_x][Feldgroesse_y];
	private final int blockStatus[][] = new int[Feldgroesse_x][Feldgroesse_y];
	public int m = 0, n = 0, x = 1, y = 1, a, b, k, l;
	public int radius = 10;

	public int ground = 0;
	public int solid = 1;
	public int breakblock = 2;
	public int bombesetzen = 3;
	public int spieler_bombe = 4;
	public int explosion_mitte = 5;
	public int explosion_horizontal = 6;
	public int explosion_vertikal = 7;
	public int spieler = 8;
	public int versteckterausgang = 9;
	public int ausgang = 10;
	public int spieler2 = 11;
	public int spieler2_bombe = 12;
	public boolean moveRechts = false;
	public boolean moveLinks = false;
	public boolean moveRunter = false;
	public boolean moveHoch = false;
	public boolean bomb = false;
	public boolean nextbomb = true;

	/*****************
	 * Timer / Bombe *
	 *****************/
	javax.swing.Timer explosion = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					/*******************************************************
					 * Game_over wenn spieler von der Bombe getroffen wird *
					 *******************************************************/
					for (int z = 1; z <= radius; z++) {
						if (blockStatus[a][b] == spieler2_bombe) {
							game_over.start();
						}
						if (a + z < Feldgroesse_x) {
							if (blockStatus[a + (z - 1)][b] != solid) {
								if (blockStatus[a + z][b] == spieler2) {
									game_over.start();
								}
							}
						}
						if (a - z > 0) {
							if (blockStatus[a - (z - 1)][b] != solid) {
								if (blockStatus[a - z][b] == spieler2) {
									game_over.start();
								}
							}
						}
						if (b + z < Feldgroesse_y) {
							if (blockStatus[a][b + (z - 1)] != solid) {
								if (blockStatus[a][b + z] == spieler2) {
									game_over.start();
								}
							}
						}

						if (b - z > 0) {
							if (blockStatus[a][b - (z - 1)] != solid) {
								if (blockStatus[a][b - z] == spieler2) {
									game_over.start();
								}
							}
						}

					}
					/**********************************************************
					 * ersetzen der break- und spieler blocks durch explosion *
					 **********************************************************/
					for (int z = 1; z <= radius; z++) {
						if (a + z < Feldgroesse_x) {
							if (blockStatus[a + (z - 1)][b] != solid) {
								if ((blockStatus[a + z][b] == spieler2
										|| blockStatus[a + z][b] == breakblock || blockStatus[a
										+ z][b] == ground)) {
									blockStatus[a + z][b] = explosion_horizontal;
								}
								if ((blockStatus[a + z][b] == versteckterausgang)) {
									blockStatus[a + z][b] = ausgang;
								}
							}
						}
						if (a - z > 0) {
							if (blockStatus[a - (z - 1)][b] != solid) {
								if ((blockStatus[a - z][b] == spieler2
										|| blockStatus[a - z][b] == breakblock || blockStatus[a
										- z][b] == ground)) {
									blockStatus[a - z][b] = explosion_horizontal;
								}
								if ((blockStatus[a - z][b] == versteckterausgang)) {
									blockStatus[a - z][b] = ausgang;
								}
							}
						}
						if (b - z > 0) {
							if (blockStatus[a][b - (z - 1)] != solid) {
								if ((blockStatus[a][b - z] == spieler2
										|| blockStatus[a][b - z] == breakblock || blockStatus[a][b
										- z] == ground)) {
									blockStatus[a][b - z] = explosion_vertikal;
								}
								if ((blockStatus[a][b - z] == versteckterausgang)) {
									blockStatus[a][b - z] = ausgang;
								}
							}
						}
						if (b + z < Feldgroesse_y) {
							if (blockStatus[a][b + (z - 1)] != solid) {
								if ((blockStatus[a][b + z] == spieler2
										|| blockStatus[a][b + z] == breakblock || blockStatus[a][b
										+ z] == ground)) {
									blockStatus[a][b + z] = explosion_vertikal;
								}
								if ((blockStatus[a][b + z] == versteckterausgang)) {
									blockStatus[a][b + z] = ausgang;
								}

							}
						}
					}
					blockStatus[a][b] = explosion_mitte;
					zeichnen2();
					explosion_ende.start();

					explosion.stop();

				}
			});

	javax.swing.Timer explosion_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					/****************************************************
					 * ersetzen der explosionsbl�cke durch groundblocks *
					 ****************************************************/

					for (k = 0; k < Feldgroesse_x; k++) {
						for (l = 0; l < Feldgroesse_y; l++) {
							if (blockStatus[k][l] == explosion_horizontal
									|| blockStatus[k][l] == explosion_vertikal
									|| blockStatus[k][l] == explosion_mitte) {

								blockStatus[k][l] = ground;
							}
						}
					}
					zeichnen2();

					explosion_ende.stop();
					nextbomb = true;

				}
			});

	/*********************
	 * Timer neues Spiel *
	 *********************/
	javax.swing.Timer game_over = new javax.swing.Timer(600,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					new Player2();
					game_over.stop();
				}
			});

	public void testfeld() {
		/*************************************************************
		 * Innenfeld - Reihen der Spielfeld Bloecke. *
		 *************************************************************/

		for (n = 0; n < Feldgroesse_y; n++) {
			for (m = 0; m < Feldgroesse_x; m++) {
				if (m % 2 != 1 && n % 2 != 1 || m == 0 || n == 0
						|| n == Feldgroesse_y - 1 || m == Feldgroesse_x - 1) {
					blockStatus[m][n] = solid;
				}

				else {
					blockStatus[m][n] = breakblock;
				}

			}
		}

		/********************
		 * Ecken freimachen *
		 ********************/

		// Oben links
		blockStatus[1][1] = ground; // <-- aktuelle spieler start posi
		blockStatus[1][2] = ground;
		blockStatus[2][1] = ground;
		// Oben rechts
		blockStatus[Feldgroesse_x - 2][1] = ground;
		blockStatus[Feldgroesse_x - 2][2] = ground;
		blockStatus[Feldgroesse_x - 3][1] = ground;
		// Unten links
		blockStatus[1][Feldgroesse_y - 2] = ground;
		blockStatus[1][Feldgroesse_y - 3] = ground;
		blockStatus[2][Feldgroesse_y - 2] = ground;
		// Unten rechts
		blockStatus[Feldgroesse_x - 2][Feldgroesse_y - 2] = ground;
		blockStatus[Feldgroesse_x - 2][Feldgroesse_y - 3] = ground;
		blockStatus[Feldgroesse_x - 3][Feldgroesse_y - 2] = ground;
	}

	private void zufallsPortal() {

		Random r = new Random();
		int random_x = r.nextInt(Feldgroesse_x);
		int random_y = r.nextInt(Feldgroesse_y);

		if (blockStatus[random_x][random_y] == breakblock) {
			blockStatus[random_x][random_y] = versteckterausgang;
		} else {
			zufallsPortal();
		}
	}

	public void zeichnen2() {
		panel2.removeAll();
		for (m = 0; m < Feldgroesse_x; m++) {
			for (n = 0; n < Feldgroesse_y; n++) {
				if (blockStatus[m][n] == ground) {
					fblock[m][n] = new JLabel(grndBlock);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == 1) {
					fblock[m][n] = new JLabel(solidBlock);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == breakblock) {
					fblock[m][n] = new JLabel(brkbleBlock);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}
				/*
				 * Bombe
				 */
				else if (blockStatus[m][n] == bombesetzen) {
					fblock[m][n] = new JLabel(bombe);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler2_bombe) {
					fblock[m][n] = new JLabel(playeronbomb);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_mitte) {
					fblock[m][n] = new JLabel(exp_m);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_horizontal) {
					fblock[m][n] = new JLabel(exp_h);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_vertikal) {
					fblock[m][n] = new JLabel(exp_v);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
				/*
				 * Spieler
				 */
				else if (blockStatus[m][n] == spieler2) {
					fblock[m][n] = new JLabel(player2);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
				/*
				 * Ausgang
				 */
				else if (blockStatus[m][n] == versteckterausgang) {
					fblock[m][n] = new JLabel(brkbleBlock);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == ausgang) {
					fblock[m][n] = new JLabel(portal);
					panel2.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
			}
		}

	}

	public Player2() {
		frame.getContentPane().add(panel2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize((Feldgroesse_x * 32), (Feldgroesse_y * 32));
		frame.setVisible(true);
		panel2.setLayout(null);
		panel2.setBounds(200, 200, Feldgroesse_x * 30, Feldgroesse_y * 30);
		frame.addKeyListener(this);
		frame.setResizable(false);

		/********************
		 * Spieler setzen *
		 ********************/
		testfeld();
		blockStatus[13][13] = spieler2;

		/**********************************
		 * Portal auf zuf�lligem Breakable*
		 **********************************/
		zufallsPortal();
		zeichnen2();

	}

	@Override
	public void keyTyped(KeyEvent e) {

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
			frame.dispose();
			new Player2();
		}
		if ((e.getKeyCode()) == (KeyEvent.VK_W)) {
			moveHoch = true;
			moveRunter = false;
			moveLinks = false;
			moveRechts = false;
		}

		// move player down with DOWN-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_S)) {
			moveHoch = false;
			moveRunter = true;
			moveLinks = false;
			moveRechts = false;
		}
		// move player left with LEFT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_A)) {
			moveLinks = true;
			moveRechts = false;
			moveHoch = false;
			moveRunter = false;
		}
		// move player right with RIGHT-ARROW key
		else if ((e.getKeyCode()) == (KeyEvent.VK_D)) {
			moveRechts = true;
			moveLinks = false;
			moveHoch = false;
			moveRunter = false;

		}
		// bomb with CONTROL key
		else if ((e.getKeyCode()) == (KeyEvent.VK_SPACE) && nextbomb == true) {
			bomb = true;
			nextbomb = false;
		}
		if (moveRechts == true
				&& (blockStatus[x + 1][y] == ground || blockStatus[x + 1][y] == ausgang)) {
			if (blockStatus[x + 1][y] == ausgang) {
				frame.dispose();
				new Player2();
			} else if (blockStatus[x][y] == spieler2_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			x++;
			blockStatus[x][y] = spieler2;
			zeichnen2();
		}
		if (moveLinks == true
				&& (blockStatus[x - 1][y] == ground || blockStatus[x - 1][y] == ausgang)) {
			if (blockStatus[x - 1][y] == ausgang) {
				frame.dispose();
				new Player2();
			} else if (blockStatus[x][y] == spieler2_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			x--;
			blockStatus[x][y] = spieler2;

			zeichnen2();
		}
		if (moveHoch == true
				&& (blockStatus[x][y - 1] == ground || blockStatus[x][y - 1] == ausgang)) {
			if (blockStatus[x][y - 1] == ausgang) {
				frame.dispose();
				new Player2();
			} else if (blockStatus[x][y] == spieler2_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			y--;
			blockStatus[x][y] = spieler2;

			zeichnen2();
		}
		if (moveRunter == true
				&& (blockStatus[x][y + 1] == ground || blockStatus[x][y + 1] == ausgang)) {
			if (blockStatus[x][y + 1] == ausgang) {
				frame.dispose();
				new Player2();
			} else if (blockStatus[x][y] == spieler2_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			y++;
			blockStatus[x][y] = spieler2;
			zeichnen2();
		}
		if (bomb == true) {
			blockStatus[x][y] = spieler2_bombe;
			a = x;
			b = y;
			zeichnen2();
			bomb = false;
			// explosion.start();

		}
	}

}
