package Gui;

import javax.swing.JFrame;

import Objects.Steuerung;
import Objects.Steuerung2;
import Spielfeld.Spielfeld;

/** Main Klasse, die das gesamte Spiel beinhaltet */
public class Main extends JFrame {

	/** Spielfeld in dem das Spiel stattfindet */
	public Spielfeld gamepanel;
<<<<<<< HEAD
	/** Steuerung des 1. Spielers */
	public Steuerung gameinput;
	/** Steuerung des 2. Spielers */
	public Steuerung2 gameinput2;
	/** Menüleiste des Programms */
=======
>>>>>>> 5e681c41751c555328d29c7a47f70af36a0899b2
	public Menue gamemenue;

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

	}

	/***********************************************************
	 * Erstellen eines Neuen Spiels und einfügen der Steuerung *
	 ***********************************************************/
	public void createGame() {

		final Steuerung gameinput;
		final Steuerung2 gameinput2;
		getContentPane().removeAll();
		gamepanel = new Spielfeld(this);
		gamepanel.setVisible(true);
		gamepanel.setLayout(null);
		add(gamepanel);
		gameinput = new Steuerung(this);
		gameinput2 = new Steuerung2(this);
	}

<<<<<<< HEAD
	/** Aufruf der Main Methode */
=======
	public void Einstellungen() {
		getContentPane().removeAll();
		new Einstellungen(this);
	}

>>>>>>> 5e681c41751c555328d29c7a47f70af36a0899b2
	public static void main(String[] args) {
		Main m = new Main();
	}
}
