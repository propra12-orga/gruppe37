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
 * @author gruppe37
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
	private int[][] blockStatus;
	private final JMenuItem fileItem1 = new JMenuItem(
			"Neues Spiel (Tastaturmodus)");
	private final JMenuItem fileItem7 = new JMenuItem("Server starten");
	private final JMenuItem fileItem8 = new JMenuItem("Client starten");
	private final JMenuItem fileItem2 = new JMenuItem("Level auswählen");
	private final JMenuItem fileItem3 = new JMenuItem("Speichern");
	private final JMenuItem fileItem4 = new JMenuItem("Einstellungen");
	private final JMenuItem fileItem5 = new JMenuItem("Karteneditor");
	private final JMenuItem fileItem6 = new JMenuItem("Schliessen");

	public Menue(Main parent) {

		window = parent;

		/*******************
		 * Menue erstellen *
		 *******************/
		JMenu filemenu = new JMenu("Spiel");
		filemenu.add(new JSeparator());

		/*******************************
		 * Erster Button, gennant "Neues Spiel", startet Spiel *
		 *******************************/

		fileItem1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {

					window.createGame();
					DisableButton();

				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/*******************************
		 * Siebter Button, gennant "Server starten", startet Spiel *
		 *******************************/

		fileItem7.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					window.Netzwerk();
				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/*******************************
		 * Siebter Button, gennant "Client starten", startet Spiel *
		 *******************************/

		fileItem8.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					window.client();
				}

				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/*************************************
		 * Zweiter Button, "Level auswählen, öffnet Dateibrowser zur Auswahl
		 * einer gültigen XML-Datei *
		 *************************************/

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
		 * Dritter Button - Speichern *
		 *************************************/

		fileItem3.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					new Gui.Save(window);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/*************************************
		 * Vierter Button - Einstellungsmenue *
		 *************************************/

		fileItem4.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					window.Einstellungen();
					DisableButton();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/*************************************
		 * Fünfter Button - Karteneditor *
		 *************************************/

		fileItem5.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					window.Leveleditor();
					DisableButton();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		/*************************
		 * Sechster Button - Exit *
		 *************************/

		fileItem6.setMnemonic('x');
		fileItem6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		fileItem6.add(new JSeparator());
		filemenu.add(fileItem1);
		filemenu.add(fileItem7);
		filemenu.add(fileItem8);
		filemenu.add(fileItem2);
		filemenu.add(fileItem3);
		filemenu.add(fileItem4);
		filemenu.add(fileItem5);
		filemenu.add(fileItem6);
		fileItem2.setEnabled(false);
		fileItem3.setEnabled(false);

		menubar.add(filemenu);

	}

	public void DisableButton() {
		try {
			blockStatus = window.gamepanel.getBlockStatus();

			if (blockStatus[0][0] != 0) {
				fileItem2.setEnabled(true);
				fileItem3.setEnabled(true);
			} else {
				fileItem2.setEnabled(false);
				fileItem3.setEnabled(false);
			}
		} catch (Exception e) {

		}
	}
}
