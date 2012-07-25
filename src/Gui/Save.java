package Gui;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Spielfeld.XMLWriter;

/**
 * Implementiert Speicherfunktion
 */
public class Save {
	private String Savename;

	/** Variable für den Namen des Spielstands */
	private static String caller;
	private final Main window;
	private int rueckgabe;

	public boolean isInvalidFileName(String Savename) {
		String chara = "";
		CharSequence input = Savename;
		Pattern pattern = Pattern.compile(chara);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public Save(Main parent) {
		window = parent;
		Throwable t = new Throwable();
		StackTraceElement[] ste = t.getStackTrace();
		caller = ste[1].getClassName();

		try {
			JFileChooser chooser = new JFileChooser(
					System.getProperty("user.dir") + File.separator + "XML");
			chooser.setMultiSelectionEnabled(false);
			chooser.setDialogType(JFileChooser.SAVE_DIALOG);

			FileFilter filter = new FileNameExtensionFilter(
					"Leveldateien .xml", "xml");
			chooser.setFileFilter(filter);
			rueckgabe = chooser.showSaveDialog(null);
			Savename = chooser.getSelectedFile().getPath();

			if (rueckgabe == JFileChooser.APPROVE_OPTION) {
				if (isInvalidFileName(Savename)) {
					JOptionPane
							.showMessageDialog(
									null,
									Savename
											+ ".xml"
											+ " - invalider Name. \n\n Bitte wählen Sie einen anderen Dateinamen.");
					new Save(parent);
				} else {
					new XMLWriter(window, Savename);
					return;
				}

			} else if (rueckgabe == JFileChooser.CANCEL_OPTION) {
				return;
			}

		} catch (Exception e) {

		}
	}

	public static String getCaller() {
		return caller;
	}
}