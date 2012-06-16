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
 * @author dario
 * 
 */

public class XMLReader {

	public static void handleChannelTag(Document dok) {
		NodeList nodeList = dok.getElementsByTagName("Line");
		mainTagHandler(nodeList);
	}

	private static void mainTagHandler(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				System.out.println(TextTagHandler("Status", element));
			}
			System.out.println("");
		}
	}

	private static String TextTagHandler(String tagName, Element element) {
		StringBuffer value = new StringBuffer();
		for (int i = 0; i < element.getElementsByTagName(tagName).getLength(); i++) {
			NodeList nodeList = element.getElementsByTagName(tagName).item(i)
					.getChildNodes();
			Node node = nodeList.item(0);
			if (i == 0) {
				value.append("Status: " + node.getNodeValue());
			} else {
				value.append("\n" + "Status: " + node.getNodeValue());
			}
		}
		return value.toString();
	}

}
