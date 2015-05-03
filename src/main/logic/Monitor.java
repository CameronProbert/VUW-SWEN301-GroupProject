package main.logic;

import java.util.List;

/**
 * The monitor is the main logic class of the program. It handles user input
 * passed in from the GUI and will .
 * 
 * @author Cameron Probert
 *
 */
public class Monitor {

	private final boolean verbose;

	private List<Clerk> allUsers;
	private Clerk currentUser;

	/**
	 * Creates the GUI and the monitor
	 * 
	 * @param verbose
	 */
	public Monitor(boolean verbose) {
		this.verbose = verbose;
		loadUsers();
		// TODO create GUI
	}

	private void loadUsers() {
		// TODO Load in a list of current users
		if (verbose) {
			System.out.println("Loading in users...");
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
	 * Makes a new user and logs them in
	 * 
	 * @param id
	 * @param password
	 * @param name
	 * @return
	 */
	private boolean makeNewUser(String id, String password, String name) {
		boolean validUser = false;
		if (verbose) {
			System.out.println("Making new user");
			System.out.println("ID: " + id);
			System.out.println("Name: " + name);
			System.out.println("Password: " + password);
		}
		return validUser;
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
