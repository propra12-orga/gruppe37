package Spielfeld;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Gui.Main;

/**
 * Klasse zur Erstellung des Basis-Spielfelds mit den zugewiesenen Grafiken
 * 
 * @author Gruppe37
 * @version 0.9
 * @param Main
 *            parent
 */
public class Karteneditor extends JPanel implements ChangeListener,
		MouseListener {

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
	private ImageIcon portal;
	private ImageIcon blank_field;

	/** Panel in dem das Spiel dargestellt wird */
	public JPanel panel1 = new JPanel();

	/** horizontale Koordinate des Spielfelds */
	private int m = 0;
	/** vertikale Koordinate des Spielfelds */
	private int n = 0;
	private final int blank = 0;
	private final int solid = 1;
	private final int breakblock = 2;
	private final int ground = 3;
	private final int spieler = 8;
	private final int versteckterausgang = 9;
	private final int ausgang = 10;
	private final int spieler2 = 11;

	static final int hoehe_MIN = 5;
	static final int hoehe_MAX = 30;
	static final int hoehe_INIT = 11;

	static final int breite_MIN = 5;
	static final int breite_MAX = 30;
	static final int breite_INIT = 11;

	public static int breite = breite_INIT;
	public static int hoehe = hoehe_INIT;

	private int Feldgroesse_x = breite;
	private int Feldgroesse_y = hoehe;

	/** Array in dem das Feld erstellt wird */
	private final JLabel fblock[][] = new JLabel[breite_MAX][hoehe_MAX];
	/** Definition der einzelnen Spielfeldelemente */
	private final int blockStatus[][] = new int[breite_MAX][hoehe_MAX];

	private final JSlider hoehe_s = new JSlider(hoehe_MIN, hoehe_MAX,
			hoehe_INIT);
	private final JSlider breite_s = new JSlider(breite_MIN, breite_MAX,
			breite_INIT);

	private final Main window;

	/** Spielfeld wird im Fenster Main angezeigt */
	public Karteneditor(Main parent) {

		window = parent;
		window.getContentPane().add(panel1);
		window.setVisible(true);
		panel1.setLayout(null);
		window.setResizable(false);
		window.addMouseListener(this);

		/*******************************************
		 * Starten und erstellen eines Spielfeldes *
		 *******************************************/
		// zeichnen();
		loadContentImages();
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
		portal = new ImageIcon("Images/portal.gif");
		blank_field = new ImageIcon("Images/bg.png");
	}

	/*******************************
	 * Initialisieren der XML-Datei*
	 ******************************/
	public void XMLInit() throws SAXException, IOException,
			ParserConfigurationException {
		File field = new File("Beispielfeld.xml");
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
		}

		Feldgroesse_x = XMLReader.breite_max;
		Feldgroesse_y = XMLReader.hoehe_max;

		for (int breite = 0; breite < Feldgroesse_x; breite++) {
			for (int hoehe = 0; hoehe < Feldgroesse_y; hoehe++) {
				if (XMLReader.xmlStatus[breite][hoehe] == 0) {
					JOptionPane
							.showMessageDialog(
									null,
									"Spielfeld enthält ungewollte BlockStati. Bitte Datei überarbeiten oder anderes Spielfeld auswählen. ");
					return;
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
		panel1.setBounds(0, 0, Feldgroesse_x * 30, Feldgroesse_y * 30);
		zeichnen();
	}

	public void standardfeld() {
		Feldgroesse_x = breite;
		Feldgroesse_y = hoehe;
		window.setSize(((Feldgroesse_x * 30) + 10), ((Feldgroesse_y * 30) + 50));
		panel1.setBounds(0, 0, Feldgroesse_x * 30, Feldgroesse_y * 30);

		for (n = 0; n < Feldgroesse_y; n++) {
			for (m = 0; m < Feldgroesse_x; m++) {
				blockStatus[m][n] = blank;

			}

		}
		zeichnen();

	}

	public void promt() {
		Insets mmInsets = window.getInsets();
		Dimension sizeMmInn = null;
		window.setLayout(null);

		/*
		 * Einstellungen fuer Hoehen-Slider
		 */
		hoehe_s.setPreferredSize(new Dimension(500, 50));
		sizeMmInn = hoehe_s.getPreferredSize();
		hoehe_s.setBounds(80 + mmInsets.left, 16 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		hoehe_s.addChangeListener(this);
		hoehe_s.setPaintTicks(true);
		hoehe_s.setMajorTickSpacing(5);
		hoehe_s.setMinorTickSpacing(1);
		hoehe_s.setPaintLabels(true);

		final JLabel nameHoehe = new JLabel("Hoehe");
		nameHoehe.setBounds(10 + mmInsets.left, mmInsets.top, sizeMmInn.width,
				sizeMmInn.height);
		nameHoehe.setToolTipText("Die Hoehe des Spielfelds in Bausteinen");

		/*
		 * Einstellungen fuer Breiten-Slider
		 */
		breite_s.setPreferredSize(new Dimension(500, 50));
		sizeMmInn = breite_s.getPreferredSize();
		breite_s.setBounds(80 + mmInsets.left, 116 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		breite_s.addChangeListener(this);
		breite_s.setPaintTicks(true);
		breite_s.setMajorTickSpacing(5);
		breite_s.setMinorTickSpacing(1);
		breite_s.setPaintLabels(true);

		final JLabel nameBreite = new JLabel("Breite");
		nameBreite.setBounds(10 + mmInsets.left, 100 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		nameBreite.setToolTipText("Die Breite des Spielfelds in Bausteinen");

		/*
		 * BUTTON
		 */
		final JButton okButton = new JButton("OK");
		okButton.setBounds(50, 248, 150, 50);
		okButton.setText("Raster erzeugen");

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.getContentPane().remove(okButton);
				window.getContentPane().remove(breite_s);
				window.getContentPane().remove(hoehe_s);
				window.getContentPane().remove(nameBreite);
				window.getContentPane().remove(nameHoehe);
				standardfeld();
				// XMLFeld();
			}
		});

		/*
		 * Hinzufuegen der Slider und Namen
		 */

		window.add(okButton);
		window.add(breite_s);
		window.add(hoehe_s);
		window.add(nameBreite);
		window.add(nameHoehe);

		window.setResizable(false);
		window.setSize(new Dimension(600, 400));
		window.setVisible(true);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();

		if (!source.getValueIsAdjusting()) {
			breite = breite_s.getValue();
			hoehe = hoehe_s.getValue();
		}

	}

	JLabel jlbPicture;

	public void zeichnen() {
		panel1.removeAll();
		for (m = 0; m < Feldgroesse_x; m++) {
			for (n = 0; n < Feldgroesse_y; n++) {
				if (blockStatus[m][n] == ground) {
					fblock[m][n] = new JLabel(grndBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}

				else if (blockStatus[m][n] == solid) {
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

				} else if (blockStatus[m][n] == blank) {
					fblock[m][n] = new JLabel(blank_field);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);

				}
			}
		}
	}

	public void Dropdown() {

		/*
		 * Dropdown
		 */

		// Combobox
		String[] bloecke = { "ground", "solid", "breakable", "spieler",
				"spieler2" };
		JComboBox Blockwahl = new JComboBox(bloecke);
		Blockwahl.setSelectedIndex(0);
		Blockwahl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox Blockwahl = (JComboBox) e.getSource();
				String Status = (String) Blockwahl.getSelectedItem();
				if (Blockwahl.getSelectedItem().equals("solid")) {
					blockStatus[m][n] = solid;
				} else if (Blockwahl.getSelectedItem().equals("ground")) {
					blockStatus[m][n] = ground;
				} else if (Blockwahl.getSelectedItem().equals("breakable")) {
					blockStatus[m][n] = breakblock;
				} else if (Blockwahl.getSelectedItem().equals("spieler")) {
					blockStatus[m][n] = spieler;
				} else if (Blockwahl.getSelectedItem().equals("spieler2")) {
					blockStatus[m][n] = spieler2;
				}
				window.remove(Blockwahl);
				zeichnen();
			}

		});
		window.add(Blockwahl);
		Blockwahl.setBounds(m * 30, n * 30, 30, 30);

	}

	public void mausposi() {
		PointerInfo info = MouseInfo.getPointerInfo();
		Point location = info.getLocation();
		m = Math.round((location.x - 10) / 30);
		n = Math.round((location.y - 50) / 30);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mausposi();
		if (m < Feldgroesse_x && n < Feldgroesse_y) {
			Dropdown();
		}
	}

	@Override
	public void mousePressed(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent paramMouseEvent) {
		// TODO Auto-generated method stub

	}

	public void Button() {
		/*
		 * BUTTON
		 */
		final JButton readButton = new JButton("OK");
		readButton.setBounds(50, ((Feldgroesse_x * 30) + 10), 50, 50);
		readButton.setText("XML Laden");

		readButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				XMLFeld();
			}
		});
		/*
		 * BUTTON
		 */
		final JButton read2Button = new JButton("OK");
		read2Button.setBounds(50, ((Feldgroesse_x * 30) + 10), 50, 50);
		read2Button.setText("standard Raster erzeugen");

		read2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				standardfeld();
			}
		});
		window.add(readButton);
		window.add(read2Button);
	}
}
