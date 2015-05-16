package main.events;

import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.logic.Route;

public class DeleteRoute extends BusinessEvent {

	/**
	 * Constructor accepts specific routes to delete
	 * @param r: route
	 */
	public DeleteRoute(List<Route> r) {
		routes = r;
	}


	@Override
	public String toString() {
		return "DeleteRoute []" + stringRoutes();
	}


	@Override
	public Element toXML(Document doc) {
		Element delete = doc.createElement("event");

		Attr attr = doc.createAttribute("type");
		attr.setValue("Delete Route");
		delete.setAttributeNode(attr);

		routesToXML(doc, this, delete);

		return delete;
	}

}
