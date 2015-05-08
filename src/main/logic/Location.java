package main.logic;

import java.util.List;

public class Location {

	private String name;
	private List<Route> incoming;
	private List<Route> outgoing;

	public Location(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Route> getIncoming() {
		return incoming;
	}

	public List<Route> getOutgoing() {
		return outgoing;
	}

	public void setIncoming(List<Route> incoming) {
		this.incoming = incoming;
	}

	public void setOutgoing(List<Route> outgoing) {
		this.outgoing = outgoing;
	}

	/**
	 * Returns a representation of this location as a String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Location");
		sb.append("\nName: " + name);
		return sb.toString();
	}
}
