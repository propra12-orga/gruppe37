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

		chooser.setVisible(true);
		chooser.showOpenDialog(null);

	}

	public OeffnenDialogClass(Main parent) {
		window = parent;
		oeffnen();
	}
}