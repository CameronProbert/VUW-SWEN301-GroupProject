package main.logic;

import java.util.*;
import java.util.Map.Entry;

import main.controllers.UIController;
import main.events.*;
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
	 */
	public Monitor() {
		loadUsers();
		handler = new LogHandler();
		locations = handler.getLocations();
		routes = handler.getRoutes();
		initialiseGUI();
		calculateBusinessFigures();
	}

	/**
	 * Finds all the routes that result in a loss to KPS
	 * 
	 * @return
	 */
	public List<Route> findCriticalRoutes() {
		List<Route> criticalRoutes = new ArrayList<Route>();
		List<MailDelivery> deliveries = getMailEvents();
		Map<Route, Double> deliveryProfit = new HashMap<Route, Double>();
		for (MailDelivery del : deliveries) {
			for (Route r : del.getRoutes()) {
				double costToCust = del.getWeight()
						* r.getPricePerGramCustomer() + del.getVolume()
						* r.getPricePerVolumeCustomer();
				double costToTrans = del.getWeight()
						* r.getPricePerGramTransport() + del.getVolume()
						* r.getPricePerVolumeTransport();
				double profit = costToCust - costToTrans;
				if (!deliveryProfit.containsKey(r)){
					deliveryProfit.put(r, deliveryProfit.get(r)+profit);
				} else {
					deliveryProfit.put(r, profit);
				}				
			}
		}
		for (Entry<Route, Double> e : deliveryProfit.entrySet()) {
			if (e.getValue() <= 0) {
				criticalRoutes.add(e.getKey());
			}
		}
		return criticalRoutes;
	}

	/**
	 * Recalculates the business figures and updates the gui with them
	 */
	private void calculateBusinessFigures() {
		double revenue = calculateRevenue();
		double expenditure = calculateExpenditure();
		try {
			controller.setTotalTransportFigures(revenue, expenditure, handler.getEvents().size());
		} catch (NullPointerException e) {
			controller.setTotalTransportFigures(revenue, expenditure, 0);		}
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
	 * Returns a list of Mail Delivery Events
	 * 
	 * @return
	 */
	public List<MailDelivery> getMailEvents() {
		List<MailDelivery> deliveries = new ArrayList<MailDelivery>();
		List<BusinessEvent> events = handler.getEvents();
		for (BusinessEvent event : events) {
			if (event instanceof MailDelivery) {
				deliveries.add((MailDelivery) event);
			}
		}
		return deliveries;
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
		switch (eventData.get("type")) {
		case "customerPriceUpdate":
			event = createCustPriceChange(eventData);
			break;
		case "mailDelivery":
			event = createMailDelivery(eventData);
			break;
		case "transportCostUpdate":
			if (routeExists(eventData.get("origin"),
					eventData.get("destination"),
					eventData.get("transportCompany"),
					eventData.get("priority"))) {
				event = createTransUpdate(eventData);
			} else {
				event = createOpenRoute(eventData);
			}
			break;
		case "transportDiscontinued":
			event = createDeleteRoute(eventData);
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
	 * Creates a mail delivery event
	 * 
	 * @param data
	 * @return
	 */
	public BusinessEvent createMailDelivery(Map<String, String> data) {
		MailDelivery event = null;
		String clerkName = data.get("name");
		String date = data.get("date");
		String origin = data.get("origin");
		String destination = data.get("destination");
		double weight = Double.parseDouble(data.get("weight"));
		double volume = Double.parseDouble(data.get("volume"));

		// Convert the priority to 0 or 1 for standard and air respectively
		double priority = 0;
		double revenue = 0;
		List<Route> routes = null;
		if (data.get("priority").equals("air")) {
			DijkAir air = new DijkAir(findLocation(origin),
					findLocation(destination), weight, volume);
			air.initialiseGraph(locations);
			routes = air.getBestRoute();
			revenue = air.getCostOfRoute();
			priority = 1;
		} else {
			DijkAir standard = new DijkAir(findLocation(origin),
					findLocation(destination), weight, volume);
			standard.initialiseGraph(locations);
			routes = standard.getBestRoute();
			revenue = standard.getCostOfRoute();
			priority = 0;
		}
		double time = calculateTime(routes);
		event = new MailDelivery(clerkName, date, origin, destination, weight,
				volume, priority, revenue, time, routes);
		return event;
	}

	/**
	 * Calculates the time that it should take to send the package
	 * 
	 * @param routes
	 * @return
	 */
	private double calculateTime(List<Route> routes) {
		double sum = 0;
		for (Route r : routes) {
			sum += r.getAverageTimeToDeliver();
		}
		return sum;
	}

	/**
	 * Creates a customer change price event
	 * 
	 * @param data
	 * @return
	 */
	public BusinessEvent createCustPriceChange(Map<String, String> data) {
		CustomerPriceChange event = null;
		String clerk = data.get("name");
		String date = data.get("date");
		Location origin = findLocation(data.get("origin"));
		Location destination = findLocation(data.get("destination"));
		double oldGr = Double.parseDouble(data.get(""));
		double newGr = Double.parseDouble(data.get(""));
		double oldVol = Double.parseDouble(data.get(""));
		double newVol = Double.parseDouble(data.get(""));
		List<Route> routes = findRoutes(origin, destination,
				data.get("priority"));
		event = new CustomerPriceChange(clerk, date, oldGr, newGr, oldVol,
				newVol, routes);
		return event;
	}

	/**
	 * Finds the route associated with the origin and destination locations
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	private List<Route> findRoutes(Location origin, Location destination,
			String priority) {
		List<Route> routeList = new ArrayList<Route>();
		for (Route r : routes) {
			if (r.getOrigin().equals(origin)
					&& r.getDestination().equals(destination)) {
				routeList.add(r);
			}
		}
		return routeList;
	}

	/**
	 * Finds the location associated with the name of a location
	 * 
	 * @param locationName
	 * @return
	 */
	private Location findLocation(String locationName) {
		for (Location l : locations) {
			if (l.getName().equals(locationName)) {
				return l;
			}
		}
		return null;
	}

	/**
	 * Creates a delete route event
	 * 
	 * @param data
	 * @return
	 */
	public BusinessEvent createDeleteRoute(Map<String, String> data) {
		DeleteRoute event = null;

		return event;
	}

	/**
	 * Creates an openroute event
	 * 
	 * @param data
	 * @return
	 */
	public BusinessEvent createOpenRoute(Map<String, String> data) {
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
	public BusinessEvent createTransUpdate(Map<String, String> data) {
		TransportUpdate event = null;

		return event;
	}

	/**
	 * Determines whether a route exists
	 * 
	 * @param priority
	 * @param transportCompany
	 * @param destination
	 * @param origin
	 * 
	 * @return
	 */
	private boolean routeExists(String origin, String destination,
			String transportCompany, String priority) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> getMostRecentEvent() {
		BusinessEvent event = handler.getNewestEvent();
		if (event == null) {
			List<String> noData = new ArrayList<String>();
			noData.add("No data to Display");
			return noData;
		}
		List<String> data = event.description();
		return data;
	}

	/**
	 * Returns the current business event as a list of Strings
	 *
	 * @return
	 */
	public List<String> getCurrentEvent() {
		BusinessEvent event = handler.getCurrentEvent();
		if (event == null) {
			List<String> noData = new ArrayList<String>();
			noData.add("No data to Display");
			return noData;
		}
		List<String> data = event.description();
		return data;
	}

	/**
	 * Returns the business event that occurs after the current one as a list of
	 * Strings
	 *
	 * @return
	 */
	public List<String> nextEvent() {
		BusinessEvent event = handler.getNextEvent();
		if (event == null) {
			List<String> noData = new ArrayList<String>();
			noData.add("No data to Display");
			return noData;
		}
		List<String> data = event.description();
		return data;
	}

	/**
	 * Returns the business event that occurs before the current one as a list
	 * of Strings
	 *
	 * @return
	 */
	public List<String> previousEvent() {
		BusinessEvent event = handler.getPreviousEvent();
		if (event == null) {
			List<String> noData = new ArrayList<String>();
			noData.add("No data to Display");
			return noData;
		}
		List<String> data = event.description();
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
			sb.append(currentUser.toString());
		} else {
			sb.append("Nobody logged in.");
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
