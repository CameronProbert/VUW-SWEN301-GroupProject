package main.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.controllers.UIController;
import main.events.*;
import main.fileio.LogHandler;
import main.fileio.NoRegisteredUsersException;
import main.fileio.UserIO;
import main.gui.GUI;

/**
 * The monitor is the main logic class of the program. It handles user input
 * passed in from the GUI and will process data that moves around the program.
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
	 */
	public Monitor() {
		loadUsers();
		handler = new LogHandler();
		locations = handler.getLocations();
		routes = handler.getRoutes();
		initialiseGUI();
	}

	/**
	 * Recalculates the business figures and updates the gui with them
	 */
	public void calculateBusinessFigures() {
		double revenue = calculateRevenue();
		double expenditure = calculateExpenditure();
		controller.updateRevenue(revenue);
		controller.updateExpenditure(expenditure);
		try {
			controller.setNumberOfEvents(handler.getEvents().size());
		} catch (NullPointerException e) {
			controller.setNumberOfEvents(0);
		}
	}

	/**
	 * Returns the total revenue
	 *
	 * @return
	 */
	private double calculateRevenue() {
		double revenue = 0;
		if (handler.getEvents() != null) {
			for (BusinessEvent event : handler.getEvents()) {
				if (event instanceof MailDelivery) {
					MailDelivery mail = (MailDelivery) event;
					revenue += mail.getRevenue();
				}
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
		if (handler.getEvents() != null) {
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
		}
		return expenditure;
	}

	/**
	 * Initialises the GUI
	 */
	private void initialiseGUI() {
		gui = new GUI();
		UIController controller = new UIController(gui, this);
		gui.setUIController(controller);
		this.setUIController(controller);
		gui.setUp();
	}

	/**
	 * Passes a BusinessEvent to the LogHandler to save. Receives a map of data,
	 * the 0th element of which should be the type of business event to be
	 * created.
	 *
	 * @param eventData
	 * @return
	 */
	public boolean saveEvent(Map<String, String> eventData) {
		BusinessEvent event = null;
		System.err.println(eventData.get("type"));
		switch (eventData.get("type")) {
		// TODO Get the exact type strings
		case "customerPriceUpdate":
			createCustPriceChange(eventData);
			break;
		case "mailDelivery":
			createMailDelivery(eventData);
			break;
		case "transportCostUpdate":
			createTransUpdate(eventData);
			break;
		case "transportDiscontinued":
			createDeleteRoute(eventData);
			break;
		default:
			return false;
		}
		// TODO uncomment this
		// boolean successful = handler.newEvent(event);
		// calculateBusinessFigures();
		// return successful;
		return false;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private BusinessEvent createMailDelivery(Map<String, String> data) {
		MailDelivery event = null;

		return event;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private BusinessEvent createCustPriceChange(Map<String, String> data) {
		CustomerPriceChange event = null;

		return event;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private BusinessEvent createDeleteRoute(Map<String, String> data) {
		DeleteRoute event = null;

		return event;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private BusinessEvent createOpenRoute(Map<String, String> data) {
		OpenNewRoute event = null;

		return event;
	}

	/**
	 * This method can both update an existing route's transport costs, or if
	 * the route does not exist it will create it.
	 * 
	 * @param data
	 * @return
	 */
	private BusinessEvent createTransUpdate(Map<String, String> data) {
		TransportUpdate event = null;

		return event;
	}

	/**
	 * Returns the current business event as a list of Strings
	 *
	 * @return
	 */
	public List<String> getCurrentEvent() {
		List<String> data = handler.getCurrentEvent().description();
		if (data == null) {
			data = new ArrayList<String>();
			data.add("No data to display");
		}
		return data;
	}

	/**
	 * Returns the business event that occurs after the current one as a list of
	 * Strings
	 *
	 * @return
	 */
	public List<String> nextEvent() {
		List<String> data = handler.getNextEvent().description();
		if (data == null) {
			data = new ArrayList<String>();
			data.add("No data to display");
		}
		return data;
	}

	/**
	 * Returns the business event that occurs before the current one as a list
	 * of Strings
	 *
	 * @return
	 */
	public List<String> previousEvent() {
		List<String> data = handler.getPreviousEvent().description();
		if (data == null) {
			data = new ArrayList<String>();
			data.add("No data to display");
		}
		return data;
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
