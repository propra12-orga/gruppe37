package Objects;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spieler2 extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JPanel panel2 = new JPanel();
	public static int Feldgroesse_x = 15;
	public static int Feldgroesse_y = 15;
	private static final JLabel fblock[][] = new JLabel[Feldgroesse_x][Feldgroesse_y];
	public final static int blockStatus[][] = new int[Feldgroesse_x][Feldgroesse_y];
	public static int m = 0, n = 0, x = 1, y = 1, a, b, k, l;

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

	public Spieler2() {

	}
}