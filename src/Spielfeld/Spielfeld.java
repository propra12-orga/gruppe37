package Spielfeld;
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
	private final int blockStatus[][] = new int[15][15];
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
		 * Es folgt das obere Rahmenstueck.*
		 ***********************************/

		for (m = 0; m < 15; m++) {
			blockStatus[m][0] = 3;
		}

		/***************************
		 * Das untere Rahmenstueck.*
		 ***************************/

		for (m = 0; m < 15; m++) {
			blockStatus[m][14] = 1;
		}

		/***************************
		 * Das linke Rahmenstueck. *
		 ***************************/

		for (n = 1; n < 14; n++) {
			blockStatus[0][n] = 1;
		}

		/***************************
		 * Das rechte Rahmenstueck.*
		 ***************************/

		for (n = 1; n < 14; n++) {
			blockStatus[14][n] = 1;
		}

		/*********************************************
		 * Innenfeld - Reihen der Spielfeld Bloecke. *
		 *********************************************/

		/************
		 * 1. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][1] = 2;
		}

		/************
		 * 2. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][2] = 2;
			m++;
			blockStatus[m][2] = 1;
		}

		/************
		 * 3. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][3] = 2;
		}

		/************
		 * 4. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][4] = 2;
			m++;
			blockStatus[m][4] = 1;
		}

		/************
		 * 5. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][5] = 2;
		}

		/************
		 * 6. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][6] = 2;
			m++;
			blockStatus[m][6] = 1;
		}

		/************
		 * 7. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][7] = 2;
		}

		/************
		 * 8. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][8] = 2;
			m++;
			blockStatus[m][8] = 1;
		}

		/************
		 * 9. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][9] = 2;
		}

		/************
		 * 10. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][10] = 2;
			m++;
			blockStatus[m][10] = 1;
		}

		/************
		 * 11. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][11] = 2;
		}

		/************
		 * 12. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][12] = 2;
			m++;
			blockStatus[m][12] = 1;
		}

		/************
		 * 13. Reihe *
		 ************/

		for (m = 1; m < 14; m++) {
			blockStatus[m][13] = 2;
		}

		/********************
		 * Ecken freimachen *
		 ********************/

		// Oben links
		blockStatus[1][1] = 0;
		blockStatus[1][2] = 0;
		blockStatus[2][1] = 0;
		// Oben rechts
		blockStatus[13][1] = 0;
		blockStatus[13][2] = 0;
		blockStatus[12][1] = 0;
		// Unten links
		blockStatus[1][13] = 0;
		blockStatus[1][12] = 0;
		blockStatus[2][13] = 0;
		// Unten rechts
		blockStatus[13][13] = 0;
		blockStatus[13][12] = 0;
		blockStatus[12][13] = 0;

		/**************************************************
		 * Abfragen der Blockarten und setzen der Bloecke *
		 **************************************************/
		for (m = 0; m < 15; m++) {
			for (n = 0; n < 15; n++) {
				if (blockStatus[m][n] == 0) {
					fblock[m][n] = new JLabel(grndBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == 1) {
					fblock[m][n] = new JLabel(solidBlock);
					panel1.add(fblock[m][n]);
					fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
				}

				else if (blockStatus[m][n] == 2) {
					fblock[m][n] = new JLabel(brkbleBlock);
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