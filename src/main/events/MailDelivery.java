package main.events;

public class MailDelivery extends BusinessEvent {

	private String origin;
	private String destination;
	private int weight;
	private int volume;
	private int priority;
	private int revenue;
	private int timeTaken;

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isReceived() {
		return true;
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

	public int getWeight() {
		return weight;
	}
	public int getVolume() {
		return volume;
	}

	public int getPriority() {
		return priority;
	}

	public int getRevenue() {
		return revenue;
	}

	public int getTimeTaken() {
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

}
