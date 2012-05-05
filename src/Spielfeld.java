import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spielfeld extends Default {
	public Spielfeld() {
		panel1.add(this.bild1);
		panel1.setBounds(40, 40, 80, 80);
		panel1.setVisible(true);
	}

	private final ImageIcon solidBlock = new ImageIcon("HardBlock.png");
	private final JPanel panel1 = new JPanel();
	private final JLabel bild1 = new JLabel(solidBlock);

	public static void main(String[] args) {
		new Spielfeld();
	}
}