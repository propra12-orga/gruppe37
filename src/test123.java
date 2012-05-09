import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class test123 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JFrame frame = new JFrame();
	private final ImageIcon solidBlock = new ImageIcon("HardBlock.png");
	private final JPanel panel1 = new JPanel();
	private final JLabel bild1 = new JLabel(solidBlock);
	private final int a[][] = new int[15][15];
	public int m = 0, n = 0;

	public test123() {
		frame.getContentPane().add(panel1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		panel1.setLayout(null);
		for (m = 0; m < 15; m++) {
			a[m][0] = 1;
			if (a[m][n] == 1) {
				panel1.add(bild1);
				bild1.setBounds(m * 30, n * 30, 30, 30);
			}
		}
	}

	public static void main(String[] args) {
		new test123();
	}
}