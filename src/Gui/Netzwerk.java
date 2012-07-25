package Gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Klasse zur Erstellung des Basis-Spielfelds mit den zugewiesenen Grafiken
 * 
 * @author Gruppe37
 * @version 0.9
 * @param Main
 *            parent
 */
public class Netzwerk extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4543705554977827956L;

	private final Main window;

	private ActionListener newMapListener = null;
	private ActionListener saveListener = null;
	private ActionListener loadListener = null;

	final JButton newMapButton = new JButton();
	final JButton saveButton = new JButton();
	final JButton loadButton = new JButton();

	/**
	 * Spielfeld wird im Mainpanel angezeigt
	 * 
	 * @param parent
	 *            führt Methode im Mainwindow aus
	 */
	public Netzwerk(Main parent) {
		window = parent;
		initUI();

		Button();

	}

	public void initUI() {

		window.setLayout(null);

		window.setResizable(false);
		window.setSize(new Dimension(600, 400));
		window.setVisible(true);

	}

	/** Auswahlbuttons für die einzelnen Blocks */
	public void Button() {

		newMapButton.setBounds(200, 50, 160, 60);
		newMapButton.setText("Server starten");
		newMapListener = new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadButton.setEnabled(true);
			}
		};
		newMapButton.addActionListener(newMapListener);

		saveButton.setBounds(200, 150, 160, 60);
		saveButton.setText("Connect");

		saveListener = new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				window.getContentPane().remove(loadButton);
				window.getContentPane().remove(saveButton);
				window.getContentPane().remove(newMapButton);
				loadButton.removeActionListener(loadListener);
				saveButton.removeActionListener(saveListener);
				newMapButton.removeActionListener(newMapListener);
				new network.client.Client(window);

			}
		};
		saveButton.addActionListener(saveListener);
		loadButton.setBounds(200, 250, 160, 60);
		loadButton.setText("Server beenden");
		loadListener = new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadButton.setEnabled(false);
			}

		};
		loadButton.addActionListener(loadListener);
		loadButton.setEnabled(false);
		window.add(newMapButton);
		window.add(loadButton);
		window.add(saveButton);

	}
}
