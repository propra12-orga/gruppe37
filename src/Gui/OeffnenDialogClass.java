package Gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OeffnenDialogClass {
	private final Main window;

	private void oeffnen() {
		FileFilter filter = new FileNameExtensionFilter("Leveldateien", "xml");

		JFileChooser chooser = new JFileChooser(new File(
				System.getProperty("user.dir")));
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.addChoosableFileFilter(filter);

		// chooser.setVisible(true);
		int rueckgabe = chooser.showOpenDialog(null);
		String Levelname = chooser.getSelectedFile().getName();
		if (rueckgabe == JFileChooser.APPROVE_OPTION) {
			if (Levelname.endsWith(".xml")) {
				System.out.println("Level:" + Levelname);
			} else {
				System.out
						.println("Ungültige Datei, bitte .xml-Datei auswählen.");
				oeffnen();
			}
		}

	}

	public OeffnenDialogClass(Main parent) {
		window = parent;
		oeffnen();
	}
}