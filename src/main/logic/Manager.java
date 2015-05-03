package main.logic;

/**
 * A manager is a kind of clerk that can do all the things a clerk can do as
 * well as create new accounts and read the log files.
 * 
 * @author Cameron Probert
 *
 */
public class Manager extends Clerk {

	/**
	 * Creates a new Manager with the given strings
	 * 
	 * @param name
	 * @param id
	 * @param password
	 */
	public Manager(String name, String id, String password) {
		super(name, id, password);
	}

	/**
	 * Returns the users credentials without the password as that is private
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type: Manager");
		sb.append("Name: " + name);
		sb.append("\nID: " + id);
		return sb.toString();
	}

	/**
	 * Returns the users credentials including the password. This should only
	 * ever be used for debugging
	 * 
	 * @return
	 */
	public String debugToString() {
		StringBuilder sb = new StringBuilder();
		System.err
				.println("debugToString() method has been called. This should never happen in the final version of this program");
		sb.append("Type: Manager");
		sb.append("Name: " + name);
		sb.append("\nID: " + id);
		sb.append("\nPassword: " + password);
		return sb.toString();
	}

}
