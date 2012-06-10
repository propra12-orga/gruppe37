package Spielfeld;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gui.Main;
import Objects.Steuerung;
import TestKrempel.Player;

public class Spielfeld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageIcon solidBlock;
	private ImageIcon brkbleBlock;
	private ImageIcon grndBlock;
	private ImageIcon player;
	private ImageIcon bombe;
	private ImageIcon exp_h;
	private ImageIcon exp_v;
	private ImageIcon exp_m;
	private ImageIcon playeronbomb;
	private ImageIcon portal;
	public JPanel panel1 = new JPanel();
	public int Feldgröße_x = 15;
	public int Feldgröße_y = 15;
	private final JLabel fblock[][] = new JLabel[Feldgröße_x][Feldgröße_y];
	public final int blockStatus[][] = new int[Feldgröße_x][Feldgröße_y];
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

	private final Main window;

	public Spielfeld(Main parent) {

		loadContentImages();
		window = parent;
		window.getContentPane().add(panel1);
		window.setSize(((Feldgröße_x * 30) + 10), ((Feldgröße_y * 30) + 50));
		window.setVisible(true);
		panel1.setLayout(null);
		panel1.setBounds(0, 0, Feldgröße_x * 30, Feldgröße_y * 30);
		window.setResizable(false);

		/********************
		 * Spieler setzen *
		 ********************/
		standardfeld();
		blockStatus[1][1] = spieler;
		control();
		zeichnen();
	}

	private void loadContentImages() {
		solidBlock = new ImageIcon("Images/HardBlock.png");
		brkbleBlock = new ImageIcon("Images/breakstone.jpg");
		grndBlock = new ImageIcon("Images/ground.jpg");
		player = new ImageIcon("Images/Player.png");
		bombe = new ImageIcon("Images/bomb.jpg");
		exp_h = new ImageIcon("Images/exp_h.jpg");
		exp_v = new ImageIcon("Images/exp_v.jpg");
		exp_m = new ImageIcon("Images/exp_m.jpg");
		playeronbomb = new ImageIcon("Images/Playeronbomb.png");
		portal = new ImageIcon("Images/portal.gif");
	}

	public void standardfeld() {

		/**************************************************************
		 * Standardspielfeld mit variabler Groesse in Array schreiben *
		 **************************************************************/

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

		/*******************************
		 * Startpositionen frei machen *
		 *******************************/

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
				else if (blockStatus[m][n] == ausgang) {
					fblock[m][n] = new JLabel(portal);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
				/*************************************************
				 * Bewegung der Spielfigur //abfrage der steuerung*
				 *************************************************/

			}
		}

	}

	public void control() {
		boolean moveRight = Steuerung.moveRight;
		boolean moveLeft = Steuerung.moveLeft;
		boolean moveDown = Steuerung.moveDown;
		boolean moveUp = Steuerung.moveUp;
		boolean bomb = Steuerung.bomb;
		boolean nextbomb = Steuerung.nextbomb;

		if (moveRight == true
				&& (blockStatus[x + 1][y] == ground || blockStatus[x + 1][y] == ausgang)) {
			if (blockStatus[x + 1][y] == ausgang) {
				window.dispose();
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
				window.dispose();
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
				window.dispose();
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
				window.dispose();
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
			// Bombe.explosion.start();
		}
	}

	public void setBlockStatus(int x, int y, int status) {
		blockStatus[x][y] = status;
	}

	public int getBlockStatus(int x, int y) {
		return blockStatus[x][y];
	}

}
