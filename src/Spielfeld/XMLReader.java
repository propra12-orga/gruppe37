package Spielfeld;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * TODO
 * 
 * - Die Werte aus der XML übergeben und verarbeiten. => Hat bisher nicht
 * wirklich geklappt und es kam zu grauenvollen Fehlern
 * 
 * - Da bisher keine wirkliche Funktionalität, ist diese Klasse vom Spiel
 * abgetrennt.
 * 
 * @author Gruppe 37
 */

public class XMLReader {

	public static int hoehe = 0;
	public static int breite = 0;

	public static int hoehe_max = 9;
	public static int breite_max = 6;

	public static int ground = 0;
	public static int solid = 1;
	public static int breakblock = 2;

	public static int xmlStatus[][] = new int[breite_max][hoehe_max];

	public static void handleChannelTag(Document dok) {
		NodeList nodeList = dok.getElementsByTagName("Line");
		mainTagHandler(nodeList);
	}

	private static void mainTagHandler(NodeList nodeList) {
		for (int breite = 0; breite < nodeList.getLength(); breite++) {
			Node node = nodeList.item(breite);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				System.out.println(TextTagHandler("Status", element));
			}
			System.out.println("");
		}

		hoehe_max = hoehe;
		breite_max = breite;

	}

	private static String TextTagHandler(String tagName, Element element) {
		StringBuffer value = new StringBuffer();
		for (breite = 0; breite < element.getElementsByTagName(tagName)
				.getLength(); breite++) {
			NodeList nodeList = element.getElementsByTagName(tagName)
					.item(breite).getChildNodes();
			Node node = nodeList.item(0);
			if (breite == 0) {
				value.append("Höhe: " + hoehe + " Breite: " + breite
						+ " Status: " + node.getTextContent());
			} else {
				value.append("\n" + "Höhe: " + hoehe + " Breite: " + breite
						+ " Status: " + node.getTextContent());
			}
			if (node.getTextContent().equals("Solid")) {
				xmlStatus[hoehe][breite] = solid;
			} else if (node.getTextContent().equals("Ground")) {
				xmlStatus[hoehe][breite] = ground;
			} else if (node.getTextContent().equals("Breakblock")) {
				xmlStatus[hoehe][breite] = breakblock;

			}
			if (breite == element.getElementsByTagName(tagName).getLength() - 1) {
				hoehe++;
			}
		}

		return value.toString();

	}
}
