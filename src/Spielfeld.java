import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spielfeld extends JFrame {

	/**
             *
             */
	private static final long serialVersionUID = 1L;
	private final JFrame frame = new JFrame();
	private final ImageIcon solidBlock = new ImageIcon("HardBlock.png");
	private final ImageIcon brkbleBlock = new ImageIcon("breakstone.jpg");
	private final ImageIcon grndBlock = new ImageIcon("ground.jpg");
	private final JPanel panel1 = new JPanel();
	private final JLabel fblock[][] = new JLabel[15][15];
	private final int a[][] = new int[15][15];
	public int m = 0, n = 0;

	public Spielfeld() {
		frame.getContentPane().add(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		panel1.setLayout(null);
		panel1.setBounds(200, 200, 450, 450);

		/*************************
		 * Setzen der Blockarten *
		 *************************/

		/***********************************
		 * Es folgt das obere Rahmenst�ck. *
		 ***********************************/

		for (m = 0; m < 15; m++) {
			a[m][0] = 1;
		}

		/***************************
		 * Das untere Rahmenst�ck. *
		 ***************************/

		for (m = 0; m < 15; m++) {
			a[m][14] = 1;
		}

		/***************************
		 * Das linke Rahmenst�ck. *
		 ***************************/

		for (n = 1; n < 14; n++) {
			a[0][n] = 1;
		}

		/***************************
		 * Das rechte Rahmenst�ck. *
		 ***************************/

		for (n = 1; n < 14; n++) {
			a[14][n] = 1;
		}

		/*************************************************************
		 * Innenfeld - Reihen der Spielfeld Bl�cke. *
		 *************************************************************/

		/************
		 * 1. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][1] = 2;
		}

		/************
		 * 2. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][2] = 2;
			m++;
			a[m][2] = 1;
		}

		/************
		 * 3. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][3] = 2;
		}

		/************
		 * 4. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][4] = 2;
			m++;
			a[m][4] = 1;
		}

		/************
		 * 5. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][5] = 2;
		}

		/************
		 * 6. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][6] = 2;
			m++;
			a[m][6] = 1;
		}

		/************
		 * 7. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][7] = 2;
		}

		/************
		 * 8. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][8] = 2;
			m++;
			a[m][8] = 1;
		}

		/************
		 * 9. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][9] = 2;
		}

		/************
		 * 10. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][10] = 2;
			m++;
			a[m][10] = 1;
		}

		/************
		 * 11. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][11] = 2;
		}

		/************
		 * 12. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][12] = 2;
			m++;
			a[m][12] = 1;
		}

		/************
		 * 13. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			a[m][13] = 2;
		}

		/********************
		 * Ecken freimachen *
		 ********************/

		// Oben links
		a[1][1] = 0;
		a[1][2] = 0;
		a[2][1] = 0;
		// Oben rechts
		a[13][1] = 0;
		a[13][2] = 0;
		a[12][1] = 0;
		// Unten links
		a[1][13] = 0;
		a[1][12] = 0;
		a[2][13] = 0;
		// Unten rechts
		a[13][13] = 0;
		a[13][12] = 0;
		a[12][13] = 0;

		/*************************************************
		 * Abfragen der Blockarten und setzen der Blöcke *
		 *************************************************/
		for (m = 0; m < 15; m++) {
			for (n = 0; n < 15; n++) {
				if (a[m][n] == 1) {
					fblock[m][n] = new JLabel(solidBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (a[m][n] == 2) {
					fblock[m][n] = new JLabel(brkbleBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (a[m][n] == 0) {
					fblock[m][n] = new JLabel(grndBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

			}
		}

	}

	public static void main(String[] args) {
		new Spielfeld();
	}
}