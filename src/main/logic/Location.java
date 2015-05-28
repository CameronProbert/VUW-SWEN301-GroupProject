package main.logic;

import java.util.HashSet;
import java.util.Set;

/**
 * A Location represents a real world port and contains a list of all the routes
 * that come in and leave from this location.
 * 
 * @author Cameron Probert
 *
 */
public class Location {

	private String name;
	private Set<Route> inbound;
	private Set<Route> outbound;

	/**
	 * Creates a location with the given name
	 * 
	 * @param name
	 */
	public Location(String name) {
		this.name = name;
		this.inbound = new HashSet<Route>();
		this.outbound = new HashSet<Route>();
	}

	/**
	 * Returns the name of the Location
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the set of routes that come in to this location
	 * 
	 * @return
	 */
	public Set<Route> getInbound() {
		return inbound;
	}

	/**
	 * Returns the set of routes that leave this location
	 * 
	 * @return
	 */
	public Set<Route> getOutbound() {
		return outbound;
	}

	/**
	 * Sets the set of inbound routes to the given set
	 * 
	 * @param inbound
	 */
	public void setInbound(Set<Route> inbound) {
		this.inbound = inbound;
	}

	/**
	 * Adds the given routes to the current set of inbound routes
	 * 
	 * @param inbound
	 */
	public void addInbound(Route... inbound) {
		for (Route r : inbound){
			this.inbound.add(r);
		}
	}

	/**
	 * Removes the given routes to the current set of inbound routes
	 * 
	 * @param inbound
	 */
	public void rmInbound(Route... inbound) {
		for (Route r : inbound){
			this.inbound.remove(r);
		}
	}

	/**
	 * Sets the list of outbound routes to the given set
	 * 
	 * @param inbound
	 */
	public void setOutbound(Set<Route> outbound) {
		this.outbound = outbound;
	}

	/**
	 * Adds the given routes to the current set of outbound routes
	 * 
	 * @param outbound
	 */
	public void addOutbound(Route... outbound) {
		for (Route r : outbound){
			this.outbound.add(r);
		}
	}

	/**
	 * Removes the given routes to the current set of outbound routes
	 * 
	 * @param outbound
	 */
	public void rmOutbound(Route... outbound) {
		for (Route r : outbound){
			this.outbound.remove(r);
		}
	}

	/**
	 * Returns whether this Location and another are equal. This also works with
	 * comparing to a String, if the string is the same as this locations name
	 * it will return true.
	 */
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other instanceof String) {
			return this.getName().equals((String) other);
		}
		if (!(other instanceof Location)) {
			return false;
		}
		Location otherLoc = (Location) other;
		return this.getName().equals(otherLoc.getName());
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
