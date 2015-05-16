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

public class CustomerPriceChange extends BusinessEvent {


	private double oldPricePerGram;
	private double newPricePerGram;

	private double oldPricePerVolume;
	private double newPricePerVolume;


	/**
	 * Constructor for a Customer Price Change event
	 * @param og: old price per gram
	 * @param ng: new price per gram
	 * @param ov: old price per volume
	 * @param nv: new price per volume
	 */
	public CustomerPriceChange( double og, double ng, double ov, double nv, List<Route> routes) {
		oldPricePerGram = og;
		newPricePerGram = ng;
		oldPricePerVolume = ov;
		newPricePerVolume = nv;
		this.routes=routes;
	}

	@Override
	public Element toXML(Document doc) {
		Element change = doc.createElement("event");

		Attr attr = doc.createAttribute("type");
		attr.setValue("Customer Price Change");
		change.setAttributeNode(attr);

		routesToXML(doc, this, change);

		Element oldPPGram = doc.createElement("oldPricePGram");
		oldPPGram.appendChild(doc.createTextNode(String.valueOf(getOldPricePerGram())));
		change.appendChild(oldPPGram);

		Element newPPGram = doc.createElement("newPricePGram");
		newPPGram.appendChild(doc.createTextNode(String.valueOf(getNewPricePerGram())));
		change.appendChild(newPPGram);

		Element oldPPVolume = doc.createElement("oldPricePVolume");
		oldPPVolume.appendChild(doc.createTextNode(String.valueOf(getOldPricePerVolume())));
		change.appendChild(oldPPVolume);

		Element newPPVolume = doc.createElement("newPricePVolume");
		newPPVolume.appendChild(doc.createTextNode(String.valueOf(getNewPricePerVolume())));
		change.appendChild(newPPVolume);

		return change;
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
		return "CustomerPriceChange [oldPricePerGram=" + oldPricePerGram
				+ ", newPricePerGram=" + newPricePerGram
				+ ", oldPricePerVolume=" + oldPricePerVolume
				+ ", newPricePerVolume=" + newPricePerVolume + "]" + stringRoutes();
	}

}
