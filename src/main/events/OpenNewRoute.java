package main.events;

import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.logic.Route;

public class OpenNewRoute extends BusinessEvent {

	public OpenNewRoute (List<Route> routes) {
		this.routes = routes;
	}



	@Override
	public String toString() {
		return "OpenNewRoute []" + stringRoutes();
	}



	@Override
	public Element toXML(Document doc) {
		Element save = doc.createElement("event");

		Attr attr = doc.createAttribute("type");
		attr.setValue("New Route");
		save.setAttributeNode(attr);

		routesToXML(doc, this, save);

		return save;
	}

}
