package Gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OeffnenDialogClass {
	private String Levelname;

	private void oeffnen() {
		try {
			FileFilter filter = new FileNameExtensionFilter("Leveldateien",
					"xml");
			JFileChooser chooser = new JFileChooser(new File(
					System.getProperty("user.dir") + File.separator + "XML"));

			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			chooser.setFileFilter(filter);

			int rueckgabe = chooser.showOpenDialog(null);
			Levelname = chooser.getSelectedFile().getName();
			System.out.println(Levelname);
			if (rueckgabe == JFileChooser.APPROVE_OPTION) {
				if (!Levelname.endsWith(".xml")) {
					oeffnen();
				}
			} else if (rueckgabe == JFileChooser.CANCEL_OPTION) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public OeffnenDialogClass(Main parent) {
		oeffnen();
	}

	public String getLevelName() {
		return Levelname;
	}
}