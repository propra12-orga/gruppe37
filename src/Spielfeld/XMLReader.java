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

	public static int hoehe_max = 100;
	public static int breite_max = 100;

	public static int ground = 3;
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
			}
			if (hoehe == element.getElementsByTagName(tagName).getLength() - 1) {
				breite++;
			}
		}

		return value.toString();

	}
}
