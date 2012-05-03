import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Default {
	public static void main(String[] args) {
		new Default();
	}

	public Default() {
		JFrame frame = new JFrame("Bomberman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel(
				"Hier entsteht unser Bomberman, aber sowas von ;)",
				JLabel.CENTER);

		frame.getContentPane().add(label);

		JMenuBar menubar = new JMenuBar();
		JMenu filemenu = new JMenu("Spiel");
		filemenu.add(new JSeparator());
		JMenuItem fileItem1 = new JMenuItem("Neues Spiel");
		JMenuItem fileItem2 = new JMenuItem("Einstellungen");
		JMenuItem fileItem3 = new JMenuItem("Schlieﬂen");

		fileItem3.setMnemonic('x');
		fileItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileItem3.add(new JSeparator());
		filemenu.add(fileItem1);
		filemenu.add(fileItem2);
		filemenu.add(fileItem3);
		menubar.add(filemenu);
		frame.setJMenuBar(menubar);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}