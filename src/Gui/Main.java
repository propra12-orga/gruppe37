package Gui;

import javax.swing.JFrame;

import Objects.Steuerung;
import Objects.Steuerung2;
import Spielfeld.Spielfeld;

/** Main Klasse, die das gesamte Spiel beinhaltet */
public class Main extends JFrame {

	/** Spielfeld in dem das Spiel stattfindet */
	public Spielfeld gamepanel;

	/** Menüleiste des Programms */
	public Menue gamemenue;

	/** Steuerung des 1. Spielers */
	public Steuerung gameinput;

	/** Steuerung des 2. Spielers */
	public Steuerung2 gameinput2;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		gameinput = new Steuerung(this);
		gameinput2 = new Steuerung2(this);

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

	}

	/** Aufruf der Main Methode */
	public void Einstellungen() {
		getContentPane().removeAll();
		new Einstellungen(this);
	}

	public void Dateibrowser() {
		getContentPane().removeAll();
		gamepanel = new Spielfeld(this);
		gamepanel.setVisible(true);
		gamepanel.setLayout(null);
		add(gamepanel);
		gamepanel.XMLFeld();
		gamepanel.zeichnen();
	}

	public static void main(String[] args) {
		Main m = new Main();
	}
}
