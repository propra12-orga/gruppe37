package Gui;

import java.io.File;

import javax.swing.JFileChooser;

public class OeffnenDialogClass {
	private final Main window;

	private void oeffnen() {
		JFileChooser chooser = new JFileChooser(new File(
				System.getProperty("user.dir")));
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);

		chooser.setVisible(true);
		chooser.showOpenDialog(null);

	}

	public OeffnenDialogClass(Main parent) {
		window = parent;
		oeffnen();
	}
}