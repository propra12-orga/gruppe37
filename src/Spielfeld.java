import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spielfeld extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawRect(21, 21, 740, 520);
		g.drawRect(20, 20, 740, 520);
		g.drawRect(39, 39, 700, 480);
		g.drawRect(40, 40, 700, 480);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(new Spielfeld());
	}
}
