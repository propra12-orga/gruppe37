package Gui;

import javax.swing.JFrame;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Main() {

		/************************
		 * Fenstereinstellungen *
		 ************************/

		JFrame frame = new JFrame("Bomberman");
		frame.setJMenuBar(Menue.menubar);
		frame.setSize(800, 600);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public static void main(String[] args) {
		new Main();
		new Menue();
	}

}
