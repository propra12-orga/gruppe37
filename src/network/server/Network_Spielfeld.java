package network.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gui.Main;
import Spielfeld.Sound;

/**
 * Klasse zur Erstellung des Basis-Spielfelds mit den zugewiesenen Grafiken
 * 
 * @author Gruppe37
 * @version 0.9
 * @param Main
 *            parent
 */
public class Network_Spielfeld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variablen der verschiedenen Stati eines Spielfeldblocks und der Grafiken
	 * bei Spielende
	 */
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

	/** horizontale Feldgröße */
	private int Feldgroesse_x = 1000;

	/** vertikale Feldgröße */
	private int Feldgroesse_y = 1000;

	/** Dichte der zerstörbaren Blöcke */
	private double Blockdichte = 0.7;

	/** Array in dem das Feld erstellt wird */
	private final JLabel fblock[][] = new JLabel[1000][1000];

	/** Definition der einzelnen Spielfeldelemente */
	private final int blockStatus[][] = new int[1000][1000];

	/** horizontale Koordinate des Spielfelds */
	private int m = 0;
	/** vertikale Koordinate des Spielfelds */
	private int n = 0;
	/** horizontale Koordinate von Spieler 1 */
	private final int x[] = { 0, 1 };
	/** vertikale Koordinate von Spieler 1 */
	private final int y[] = { 0, 1 };
	/** horizontale Koordinate von Spieler 1 */
	private final int x2[] = { 1, 1 };
	/** vertikale Koordinate von Spieler 1 */
	private final int y2[] = { 1, 1 };
	private int bombsLeftP1 = 1;
	private int bombsLeftP2 = 1;
	/** horizontale Koordinate der Bombe von Spieler 1 */
	private final int a[] = new int[2];
	/** vertikale Koo rdinate der Bombe von Spieler 1 */
	private final int b[] = new int[2];

	// Radien der beiden bomben
	/** Radius der Bombe von Spieler 1 */
	private final int radius1 = 2;
	/** Radius der Bombe von Spieler 2 */
	private final int radius2 = 2;
	/** Radius von Spieler 1 und 2 für die Explosion */
	private final int radius[] = new int[2];

	/** freies Bodenfeld */
	private final int ground = 13;
	/** unzerstörbarer Block */
	private final int solid = 1;
	/** zerstörbarer Block */
	private final int breakblock = 2;
	/** Bombe auf einem freien Feld */
	private final int bombesetzen = 3;
	/** Spieler 1 auf seiner Bombe */
	private final int spieler_bombe[] = { 4, 12 };
	/** Mittelpunkt der Explosion */
	private final int explosion_mitte = 5;
	/** horizontale Komponente der Explosion */
	private final int explosion_horizontal = 6;
	/** vertikale Komponente der Explosion */
	private final int explosion_vertikal = 7;
	/** Spielfigur von Spieler 1 */
	private final int spieler[] = { 8, 11 };
	/** Ausgang unter einem zerstörbaren Block */
	private final int versteckterausgang = 9;
	/** offengelegter Ausgang */
	private final int ausgang = 10;

	/*
	 * Fähigkeit Bomben zu legen wird ermöglicht
	 */
	/** Fähigkeit des 1. Spielers eine Bombe zu legen */
	public static boolean nextbomb[] = { true, true };

	/**
	 * Festlegung, dass die Spielfiguren am Leben sind
	 */

	boolean playeralive[] = { true, true };

	/** Gibt an, ob ein Portal vorhanden ist */
	private static boolean Portal_vorhanden = false;

	private final Main window;

	/** Spielfeld wird im Fenster Main angezeigt */
	public Network_Spielfeld(Main parent) {

		loadContentImages();

		// Fenstereinstellungen
		window = parent;
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
		player1wins = new ImageIcon("Images/Player1Wins.jpg");
		player2wins = new ImageIcon("Images/Player2Wins.jpg");
		bothplayerdead = new ImageIcon("Images/BothPlayerDead.jpg");
	}

	/*
	 * Standardspielfeld mit variabler Groesse und zufälligem aufbau aus
	 * zerstörbare und freien Blöcken in Array schreiben
	 */

	public void standardfeld() {

		/** horizontale Feldgröße */
		Feldgroesse_x = Gui.Einstellungen.breite;
		/** vertikale Feldgröße */
		Feldgroesse_y = Gui.Einstellungen.hoehe;

		/** Dichte der zerstörbaren Blöcke */
		Blockdichte = (Gui.Einstellungen.dichte * 0.01);

		window.setSize(((Feldgroesse_x * 30) + 10), ((Feldgroesse_y * 30) + 50));
		window.netpanel.setBounds(0, 0, Feldgroesse_x * 30, Feldgroesse_y * 30);
		for (n = 0; n < Feldgroesse_y; n++) {
			for (m = 0; m < Feldgroesse_x; m++) {
				if (m % 2 != 1 && n % 2 != 1 || m == 0 || n == 0
						|| n == Feldgroesse_y - 1 || m == Feldgroesse_x - 1) {
					blockStatus[m][n] = solid;
				}

				else {
					if ((1 - Math.random()) >= Blockdichte) {
						blockStatus[m][n] = ground;
					} else {
						blockStatus[m][n] = breakblock;
					}
				}

			}
		}

		/*******************************************************
		 * Sicherstellung, dass die Startpositionen frei sind. *
		 *******************************************************/

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

		// Spieler setzen und Positions-Reset bei Neustart
		/** horizontale Position von Spieler 1 */
		x[0] = 1;
		/** vertikale Position von Spieler 1 */
		y[0] = 1;
		/** horizontale Position von Spieler 2 */
		x[1] = (Feldgroesse_x - 2);
		/** vertikale Position von Spieler 2 */
		y[1] = (Feldgroesse_y - 2);
		blockStatus[x[0]][y[0]] = spieler[0];
		blockStatus[x[1]][y[1]] = spieler[1];
		playeralive[0] = true;
		playeralive[1] = true;
		radius[0] = radius1;
		radius[1] = radius2;
		Portal_vorhanden = false;
	}

	/***********************************************************************
	 * Auslesen der Blockstati und zuordnung der passenden Icons zu diesen *
	 ***********************************************************************/
	public void zeichnen() {
		window.netpanel.removeAll();
		for (m = 0; m < Feldgroesse_x; m++) {
			for (n = 0; n < Feldgroesse_y; n++) {
				if (blockStatus[m][n] == ground) {
					fblock[m][n] = new JLabel(grndBlock);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == solid) {
					fblock[m][n] = new JLabel(solidBlock);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == breakblock) {
					fblock[m][n] = new JLabel(brkbleBlock);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}
				/*
				 * Bombe und Explosion
				 */
				else if (blockStatus[m][n] == bombesetzen) {
					fblock[m][n] = new JLabel(bombe);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler_bombe[0]) {
					fblock[m][n] = new JLabel(playeronbomb);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler_bombe[1]) {
					fblock[m][n] = new JLabel(player2onbomb);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_mitte) {
					fblock[m][n] = new JLabel(exp_m);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_horizontal) {
					fblock[m][n] = new JLabel(exp_h);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_vertikal) {
					fblock[m][n] = new JLabel(exp_v);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				/*
				 * Spieler
				 */
				else if (blockStatus[m][n] == spieler[0]) {
					fblock[m][n] = new JLabel(player);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				/*
				 * Spieler2
				 */
				else if (blockStatus[m][n] == spieler[1]) {
					fblock[m][n] = new JLabel(player2);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}

				/*
				 * Ausgang
				 */
				else if (blockStatus[m][n] == ausgang) {
					fblock[m][n] = new JLabel(portal);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				} else if (blockStatus[m][n] == versteckterausgang) {
					fblock[m][n] = new JLabel(brkbleBlock);
					window.netpanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}
			}
		}
	}

	/*****************
	 * Bombe *
	 *****************/
	public void explozeichnen(int playerNR) {

		int k = a[playerNR];
		int l = b[playerNR];

		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (blockStatus[k][l] == spieler_bombe[playerNR]) {
				playeralive[playerNR] = false;
			}
			blockStatus[k][l] = explosion_mitte;
			blockStatus[0][0] = solid;
			if (k + z1 < Feldgroesse_x) {
				if (blockStatus[k + z1][l] == solid) {
					break;
				} else {
					if (blockStatus[k + z1][l] == spieler[0]) {
						playeralive[0] = false;
					}
					if (blockStatus[k + z1][l] == spieler[1]) {
						playeralive[1] = false;
					}
					if (blockStatus[k + z1][l] == spieler_bombe[0]) {
						explozeichnen(0);

					}
					if (blockStatus[k + z1][l] == spieler_bombe[1]) {
						explozeichnen(1);

					}
					if (blockStatus[k + z1][l] == bombesetzen) {
						if (blockStatus[k + z1][l] == blockStatus[x2[0]][y2[0]]) {
							explozeichnen(0);
						}
						if (blockStatus[k + z1][l] == blockStatus[x2[1]][y2[1]]) {
							explozeichnen(1);
						}

					}
				}
			}
		}
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (k - z1 > 0) {
				if (blockStatus[k - z1][l] == solid) {
					break;
				} else {
					if (blockStatus[k - z1][l] == spieler[0]) {
						playeralive[0] = false;
					}
					if (blockStatus[k - z1][l] == spieler[1]) {
						playeralive[1] = false;
					}
					if (blockStatus[k - z1][l] == spieler_bombe[0]) {
						explozeichnen(0);

					}
					if (blockStatus[k - z1][l] == spieler_bombe[1]) {
						explozeichnen(1);

					}
					if (blockStatus[k - z1][l] == bombesetzen) {
						if (blockStatus[k - z1][l] == blockStatus[x2[0]][y2[0]]) {
							explozeichnen(0);
						}
						if (blockStatus[k - z1][l] == blockStatus[x2[1]][y2[1]]) {
							explozeichnen(1);
						}

					}
				}
			}
		}
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (l + z1 < Feldgroesse_y) {
				if (blockStatus[k][l + z1] == solid) {
					break;
				} else {
					if (blockStatus[k][l + z1] == spieler[0]) {
						playeralive[0] = false;
					}
					if (blockStatus[k][l + z1] == spieler[1]) {
						playeralive[1] = false;
					}
					if (blockStatus[k][l + z1] == spieler_bombe[0]) {
						explozeichnen(0);

					}
					if (blockStatus[k][l + z1] == spieler_bombe[1]) {
						explozeichnen(1);

					}
					if (blockStatus[k][l + z1] == bombesetzen) {
						if (blockStatus[k][l + z1] == blockStatus[x2[0]][y2[0]]) {
							explozeichnen(0);
						}
						if (blockStatus[k][l + z1] == blockStatus[x2[1]][y2[1]]) {
							explozeichnen(1);
						}

					}
				}
			}
		}
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (l - z1 > 0) {
				if (blockStatus[k][l - z1] == solid) {
					break;
				} else {
					if (blockStatus[k][l - z1] == spieler[0]) {
						playeralive[0] = false;
					}
					if (blockStatus[k][l - z1] == spieler[1]) {
						playeralive[1] = false;
					}
					if (blockStatus[k][l - z1] == spieler_bombe[0]) {
						explozeichnen(0);

					}
					if (blockStatus[k][l - z1] == spieler_bombe[1]) {
						explozeichnen(1);

					}
					if (blockStatus[k][l - z1] == bombesetzen) {
						if (blockStatus[k][l - z1] == blockStatus[x2[0]][y2[0]]) {
							explozeichnen(0);
						}
						if (blockStatus[k][l - z1] == blockStatus[x2[1]][y2[1]]) {
							explozeichnen(1);
						}

					}
				}
			}

		}
		if (playeralive[0] == false || playeralive[1] == false) {
			if (playeralive[0] == false && playeralive[1] == false) {
				game_over.start();
			} else {
				zufallsPortal();
			}
		}
		/**********************************************************
		 * ersetzen der break- und spieler blocks durch explosion *
		 **********************************************************/
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (k + z1 < Feldgroesse_x) {
				if (blockStatus[k + z1][l] == solid) {
					break;
				} else {
					if (blockStatus[k + z1][l] == spieler[1]
							|| blockStatus[k + z1][l] == spieler[0]
							|| blockStatus[k + z1][l] == breakblock
							|| blockStatus[k + z1][l] == ground) {
						blockStatus[k + z1][l] = explosion_horizontal;
					}

					if (blockStatus[k + z1][l] == versteckterausgang) {
						blockStatus[k + z1][l] = ausgang;
					}
				}
			}
		}
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (k - z1 > 0) {
				if (blockStatus[k - z1][l] == solid) {
					break;
				} else {
					if (blockStatus[k - z1][l] == spieler[1]
							|| blockStatus[k - z1][l] == spieler[0]
							|| blockStatus[k - z1][l] == breakblock
							|| blockStatus[k - z1][l] == ground) {
						blockStatus[k - z1][l] = explosion_horizontal;

					}
					if (blockStatus[k - z1][l] == versteckterausgang) {
						blockStatus[k - z1][l] = ausgang;
					}
				}
			}
		}
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (l - z1 > 0) {
				if (blockStatus[k][l - z1] == solid) {
					break;
				} else {
					if (blockStatus[k][l - z1] == spieler[1]
							|| blockStatus[k][l - z1] == spieler[0]
							|| blockStatus[k][l - z1] == breakblock
							|| blockStatus[k][l - z1] == ground) {
						blockStatus[k][l - z1] = explosion_vertikal;
					}

					if (blockStatus[k][l - z1] == versteckterausgang) {
						blockStatus[k][l - z1] = ausgang;
					}
				}
			}
		}
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (l + z1 < Feldgroesse_y) {
				if (blockStatus[k][l + z1] == solid) {
					break;
				} else {
					if (blockStatus[k][l + z1] == spieler[1]
							|| blockStatus[k][l + z1] == spieler[0]
							|| blockStatus[k][l + z1] == breakblock
							|| blockStatus[k][l + z1] == ground) {
						blockStatus[k][l + z1] = explosion_vertikal;
					}

					if (blockStatus[k][l + z1] == versteckterausgang) {
						blockStatus[k][l + z1] = ausgang;
					}

				}
			}
		}
		zeichnen();
		Sound.soundeffekt("Audio/boom.au");

	}

	/** beendet das Zeichnen der Explosion */
	public void exploende(int playerNR) {

		int k = a[playerNR];
		int l = b[playerNR];
		for (int z1 = 1; z1 <= radius[playerNR]; z1++) {
			if (k + z1 < Feldgroesse_x) {
				if (blockStatus[k + (z1 - 1)][l] != solid) {
					if (blockStatus[k + z1][l] == explosion_horizontal) {
						blockStatus[k + z1][l] = ground;
					}

				}
			}
			if (k - z1 > 0) {
				if (blockStatus[k - (z1 - 1)][l] != solid) {
					if (blockStatus[k - z1][l] == explosion_horizontal) {
						blockStatus[k - z1][l] = ground;
					}

				}
			}
			if (l - z1 > 0) {
				if (blockStatus[k][l - (z1 - 1)] != solid) {
					if (blockStatus[k][l - z1] == explosion_vertikal) {
						blockStatus[k][l - z1] = ground;
					}

				}
			}
			if (l + z1 < Feldgroesse_y) {
				if (blockStatus[k][l + (z1 - 1)] != solid) {
					if (blockStatus[k][l + z1] == explosion_vertikal) {
						blockStatus[k][l + z1] = ground;
					}

				}
			}
		}
		blockStatus[k][l] = ground;
		zeichnen();

		nextbomb[playerNR] = true;
		if (playerNR == 0)
			bombsLeftP1++;

		if (playerNR == 1)
			bombsLeftP2++;

	}

	/*********************************************************************
	 * Timer für eine kurze verzögerung vor neustart bei sieg/niederlage *
	 *********************************************************************/
	// Einblenden der Sieg/ Niederlage Bilder
	javax.swing.Timer game_over = new javax.swing.Timer(600,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (playeralive[0] == true && playeralive[1] == false) {
						window.setSize(320, 230);
						JLabel endscreen;
						window.netpanel.removeAll();
						endscreen = new JLabel(player1wins);
						endscreen.setBounds(0, 0, 320, 180);
						window.netpanel.add(endscreen);
						Sound.stoppen();
						Sound.soundeffekt("Audio/player1wins.au");

					}

					if (playeralive[0] == false && playeralive[1] == true) {
						window.setSize(330, 230);
						JLabel endscreen;
						window.netpanel.removeAll();
						endscreen = new JLabel(player2wins);
						endscreen.setBounds(0, 0, 320, 180);
						window.netpanel.add(endscreen);
						Sound.stoppen();
						Sound.soundeffekt("Audio/player2wins.au");
					}

					if (playeralive[0] == false && playeralive[1] == false) {
						window.setSize(330, 230);
						JLabel endscreen;
						window.netpanel.removeAll();
						endscreen = new JLabel(bothplayerdead);
						endscreen.setBounds(0, 0, 320, 180);
						window.netpanel.add(endscreen);
						Sound.stoppen();
						Sound.soundeffekt("Audio/bothplayersdead2.au");

					}
					x[0] = 1;
					y[0] = 1;
					x[1] = (Feldgroesse_x - 2);
					y[1] = (Feldgroesse_y - 2);
					blockStatus[x[0]][y[0]] = spieler[0];
					blockStatus[x[1]][y[1]] = spieler[1];
					playeralive[0] = true;
					playeralive[1] = true;
					game_over_intern.start();
					game_over.stop();

				}
			});

	// Neustart
	/** startet ein neues Spiel nach Sieg eines Spielers */
	javax.swing.Timer game_over_intern = new javax.swing.Timer(3800,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Sound.loopen();
					standardfeld();
					zeichnen();
					game_over_intern.stop();
				}
			});

	/*****************************************************************
	 * erstellt ein Portal unter einem zufälligen zerstörbaren Block *
	 *****************************************************************/
	private void zufallsPortal() {
		if (Portal_vorhanden == false) {
			Random r = new Random();
			int random_x = r.nextInt(Feldgroesse_x);
			int random_y = r.nextInt(Feldgroesse_y);

			if (blockStatus[random_x][random_y] == breakblock) {
				blockStatus[random_x][random_y] = versteckterausgang;
				Portal_vorhanden = true;
			} else if (blockStatus[random_x][random_y] == ground) {
				blockStatus[random_x][random_y] = ausgang;
				Portal_vorhanden = true;
			} else {
				zufallsPortal();
			}
		}

	}

	public void control(int playerNR) {
		/** Bewegung nach rechts */
		boolean moveRight2 = Server.moveRight2;
		/** Bewegung nach links */
		boolean moveLeft2 = Server.moveLeft2;
		/** Bewegung nach unten */
		boolean moveDown2 = Server.moveDown2;
		/** Bewegung nach oben */
		boolean moveUp2 = Server.moveUp2;
		/** Bombe legen */
		boolean bombe = Server.bombe;

		/** Bewegung nach rechts */
		boolean moveRight = Network_Spieler.moveRight;
		/** Bewegung nach links */
		boolean moveLeft = Network_Spieler.moveLeft;
		/** Bewegung nach unten */
		boolean moveDown = Network_Spieler.moveDown;
		/** Bewegung nach oben */
		boolean moveUp = Network_Spieler.moveUp;
		/** Bombe legen */
		boolean bomb = Network_Spieler.bomb;

		try {
			if (moveLeft2 == true
					&& playeralive[1] == true
					&& (blockStatus[x[1] - 1][y[1]] == ground || blockStatus[x[1] - 1][y[1]] == ausgang)) {
				if (blockStatus[x[1] - 1][y[1]] == ausgang) {
					blockStatus[x[1]][y[1]] = ground;
					game_over.start();
				} else if (blockStatus[x[1]][y[1]] == spieler_bombe[1]) {
					blockStatus[x[1]][y[1]] = bombesetzen;
				} else {
					blockStatus[x[1]][y[1]] = ground;
				}
				x[1]--;
				blockStatus[x[1]][y[1]] = spieler[1];

				zeichnen();
			}
			if (moveUp2 == true
					&& playeralive[1] == true
					&& (blockStatus[x[1]][y[1] - 1] == ground || blockStatus[x[1]][y[1] - 1] == ausgang)) {
				if (blockStatus[x[1]][y[1] - 1] == ausgang) {
					blockStatus[x[1]][y[1]] = ground;
					game_over.start();
				} else if (blockStatus[x[1]][y[1]] == spieler_bombe[1]) {
					blockStatus[x[1]][y[1]] = bombesetzen;
				} else {
					blockStatus[x[1]][y[1]] = ground;
				}
				y[1]--;
				blockStatus[x[1]][y[1]] = spieler[1];

				zeichnen();
			}

			if (moveDown2 == true
					&& playeralive[1] == true
					&& (blockStatus[x[1]][y[1] + 1] == ground || blockStatus[x[1]][y[1] + 1] == ausgang)) {

				if (blockStatus[x[1]][y[1] + 1] == ausgang) {
					blockStatus[x[1]][y[1]] = ground;
					game_over.start();
				}
				if (blockStatus[x[1]][y[1]] == spieler_bombe[1]) {
					blockStatus[x[1]][y[1]] = bombesetzen;
				} else {
					blockStatus[x[1]][y[1]] = ground;
				}
				y[1]++;
				blockStatus[x[1]][y[1]] = spieler[1];
				zeichnen();
			}
			if (moveRight2 == true
					&& playeralive[1] == true
					&& (blockStatus[x[1] + 1][y[1]] == ground || blockStatus[x[1] + 1][y[1]] == ausgang)) {
				if (blockStatus[x[1] + 1][y[1]] == ausgang) {
					blockStatus[x[1]][y[1]] = ground;
					game_over.start();
				} else if (blockStatus[x[1]][y[1]] == spieler_bombe[1]) {
					blockStatus[x[1]][y[1]] = bombesetzen;
				} else {
					blockStatus[x[1]][y[1]] = ground;
				}
				x[1]++;
				blockStatus[x[1]][y[1]] = spieler[1];
				zeichnen();
			}
			if (bombe == true && playeralive[1] == true) {
				if (bombsLeftP2 > 0) {
					blockStatus[x[1]][y[1]] = spieler_bombe[1];

					a[1] = x[1];
					b[1] = y[1];
					x2[1] = x[1];
					y2[1] = y[1];

					Network_Bomb bombe2 = new Network_Bomb(this, 1);
					zeichnen();
					bombe2.explosion.start();
					bombsLeftP2--;

				}
			}
			if (moveRight == true
					&& playeralive[0] == true
					&& (blockStatus[x[0] + 1][y[0]] == ground || blockStatus[x[0] + 1][y[0]] == ausgang)) {
				if (blockStatus[x[0] + 1][y[0]] == ausgang) {
					blockStatus[x[0]][y[0]] = ground;
					game_over.start();
				} else if (blockStatus[x[0]][y[0]] == spieler_bombe[0]) {
					blockStatus[x[0]][y[0]] = bombesetzen;
				} else {
					blockStatus[x[0]][y[0]] = ground;
				}
				x[0]++;
				blockStatus[x[0]][y[0]] = spieler[0];
				zeichnen();
			}
			if (moveLeft == true
					&& playeralive[0] == true
					&& (blockStatus[x[0] - 1][y[0]] == ground || blockStatus[x[0] - 1][y[0]] == ausgang)) {
				if (blockStatus[x[0] - 1][y[0]] == ausgang) {
					blockStatus[x[0]][y[0]] = ground;
					game_over.start();
				} else if (blockStatus[x[0]][y[0]] == spieler_bombe[0]) {
					blockStatus[x[0]][y[0]] = bombesetzen;
				} else {
					blockStatus[x[0]][y[0]] = ground;
				}
				x[0]--;
				blockStatus[x[0]][y[0]] = spieler[0];

				zeichnen();
			}
			if (moveUp == true
					&& playeralive[0] == true
					&& (blockStatus[x[0]][y[0] - 1] == ground || blockStatus[x[0]][y[0] - 1] == ausgang)) {
				if (blockStatus[x[0]][y[0] - 1] == ausgang) {
					blockStatus[x[0]][y[0]] = ground;
					game_over.start();
				} else if (blockStatus[x[0]][y[0]] == spieler_bombe[0]) {
					blockStatus[x[0]][y[0]] = bombesetzen;
				} else {
					blockStatus[x[0]][y[0]] = ground;
				}
				y[0]--;
				blockStatus[x[0]][y[0]] = spieler[0];

				zeichnen();
			}

			if (moveDown == true
					&& playeralive[0] == true
					&& (blockStatus[x[0]][y[0] + 1] == ground || blockStatus[x[0]][y[0] + 1] == ausgang)) {

				if (blockStatus[x[0]][y[0] + 1] == ausgang) {
					blockStatus[x[0]][y[0]] = ground;
					game_over.start();
				}
				if (blockStatus[x[0]][y[0]] == spieler_bombe[0]) {
					blockStatus[x[0]][y[0]] = bombesetzen;
				} else {
					blockStatus[x[0]][y[0]] = ground;
				}
				y[0]++;
				blockStatus[x[0]][y[0]] = spieler[0];
				zeichnen();
			}

			if (bomb == true && playeralive[0] == true) {

				if (bombsLeftP1 > 0) {

					blockStatus[x[0]][y[0]] = spieler_bombe[0];

					a[0] = x[0];
					b[0] = y[0];
					x2[0] = x[0];
					y2[0] = y[0];

					Network_Bomb bombe2 = new Network_Bomb(this, 0);
					zeichnen();
					bombe2.explosion.start();
					bombsLeftP1--;

				}

			}

		} catch (Exception e) {

		}
	}

	/** Getter zum Abfragen der Breite des Felds */
	public int getFeldgroesse_x() {
		return Feldgroesse_x;
	}

	/** Getter zum Abfragen der Höhe des Felds */
	public int getFeldgroesse_y() {
		return Feldgroesse_y;
	}

	/** Getter zum Abfragen der Höhe des Felds */
	public int[] getX2() {
		return x2;
	}

	/** Getter zum Abfragen der Höhe des Felds */
	public int[] getY2() {
		return y2;
	}

	/** Getter zum Abfragen der einzelnen Blockstati */
	public int[][] getBlockStatus() {
		return blockStatus;
	}

}
