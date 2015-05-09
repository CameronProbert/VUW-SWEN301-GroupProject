package main.events;

import java.util.List;

import main.logic.Clerk;
import main.logic.Route;
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
	protected List<Route> routes;  // this will be added when a route class is added

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

	/**
	 * returns the routes associated with this business event
	 * @return
	 */
	public List<Route> getRoutes(){
		return this.routes;
	}

}
