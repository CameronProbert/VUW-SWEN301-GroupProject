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
	}

	/**
	 * Logs in a user if they have the correct credentials
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public Clerk logIn(String id, String password) throws InvalidLoginException{
		Clerk user = null;
		for (Clerk clerk : allUsers){
			if (clerk.equals(id, password)){
				user = clerk;
				currentUser = clerk;
				break;
			}
		}
		return user;
	}
	
	public void logOut(){
		
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

}
