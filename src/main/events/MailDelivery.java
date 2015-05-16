package main.events;

import java.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.logic.Route;

public class MailDelivery extends BusinessEvent {

	private String origin;
	private String destination;
	private double weight;
	private double volume;
	private double priority;
	private double revenue;
	private double timeTaken;
	private boolean isReceived;

	public MailDelivery(String or, String des, double we, double vol, double prio, double rev, double time, List<Route> routes) {
		origin = or;
		destination = des;
		weight = we;
		volume = vol;
		priority = prio;
		revenue = rev;
		timeTaken = time;
		this.routes = routes;

		isReceived = false; //initialise false
	}

	public boolean isReceived() {
		return isReceived;
	}

	public void setReceived(boolean b) {
		isReceived = b;
	}

	public void operation() {

	}

	//getters
	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}
	public double getVolume() {
		return volume;
	}

	public double getPriority() {
		return priority;
	}

	public double getRevenue() {
		return revenue;
	}

	public double getTimeTaken() {
		return timeTaken;
	}

	//setters
	public void setWeight(int w) {
		weight=w;
	}

	public void setVolume(int v) {
		volume = v;
	}

	public void setPriority(int p) {
		priority = p;
	}

	public void setTimeTaken(int time) {
		timeTaken = time;
	}

	@Override
	public String toString() {
		return "MailDelivery [origin=" + origin + ", destination="
				+ destination + ", weight=" + weight + ", volume=" + volume
				+ ", priority=" + priority + ", revenue=" + revenue
				+ ", timeTaken=" + timeTaken + ", isReceived=" + isReceived
				+ "]" + stringRoutes();
	}

	@Override
	public Element toXML(Document doc) {
		// TODO also need to get whether it has been received or not
		Element mail = doc.createElement("event");

		Attr attr = doc.createAttribute("type");
		attr.setValue("Mail Delivery");
		mail.setAttributeNode(attr);

		routesToXML(doc, this, mail);

		Element origin = doc.createElement("origin");
		origin.appendChild(doc.createTextNode(getOrigin()));
		mail.appendChild(origin);

		Element destination = doc.createElement("destination");
		destination.appendChild(doc.createTextNode(getDestination()));
		mail.appendChild(destination);

		Element weight = doc.createElement("weight");
		weight.appendChild(doc.createTextNode(String.valueOf(getWeight())));
		mail.appendChild(weight);

		Element volume = doc.createElement("volume");
		volume.appendChild(doc.createTextNode(String.valueOf(getVolume())));
		mail.appendChild(volume);

		Element priority = doc.createElement("priority");
		priority.appendChild(doc.createTextNode(String.valueOf(getPriority())));
		mail.appendChild(priority);

		Element revenue = doc.createElement("revenue");
		revenue.appendChild(doc.createTextNode(String.valueOf(getRevenue())));
		mail.appendChild(revenue);

		Element timeTaken = doc.createElement("timeTaken");
		timeTaken.appendChild(doc.createTextNode(String.valueOf(getTimeTaken())));
		mail.appendChild(timeTaken);

		return mail;
	}

}
