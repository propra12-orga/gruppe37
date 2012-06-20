package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * Erstellung der Menüleiste mit den Buttons zum Spielstart, Einstellungsmenue
 * und Beenden des Programms
 * 
 * @author Johannes
 * 
 */
public class Menue extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final JMenuBar menubar = new JMenuBar(); // Neue Menüleiste
															// wird
															// initialisiert
	private final Main window;

	public Menue(Main parent) {

		window = parent;

		/*******************
		 * Menue erstellen *
		 *******************/
		JMenu filemenu = new JMenu("Spiel");
		filemenu.add(new JSeparator());

		/*******************************
		 * Erster Button - Neues Spiel *
		 *******************************/

		JMenuItem fileItem1 = new JMenuItem("Neues Spiel");

		fileItem1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {

					window.createGame();
				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/**
		 * Zweiter Button - Level einlesen
		 */

		JMenuItem fileItem2 = new JMenuItem("Level auswählen");

		fileItem2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					window.Dateibrowser();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		/*************************************
		 * Dritter Button - Einstellungsmenue*
		 *************************************/

		JMenuItem fileItem3 = new JMenuItem("Einstellungen");

		fileItem3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					window.Einstellungen();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/*************************
		 * Vierter Button - Exit *
		 *************************/
		JMenuItem fileItem4 = new JMenuItem("Schliessen");
		fileItem4.setMnemonic('x');
		fileItem4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileItem4.add(new JSeparator());
		filemenu.add(fileItem1);
		filemenu.add(fileItem2);
		filemenu.add(fileItem3);
		filemenu.add(fileItem4);
		menubar.add(filemenu);

	}
}
