package Gui;

import javax.swing.JFrame;

import Objects.Steuerung;
import Objects.Steuerung2;
import Spielfeld.Spielfeld;

/** Main Klasse, die das gesamte Spiel beinhaltet */
public class Main extends JFrame {

	/** Spielfeld in dem das Spiel stattfindet */
	public Spielfeld gamepanel;
	/** Steuerung des 1. Spielers */
	public Steuerung gameinput;
	/** Steuerung des 2. Spielers */
	public Steuerung2 gameinput2;
	/** Men�leiste des Programms */
	public Menue gamemenue;

	private static final long serialVersionUID = 1L;

	/** Start des Programms mit den Fenstereigenschaften */
	public Main() {

		super("Bomberman");
		/************************
		 * Fenstereinstellungen *
		 ************************/
		gamemenue = new Menue(this);
		createGame();
		setJMenuBar(Menue.menubar);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

	}

	/***********************************************************
	 * Erstellen eines Neuen Spiels und einf�gen der Steuerung *
	 ***********************************************************/
	public void createGame() {
		if (gamepanel != null) {
			gamepanel = null;
		}
		gamepanel = new Spielfeld(this);
		gamepanel.setVisible(true);
		gamepanel.setLayout(null);
		add(gamepanel);
		gameinput = new Steuerung(this);
		gameinput2 = new Steuerung2(this);
	}

	/** Aufruf der Main Methode */
	public static void main(String[] args) {
		Main m = new Main();
	}
}
