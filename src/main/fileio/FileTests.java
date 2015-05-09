package main.fileio;

import java.util.ArrayList;
import java.util.List;

import main.events.*;
import main.logic.Location;
import main.logic.NoDaysToShipException;
import main.logic.Route;
import main.logic.Route.DaysOfWeek;
import main.logic.Route.TransportType;

public class FileTests {

	public FileTests(){
		List<BusinessEvent> events = eventsOne();
		SaveXML save = new SaveXML();
		save.save(events);
	}

	public static List<BusinessEvent> eventsOne(){
		Location origin = new Location("Auckland");
		Location destination = new Location("Wellington");
		TransportType tt = TransportType.Standard;
		List<Route> routes = new ArrayList<Route>();
		try {
			Route route = new Route(origin, destination, "Transport firm A", tt, 10, 10, 20, 20, 2, DaysOfWeek.Monday);
			routes.add(route);
		} catch (NoDaysToShipException e) {
			e.printStackTrace();
		}
		List<BusinessEvent> events = new ArrayList<BusinessEvent>();
		MailDelivery mail = new MailDelivery("Auckland", "Wellington", 20, 20, 1, 20, 40, routes);
		events.add(mail);

		CustomerPriceChange change = new CustomerPriceChange(10, 20, 10, 20, routes);
		events.add(change);

		DeleteRoute delete = new DeleteRoute(routes);
		events.add(delete);

		OpenNewRoute open = new OpenNewRoute(routes);
		events.add(open);

		TransportUpdate update = new TransportUpdate(10, 20, 10, 20, routes);
		events.add(update);

		return events;
	}

	public static void main(String[] args){
		new FileTests();
	}

}
