package main.events;

import main.logic.Route;

public class OpenNewRoute extends BusinessEvent {

	private Route route;

	public OpenNewRoute (Route r) {
		route = r;
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
