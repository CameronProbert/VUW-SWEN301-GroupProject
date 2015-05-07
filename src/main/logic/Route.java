package main.logic;

import java.util.Set;

/**
 * The Route class represents a real route between two destinations
 * 
 * @author Cameron Probert
 *
 */
public class Route {

	public enum TransportType {
		standard, fast
	}

	public enum DaysOfWeek {
		monday, tuesday, wednesday, thursday, friday, saturday, sunday
	}

	private Location origin;
	private Location destination;
	private Double averageTimeToDeliver; // Number of hours taken to deliver on
											// this route
	private String transportFirm;
	private double pricePerGramTransport;
	private double pricePerVolumeTransport;
	private double pricePerGramCustomer;
	private double pricePerVolumeCustomer;
	private Set<DaysOfWeek> days;
	private int departureFrequency; // TODO WILL NEED TO CHANGE OFF INT

	public Route(Location origin, Location destination, String transportFirm,
			double pricePerGramTransport, double pricePerVolumeTransport,
			double pricePerGramCustomer, double pricePerVolumeCustomer) {
		this.origin = origin;
		this.destination = destination;
		this.transportFirm = transportFirm;
		this.pricePerGramTransport = pricePerGramTransport;
		this.pricePerVolumeTransport = pricePerVolumeTransport;
		this.pricePerGramCustomer = pricePerGramCustomer;
		this.pricePerVolumeCustomer = pricePerVolumeCustomer;
	}
	
	/**
	 * Will enable each of the given days of the week
	 */
	public void enableDays(Set<DaysOfWeek> daysToAdd){
		
	}
	
	/**
	 * Will disable each of the given days of the week
	 */
	public void disableDays(Set<DaysOfWeek> daysToAdd){
		
	}
	
	/**
	 * Will enable each of the given days of the week
	 */
	public Set<DaysOfWeek> getDays(){
		return days;
	}

	public Location getOrigin() {
		return origin;
	}

	public Location getDestination() {
		return destination;
	}

	public double getAverageTimeToDeliver() {
		return averageTimeToDeliver;
	}

	public String getTransportFirm() {
		return transportFirm;
	}

	public double getPricePerGramTransport() {
		return pricePerGramTransport;
	}

	public double getPricePerVolumeTransport() {
		return pricePerVolumeTransport;
	}

	public double getPricePerGramCustomer() {
		return pricePerGramCustomer;
	}

	public double getPricePerVolumeCustomer() {
		return pricePerVolumeCustomer;
	}

	public int getDepartureFrequency() {
		return departureFrequency;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public void setAverageTimeToDeliver(double averageTimeToDeliver) {
		this.averageTimeToDeliver = averageTimeToDeliver;
	}

	public void setTransportFirm(String transportFirm) {
		this.transportFirm = transportFirm;
	}

	public void setPricePerGramTransport(double pricePerGramTransport) {
		this.pricePerGramTransport = pricePerGramTransport;
	}

	public void setPricePerVolumeTransport(double pricePerVolumeTransport) {
		this.pricePerVolumeTransport = pricePerVolumeTransport;
	}

	public void setPricePerGramCustomer(double pricePerGramCustomer) {
		this.pricePerGramCustomer = pricePerGramCustomer;
	}

	public void setPricePerVolumeCustomer(double pricePerVolumeCustomer) {
		this.pricePerVolumeCustomer = pricePerVolumeCustomer;
	}

	public void setDepartureFrequency(int departureFrequency) {
		this.departureFrequency = departureFrequency;
	}
}
