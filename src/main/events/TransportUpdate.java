package main.events;

import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.logic.Route;

public class TransportUpdate extends BusinessEvent {

	private double oldPricePerGram;
	private double newPricePerGram;

	private double oldPricePerVolume;
	private double newPricePerVolume;

	public TransportUpdate( double og, double ng, double ov, double nv, List<Route> routes ) {
		oldPricePerGram = og;
		newPricePerGram = ng;
		oldPricePerVolume = ov;
		newPricePerVolume = nv;
		this.routes = routes;
	}

	public void setNewPricePerGram( int i) {
		oldPricePerGram = newPricePerGram;
		newPricePerGram = i;
	}

	public void setNewPricePerVolume( int i ) {
		oldPricePerVolume = newPricePerVolume;
		newPricePerVolume = i;
	}

	public double getOldPricePerGram() {
		return oldPricePerGram;
	}

	public double getNewPricePerGram() {
		return newPricePerGram;
	}

	public double getOldPricePerVolume() {
		return oldPricePerVolume;
	}

	public double getNewPricePerVolume() {
		return newPricePerVolume;
	}


	@Override
	public String toString() {
		return "TransportUpdate [oldPricePerGram=" + oldPricePerGram
				+ ", newPricePerGram=" + newPricePerGram
				+ ", oldPricePerVolume=" + oldPricePerVolume
				+ ", newPricePerVolume=" + newPricePerVolume + "]" + stringRoutes();
	}

	@Override
	public Element toXML(Document doc) {
		Element transport = doc.createElement("event");

		Attr attr = doc.createAttribute("type");
		attr.setValue("Transport Update");
		transport.setAttributeNode(attr);

		routesToXML(doc, this, transport);

		Element oldPPGram = doc.createElement("oldPricePGram");
		oldPPGram.appendChild(doc.createTextNode(String.valueOf(getOldPricePerGram())));
		transport.appendChild(oldPPGram);

		Element newPPGram = doc.createElement("newPricePGram");
		newPPGram.appendChild(doc.createTextNode(String.valueOf(getNewPricePerGram())));
		transport.appendChild(newPPGram);

		Element oldPPVolume = doc.createElement("oldPricePVolume");
		oldPPVolume.appendChild(doc.createTextNode(String.valueOf(getOldPricePerVolume())));
		transport.appendChild(oldPPVolume);

		Element newPPVolume = doc.createElement("newPricePVolume");
		newPPVolume.appendChild(doc.createTextNode(String.valueOf(getNewPricePerVolume())));
		transport.appendChild(newPPVolume);

		return transport;
	}

}
