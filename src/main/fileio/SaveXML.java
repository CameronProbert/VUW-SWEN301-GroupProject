package main.fileio;

import java.util.List;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

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

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import main.events.*;

/**
 * Saves the xml file given a list of business events. The grunt of the saving is done inside the business event classes themselves.
 * @author burlinfran
 *
 */
public class SaveXML {

	private Document doc;
	private Element rootElement;
	private String fileName;

	public SaveXML(String fN){
		this.fileName = fN;
	}

	public boolean save(List<BusinessEvent> events){
		try {
			//System.out.println("length of events : " + events.size() + " fileName : " + fileName);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();

			rootElement = doc.createElement("businessEvents");
			doc.appendChild(rootElement);


			for(BusinessEvent event: events){
				rootElement.appendChild(event.toXML(doc));
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			URL url = ClassLoader.getSystemResource(fileName);
			File fXmlFile;
			try{
				fXmlFile = new File(url.toURI());
			}catch(URISyntaxException e){
				fXmlFile = new File(url.getPath());
			}

			StreamResult result = new StreamResult(fXmlFile);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			//System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		}
		return true;
	}


}
