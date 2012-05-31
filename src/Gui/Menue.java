package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import Spielfeld.Spielfeld;

public class Menue extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final JMenuBar menubar = new JMenuBar();

	public Menue() {

		/*********
		 * Menue *
		 *********/
		JMenu filemenu = new JMenu("Spiel");
		filemenu.add(new JSeparator());

		/*****************
		 * Erster Button *
		 *****************/

		JMenuItem fileItem1 = new JMenuItem("Neues Spiel");

		fileItem1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					JPanel Spielfeld = new Spielfeld();
					Spielfeld.setVisible(true);
					Spielfeld.setLayout(null);
					add(Spielfeld);
				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/******************
		 * Zweiter Button *
		 ******************/

		JMenuItem fileItem2 = new JMenuItem("Einstellungen");

		fileItem2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					Einstellungen frame = new Einstellungen();
					frame.setVisible(true);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/******************
		 * Dritter Button *
		 ******************/
		JMenuItem fileItem3 = new JMenuItem("Schliessen");
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

	}
}
