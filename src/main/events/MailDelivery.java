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

}
