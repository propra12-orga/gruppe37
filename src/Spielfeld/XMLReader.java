package Spielfeld;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * TODO
 * 
 * Spielerposi verarbeiten
 * 
 * @author gruppe37
 */

public class XMLReader {
	/** hoehe */
	public static int hoehe = 0;
	/** breite */
	public static int breite = 0;
	/** maximale hoehe */
	public static int hoehe_max = 100;
	/** maximale breite */
	public static int breite_max = 100;
	/** Blockstatus ground */
	public static int ground = 3;
	/** Blockstatus solid */
	public static int solid = 1;
	/** Spielfigur von Spieler 1 */
	public static int spieler = 8;
	/** Spielfigur von Spieler 2 */
	public static int spieler2 = 11;
	/** Blockstatus breakblock */
	public static int breakblock = 2;
	/** offengelegter Ausgang */
	public static int ausgang = 10;
	/** Blockstatus fail */
	public static int fail = 42;
	/** XMLStatus Array */

	public static int xmlStatus[][] = new int[breite_max][hoehe_max];

	public static void handleChannelTag(Document dok) {
		NodeList nodeList = dok.getElementsByTagName("Line");
		mainTagHandler(nodeList);
	}

	/** Handler für die Werte */
	private static void mainTagHandler(NodeList nodeList) {
		for (int breite = 0; breite < nodeList.getLength(); breite++) {
			Node node = nodeList.item(breite);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				TextTagHandler("Status", element);
			}

		}

		hoehe_max = hoehe;
		breite_max = breite;

	}

	/** Methode zum resetten der einzelnen Variablen */
	public static void Reset() {
		hoehe = 0;
		breite = 0;

		hoehe_max = 100;
		breite_max = 100;

		ground = 3;
		solid = 1;
		breakblock = 2;

	}

	/**
	 * Verarbeitet die einzelnen Zeilen und setzt den xmlStatus bzw. daraus
	 * resultierenden Blockstatus fest
	 */
	private static void TextTagHandler(String tagName, Element element) {
		for (hoehe = 0; hoehe < element.getElementsByTagName(tagName)
				.getLength(); hoehe++) {
			NodeList nodeList = element.getElementsByTagName(tagName)
					.item(hoehe).getChildNodes();
			Node node = nodeList.item(0);

			if (node.getTextContent().equals("Solid")) {
				xmlStatus[breite][hoehe] = solid;
			} else if (node.getTextContent().equals("Ground")) {
				xmlStatus[breite][hoehe] = ground;
			} else if (node.getTextContent().equals("Breakblock")) {
				xmlStatus[breite][hoehe] = breakblock;
			} else if (node.getTextContent().equals("Spieler")) {
				xmlStatus[breite][hoehe] = spieler;
			} else if (node.getTextContent().equals("Spieler2")) {
				xmlStatus[breite][hoehe] = spieler2;
			} else if (node.getTextContent().equals("Ausgang")) {
				xmlStatus[breite][hoehe] = ausgang;
			} else {
				xmlStatus[breite][hoehe] = fail;
			}
			if (hoehe == element.getElementsByTagName(tagName).getLength() - 1) {
				breite++;
			}

		}

	}
}
