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

		/***********************************
		 * Es folgt das obere Rahmenstück. *
		 ***********************************/

		for (m = 0; m < 15; m++) {
			a[m][0] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
		}

		/***************************
		 * Das untere Rahmenstück. *
		 ***************************/

		n = 14;
		for (m = 0; m < 15; m++) {
			a[m][14] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
		}

		/***************************
		 * Das linke Rahmenstück. *
		 ***************************/

		m = 0;
		for (n = 0; n < 15; n++) {
			a[0][n] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
		}

		/***************************
		 * Das rechte Rahmenstück. *
		 ***************************/

		m = 14;
		for (n = 0; n < 15; n++) {
			a[14][n] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
		}

		/*************************************************************
		 * Innenfeld - Reihen der statischen, unzerstörbaren Blöcke. *
		 *************************************************************/

		/************
		 * 1. Reihe *
		 ************/

		m = 2;
		n = 2;
		for (m = 2; m < 13; m++) {
			a[m][2] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
			m++;
		}

		/************
		 * 2. Reihe *
		 ************/

		m = 2;
		n = 4;
		for (m = 2; m < 13; m++) {
			a[m][4] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
			m++;
		}

		/************
		 * 3. Reihe *
		 ************/

		m = 2;
		n = 6;
		for (m = 2; m < 13; m++) {
			a[m][6] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
			m++;
		}

		/************
		 * 4. Reihe *
		 ************/

		m = 2;
		n = 8;
		for (m = 2; m < 13; m++) {
			a[m][8] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
			m++;
		}

		/************
		 * 5. Reihe *
		 ************/

		m = 2;
		n = 10;
		for (m = 2; m < 13; m++) {
			a[m][10] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
			m++;
		}

		/************
		 * 6. Reihe *
		 ************/

		m = 2;
		n = 12;
		for (m = 2; m < 13; m++) {
			a[m][12] = 1;
			if (a[m][n] == 1) {
				fblock[m][n] = new JLabel(solidBlock);
				panel1.add(fblock[m][n]);
				fblock[m][n].setBounds(m * 30, n * 30, 30, 30);
			}
			m++;
		}

	}

	public static void main(String[] args) {
		new Spielfeld();
	}
}