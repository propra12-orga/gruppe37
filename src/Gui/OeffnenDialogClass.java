package Gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Definiert Parameter für den Dateibrowser
 * 
 */
public class OeffnenDialogClass {
	private String Levelname = null;

	/**
	 * Methode zum Öffnen einer XML Datei. Überprüft auch, ob eine gülltige
	 * Datei gewählt wurde.
	 */
	public void oeffnen() {
		try {
			FileFilter filter = new FileNameExtensionFilter(
					"Leveldateien .xml", "xml");
			JFileChooser chooser = new JFileChooser(new File(
					System.getProperty("user.dir") + File.separator + "XML"));
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			chooser.setFileFilter(filter);

			int rueckgabe = chooser.showOpenDialog(null);
			Levelname = chooser.getSelectedFile().getPath();
			if (rueckgabe == JFileChooser.APPROVE_OPTION) {
				if (!Levelname.endsWith(".xml")) {
					oeffnen();
				}
			} else if (rueckgabe == JFileChooser.CANCEL_OPTION) {
				return;
			}
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Fügt den Browser zum Fenster hinzu
	 * 
	 * @param parent
	 */
	public OeffnenDialogClass(Main parent) {
		oeffnen();
	}

	/**
	 * 
	 * @return liefert den Namen des Levels als String zurück
	 */
	public String getLevelName() {
		return Levelname;
	}

}