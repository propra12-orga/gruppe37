package Objects;

import javax.swing.JPanel;

import Spielfeld.Spielfeld;

public class Spieler2 extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Spielfeld panel;

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

	public Spieler2(Spielfeld parent) {
		panel = parent;

	}

	public void moveRechts() {
		boolean moveRechts = Steuerung2.moveRechts;

		int x2 = this.getX2();
		int y2 = this.getY2();
		int nextStatus = panel.getBlockStatus1(x2 + 1, y2);
		int currentBlock = panel.getBlockStatus1(x2, y2);

		if (moveRechts == true && Spielfeld.player2alive == true
				&& nextStatus == ground || nextStatus == ausgang) {

			if (nextStatus == ausgang) { // game_over.start();

			} else if (currentBlock == spieler2_bombe) {
				panel.setBlockStatus1(x2 + 1, y2, bombesetzen);
			} else {
				panel.setBlockStatus1(x2 + 1, y2, ground);
			}
			x2++;
			panel.setBlockStatus1(x2 + 1, y2, spieler);
			System.out.println("bla" + y2);
		}
	}

	public void moveLinks() {
		boolean moveLinks = Steuerung2.moveLinks;

		int x2 = this.getX2();
		int y2 = this.getY2();
		int nextStatus = panel.getBlockStatus1(x2 - 1, y2);
		int currentBlock = panel.getBlockStatus1(x2, y2);

		if (moveLinks == true && Spielfeld.player2alive == true
				&& nextStatus == ground || nextStatus == ausgang) {

			if (nextStatus == ausgang) { // game_over.start();
			} else if

			(currentBlock == spieler2_bombe) {
				panel.setBlockStatus1(x2 - 1, y2, bombesetzen);
			} else {
				panel.setBlockStatus1(x2 - 1, y2, ground);
			}
			x2--;
			panel.setBlockStatus1(x2 - 1, y2, spieler);
			System.out.println("bla" + y2);
		}
	}

	public void moveHoch() {
		boolean moveHoch = Steuerung2.moveHoch;

		int x2 = this.getX2();
		int y2 = this.getY2();
		int nextStatus = panel.getBlockStatus1(x2, y2 - 1);
		int currentBlock = panel.getBlockStatus1(x2, y2);

		if (moveHoch == true && Spielfeld.player2alive == true
				&& nextStatus == ground || nextStatus == ausgang) {

			if (nextStatus == ausgang) { // game_over.start();
			}
		} else if

		(currentBlock == spieler2_bombe) {
			panel.setBlockStatus1(x2, y2 - 1, bombesetzen);
		} else {
			panel.setBlockStatus1(x2, y2 - 1, ground);
		}
		y2--;
		panel.setBlockStatus1(x2, y2 - 1, spieler);
		System.out.println("bla" + y2);
	}

	public void moveRunter() {
		boolean moveRunter = Steuerung2.moveRunter;

		int x2 = this.getX2();
		int y2 = this.getY2();
		int nextStatus = panel.getBlockStatus1(x2, y2 + 1);
		int currentBlock = panel.getBlockStatus1(x2, y2);

		if (moveRunter == true && Spielfeld.player2alive == true
				&& nextStatus == ground || nextStatus == ausgang) {

			if (nextStatus == ausgang) { // game_over.start();

			} else if (currentBlock == spieler2_bombe) {
				panel.setBlockStatus1(x2, y2 + 1, bombesetzen);
			} else {
				panel.setBlockStatus1(x2, y2 + 1, ground);
			}
			y2++;
			panel.setBlockStatus1(x2, y2 + 1, spieler);
			System.out.println("bla" + y2);
		}
	}

	// public void bomb() {
	//
	// boolean bomb2 = Steuerung2.bomb2; } if (bomb2 == true &&
	// Spielfeld.player2alive == true && Spielfeld.nextbomb2 == true) {
	// nextbomb2 = false; blockStatus[x2][y2] = spieler2_bombe; a2 = x2; b2 =
	// y2; zeichnen(); explosion2.start(); }
	//

}
