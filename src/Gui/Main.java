package Gui;

import javax.swing.JFrame;

import Objects.Steuerung;
import Objects.Steuerung2;
import Spielfeld.Spielfeld;

public class Main extends JFrame {

	public Spielfeld gamepanel;
	public Steuerung gameinput;
	public Steuerung2 gameinput2;
	public Menue gamemenue;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public void Einstellungen() {

		new Einstellungen(this);
	}

	public static void main(String[] args) {
		Main m = new Main();
	}
}
