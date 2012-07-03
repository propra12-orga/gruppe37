package Gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Spielfeld.Sound;

/**
 * Legt die Komponenten des Einstellungsmenues fest
 */
public class Einstellungen implements ChangeListener {

	static final int hoehe_MIN = 5;
	/** legt die minimale Höhe des Spielfelds fest */
	static final int hoehe_MAX = 30;
	/** legt die maximale Höhe des Spielfelds fest */
	static int hoehe_INIT = 11;
	/** Standardwert für die Höhe */

	static final int breite_MIN = 5;
	/** legt die minimale Breite des Spielfelds fest */
	static final int breite_MAX = 30;
	/** legt die maximale Höhe des Spielfelds fest */
	static int breite_INIT = 11;
	/** Standardwet für die Breite */

	static final int dichte_MIN = 0;
	/** legt die minimale Blockdichte des Spielfelds fest */
	static final int dichte_MAX = 100;
	/** legt die maximale Blockdichte des Spielfelds fest */
	static int dichte_INIT = 70;
	/** Standardwert für die Blockdichte */

	public static int breite = breite_INIT;
	public static int dichte = dichte_INIT;
	public static int hoehe = hoehe_INIT;

	private final JSlider hoehe_s = new JSlider(hoehe_MIN, hoehe_MAX,
			hoehe_INIT);
	private final JSlider breite_s = new JSlider(breite_MIN, breite_MAX,
			breite_INIT);
	private final JSlider dichte_s = new JSlider(dichte_MIN, dichte_MAX,
			dichte_INIT);

	private final Main window;

	public void initUI() {

		Insets mmInsets = window.getInsets();
		Dimension sizeMmInn = null;
		window.setLayout(null);

		/*
		 * Einstellungen fuer Hoehen-Slider
		 */
		hoehe_s.setPreferredSize(new Dimension(500, 50));
		sizeMmInn = hoehe_s.getPreferredSize();
		hoehe_s.setBounds(80 + mmInsets.left, 16 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		hoehe_s.addChangeListener(this);
		hoehe_s.setPaintTicks(true);
		hoehe_s.setMajorTickSpacing(5);
		hoehe_s.setMinorTickSpacing(1);
		hoehe_s.setPaintLabels(true);

		JLabel nameHoehe = new JLabel("Hoehe");
		nameHoehe.setBounds(10 + mmInsets.left, mmInsets.top, sizeMmInn.width,
				sizeMmInn.height);
		nameHoehe.setToolTipText("Die Hoehe des Spielfelds in Bausteinen");

		/*
		 * Einstellungen fuer Breiten-Slider
		 */
		breite_s.setPreferredSize(new Dimension(500, 50));
		sizeMmInn = breite_s.getPreferredSize();
		breite_s.setBounds(80 + mmInsets.left, 116 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		breite_s.addChangeListener(this);
		breite_s.setPaintTicks(true);
		breite_s.setMajorTickSpacing(5);
		breite_s.setMinorTickSpacing(1);
		breite_s.setPaintLabels(true);

		JLabel nameBreite = new JLabel("Breite");
		nameBreite.setBounds(10 + mmInsets.left, 100 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		nameBreite.setToolTipText("Die Breite des Spielfelds in Bausteinen");

		/*
		 * Einstellungen fuer Dichten-Slider
		 */
		dichte_s.setPreferredSize(new Dimension(500, 50));
		sizeMmInn = dichte_s.getPreferredSize();
		dichte_s.setBounds(80 + mmInsets.left, 216 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		dichte_s.addChangeListener(this);
		dichte_s.setPaintTicks(true);
		dichte_s.setMajorTickSpacing(10);
		dichte_s.setMinorTickSpacing(1);
		dichte_s.setPaintLabels(true);

		JLabel nameDichte = new JLabel("Dichte (in %)");
		nameDichte.setBounds(10 + mmInsets.left, 200 + mmInsets.top,
				sizeMmInn.width, sizeMmInn.height);
		nameDichte
				.setToolTipText("Die Dichte der zerstörbaren Blöcke auf dem Spielfeld");

		/*
		 * Fuegt Slider mit Namen zum Window hinzu
		 */
		window.add(breite_s);
		window.add(dichte_s);
		window.add(hoehe_s);
		window.add(nameBreite);
		window.add(nameHoehe);
		window.add(nameDichte);

		window.setResizable(false);
		window.setSize(new Dimension(600, 400));
		window.setVisible(true);

	}

	/**
	 * fragt den Status der Slider ab
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();

		if (!source.getValueIsAdjusting()) {
			dichte = dichte_s.getValue();
			breite = breite_s.getValue();
			hoehe = hoehe_s.getValue();
			dichte_INIT = dichte;
			breite_INIT = breite;
			hoehe_INIT = hoehe;
		}

	}

	/**
	 * Dropdown-Menue zur Auswahl der Hintergrundmusik
	 */
	public void dropdown() {
		String[] lang = { "-Auswahl-", "ImperialMarch", "Amaranth",
				"TwistedSister", "breakingthelaw", "heartsonfire",
				"Fratzengeballer" };
		// Erste Combo-Box
		JComboBox combo1 = new JComboBox();
		for (String s : lang)
			combo1.addItem(s);
		combo1.setBounds(140, 10, 120, 20);
		combo1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox selectedChoice = (JComboBox) e.getSource();
				String Auswahl = selectedChoice.getSelectedItem().toString();
				Sound.stoppen();
				Sound.hintergrundmusik(Auswahl);
			}
		});

		JLabel nameDrop = new JLabel("Hintergrundmusik:");
		nameDrop.setBounds(10, 10, 120, 20);
		nameDrop.setToolTipText("Wähle eine Hintergrundmusik.");
		window.add(combo1);
		window.add(nameDrop);
	}

	/**
	 * 
	 * @param parent
	 */
	public Einstellungen(Main parent) {
		window = parent;

		initUI();
		dropdown();

	}
}
