package main.events;

import main.logic.Route;

public class DeleteRoute extends BusinessEvent {

	Route route;

	/**
	 * Constructor accepts a specific route to delete
	 * @param r: route
	 */
	public DeleteRoute(Route r) {
		route = r;
	}


	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
