package Spielfeld;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLInit {

	public static void main(String[] args) throws SAXException, IOException,
			ParserConfigurationException {
		File field = new File("Beispielfeld.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document dok = documentBuilder.parse(field);
		XMLReader.handleChannelTag(dok);

	}

}
