package main.events;

import main.logic.Clerk;
/**
 * Abstract class to describe all business events. Business events are used for statistic
 * computation and they are displayed in the xml file
 * 
 * @author Francine
 *
 */

public abstract class BusinessEvent {

	protected Clerk clerk; 
	protected String date;
	// private Route route;  // this will be added when a route class is added
	
	/**
	 * returns the date the event was created
	 * @return date
	 */
	public String dateDone(){
		return date;
	}
	
	/**
	 * returns the xml description of the business event which is saved to file
	 * @return
	 */
	public abstract String toXML();
}