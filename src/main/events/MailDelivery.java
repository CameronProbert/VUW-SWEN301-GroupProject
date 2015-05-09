package main.events;

import java.util.List;

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

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
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

}
