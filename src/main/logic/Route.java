package main.logic;

import java.util.EnumSet;
import java.util.Set;

/**
 * The Route class represents a real route between two destinations
 * 
 * @author Cameron Probert
 *
 */
public class Route {

	/**
	 * The type of transport that the route uses
	 * 
	 * @author Cameron Probert
	 *
	 */
	public enum TransportType {
		Standard, Air
	}

	public enum DaysOfWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	private Location origin;
	private Location destination;
	private Double averageTimeToDeliver; // Number of hours taken to deliver on
											// this route
	private String transportFirm;
	private TransportType transportType;
	private double pricePerGramTransport;
	private double pricePerVolumeTransport;
	private double pricePerGramCustomer;
	private double pricePerVolumeCustomer;
	private Set<DaysOfWeek> days;
	private double departureFrequency;

	/**
	 * Creates a new Route with the given fields
	 * 
	 * @param origin
	 * @param destination
	 * @param transportFirm
	 * @param transportType
	 * @param pricePerGramTransport
	 * @param pricePerVolumeTransport
	 * @param pricePerGramCustomer
	 * @param pricePerVolumeCustomer
	 */
	public Route(Location origin, Location destination, String transportFirm,
			TransportType transportType, double pricePerGramTransport,
			double pricePerVolumeTransport, double pricePerGramCustomer,
			double pricePerVolumeCustomer, double departureFrequency,
			DaysOfWeek... days) {
		this.origin = origin;
		this.destination = destination;
		this.transportFirm = transportFirm;
		this.transportType = transportType;
		this.pricePerGramTransport = pricePerGramTransport;
		this.pricePerVolumeTransport = pricePerVolumeTransport;
		this.pricePerGramCustomer = pricePerGramCustomer;
		this.pricePerVolumeCustomer = pricePerVolumeCustomer;
		this.departureFrequency = departureFrequency;
		initialiseDays();
		enableDays(days);
	}

	/**
	 * Initialises the days set. Uses an EnumSet to retain the order of the days
	 */
	private void initialiseDays() {
		this.days = EnumSet.noneOf(DaysOfWeek.class);
	}

	/**
	 * Will enable each of the given days of the week
	 */
	public void enableDays(DaysOfWeek... daysToAdd) {
		for (DaysOfWeek day : daysToAdd) {
			days.add(day);
		}
	}

	/**
	 * Will disable each of the given days of the week
	 */
	public void disableDays(DaysOfWeek... daysToRm) {
		for (DaysOfWeek day : daysToRm) {
			days.remove(day);
		}
	}

	/**
	 * Returns a set of the days of the week the route runs on
	 */
	public Set<DaysOfWeek> getDays() {
		return days;
	}

	/**
	 * Returns the origin location
	 * @return
	 */
	public Location getOrigin() {
		return origin;
	}

	/**
	 * Returns the destination location
	 * @return
	 */
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

	/**
	 * Returns the price customers pay for volume
	 * @return
	 */
	public double getPricePerVolumeCustomer() {
		return pricePerVolumeCustomer;
	}

	/**
	 * Returns how often the route runs on days that it does run
	 * @return
	 */
	public double getDepartureFrequency() {
		return departureFrequency;
	}

	/**
	 * Return the transport types
	 * @return
	 */
	public TransportType getTransportType() {
		return transportType;
	}

	public void setAverageTimeToDeliver(double averageTimeToDeliver) {
		this.averageTimeToDeliver = averageTimeToDeliver;
	}

	/**
	 * Sets the price per gram we pay the transport company
	 * @param pricePerVolumeCustomer
	 */
	public void setPricePerGramTransport(double pricePerGramTransport) {
		this.pricePerGramTransport = pricePerGramTransport;
	}

	/**
	 * Sets the price per volume we pay the transport company
	 * @param pricePerVolumeCustomer
	 */
	public void setPricePerVolumeTransport(double pricePerVolumeTransport) {
		this.pricePerVolumeTransport = pricePerVolumeTransport;
	}

	/**
	 * Sets the price per gram the customer pays
	 * @param pricePerVolumeCustomer
	 */
	public void setPricePerGramCustomer(double pricePerGramCustomer) {
		this.pricePerGramCustomer = pricePerGramCustomer;
	}

	/**
	 * Sets the price per volume the customer pays
	 * @param pricePerVolumeCustomer
	 */
	public void setPricePerVolumeCustomer(double pricePerVolumeCustomer) {
		this.pricePerVolumeCustomer = pricePerVolumeCustomer;
	}

	/**
	 * Sets the departure frequency to the given number. If the number is
	 * greater 24 or less than 0 it will not change and return a false to show
	 * this
	 * 
	 * @param departureFrequency
	 * @return
	 */
	public boolean setDepartureFrequency(double departureFrequency) {
		if (departureFrequency <= 24 && departureFrequency > 0) {
			this.departureFrequency = departureFrequency;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Route \n[origin=");
		builder.append(origin);
		builder.append(", destination=");
		builder.append(destination);
		builder.append("\naverageTimeToDeliver=");
		builder.append(averageTimeToDeliver);
		builder.append("\ntransportFirm=");
		builder.append(transportFirm);
		builder.append(", transportType=");
		builder.append(transportType.name());
		builder.append("\npricePerGramTransport=");
		builder.append(pricePerGramTransport);
		builder.append(", pricePerVolumeTransport=");
		builder.append(pricePerVolumeTransport);
		builder.append("\npricePerGramCustomer=");
		builder.append(pricePerGramCustomer);
		builder.append(", pricePerVolumeCustomer=");
		builder.append(pricePerVolumeCustomer);
		builder.append("\ndays=");
		for (DaysOfWeek day : days) {
			builder.append(", " + day.name());
		}
		builder.append(", departureFrequency=");
		builder.append(departureFrequency);
		builder.append("]");
		return builder.toString();
	}
}
