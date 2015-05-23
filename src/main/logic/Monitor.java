package main.logic;

import java.util.List;
import java.util.Set;

import main.controllers.UIController;
import main.events.BusinessEvent;
import main.events.MailDelivery;
import main.fileio.LogHandler;
import main.fileio.NoRegisteredUsersException;
import main.fileio.UserIO;
import main.gui.GUI;

/**
 * The monitor is the main logic class of the program. It handles user input
 * passed in from the GUI and will .
 *
 * @author Cameron Probert
 *
 */
public class Monitor {

	private LogHandler handler;
	private GUI gui;

	private Set<Clerk> allUsers;
	private Clerk currentUser;

	private Set<Location> locations;
	private Set<Route> routes;
	private UIController controller;

	/**
	 * Creates the GUI and the monitor
	 *
	 * @param verbose
	 */
	public Monitor() {
		loadUsers();
		handler = new LogHandler();
		// TODO locations = handler.getLocations();
		routes = handler.getRoutes();
		initialiseGUI();
		calculateBusinessFigures();
	}

	/**
	 * Recalculates the business figures and updates the gui with them
	 */
	private void calculateBusinessFigures() {
		double revenue = calculateRevenue();
		// TODO gui.updateRevenue(revenue);
		double expenditure = calculateExpenditure();
		// TODO gui.updateExpenditure(expenditure);
	}

	/**
	 * Returns the total revenue
	 *
	 * @return
	 */
	private double calculateRevenue() {
		double revenue = 0;
		for (BusinessEvent event : handler.getEvents()) {
			if (event instanceof MailDelivery) {
				MailDelivery mail = (MailDelivery) event;
				revenue += mail.getRevenue();
			}
		}
		return revenue;
	}

	/**
	 * Returns the total expenditure
	 *
	 * @return
	 */
	private double calculateExpenditure() {
		double expenditure = 0;
		for (BusinessEvent event : handler.getEvents()) {
			if (event instanceof MailDelivery) {
				MailDelivery mail = (MailDelivery) event;
				double mailExp = 0;
				for (Route route : mail.getRoutes()) {
					mailExp += mail.getWeight()
							* route.getPricePerGramTransport();
					mailExp += mail.getVolume()
							* route.getPricePerVolumeTransport();
				}
				expenditure += mailExp;
			}
		}
		return expenditure;
	}

	/**
	 * Initialises the GUI
	 */
	private void initialiseGUI() {
		gui = new GUI();
	}

	/**
	 * Passes a BusinessEvent to the LogHandler to save
	 *
	 * @param event
	 * @return
	 */
	public boolean saveEvent(BusinessEvent event) {
		boolean successful = handler.newEvent(event);
		calculateBusinessFigures();
		return successful;
	}

	/**
	 * Returns the business event that occurs after the current one as a list of
	 * trings
	 *
	 * @return
	 */
	public List<String> nextEvent() {
		return handler.getNextEvent().description();
	}

	/**
	 * Returns the business event that occurs before the current one as a list
	 * of Strings
	 *
	 * @return
	 */
	public List<String> previousEvent() {
		return handler.getPreviousEvent().description();
	}

	/**
	 * Loads a list of the users in from a file
	 */
	private void loadUsers() {
		System.out.println("Loading in users...");
		try {
			allUsers = UserIO.loadUsers();
		} catch (NoRegisteredUsersException e) {
			e.printStackTrace();
		}
		System.out.println("Done loading users.");
	}

	/**
	 * Logs in a user if they have the correct credentials
	 *
	 * @param id
	 * @param password
	 * @return
	 */
	public Clerk logIn(String id, String password) throws InvalidLoginException {
		Clerk user = null;
		for (Clerk clerk : allUsers) {
			if (clerk.isUser(id, password)) {
				user = clerk;
				currentUser = clerk;
				break;
			}
		}
		return user;
	}

	/**
	 * Logs out the current user
	 */
	public void logOut() {
		currentUser = null;
	}

	/**
	 * Makes a new user
	 *
	 * @param id
	 * @param password
	 * @param name
	 * @return
	 */
	public boolean makeNewUser(String id, String password, String name,
			boolean isManager) {
		Clerk user = null;
		if (isManager) {
			user = new Manager(name, id, password);
		} else {
			user = new Clerk(name, id, password);
		}
		allUsers.add(user);
		UserIO.saveUsers(allUsers);
		return (user != null);
	}

	/**
	 * Returns the saved set of locations
	 *
	 * @return
	 */
	public Set<Location> getLocations() {
		return locations;
	}

	/**
	 * Sets the list of locations
	 *
	 * @param locations
	 */
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	/**
	 * Adds a locations to the list of locations
	 *
	 * @param locations
	 */
	public void addLocations(Location... locations) {
		for (Location l : locations) {
			this.locations.add(l);
		}
	}

	/**
	 * Adds a locations to the list of locations
	 *
	 * @param locations
	 */
	public void rmLocations(Location... locations) {
		for (Location l : locations) {
			this.locations.remove(l);
		}
	}

	/**
	 * Returns the set of routes stored in this class
	 *
	 * @return
	 */
	public Set<Route> getRoutes() {
		return routes;
	}

	/**
	 * Sets the set of routes to the given set
	 *
	 * @param routes
	 */
	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}

	/**
	 * Adds a locations to the list of locations
	 *
	 * @param locations
	 */
	public void addRoutes(Route... routes) {
		for (Route r : routes) {
			this.routes.add(r);
		}
	}

	/**
	 * Adds a locations to the list of locations
	 *
	 * @param locations
	 */
	public void rmRoutes(Route... routes) {
		for (Route r : routes) {
			this.routes.remove(r);
		}
	}

	/**
	 * Returns the monitor class as a string
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Monitor class");
		sb.append("\nCurrently logged in: ");
		if (currentUser != null) {
			sb.append("\n" + currentUser.toString());
		} else {
			sb.append("\nNobody logged in.");
		}
		return sb.toString();
	}

	/**
	 * Sets the UIController to the given controller
	 *
	 * @param controller
	 */
	public void setUIController(UIController controller) {
		this.controller = controller;
	}

}
