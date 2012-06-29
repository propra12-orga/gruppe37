package Objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Spielfeld.Spielfeld;

public class Bomb {

	javax.swing.Timer explosion1 = new javax.swing.Timer(2000,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					explosion1_zeichnen.start();
					explosion1.stop();
				}
			});
	javax.swing.Timer explosion1_zeichnen = new javax.swing.Timer(0,
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Spielfeld.explozeichnen();
				}
			});

}