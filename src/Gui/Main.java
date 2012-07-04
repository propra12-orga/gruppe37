package Gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Objects.Spieler;
import Spielfeld.Karteneditor;
import Spielfeld.Spielfeld;

/** Main Klasse, die das gesamte Spiel beinhaltet */
public class Main extends JFrame {

	/** Spielfeld in dem das Spiel stattfindet */
	public Spielfeld gamepanel;

	/** Spielfeld in dem das Spiel stattfindet */
	public Karteneditor gameedit;

	/** Menüleiste des Programms */
	public Menue gamemenue;

	/** Steuerung des 1. Spielers */
	public Spieler Spieler1;

	/** Steuerung des 2. Spielers */
	public Spieler Spieler2;

	private static final long serialVersionUID = 1L;

	/** Start des Programms mit den Fenstereigenschaften */
	public Main() {

		super("Bomberman");
		/************************
		 * Fenstereinstellungen *
		 ************************/
		gamemenue = new Menue(this);
		this.setSize(500, 500);
		setJMenuBar(Menue.menubar);
		getContentPane().setLayout(null);
		Hintergrund();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		setResizable(false);
		Spieler1 = new Spieler(this, 0);
		Spieler2 = new Spieler(this, 1);

	}

	/***********************************************************
	 * Erstellen eines Neuen Spiels und einfügen der Steuerung *
	 ***********************************************************/
	public void createGame() {

		getContentPane().removeAll();
		gamepanel = new Spielfeld(this);
		gamepanel.setVisible(true);
		gamepanel.setLayout(null);
		add(gamepanel);
		gamepanel.standardfeld();
		gamepanel.zeichnen();
		requestFocusInWindow();

	}

	/** einfuegen des leveleditors */
	public void Leveleditor() {

		getContentPane().removeAll();
		gameedit = new Karteneditor(this);
		gameedit.setVisible(true);
		gameedit.setLayout(null);
		add(gameedit);
		gameedit.blankfield();
		gameedit.zeichnen();
		requestFocusInWindow();

	}

	/** Aufruf der Main Methode */
	public void Einstellungen() {
		getContentPane().removeAll();

		new Einstellungen(this);
	}

	/** implementiert den Dateibrowser zum Einlesen von XML Dateien */
	public void Dateibrowser() {
		getContentPane().removeAll();
		gamepanel = new Spielfeld(this);
		gamepanel.setVisible(true);
		gamepanel.setLayout(null);
		add(gamepanel);
		gamepanel.XMLFeld();
	}

	/** stellt Moeglichkeit bereit, ein Spiel zu speichern */
	public void Save() {
		getContentPane().removeAll();
		gamepanel = new Spielfeld(this);
		gamepanel.setVisible(true);
		gamepanel.setLayout(null);
		add(gamepanel);
		new Save(this);
	}

	public static void main(String[] args) {
		new Main();
	}

	/** Methode zur Einblendung des Startbildes **/

	public void Hintergrund() {

		/** Startbild **/
		ImageIcon hintergrundbild = new ImageIcon("Images/hintergrund.jpg");
		/** Label in dem das Bild dargestellt wird **/
		JLabel background = new JLabel(hintergrundbild);
		background.setBounds(0, 0, 500, 500);
		this.add(background);
	}
}
