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
	public int Feldgröße_x = 101;
	public int Feldgröße_y = 31;
	private final JLabel fblock[][] = new JLabel[Feldgröße_x][Feldgröße_y];
	private final int blockStatus[][] = new int[Feldgröße_x][Feldgröße_y];
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

					for (int z = 1; z <= radius; z++) {
						if (blockStatus[a][b] == spieler_bombe) {
							game_over.start();
						}
						if (a + z < Feldgröße_x) {
							if (blockStatus[a + (z - 1)][b] != solid) {
								if (blockStatus[a + z][b] == spieler) {
									game_over.start();
								}
							}
						}
						if (a - z > 0) {
							if (blockStatus[a - (z - 1)][b] != solid) {
								if (blockStatus[a - z][b] == spieler) {
									game_over.start();
								}
							}
						}
						if (b + z < Feldgröße_y) {
							if (blockStatus[a][b + (z - 1)] != solid) {
								if (blockStatus[a][b + z] == spieler) {
									game_over.start();
								}
							}
						}

						if (b - z > 0) {
							if (blockStatus[a][b - (z - 1)] != solid) {
								if (blockStatus[a][b - z] == spieler) {
									game_over.start();
								}
							}
						}

					}

					for (int z = 1; z <= radius; z++) {
						if (a + z < Feldgröße_x) {
							if (blockStatus[a + (z - 1)][b] != solid) {
								if ((blockStatus[a + z][b] == spieler
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
								if ((blockStatus[a - z][b] == spieler
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
								if ((blockStatus[a][b - z] == spieler
										|| blockStatus[a][b - z] == breakblock || blockStatus[a][b
										- z] == ground)) {
									blockStatus[a][b - z] = explosion_vertikal;
								}
								if ((blockStatus[a][b - z] == versteckterausgang)) {
									blockStatus[a][b - z] = ausgang;
								}
							}
						}
						if (b + z < Feldgröße_y) {
							if (blockStatus[a][b + (z - 1)] != solid) {
								if ((blockStatus[a][b + z] == spieler
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
					zeichnen();
					explosion_ende.start();

					explosion.stop();

				}
			});

	javax.swing.Timer explosion_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					for (k = 0; k < Feldgröße_x; k++) {
						for (l = 0; l < Feldgröße_y; l++) {
							if (blockStatus[k][l] == explosion_horizontal
									|| blockStatus[k][l] == explosion_vertikal
									|| blockStatus[k][l] == explosion_mitte) {

								blockStatus[k][l] = ground;
							}
						}
					}
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
		/*************************************************************
		 * Innenfeld - Reihen der Spielfeld Blï¿½cke. *
		 *************************************************************/

		for (n = 0; n < Feldgröße_y; n++) {
			for (m = 0; m < Feldgröße_x; m++) {
				if (m % 2 != 1 && n % 2 != 1 || m == 0 || n == 0
						|| n == Feldgröße_y - 1 || m == Feldgröße_x - 1) {
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
		blockStatus[Feldgröße_x - 2][1] = ground;
		blockStatus[Feldgröße_x - 2][2] = ground;
		blockStatus[Feldgröße_x - 3][1] = ground;
		// Unten links
		blockStatus[1][Feldgröße_y - 2] = ground;
		blockStatus[1][Feldgröße_y - 3] = ground;
		blockStatus[2][Feldgröße_y - 2] = ground;
		// Unten rechts
		blockStatus[Feldgröße_x - 2][Feldgröße_y - 2] = ground;
		blockStatus[Feldgröße_x - 2][Feldgröße_y - 3] = ground;
		blockStatus[Feldgröße_x - 3][Feldgröße_y - 2] = ground;
	}

	private void zufallsPortal() {

		Random r = new Random();
		int random_x = r.nextInt(Feldgröße_x);
		int random_y = r.nextInt(Feldgröße_y);

		if (blockStatus[random_x][random_y] == breakblock) {
			blockStatus[random_x][random_y] = versteckterausgang;
		} else {
			zufallsPortal();
		}
	}

	public void zeichnen() {
		panel1.removeAll();
		for (m = 0; m < Feldgröße_x; m++) {
			for (n = 0; n < Feldgröße_y; n++) {
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
				else if (blockStatus[m][n] == versteckterausgang) {
					fblock[m][n] = new JLabel(brkbleBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize((Feldgröße_x * 32), (Feldgröße_y * 32));
		frame.setVisible(true);
		panel1.setLayout(null);
		panel1.setBounds(200, 200, Feldgröße_x * 30, Feldgröße_y * 30);
		frame.addKeyListener(this);
		frame.setResizable(false);

		/********************
		 * Spieler setzen *
		 ********************/
		testfeld();
		blockStatus[1][1] = spieler;

		/**********************************
		 * Portal auf zufälligem Breakable*
		 **********************************/
		zufallsPortal();
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
