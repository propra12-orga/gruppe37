package TestKrempel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class regex {

	public boolean isInvalidFileName(String fileName) {
		String chara = ".*[?/<>|*:\"{\\\\}].*";
		CharSequence input = fileName;
		Pattern pattern = Pattern.compile(chara);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	public static void main(String[] args) {
		regex reg = new regex();
		String fileName = "?test";
		if (reg.isInvalidFileName(fileName)) {
			JOptionPane.showMessageDialog(null, fileName + " - invalid name");
		} else {
			JOptionPane.showMessageDialog(null, fileName + " - valid name");
		}
	}
}
