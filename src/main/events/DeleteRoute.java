package main.events;

import java.util.List;

import main.logic.Route;

public class DeleteRoute extends BusinessEvent {

	/**
	 * Constructor accepts specific routes to delete
	 * @param r: route
	 */
	public DeleteRoute(List<Route> r) {
		routes = r;
	}


	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String toString() {
		return "DeleteRoute []" + stringRoutes();
	}

}
