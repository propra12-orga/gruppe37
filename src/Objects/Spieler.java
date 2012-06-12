package Objects;

import javax.swing.JPanel;

import Spielfeld.Spielfeld;

public class Spieler extends JPanel {

	private final Spielfeld panel;
	/**
	 * 
	 */
	public static int ground = 0;
	public static int solid = 1;
	public static int breakblock = 2;
	public static int bombesetzen = 3;
	public static int spieler_bombe = 4;
	public static int explosion_mitte = 5;
	public static int explosion_horizontal = 6;
	public static int explosion_vertikal = 7;
	public static int spieler = 8;
	public static int versteckterausgang = 9;
	public static int ausgang = 10;
	public static int spieler2 = 11;
	public static int spieler2_bombe = 12;

	private static final long serialVersionUID = 1L;

	// Versuch einer Get / Set Methode um die daten für eine bewegung (runter)
	// einzulesen ..... funktioniert noch nicht ^^

	public Spieler(Spielfeld parent) {
		panel = parent;
		boolean moveDown = Steuerung.moveDown;
		if (moveDown == true) {
			int y = panel.getY();
			int x = panel.getX();
			int nextStatus = panel.getBlockStatus(x, y + 1);// status des Blocks
															// unter dem
															// aktuellen zwecks
															// kollisionsabfrage
			int currentBlock = panel.getBlockStatus(x, y);// Status des
															// aktuellen blocks

			panel.setBlockStatus(x, y + 1, spieler);
			System.out.println("EEEEEEYYYYYYYY");
			// Spielfeld.zeichnen();
		}
	}
}