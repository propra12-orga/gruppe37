package Gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Spielfeld.XMLWriter;

public class Save {

	private String Savename;

	public Save() {

		try {
			JFileChooser chooser = new JFileChooser(
					System.getProperty("user.dir") + File.separator + "XML");
			chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			chooser.setMultiSelectionEnabled(false);

			FileFilter filter = new FileNameExtensionFilter(
					"Leveldateien .xml", "xml");
			chooser.setFileFilter(filter);

			int rueckgabe = chooser.showSaveDialog(null);
			Savename = chooser.getSelectedFile().getPath();

			if (rueckgabe == JFileChooser.APPROVE_OPTION) {
				new XMLWriter(Savename);
				return;
			} else if (rueckgabe == JFileChooser.CANCEL_OPTION) {
				return;
			}

		} catch (Exception e) {

		}
	}
}