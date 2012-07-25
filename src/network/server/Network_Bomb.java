package network.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Network_Bomb {

	private final Network_Spielfeld Feld;
	/** Network_Spielfeld */
	private final int playerNR;

	/** Bombe auf einem freien Feld */
	private final int bombesetzen = 3;
	private final int explosion_mitte = 5;
	/** Spieler 1 auf seiner Bombe */
	private final int spieler_bombe[] = { 4, 12 };
	private int[][] blockStatus;
	int x[], y[];

	/**
	 * Anzahl der Bomben, die ein Spieler gleichzeitig legen kann, wird weniger
	 * mit jeder gelegten Bombe und erhöht sich, wenn eine Bombe explodiert
	 */
	/**
	 * Bombe
	 * 
	 * @param parent
	 *            Network_Spielfeld
	 * @param playerType
	 *            Spieler 1 oder 2
	 * @param bombsCount
	 *            Anzahl der verfügbaren Bomben
	 */
	public Network_Bomb(Network_Spielfeld parent, int playerType) {
		Feld = parent;
		playerNR = playerType;

	}

	/**
	 * Timer für die explosion
	 */
	public javax.swing.Timer explosion = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					explosion_zeichnen.start();
					explosion.stop();
				}
			});
	/**
	 * zeichnet die explosion ins Feld in Abhängigkeit vom Spieler, der sie
	 * gelegt hat.
	 */
	public javax.swing.Timer explosion_zeichnen = new javax.swing.Timer(0,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					blockStatus = Feld.getBlockStatus();
					x = Feld.getX2();
					y = Feld.getY2();
					if (blockStatus[x[1]][y[1]] == spieler_bombe[1]
							|| blockStatus[x[1]][y[1]] == bombesetzen
							|| blockStatus[x[0]][y[0]] == spieler_bombe[0]
							|| blockStatus[x[0]][y[0]] == bombesetzen) {
						Feld.explozeichnen(playerNR);
						explosion_ende.start();
						explosion_zeichnen.stop();
					}
					explosion_zeichnen.stop();
				}
			});

	/**********************************************
	 * ersetzen der explosion durch ground-Blocks *
	 **********************************************/
	public javax.swing.Timer explosion_ende = new javax.swing.Timer(500,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					blockStatus = Feld.getBlockStatus();
					x = Feld.getX2();
					y = Feld.getY2();
					if (blockStatus[x[1]][y[1]] == explosion_mitte) {
						Feld.exploende(1);
						explosion_ende.stop();
					}
					if (blockStatus[x[0]][y[0]] == explosion_mitte) {
						Feld.exploende(0);

					}
					explosion_ende.stop();
				}
			});
}