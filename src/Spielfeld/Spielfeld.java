package Spielfeld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Gui.Main;
import Gui.OeffnenDialogClass;
import Objects.Steuerung;
import Objects.Steuerung2;

/**
 * Klasse zur Erstellung des Basis-Spielfelds mit den zugewiesenen Grafiken
 * 
 * @author Gruppe37
 * @version 0.9
 * @param Main
 *            parent
 */
public class Spielfeld extends JPanel {

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
	private int Feldgroesse_x = 100;

	/** vertikale Feldgröße */
	private int Feldgroesse_y = 100;

	/** Dichte der zerstörbaren Blöcke */
	private double Blockdichte = 0.7;

	/** Array in dem das Feld erstellt wird */
	private final JLabel fblock[][] = new JLabel[Feldgroesse_x][Feldgroesse_y];

	/** Definition der einzelnen Spielfeldelemente */
	private final int blockStatus[][] = new int[Feldgroesse_x][Feldgroesse_y];

	/** horizontale Koordinate des Spielfelds */
	private int m = 0;
	/** horizontale Koordinate von Spieler 1 */
	private int x = 1;
	/** vertikale Koordinate von Spieler 1 */
	private int y = 1;
	/** horizontale Koordiante von Spieler 2 */
	private int x2 = (Feldgroesse_x - 2);
	/** vertikale Koordinate von Spieler 2 */
	private int y2 = (Feldgroesse_y - 2);
	/** vertikale Koordinate des Spielfelds */
	private int n = 0;
	/** horizontale Koordinate der Bombe von Spieler 1 */
	private int a1;
	/** horizontale Koordinate der Bombe von Spieler 2 */
	private int a2;
	/** vertikale Koordinate der Bombe von Spieler 1 */
	private int b1;
	/** vertikale Koordinate der Bombe von Spieler 2 */
	private int b2;

	// Radien der beiden bomben
	/** Radius der Bombe von Spieler 1 */
	private final int radius1 = 6;
	/** Radius der Bombe von Spieler 2 */
	private final int radius2 = 6;

	/** freies Bodenfeld */
	private final int ground = 0;
	/** unzerstörbarer Block */
	private final int solid = 1;
	/** zerstörbarer Block */
	private final int breakblock = 2;
	/** Bombe auf einem freien Feld */
	private final int bombesetzen = 3;
	/** Spieler 1 auf seiner Bombe */
	private final int spieler_bombe = 4;
	/** Mittelpunkt der Explosion */
	private final int explosion_mitte = 5;
	/** horizontale Komponente der Explosion */
	private final int explosion_horizontal = 6;
	/** vertikale Komponente der Explosion */
	private final int explosion_vertikal = 7;
	/** Spielfigur von Spieler 1 */
	private final int spieler = 8;
	/** Ausgang unter einem zerstörbaren Block */
	private final int versteckterausgang = 9;
	/** offengelegter Ausgang */
	private final int ausgang = 10;
	/** Spielfigur von Spieler 2 */
	private final int spieler2 = 11;
	/** Spieler 2 auf seiner Bombe */
	private final int spieler2_bombe = 12;

	/*
	 * Fähigkeit Bomben zu legen wird ermöglicht
	 */
	/** Fähigkeit des 1. Spielers eine Bombe zu legen */
	public static boolean nextbomb1 = true;
	/** Fähigkeit des 2. Spielers eine Bombe zu legen */
	public static boolean nextbomb2 = true;

	/**
	 * Festlegung, dass die Spielfiguren am Leben sind
	 */
	/** Gibt an, ob der 1. Spieler am Leben ist */
	private static boolean player1alive = true;
	/** Gibt an, ob der 2. Spieler am Leben ist */
	private static boolean player2alive = true;

	/** Gibt an, ob ein Portal vorhanden ist */
	private static boolean Portal_vorhanden = false;

	private final Main window;

