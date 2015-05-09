package main.events;

import java.util.List;

import main.logic.Route;

public class OpenNewRoute extends BusinessEvent {

	public OpenNewRoute (List<Route> routes) {
		this.routes = routes;
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "OpenNewRoute []" + stringRoutes();
	}

}
