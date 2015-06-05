package main.logic;

import java.util.*;
import java.util.Map.Entry;

import main.controllers.UIController;
import main.events.*;
import main.fileio.LogHandler;
import main.fileio.NoRegisteredUsersException;
import main.fileio.UserIO;
import main.gui.GUI;
import main.logic.Route.DaysOfWeek;
import main.logic.Route.TransportType;

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
		for(Location loc: locations){
			loc.attachMonitor(this);
		}
		routes = handler.getRoutes();
		initialiseGUI();
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
				if (!deliveryProfit.containsKey(r)) {
					Double p = deliveryProfit.get(r);
					deliveryProfit.put(r, p + profit);
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
	public void calculateBusinessFigures() {
		double revenue = calculateRevenue();
		double expenditure = calculateExpenditure();
		try {
			controller.setTotalTransportFigures(revenue, expenditure, handler
					.getEvents().size());
		} catch (NullPointerException e) {
			controller.setTotalTransportFigures(revenue, expenditure, 0);
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
		if(deliveries.size()==0){
			System.out.println("no dels");
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
	public boolean saveEvent(Route route, Map<String, String> eventData) {
		BusinessEvent event = null;
		switch (eventData.get("type")) {
		case "customerPriceUpdate":
			event = createCustPriceChange(route, eventData);
			break;
		case "mailDelivery":
			try {
				event = createMailDelivery(eventData);
			} catch (LocationsNotConnectedException e) {
				// TODO Update user somehow
				return false;
			}
			break;
		case "transportCostUpdate":
			event = createTransUpdate(route, eventData);
			break;
		case "createRoute":
			event = createOpenRoute(eventData);
			break;
		case "transportDiscontinued":
			event = createDeleteRoute(route, eventData);
			break;
		default:
			return false;
		}
		boolean successful = handler.newEvent(event);
		calculateBusinessFigures();
		return successful;
	}

	/**
	 * Creates a mail delivery event
	 * TODO handle if we cannot find a route between two locations
	 * @param data
	 * @return
	 * @throws LocationsNotConnectedException 
	 */
	public BusinessEvent createMailDelivery(Map<String, String> data) throws LocationsNotConnectedException {
		MailDelivery event = null;
		String clerkName = currentUser.getName();
		String time = data.get("time");
		String origin = data.get("origin");
		String destination = data.get("destination");
		double weight = Double.parseDouble(data.get("weight"));
		double volume = Double.parseDouble(data.get("volume"));

		// Convert the priority to 0 or 1 for standard and air respectively
		double priority = 0;
		double revenue = 0;
		List<Route> routes = null;
		if (data.get("priority").equals("Air")) {
			DijkAir air = new DijkAir(findLocation(origin),
					findLocation(destination), weight, volume);
			air.initialiseGraph(locations);
			routes = air.getBestRoute();
			revenue = air.getCostOfRoute();
			priority = 1;
		} else {
			DijkStandard standard = new DijkStandard(findLocation(origin),
					findLocation(destination), weight, volume);
			standard.initialiseGraph(locations);
			routes = standard.getBestRoute();
			if (routes == null || routes.isEmpty()) {
				System.err
				.println("ERROR ROUTES IS NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			}
			revenue = standard.getCostOfRoute();
			priority = 0;
		}
		if (routes.isEmpty()){
			throw new LocationsNotConnectedException();
		}
		double expectedTime = calculateTime(routes);
		event = new MailDelivery(clerkName, time, origin, destination, weight,
				volume, priority, revenue, expectedTime, routes);
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
	 * @param route
	 *
	 * @param data
	 * @return
	 */
	public BusinessEvent createCustPriceChange(Route route,
			Map<String, String> data) {
		CustomerPriceChange event = null;
		String clerkName = currentUser.getName();
		String date = data.get("time");
		double oldGr = route.getPricePerGramCustomer();
		double newGr = Double.parseDouble(data.get("customerNewPricePerGram"));
		double oldVol = route.getPricePerVolumeCustomer();
		double newVol = Double
				.parseDouble(data.get("customerNewPricePerCubic"));
		List<Route> routes = new ArrayList<Route>();
		routes.add(route);
		route.setPricePerGramCustomer(newGr);
		route.setPricePerVolumeCustomer(newVol);
		event = new CustomerPriceChange(clerkName, date, oldGr, newGr, oldVol,
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
	 * Finds the location associated with the name of a location, if the
	 * location does not exist then create it
	 *
	 * @param locationName
	 * @return
	 */
	private Location findLocation(String locationName) {
		for (Location l : locations) {
			if (l.getName().equalsIgnoreCase(locationName)) {
				return l;
			}
		}
		Location loc = new Location(locationName);
		loc.attachMonitor(this);
		return loc;
	}

	/**
	 * Creates a delete route event
	 *
	 * @param route
	 *
	 * @param data
	 * @return
	 */
	public BusinessEvent createDeleteRoute(Route route, Map<String, String> data) {
		List<Route> routes = new ArrayList<Route>();
		routes.add(route);
		this.routes.remove(route);
		return new DeleteRoute(currentUser.name, data.get("time"), routes);
	}

	/**
	 * Creates an openroute event
	 *
	 * @param data
	 * @return
	 */
	public BusinessEvent createOpenRoute(Map<String, String> data) {
		OpenNewRoute event = null;
		Location origin = findLocation(data.get("origin"));
		Location destination = findLocation(data.get("destination"));
		String transportFirm = data.get("transportFirm");
		TransportType transportType = TransportType.valueOf(data
				.get("transportType"));
		double costWeightTrans = Double.parseDouble(data
				.get("transportsCostPerGram"));
		double costVolTrans = Double.parseDouble(data
				.get("transportsCostPerCubic"));
		double costWeightCust = Double.parseDouble(data
				.get("customerPricePerGram"));
		double costVolCust = Double.parseDouble(data
				.get("customerPricePerCubic"));
		double frequency = Double.parseDouble(data.get("frequency"));
		DaysOfWeek day = DaysOfWeek.valueOf(data.get("transportDay"));
		Route route = null;
		try {
			route = new Route(origin, destination, transportFirm,
					transportType, costWeightTrans, costVolTrans,
					costWeightCust, costVolCust, frequency, day);
		} catch (NoDaysToShipException e) {

		} catch (InvalidLocationException e) {

		}
		if (route == null) {
			System.out
			.println("CREATED ROOT IS NULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
		origin.addOutbound(route);
		origin.addInbound(route);
		List<Route> routes = new ArrayList<Route>();
		routes.add(route);
		this.routes.add(route);
		event = new OpenNewRoute(currentUser.name, data.get("time"), routes);
		return event;
	}

	/**
	 * This method can both update an existing route's transport costs, or if
	 * the route does not exist it will create it.
	 *
	 * @param route
	 *
	 * @param data
	 * @return
	 */
	public BusinessEvent createTransUpdate(Route route, Map<String, String> data) {
		TransportUpdate event = null;
		List<Route> routes = new ArrayList<Route>();
		routes.add(route);
		double transVol = Double.parseDouble(data
				.get("transportNewCostPerCubic"));
		double transGram = Double.parseDouble(data
				.get("transportNewCostPerGram"));
		event = new TransportUpdate(currentUser.getName(), data.get("time"),
				route.getPricePerVolumeTransport(), transGram,
				route.getPricePerVolumeTransport(), transVol, routes);
		route.setPricePerGramTransport(transGram);
		route.setPricePerVolumeTransport(transVol);
		return event;
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
	 * Given a route, finds all the relevant statistics and pushes them to the
	 * GUI to be displayed
	 *
	 * @param route
	 */
	public void getEventsForRoute(Route route) {
		double revenue = route.getRevenue(getMailEvents()); // TODO double check the mail delivery list is populated
		double expenditure = route.getExpenditure(getMailEvents());
		int numOfEvents = route.getNumEvents(getMailEvents());
		double averageDeliveryTime = 0;
		boolean isCritical = false;
		for(Route r: findCriticalRoutes()){
			if(route.equals(r)){
				isCritical = true;
			}
		}

		controller.setRouteFigures(revenue, expenditure, numOfEvents,
				isCritical);
	}

	/**
	 * Given a location will return the info about it and call the mehod to update
	 * @param loc
	 */
	public void getEventsForLocation(Location loc){
		double totalVol = loc.getTotalVolume();
		double totalWeight = loc.getTotalWeight();
		int totalNumItems = loc.getDeliveriesIn();
		controller.setLocationFigures(totalVol, totalWeight, totalNumItems);
	}

	public List<MailDelivery> getNotReceivedDels(){
		List<MailDelivery> unrecMail = new ArrayList<MailDelivery>();
		for(MailDelivery m : getMailEvents()){
			if(m.getTimeTaken()==0){
				unrecMail.add(m);
			}
		}
		return unrecMail;
	}

	public void setTimeTaken(MailDelivery m, String time){
		m.setTimeTaken(time);
	}

	public List<String> getMailDelGeneric(){
		List<String> descriptions = new ArrayList<String>();
		for(MailDelivery m : getMailEvents()){
			if(!descriptions.contains(m.shortDes())){
				descriptions.add(m.shortDes());
			}
		}
		return descriptions;
	}

	public double getAverageDeliveryTime(String delDes){
		int totNum = 0;
		double avTime = 0;
		for(MailDelivery m : getMailEvents()){
			if(m.shortDes().equals(delDes)){
				totNum ++;
				avTime += m.getTimeTaken();
			}
		}
		return avTime/totNum;
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
		if (findUser(id) == null) {
			return false;
		} else if (isManager) {
			user = new Manager(name, id, password);
		} else {
			user = new Clerk(name, id, password);
		}
		allUsers.add(user);
		UserIO.saveUsers(allUsers);
		return (user != null);
	}

	/**
	 * Finds the user with the specified id
	 *
	 * @param id
	 * @return
	 */
	private Clerk findUser(String id) {
		for (Clerk c : allUsers) {
			if (c.getId().equals(id)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Removes the user with the specified id
	 *
	 * @param id
	 * @return
	 */
	public boolean removeUser(String id) {
		Clerk user = findUser(id);
		if (user != null) {
			allUsers.remove(user);
			UserIO.saveUsers(allUsers);
			return true;
		}
		return false;
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
