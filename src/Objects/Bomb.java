package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Spielfeld.Spielfeld;

public class Bomb {

	private final Spielfeld Feld;
	/** Spielfeld */
	private final int playerNR;
	/** gibt an, ob Spieler 1 oder 2 gemeint ist */
	private final int bombsLeft;

	/**
	 * Anzahl der Bomben, die ein Spieler gleichzeitig legen kann, wird weniger
	 * mit jeder gelegten Bombe und erhöht sich, wenn eine Bombe explodiert
	 */
	/**
	 * Bombe
	 * 
	 * @param parent
	 *            Spielfeld
	 * @param playerType
	 *            Spieler 1 oder 2
	 * @param bombsCount
	 *            Anzahl der verfügbaren Bomben
	 */
	public Bomb(Spielfeld parent, int playerType, int bombsCount) {
		Feld = parent;
		playerNR = playerType;
		bombsLeft = bombsCount;

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

					Feld.explozeichnen(playerNR, bombsLeft);
					explosion_ende.start();
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
					Feld.exploende(playerNR, bombsLeft);
					explosion_ende.stop();
				}
			});
}