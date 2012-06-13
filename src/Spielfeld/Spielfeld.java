package Spielfeld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gui.Main;
import Objects.Steuerung;
import Objects.Steuerung2;

public class Spielfeld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageIcon solidBlock;
	private ImageIcon brkbleBlock;
	private ImageIcon grndBlock;
	private ImageIcon player;
	private ImageIcon player2;
	private ImageIcon bombe;
	private ImageIcon exp_h;
	private ImageIcon exp_v;
	private ImageIcon exp_m;
	private ImageIcon playeronbomb;
	private ImageIcon player2onbomb;
	private ImageIcon portal;
	private ImageIcon player1wins;
	private ImageIcon player2wins;
	private ImageIcon bothplayerdead;
	public JPanel panel1 = new JPanel();

	// Variablen zur Spielfelderstellung
	public int Feldgroesse_x = 15;
	public int Feldgroesse_y = 15;
	public double Blockdichte = 0.3;

	private final JLabel fblock[][] = new JLabel[Feldgroesse_x][Feldgroesse_y];
	public final int blockStatus[][] = new int[Feldgroesse_x][Feldgroesse_y];
	public int m = 0, n = 0, a1, a2, b1, b2, k, l, x = 1, y = 1,
			x2 = (Feldgroesse_x - 2), y2 = (Feldgroesse_y - 2);

	// Radien der beiden bomben
	public int radius1 = 6;
	public int radius2 = 6;

	// variablen für die Blockstati
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
	public static boolean nextbomb1 = true;
	public static boolean nextbomb2 = true;

	public static boolean player1alive = true;
	public static boolean player2alive = true;

	private final Main window;

	public Spielfeld(Main parent) {

		loadContentImages();

		// Fenstereinstellungen
		window = parent;
		window.getContentPane().add(panel1);
		window.setSize(((Feldgroesse_x * 30) + 10), ((Feldgroesse_y * 30) + 50));
		window.setVisible(true);
		panel1.setLayout(null);
		panel1.setBounds(0, 0, Feldgroesse_x * 30, Feldgroesse_y * 30);
		window.setResizable(false);

		/*******************************************
		 * Starten und erstellen eines Spielfeldes *
		 *******************************************/

		standardfeld();
		zufallsPortal();
		control();
		control2();
		zeichnen();
	}

	/****************************************
	 * Laden der einzelnen Icons der Blöcke *
	 ****************************************/
	private void loadContentImages() {
		solidBlock = new ImageIcon("Images/HardBlock.png");
		brkbleBlock = new ImageIcon("Images/breakstone.jpg");
		grndBlock = new ImageIcon("Images/ground.jpg");
		player = new ImageIcon("Images/Player.png");
		player2 = new ImageIcon("Images/Player2.png");
		bombe = new ImageIcon("Images/bomb.jpg");
		exp_h = new ImageIcon("Images/exp_h.jpg");
		exp_v = new ImageIcon("Images/exp_v.jpg");
		exp_m = new ImageIcon("Images/exp_m.jpg");
		playeronbomb = new ImageIcon("Images/Playeronbomb.png");
		player2onbomb = new ImageIcon("Images/Player2onbomb.png");
		portal = new ImageIcon("Images/portal.gif");
		player1wins = new ImageIcon("Images/player1wins.jpg");
		player2wins = new ImageIcon("Images/player2wins.jpg");
		bothplayerdead = new ImageIcon("Images/bothplayerdead.jpg");
	}

	/****************************************************************
	 * Standardspielfeld mit variabler Groesse und zufälligem * aufbau aus
	 * zerstörbareb und freien Blöckenin Array schreiben *
	 ****************************************************************/
	public void standardfeld() {

		for (n = 0; n < Feldgroesse_y; n++) {
			for (m = 0; m < Feldgroesse_x; m++) {
				if (m % 2 != 1 && n % 2 != 1 || m == 0 || n == 0
						|| n == Feldgroesse_y - 1 || m == Feldgroesse_x - 1) {
					blockStatus[m][n] = solid;
				}

				else {
					if (Math.random() <= Blockdichte) {
						blockStatus[m][n] = ground;
					} else {
						blockStatus[m][n] = breakblock;
					}
				}

			}
		}

		/*******************************
		 * Startpositionen frei machen *
		 *******************************/

		// Oben links
		blockStatus[1][1] = ground;
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

		// Spieler setzen und Positions-reset bei neustart
		x = 1;
		y = 1;
		x2 = (Feldgroesse_x - 2);
		y2 = (Feldgroesse_y - 2);
		blockStatus[x][y] = spieler;
		blockStatus[x2][y2] = spieler2;
		player1alive = true;
		player2alive = true;
	}

	/***********************************************************************
	 * Auslesen der Blockstati und zuordnung der passenden Icons zu diesen *
	 ***********************************************************************/
	public void zeichnen() {
		panel1.removeAll();
		for (m = 0; m < Feldgroesse_x; m++) {
			for (n = 0; n < Feldgroesse_y; n++) {
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
				 * Bombe und Explosion
				 */
				else if (blockStatus[m][n] == bombesetzen) {
					fblock[m][n] = new JLabel(bombe);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler_bombe) {
					fblock[m][n] = new JLabel(playeronbomb);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler2_bombe) {
					fblock[m][n] = new JLabel(player2onbomb);
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
				 * Spieler2
				 */
				else if (blockStatus[m][n] == spieler2) {
					fblock[m][n] = new JLabel(player2);
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
				} else if (blockStatus[m][n] == versteckterausgang) {
					fblock[m][n] = new JLabel(brkbleBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}
			}
		}
	}

	/*****************
	 * Timer / Bombe *
	 *****************/
	javax.swing.Timer explosion1 = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					explosion1_zeichnen.start();
					explosion1.stop();
				}
			});
	javax.swing.Timer explosion1_zeichnen = new javax.swing.Timer(0,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					/*******************************************************
					 * Game_over wenn spieler von der Bombe getroffen wird *
					 *******************************************************/

					for (int z1 = 1; z1 <= radius1; z1++) {
						if (blockStatus[a1][b1] == spieler_bombe) {
							game_over.start();
						}
						if (a1 + z1 < Feldgroesse_x) {
							if (blockStatus[a1 + (z1 - 1)][b1] != solid) {
								if (blockStatus[a1 + z1][b1] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a1 + z1][b1] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}
						if (a1 - z1 > 0) {
							if (blockStatus[a1 - (z1 - 1)][b1] != solid) {
								if (blockStatus[a1 - z1][b1] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a1 - z1][b1] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}
						if (b1 + z1 < Feldgroesse_y) {
							if (blockStatus[a1][b1 + (z1 - 1)] != solid) {
								if (blockStatus[a1][b1 + z1] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a1][b1 + z1] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}

						if (b1 - z1 > 0) {
							if (blockStatus[a1][b1 - (z1 - 1)] != solid) {
								if (blockStatus[a1][b1 - z1] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a1][b1 - z1] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}

					}
					/**********************************************************
					 * ersetzen der break- und spieler blocks durch explosion *
					 **********************************************************/
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (a1 + z1 < Feldgroesse_x) {
							if (blockStatus[a1 + (z1 - 1)][b1] != solid) {
								if ((blockStatus[a1 + z1][b1] == spieler2
										|| blockStatus[a1 + z1][b1] == spieler
										|| blockStatus[a1 + z1][b1] == breakblock || blockStatus[a1
										+ z1][b1] == ground)) {
									blockStatus[a1 + z1][b1] = explosion_horizontal;
								}
								if ((blockStatus[a1 + z1][b1] == bombesetzen)) {
									explosion2.stop();
									explosion2_zeichnen.start();
								}
								if ((blockStatus[a1 + z1][b1] == versteckterausgang)) {
									blockStatus[a1 + z1][b1] = ausgang;
								}
							}
						}
						if (a1 - z1 > 0) {
							if (blockStatus[a1 - (z1 - 1)][b1] != solid) {
								if ((blockStatus[a1 - z1][b1] == spieler2
										|| blockStatus[a1 - z1][b1] == spieler
										|| blockStatus[a1 - z1][b1] == breakblock || blockStatus[a1
										- z1][b1] == ground)) {
									blockStatus[a1 - z1][b1] = explosion_horizontal;
								}
								if ((blockStatus[a1 - z1][b1] == bombesetzen)) {
									explosion2.stop();
									explosion2_zeichnen.start();
								}
								if ((blockStatus[a1 - z1][b1] == versteckterausgang)) {
									blockStatus[a1 - z1][b1] = ausgang;
								}
							}
						}
						if (b1 - z1 > 0) {
							if (blockStatus[a1][b1 - (z1 - 1)] != solid) {
								if ((blockStatus[a1][b1 - z1] == spieler2
										|| blockStatus[a1][b1 - z1] == spieler
										|| blockStatus[a1][b1 - z1] == breakblock || blockStatus[a1][b1
										- z1] == ground)) {
									blockStatus[a1][b1 - z1] = explosion_vertikal;
								}
								if ((blockStatus[a1][b1 - z1] == bombesetzen)) {
									explosion2.stop();
									explosion2_zeichnen.start();
								}
								if ((blockStatus[a1][b1 - z1] == versteckterausgang)) {
									blockStatus[a1][b1 - z1] = ausgang;
								}
							}
						}
						if (b1 + z1 < Feldgroesse_y) {
							if (blockStatus[a1][b1 + (z1 - 1)] != solid) {
								if ((blockStatus[a1][b1 + z1] == spieler2
										|| blockStatus[a1][b1 + z1] == spieler
										|| blockStatus[a1][b1 + z1] == breakblock || blockStatus[a1][b1
										+ z1] == ground)) {
									blockStatus[a1][b1 + z1] = explosion_vertikal;
								}
								if ((blockStatus[a1][b1 + z1] == bombesetzen)) {
									explosion2.stop();
									explosion2_zeichnen.start();
								}
								if ((blockStatus[a1][b1 + z1] == versteckterausgang)) {
									blockStatus[a1][b1 + z1] = ausgang;
								}

							}
						}
					}
					blockStatus[a1][b1] = explosion_mitte;
					zeichnen();
					explosion1_ende.start();

					explosion1_zeichnen.stop();

				}
			});
	/**********************************************
	 * ersetzen der explosion durch ground-Blocks *
	 **********************************************/
	javax.swing.Timer explosion1_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (a1 + z1 < Feldgroesse_x) {
							if (blockStatus[a1 + (z1 - 1)][b1] != solid) {
								if ((blockStatus[a1 + z1][b1] == explosion_horizontal)) {
									blockStatus[a1 + z1][b1] = ground;
								}

							}
						}
						if (a1 - z1 > 0) {
							if (blockStatus[a1 - (z1 - 1)][b1] != solid) {
								if ((blockStatus[a1 - z1][b1] == explosion_horizontal)) {
									blockStatus[a1 - z1][b1] = ground;
								}

							}
						}
						if (b1 - z1 > 0) {
							if (blockStatus[a1][b1 - (z1 - 1)] != solid) {
								if ((blockStatus[a1][b1 - z1] == explosion_vertikal)) {
									blockStatus[a1][b1 - z1] = ground;
								}

							}
						}
						if (b1 + z1 < Feldgroesse_y) {
							if (blockStatus[a1][b1 + (z1 - 1)] != solid) {
								if ((blockStatus[a1][b1 + z1] == explosion_vertikal)) {
									blockStatus[a1][b1 + z1] = ground;
								}

							}
						}
					}
					blockStatus[a1][b1] = ground;
					zeichnen();

					explosion1_ende.stop();
					nextbomb1 = true;

				}
			});

	/*
	 * Timer / Bombe2
	 */
	javax.swing.Timer explosion2 = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					explosion2_zeichnen.start();
					explosion2.stop();
				}
			});
	javax.swing.Timer explosion2_zeichnen = new javax.swing.Timer(0000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					/********************************************************
					 * Game_over wenn spieler von der Bombe2 getroffen wird *
					 ********************************************************/

					for (int z2 = 1; z2 <= radius2; z2++) {
						if (blockStatus[a2][b2] == spieler2_bombe) {
							game_over.start();
						}
						if (a2 + z2 < Feldgroesse_x) {
							if (blockStatus[a2 + (z2 - 1)][b2] != solid) {
								if (blockStatus[a2 + z2][b2] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a2 + z2][b2] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}
						if (a2 - z2 > 0) {
							if (blockStatus[a2 - (z2 - 1)][b2] != solid) {
								if (blockStatus[a2 - z2][b2] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a2 - z2][b2] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}
						if (b2 + z2 < Feldgroesse_y) {
							if (blockStatus[a2][b2 + (z2 - 1)] != solid) {
								if (blockStatus[a2][b2 + z2] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a2][b2 + z2] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}

						if (b2 - z2 > 0) {
							if (blockStatus[a2][b2 - (z2 - 1)] != solid) {
								if (blockStatus[a2][b2 - z2] == spieler) {
									player1alive = false;
									game_over.start();
								}
								if (blockStatus[a2][b2 - z2] == spieler2) {
									player2alive = false;
									game_over.start();
								}
							}
						}

					}
					/**********************************************************
					 * ersetzen der break- und spieler blocks durch explosion *
					 **********************************************************/
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (a2 + z2 < Feldgroesse_x) {
							if (blockStatus[a2 + (z2 - 1)][b2] != solid) {
								if ((blockStatus[a2 + z2][b2] == spieler2
										|| blockStatus[a2 + z2][b2] == spieler
										|| blockStatus[a2 + z2][b2] == breakblock || blockStatus[a2
										+ z2][b2] == ground)) {
									blockStatus[a2 + z2][b2] = explosion_horizontal;
								}
								if ((blockStatus[a2 + z2][b2] == bombesetzen)) {
									explosion1.stop();
									explosion1_zeichnen.start();
								}
								if ((blockStatus[a2 + z2][b2] == versteckterausgang)) {
									blockStatus[a2 + z2][b2] = ausgang;
								}
							}
						}
						if (a2 - z2 > 0) {
							if (blockStatus[a2 - (z2 - 1)][b2] != solid) {
								if ((blockStatus[a2 - z2][b2] == spieler2
										|| blockStatus[a2 - z2][b2] == spieler
										|| blockStatus[a2 - z2][b2] == breakblock || blockStatus[a2
										- z2][b2] == ground)) {
									blockStatus[a2 - z2][b2] = explosion_horizontal;
								}
								if ((blockStatus[a2 - z2][b2] == bombesetzen)) {
									explosion1.stop();
									explosion1_zeichnen.start();
								}
								if ((blockStatus[a2 - z2][b2] == versteckterausgang)) {
									blockStatus[a2 - z2][b2] = ausgang;
								}
							}
						}
						if (b2 - z2 > 0) {
							if (blockStatus[a2][b2 - (z2 - 1)] != solid) {
								if ((blockStatus[a2][b2 - z2] == spieler2
										|| blockStatus[a2][b2 - z2] == spieler
										|| blockStatus[a2][b2 - z2] == breakblock || blockStatus[a2][b2
										- z2] == ground)) {
									blockStatus[a2][b2 - z2] = explosion_vertikal;
								}
								if ((blockStatus[a2][b2 - z2] == bombesetzen)) {
									explosion1.stop();
									explosion1_zeichnen.start();
								}
								if ((blockStatus[a2][b2 - z2] == versteckterausgang)) {
									blockStatus[a2][b2 - z2] = ausgang;
								}
							}
						}
						if (b2 + z2 < Feldgroesse_y) {
							if (blockStatus[a2][b2 + (z2 - 1)] != solid) {
								if ((blockStatus[a2][b2 + z2] == spieler2
										|| blockStatus[a2][b2 + z2] == spieler
										|| blockStatus[a2][b2 + z2] == breakblock || blockStatus[a2][b2
										+ z2] == ground)) {
									blockStatus[a2][b2 + z2] = explosion_vertikal;
								}
								if ((blockStatus[a2][b2 + z2] == bombesetzen)) {
									explosion1.stop();
									explosion1_zeichnen.start();
								}
								if ((blockStatus[a2][b2 + z2] == versteckterausgang)) {
									blockStatus[a2][b2 + z2] = ausgang;
								}

							}
						}
					}
					blockStatus[a2][b2] = explosion_mitte;
					zeichnen();
					explosion2_ende.start();

					explosion2_zeichnen.stop();

				}
			});

	/**********************************************
	 * ersetzen der explosion durch ground-Blocks *
	 **********************************************/
	javax.swing.Timer explosion2_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int z = 1; z <= radius2; z++) {
						if (a2 + z < Feldgroesse_x) {
							if (blockStatus[a2 + (z - 1)][b2] != solid) {
								if ((blockStatus[a2 + z][b2] == explosion_horizontal)) {
									blockStatus[a2 + z][b2] = ground;
								}

							}
						}
						if (a2 - z > 0) {
							if (blockStatus[a2 - (z - 1)][b2] != solid) {
								if ((blockStatus[a2 - z][b2] == explosion_horizontal)) {
									blockStatus[a2 - z][b2] = ground;
								}

							}
						}
						if (b2 - z > 0) {
							if (blockStatus[a2][b2 - (z - 1)] != solid) {
								if ((blockStatus[a2][b2 - z] == explosion_vertikal)) {
									blockStatus[a2][b2 - z] = ground;
								}

							}
						}
						if (b2 + z < Feldgroesse_y) {
							if (blockStatus[a2][b2 + (z - 1)] != solid) {
								if ((blockStatus[a2][b2 + z] == explosion_vertikal)) {
									blockStatus[a2][b2 + z] = ground;
								}

							}
						}
					}
					blockStatus[a2][b2] = ground;
					zeichnen();

					explosion2_ende.stop();
					nextbomb2 = true;

				}
			});

	/*********************************************************************
	 * Timer für eine kurze verzögerung vor neustart bei sieg/niederlage *
	 *********************************************************************/
	// Einblenden der Sieg/ Niederlage Bilder
	javax.swing.Timer game_over = new javax.swing.Timer(600,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (player1alive == true && player2alive == false) {
						// ImageIcon player1wins darstellen

					}
					if (player2alive == true && player1alive == false) {
						// ImageIcon player2wins darstellen
					}
					if (player2alive == false && player2alive == false) {
						// ImageIcon bothplayerdead darstellen
					}
					game_over_intern.start();
					game_over.stop();
				}
			});

	// Neustart
	javax.swing.Timer game_over_intern = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					standardfeld();
					zeichnen();
					game_over_intern.stop();
				}
			});

	/*****************************************************************
	 * erstellt ein Portal unter einem zufälligen zerstörbaren Block *
	 *****************************************************************/
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

	/***********************************
	 * Methode fuer die erste Steurung *
	 **********************************/

	public void control() {
		boolean moveRight = Steuerung.moveRight;
		boolean moveLeft = Steuerung.moveLeft;
		boolean moveDown = Steuerung.moveDown;
		boolean moveUp = Steuerung.moveUp;
		boolean bomb = Steuerung.bomb;

		if (moveRight == true
				&& player1alive == true
				&& (blockStatus[x + 1][y] == ground || blockStatus[x + 1][y] == ausgang)) {
			if (blockStatus[x + 1][y] == ausgang) {
				game_over.start();
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
				&& player1alive == true
				&& (blockStatus[x - 1][y] == ground || blockStatus[x - 1][y] == ausgang)) {
			if (blockStatus[x - 1][y] == ausgang) {
				game_over.start();
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
				&& player1alive == true
				&& (blockStatus[x][y - 1] == ground || blockStatus[x][y - 1] == ausgang)) {
			if (blockStatus[x][y - 1] == ausgang) {
				game_over.start();
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
				&& player1alive == true
				&& (blockStatus[x][y + 1] == ground || blockStatus[x][y + 1] == ausgang)) {

			if (blockStatus[x][y + 1] == ausgang) {
				game_over.start();
			}
			if (blockStatus[x][y] == spieler_bombe) {
				blockStatus[x][y] = bombesetzen;
			} else {
				blockStatus[x][y] = ground;
			}
			y++;
			blockStatus[x][y] = spieler;
			zeichnen();
		}

		if (bomb == true && player1alive == true && nextbomb1 == true) {
			nextbomb1 = false;
			blockStatus[x][y] = spieler_bombe;
			a1 = x;
			b1 = y;
			zeichnen();
			explosion1.start();
		}
	}

	/*************************************
	 * Methode fuer die zweite Steuerung *
	 *************************************/
	public void control2() {
		boolean moveRechts = Steuerung2.moveRechts;
		boolean moveLinks = Steuerung2.moveLinks;
		boolean moveRunter = Steuerung2.moveRunter;
		boolean moveHoch = Steuerung2.moveHoch;
		boolean bomb2 = Steuerung2.bomb2;

		if (moveRechts == true
				&& player2alive == true
				&& (blockStatus[x2 + 1][y2] == ground || blockStatus[x2 + 1][y2] == ausgang)) {

			if (blockStatus[x2 + 1][y2] == ausgang) {
				game_over.start();
			} else if (blockStatus[x2][y2] == spieler2_bombe) {
				blockStatus[x2][y2] = bombesetzen;
			} else {
				blockStatus[x2][y2] = ground;
			}
			x2++;
			blockStatus[x2][y2] = spieler2;
			zeichnen();
		}
		if (moveLinks == true
				&& player2alive == true
				&& (blockStatus[x2 - 1][y2] == ground || blockStatus[x2 - 1][y2] == ausgang)) {

			if (blockStatus[x2 - 1][y2] == ausgang) {
				game_over.start();
			} else if (blockStatus[x2][y2] == spieler2_bombe) {
				blockStatus[x2][y2] = bombesetzen;
			} else {
				blockStatus[x2][y2] = ground;
			}
			x2--;
			blockStatus[x2][y2] = spieler2;

			zeichnen();
		}
		if (moveHoch == true
				&& player2alive == true
				&& (blockStatus[x2][y2 - 1] == ground || blockStatus[x2][y2 - 1] == ausgang)) {
			if (blockStatus[x2][y2 - 1] == ausgang) {
				game_over.start();
			} else if (blockStatus[x2][y2] == spieler2_bombe) {
				blockStatus[x2][y2] = bombesetzen;
			} else {
				blockStatus[x2][y2] = ground;
			}
			y2--;
			blockStatus[x2][y2] = spieler2;
			zeichnen();
		}

		if (moveRunter == true
				&& player2alive == true
				&& (blockStatus[x2][y2 + 1] == ground || blockStatus[x2][y2 + 1] == ausgang)) {
			if (blockStatus[x2][y2 + 1] == ausgang) {
				game_over.start();
			} else if (blockStatus[x2][y2] == spieler2_bombe) {
				blockStatus[x2][y2] = bombesetzen;
			} else {
				blockStatus[x2][y2] = ground;
			}
			y2++;
			blockStatus[x2][y2] = spieler2;
			zeichnen();
		}
		if (bomb2 == true && player2alive == true && nextbomb2 == true) {
			nextbomb2 = false;
			blockStatus[x2][y2] = spieler2_bombe;
			a2 = x2;
			b2 = y2;
			zeichnen();
			explosion2.start();
		}
	}

	// Ansatz für Get/ Set Methoden zu übermittelung der aktuellen werte an die
	// Klassen die Später Bombe und Spieler darstellen
	/*
	 * public void setX(int x) { this.x = x; }
	 * 
	 * public int getX(int x) { return x; }
	 * 
	 * public void setY(int y2) { this.y2 = y2; }
	 * 
	 * public int getY(int y2) { return y2;
	 * 
	 * }
	 * 
	 * public int getX2(int x2) { return x2; }
	 * 
	 * public void setX2(int y2) { this.y2 = y2; }
	 * 
	 * public int getY2(int x2) { return x2; }
	 * 
	 * public void setY2(int y2) { this.y2 = y2; }
	 * 
	 * public void setBlockStatus1(int x, int y, int status) { blockStatus[x][y]
	 * = status; }
	 * 
	 * public int getBlockStatus1(int x, int y) { return blockStatus[x][y]; }
	 * 
	 * public void setBlockStatus2(int x2, int y2, int status) {
	 * blockStatus[x2][y2] = status; }
	 * 
	 * public int getBlockStatus2(int x2, int y2) { return blockStatus[x2][y2];
	 * }
	 */
}
