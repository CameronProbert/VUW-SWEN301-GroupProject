package main.logic;

import java.util.List;
import java.util.Set;

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

	/**
	 * Creates the GUI and the monitor
	 * 
	 * @param verbose
	 */
	public Monitor() {
		loadUsers();
		// handler = new LogHandler();
		// busEvents = handler.getBusinessEvents();
		// routes = handler.getRoutes();
		initialiseGUI();
	}
	
	private void initialiseGUI() {
		gui = new GUI();
	}

	public boolean save(){
		// TODO Call save with on handler
		// return handler.save();
		return false;
	}

	/**
	 * Loads a list of the users in from a file
	 */
	private void loadUsers() {
		try {
			allUsers = UserIO.loadUsers();
		} catch (NoRegisteredUsersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * Logs out the current user and takes the program back to the log in state
	 */
	public void logOut() {
		// TODO run a confirm dialog
		currentUser = null;
		// TODO return to log in screen
	}

	/**
	 * Makes a new user
	 * 
	 * @param id
	 * @param password
	 * @param name
	 * @return
	 */
	private boolean makeNewUser(String id, String password, String name) {
		boolean validUser = false;
		return validUser;
		//TODO possibly log the user in
	}

	/**
	 * Returns the saved set of locations
	 * @return
	 */
	public Set<Location> getLocations() {
		return locations;
	}

	/**
	 * Sets the list of locations
	 * @param locations
	 */
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	/**
	 * Adds a locations to the list of locations
	 * @param locations
	 */
	public void addLocations(Location... locations) {
		for (Location l : locations){
			this.locations.add(l);
		}
	}

	/**
	 * Adds a locations to the list of locations
	 * @param locations
	 */
	public void rmLocations(Location... locations) {
		for (Location l : locations){
			this.locations.remove(l);
		}
	}

	/**
	 * Returns the set of routes stored in this class
	 * @return
	 */
	public Set<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}

	/**
	 * Adds a locations to the list of locations
	 * @param locations
	 */
	public void addRoutes(Route... routes) {
		for (Route r : routes){
			this.routes.add(r);
		}
	}

	/**
	 * Adds a locations to the list of locations
	 * @param locations
	 */
	public void rmRoutes(Route... routes) {
		for (Route r : routes){
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

}
