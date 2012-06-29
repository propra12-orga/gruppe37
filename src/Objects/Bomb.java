package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Spielfeld.Spielfeld;

public class Bomb {

	private final Spielfeld Feld;
	private final int playerNR;
	private final int bombsLeft;

	public Bomb(Spielfeld parent, int playerType, int bombsCount) {
		Feld = parent;
		playerNR = playerType;
		bombsLeft = bombsCount;

	}

	public javax.swing.Timer explosion = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					explosion_zeichnen.start();
					explosion.stop();
				}
			});
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