package Spielfeld;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Gui.Main;
import Gui.Save;

public class XMLWriter {
	/** horizontale Feldgröße */
	protected int Feldgroesse_x = 100;
	/** vertikale Feldgröße */
	protected int Feldgroesse_y = 100;
	private final int ground = 13;
	private final int solid = 1;
	private final int breakblock = 2;
	/** Spielfigur von Spieler 1 */
	private final int spieler = 8;
	/** Spielfigur von Spieler 2 */
	private final int spieler2 = 11;
	private String fieldType;

	private final Main window;
	private int[][] blockStatus;

	public XMLWriter(Main parent, String addr) {
		window = parent;

		if (Save.getCaller().contains("editor")) {
			blockStatus = Karteneditor.getBlockStatus();
			Feldgroesse_x = Karteneditor.getFeldgroesse_x();
			Feldgroesse_y = Karteneditor.getFeldgroesse_y();

		} else {
			blockStatus = window.gamepanel.getBlockStatus();
			Feldgroesse_x = window.gamepanel.getFeldgroesse_x();
			Feldgroesse_y = window.gamepanel.getFeldgroesse_y();
		}
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document doc = documentBuilder.newDocument();
			Element Map = doc.createElement("Map");

			doc.appendChild(Map);

			for (int breite = 0; breite < Feldgroesse_x; breite++) {
				Element Line = doc.createElement("Line");
				Map.appendChild(Line);

				for (int hoehe = 0; hoehe < Feldgroesse_y; hoehe++) {
					Element Status = doc.createElement("Status");
					Line.appendChild(Status);

					if (blockStatus[breite][hoehe] == solid) {
						fieldType = "Solid";
						Status.appendChild(doc.createTextNode(fieldType));
					}
					if (blockStatus[breite][hoehe] == ground) {
						fieldType = "Ground";
						Status.appendChild(doc.createTextNode(fieldType));
					}
					if (blockStatus[breite][hoehe] == breakblock) {
						fieldType = "Breakblock";
						Status.appendChild(doc.createTextNode(fieldType));
					}
					if (blockStatus[breite][hoehe] == spieler) {
						fieldType = "Spieler";
						Status.appendChild(doc.createTextNode(fieldType));
					}
					if (blockStatus[breite][hoehe] == spieler2) {
						fieldType = "Spieler2";
						Status.appendChild(doc.createTextNode(fieldType));
					}
				}
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);

			if (addr.contains(".xml")) {
				StreamResult result = new StreamResult(new File(addr));
				transformer.transform(source, result);
			} else {
				StreamResult result = new StreamResult(new File(addr + ".xml"));
				transformer.transform(source, result);
			}

			System.out.println("Gespeichert");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

}