	/** Spielfeld wird im Fenster Main angezeigt */
	public Spielfeld(Main parent) {

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

	/*******************************
	 * Initialisieren der XML-Datei*
	 ******************************/

	public void XMLInit() throws SAXException, IOException,
			ParserConfigurationException, NullPointerException,
			FileNotFoundException {
		OeffnenDialogClass oeffne = new OeffnenDialogClass(null);
		File field = new File(oeffne.getLevelName());

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document dok = documentBuilder.parse(field);
		XMLReader.handleChannelTag(dok);
	}

	public void XMLFeld() {
		try {
			XMLInit();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		Feldgroesse_x = XMLReader.breite_max;
		Feldgroesse_y = XMLReader.hoehe_max;

		for (int breite = 0; breite < Feldgroesse_x; breite++) {
			for (int hoehe = 0; hoehe < Feldgroesse_y; hoehe++) {
				if (XMLReader.xmlStatus[breite][hoehe] == 0) {
					JOptionPane
							.showMessageDialog(
									null,
									"Spielfeld entspricht nicht dem vorgegeben Format. Bitte Datei überarbeiten oder anderes Spielfeld auswählen");
				}
				if (XMLReader.xmlStatus[breite][hoehe] == XMLReader.solid) {
					blockStatus[breite][hoehe] = solid;
				} else if (XMLReader.xmlStatus[breite][hoehe] == XMLReader.breakblock) {
					blockStatus[breite][hoehe] = breakblock;
				} else if (XMLReader.xmlStatus[breite][hoehe] == XMLReader.ground) {
					blockStatus[breite][hoehe] = ground;
				}
			}
		}

		/*******************************************************
		 * Sicherstellung, dass die Startpositionen frei sind. *
		 *******************************************************/
		window.setSize(((Feldgroesse_x * 30) + 10), ((Feldgroesse_y * 30) + 50));
		window.gamepanel
				.setBounds(0, 0, Feldgroesse_x * 30, Feldgroesse_y * 30);

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
		x = 1;
		/** vertikale Position von Spieler 1 */
		y = 1;
		/** horizontale Position von Spieler 2 */
		x2 = (Feldgroesse_x - 2);
		/** vertikale Position von Spieler 2 */
		y2 = (Feldgroesse_y - 2);
		blockStatus[x][y] = spieler;
		blockStatus[x2][y2] = spieler2;
		player1alive = true;
		player2alive = true;
		zeichnen();
		XMLReader.Reset();
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
		window.gamepanel
				.setBounds(0, 0, Feldgroesse_x * 30, Feldgroesse_y * 30);
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
		x = 1;
		/** vertikale Position von Spieler 1 */
		y = 1;
		/** horizontale Position von Spieler 2 */
		x2 = (Feldgroesse_x - 2);
		/** vertikale Position von Spieler 2 */
		y2 = (Feldgroesse_y - 2);
		blockStatus[x][y] = spieler;
		blockStatus[x2][y2] = spieler2;
		player1alive = true;
		player2alive = true;
		Portal_vorhanden = false;
	}

	/***********************************************************************
	 * Auslesen der Blockstati und zuordnung der passenden Icons zu diesen *
	 ***********************************************************************/
	public void zeichnen() {
		window.gamepanel.removeAll();
		for (m = 0; m < Feldgroesse_x; m++) {
			for (n = 0; n < Feldgroesse_y; n++) {
				if (blockStatus[m][n] == ground) {
					fblock[m][n] = new JLabel(grndBlock);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == solid) {
					fblock[m][n] = new JLabel(solidBlock);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == breakblock) {
					fblock[m][n] = new JLabel(brkbleBlock);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}
				/*
				 * Bombe und Explosion
				 */
				else if (blockStatus[m][n] == bombesetzen) {
					fblock[m][n] = new JLabel(bombe);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler_bombe) {
					fblock[m][n] = new JLabel(playeronbomb);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == spieler2_bombe) {
					fblock[m][n] = new JLabel(player2onbomb);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_mitte) {
					fblock[m][n] = new JLabel(exp_m);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_horizontal) {
					fblock[m][n] = new JLabel(exp_h);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				} else if (blockStatus[m][n] == explosion_vertikal) {
					fblock[m][n] = new JLabel(exp_v);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				/*
				 * Spieler
				 */
				else if (blockStatus[m][n] == spieler) {
					fblock[m][n] = new JLabel(player);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				/*
				 * Spieler2
				 */
				else if (blockStatus[m][n] == spieler2) {
					fblock[m][n] = new JLabel(player2);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}

				/*
				 * Ausgang
				 */
				else if (blockStatus[m][n] == ausgang) {
					fblock[m][n] = new JLabel(portal);
					window.gamepanel.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				} else if (blockStatus[m][n] == versteckterausgang) {
					fblock[m][n] = new JLabel(brkbleBlock);
					window.gamepanel.add(fblock[m][n]);
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
							player1alive = false;
						}
						if (a1 + z1 < Feldgroesse_x) {
							if (blockStatus[a1 + z1][b1] == solid) {
								break;
							} else {
								if (blockStatus[a1 + z1][b1] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a1 + z1][b1] == spieler2) {
									player2alive = false;
								}
							}
						}
					}
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (a1 - z1 > 0) {
							if (blockStatus[a1 - z1][b1] == solid) {
								break;
							} else {
								if (blockStatus[a1 - z1][b1] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a1 - z1][b1] == spieler2) {
									player2alive = false;
								}
							}
						}
					}
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (b1 + z1 < Feldgroesse_y) {
							if (blockStatus[a1][b1 + z1] == solid) {
								break;
							} else {
								if (blockStatus[a1][b1 + z1] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a1][b1 + z1] == spieler2) {
									player2alive = false;
								}
							}
						}
					}
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (b1 - z1 > 0) {
							if (blockStatus[a1][b1 - z1] == solid) {
								break;
							} else {
								if (blockStatus[a1][b1 - z1] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a1][b1 - z1] == spieler2) {
									player2alive = false;
								}
							}
						}

					}
					if (player1alive == false || player2alive == false) {
						if (player1alive == false && player2alive == false) {
							game_over.start();
						} else {
							zufallsPortal();
						}
					}
					/**********************************************************
					 * ersetzen der break- und spieler blocks durch explosion *
					 **********************************************************/
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (a1 + z1 < Feldgroesse_x) {
							if (blockStatus[a1 + z1][b1] == solid) {
								break;
							} else {
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
					}
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (a1 - z1 > 0) {
							if (blockStatus[a1 - z1][b1] == solid) {
								break;
							} else {
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
					}
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (b1 - z1 > 0) {
							if (blockStatus[a1][b1 - z1] == solid) {
								break;
							} else {
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
					}
					for (int z1 = 1; z1 <= radius1; z1++) {
						if (b1 + z1 < Feldgroesse_y) {
							if (blockStatus[a1][b1 + z1] == solid) {
								break;
							} else {
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
							player2alive = false;

						}
						if (a2 + z2 < Feldgroesse_x) {
							if (blockStatus[a2 + z2][b2] == solid) {
								break;
							} else {
								if (blockStatus[a2 + z2][b2] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a2 + z2][b2] == spieler2) {
									player2alive = false;
								}
							}
						}
					}
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (a2 - z2 > 0) {
							if (blockStatus[a2 - z2][b2] == solid) {
								break;
							} else {
								if (blockStatus[a2 - z2][b2] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a2 - z2][b2] == spieler2) {
									player2alive = false;
								}
							}
						}
					}
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (b2 + z2 < Feldgroesse_y) {
							if (blockStatus[a2][b2 + z2] == solid) {
								break;
							} else {
								if (blockStatus[a2][b2 + z2] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a2][b2 + z2] == spieler2) {
									player2alive = false;
								}
							}
						}
					}
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (b2 - z2 > 0) {
							if (blockStatus[a2][b2 - z2] == solid) {
								break;
							} else {
								if (blockStatus[a2][b2 - z2] == spieler) {
									player1alive = false;
								}
								if (blockStatus[a2][b2 - z2] == spieler2) {
									player2alive = false;
								}
							}
						}

					}
					if (player1alive == false || player2alive == false) {
						if (player1alive == false && player2alive == false) {
							game_over.start();
						} else {
							zufallsPortal();
						}
					}
					/**********************************************************
					 * ersetzen der break- und spieler blocks durch explosion *
					 **********************************************************/
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (a2 + z2 < Feldgroesse_x) {
							if (blockStatus[a2 + z2][b2] == solid) {
								break;
							} else {
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
					}
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (a2 - z2 > 0) {
							if (blockStatus[a2 - z2][b2] == solid) {
								break;
							} else {
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
					}
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (b2 - z2 > 0) {
							if (blockStatus[a2][b2 - z2] == solid) {
								break;
							} else {
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
					}
					for (int z2 = 1; z2 <= radius2; z2++) {
						if (b2 + z2 < Feldgroesse_y) {
							if (blockStatus[a2][b2 + z2] == solid) {
								break;
							} else {
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
						window.setSize(320, 230);
						JLabel endscreen;
						window.gamepanel.removeAll();
						endscreen = new JLabel(player1wins);
						window.gamepanel.add(endscreen);
						endscreen.setBounds(0, 0, 320, 180);

					}

					if (player2alive == true && player1alive == false) {
						window.setSize(330, 230);
						JLabel endscreen;
						window.gamepanel.removeAll();
						endscreen = new JLabel(player2wins);
						window.gamepanel.add(endscreen);
						endscreen.setBounds(0, 0, 320, 180);
					}

					if (player1alive == false && player2alive == false) {
						window.setSize(330, 230);
						JLabel endscreen;
						window.gamepanel.removeAll();
						endscreen = new JLabel(bothplayerdead);
						window.gamepanel.add(endscreen);
						endscreen.setBounds(0, 0, 320, 180);
					}
					x = 1;
					y = 1;
					x2 = (Feldgroesse_x - 2);
					y2 = (Feldgroesse_y - 2);
					blockStatus[x][y] = spieler;
					blockStatus[x2][y2] = spieler2;
					player1alive = true;
					player2alive = true;
					game_over_intern.start();
					game_over.stop();

				}
			});

	// Neustart
	javax.swing.Timer game_over_intern = new javax.swing.Timer(5000,
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

	/***********************************
	 * Methode fuer die erste Steurung *
	 **********************************/

	public void control() {
		boolean moveRight = Steuerung.moveRight; // Bewegung von Spieler 1 nach
													// rechts
		boolean moveLeft = Steuerung.moveLeft; // Bewegung von Spieler 1 nach
												// links
		boolean moveDown = Steuerung.moveDown; // Bewegung von Spieler 1 nach
												// unten
		boolean moveUp = Steuerung.moveUp; // Bewegung von Spieler 1 nach oben
		boolean bomb = Steuerung.bomb; // Bombenlegung von Spieler 1

		if (moveRight == true
				&& player1alive == true
				&& (blockStatus[x + 1][y] == ground || blockStatus[x + 1][y] == ausgang)) {
			if (blockStatus[x + 1][y] == ausgang) {
				blockStatus[x][y] = ground;
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
				blockStatus[x][y] = ground;
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
				blockStatus[x][y] = ground;
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
				blockStatus[x][y] = ground;
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
		boolean moveRechts = Steuerung2.moveRechts; // Bewegung von Spieler 2
													// nach rechts
		boolean moveLinks = Steuerung2.moveLinks; // Bewegung von SPieler 2 nach
													// links
		boolean moveRunter = Steuerung2.moveRunter; // Bewegung von Spieler 2
													// nach unten
		boolean moveHoch = Steuerung2.moveHoch; // Bewegung von Spieler 2 nach
												// oben
		boolean bomb2 = Steuerung2.bomb2; // Bombenlegung von Spieler 2

		if (moveRechts == true
				&& player2alive == true
				&& (blockStatus[x2 + 1][y2] == ground || blockStatus[x2 + 1][y2] == ausgang)) {

			if (blockStatus[x2 + 1][y2] == ausgang) {
				blockStatus[x][y] = ground;
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
				blockStatus[x][y] = ground;
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
				blockStatus[x][y] = ground;
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
				blockStatus[x][y] = ground;
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

	public void setX(int x) {
		this.x = x;
	}

	public int getX(int x) {
		return x;
	}

	public void setY(int y2) {
		this.y2 = y2;
	}

	public int getY(int y2) {
		return y2;

	}

	public int getX2(int x2) {
		return x2;
	}

	public void setX2(int y2) {
		this.y2 = y2;
	}

	public int getY2(int x2) {
		return x2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public void setBlockStatus1(int x, int y, int status) {
		blockStatus[x][y] = status;
	}

	public int getBlockStatus1(int x, int y) {
		return blockStatus[x][y];
	}

	public void setBlockStatus2(int x2, int y2, int status) {
		blockStatus[x2][y2] = status;
	}

	public int getBlockStatus2(int x2, int y2) {
		return blockStatus[x2][y2];
	}

}
