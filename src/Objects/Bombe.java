package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Spielfeld.Spielfeld;

public class Bombe extends Spielfeld {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int ground = 0;
	public int solid = 1;
	public int breakblock = 2;
	public int bombesetzen = 3;
	public int spieler_bombe = 4;
	public int explosion_mitte = 5;
	public int explosion_horizontal = 6;
	public int explosion_vertikal = 7;
	public int spieler = 8;
	public int versteckterausgang = 9;
	public int ausgang = 10;
	public int spieler2 = 11;
	public int spieler2_bombe = 12;

	public Bombe() {

	}

	/*****************
	 * Timer / Bombe
	 * 
	 * @return *
	 *****************/

	public javax.swing.Timer explosion = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					/*******************************************************
					 * Game_over wenn spieler von der Bombe getroffen wird *
					 *******************************************************/
					for (int z = 1; z <= radius; z++) {
						if (blockStatus[a][b] == spieler_bombe) {
							game_over.start();
						}
						if (a + z < Feldgröße_x) {
							if (blockStatus[a + (z - 1)][b] != solid) {
								if (blockStatus[a + z][b] == spieler) {
									game_over.start();
								}
							}
						}
						if (a - z > 0) {
							if (blockStatus[a - (z - 1)][b] != solid) {
								if (blockStatus[a - z][b] == spieler) {
									game_over.start();
								}
							}
						}
						if (b + z < Feldgröße_y) {
							if (blockStatus[a][b + (z - 1)] != solid) {
								if (blockStatus[a][b + z] == spieler) {
									game_over.start();
								}
							}
						}

						if (b - z > 0) {
							if (blockStatus[a][b - (z - 1)] != solid) {
								if (blockStatus[a][b - z] == spieler) {
									game_over.start();
								}
							}
						}

					}
					/**********************************************************
					 * ersetzen der break- und spieler blocks durch explosion *
					 **********************************************************/
					for (int z = 1; z <= radius; z++) {
						if (a + z < Feldgröße_x) {
							if (blockStatus[a + (z - 1)][b] != solid) {
								if ((blockStatus[a + z][b] == spieler
										|| blockStatus[a + z][b] == breakblock || blockStatus[a
										+ z][b] == ground)) {
									blockStatus[a + z][b] = explosion_horizontal;
								}
								if ((blockStatus[a + z][b] == versteckterausgang)) {
									blockStatus[a + z][b] = ausgang;
								}
							}
						}
						if (a - z > 0) {
							if (blockStatus[a - (z - 1)][b] != solid) {
								if ((blockStatus[a - z][b] == spieler
										|| blockStatus[a - z][b] == breakblock || blockStatus[a
										- z][b] == ground)) {
									blockStatus[a - z][b] = explosion_horizontal;
								}
								if ((blockStatus[a - z][b] == versteckterausgang)) {
									blockStatus[a - z][b] = ausgang;
								}
							}
						}
						if (b - z > 0) {
							if (blockStatus[a][b - (z - 1)] != solid) {
								if ((blockStatus[a][b - z] == spieler
										|| blockStatus[a][b - z] == breakblock || blockStatus[a][b
										- z] == ground)) {
									blockStatus[a][b - z] = explosion_vertikal;
								}
								if ((blockStatus[a][b - z] == versteckterausgang)) {
									blockStatus[a][b - z] = ausgang;
								}
							}
						}
						if (b + z < Feldgröße_y) {
							if (blockStatus[a][b + (z - 1)] != solid) {
								if ((blockStatus[a][b + z] == spieler
										|| blockStatus[a][b + z] == breakblock || blockStatus[a][b
										+ z] == ground)) {
									blockStatus[a][b + z] = explosion_vertikal;
								}
								if ((blockStatus[a][b + z] == versteckterausgang)) {
									blockStatus[a][b + z] = ausgang;
								}

							}
						}
					}
					blockStatus[a][b] = explosion_mitte;
					zeichnen();
					explosion_ende.start();

					explosion.stop();

				}
			});

	public javax.swing.Timer explosion_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					/****************************************************
					 * ersetzen der explosionsbl�cke durch groundblocks *
					 ****************************************************/

					for (k = 0; k < Feldgröße_x; k++) {
						for (l = 0; l < Feldgröße_y; l++) {
							if (blockStatus[k][l] == explosion_horizontal
									|| blockStatus[k][l] == explosion_vertikal
									|| blockStatus[k][l] == explosion_mitte) {

								blockStatus[k][l] = ground;
							}
						}
					}
					zeichnen();

					explosion_ende.stop();
					nextbomb = true;

				}
			});

	/*********************
	 * Timer neues Spiel *
	 *********************/
	public javax.swing.Timer game_over = new javax.swing.Timer(600,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new Spielfeld();
					game_over.stop();
				}
			});
	/*
	 * public void getA() { return a; }
	 * 
	 * public void getB() { return b; }
	 * 
	 * public void getRadius() { return radius; }
	 * 
	 * public void getFeldX() { return Feldgröße_x; }
	 * 
	 * public void getFeldY() { return Feldgröße_y; }
	 * 
	 * public void getWerte() { int blockstatus[][] = new
	 * int[Feldgröße_x][Feldgröße_y]; blockstatus[x][y] =
	 * Spielfeld.blockstatus[x][y]; }
	 */
}
