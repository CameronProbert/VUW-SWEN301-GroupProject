package tests.fileio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import main.events.*;
import main.fileio.LoadXML;
import main.fileio.SaveXML;
import main.logic.Location;
import main.logic.NoDaysToShipException;
import main.logic.Route;
import main.logic.Route.DaysOfWeek;
import main.logic.Route.TransportType;



public class FileTests {

	Set<Route> routes;

	public FileTests(){
		List<BusinessEvent> events = eventsOne();
		SaveXML save = new SaveXML();
		save.save(events);

		LoadXML load = new LoadXML();
		events = load.getEvents();
		for(BusinessEvent e: events){
			System.out.println(e.description());
		}

		routes = load.getRoutes();
		testRoutes();
		testEvents(events);
	}

	private void testEvents(List<BusinessEvent> events) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testRoutes() {
		assertTrue(routes.size()==1);
		Location origin = new Location("Auckland");
		Location destination = new Location("Wellington");
		for(Route r: routes){
			assertTrue(r.getOrigin().equals(origin));
			assertTrue(r.getDestination().equals(destination));
			assertTrue(r.getTransportFirm().equals("Transport firm A"));
			assertTrue(r.getPricePerGramCustomer() == 20);
			assertTrue(r.getPricePerVolumeCustomer() == 20);
			assertTrue(r.getPricePerGramTransport() == 20);
			assertTrue(r.getPricePerVolumeTransport() == 20);
			assertTrue(r.getPricePerGramCustomer() == 20);
			assertTrue(r.getDepartureFrequency() == 2);
			TransportType tt = TransportType.Standard;
			assertTrue(r.getTransportType().equals(tt));
			Set<DaysOfWeek> days = new HashSet<DaysOfWeek>();
			days.add(DaysOfWeek.Monday);
			assertTrue(r.getDays().equals(days));

		}
	}

	public static List<BusinessEvent> eventsOne(){
		Location origin = new Location("Auckland");
		Location destination = new Location("Wellington");
		TransportType tt = TransportType.Standard;
		String clerk = "Sammy";
		String date = "10/12/2014 10:53";
		List<Route> routes = new ArrayList<Route>();
		try {
			Route route = new Route(origin, destination, "Transport firm A", tt, 10, 10, 10, 10, 2, DaysOfWeek.Monday);
			routes.add(route);
		} catch (NoDaysToShipException e) {
			e.printStackTrace();
		}
		List<BusinessEvent> events = new ArrayList<BusinessEvent>();

		OpenNewRoute open = new OpenNewRoute(clerk, date, routes);
		events.add(open);

		MailDelivery mail = new MailDelivery(clerk, date, "Auckland", "Wellington", 20, 20, 1, 20, 400, routes);
		events.add(mail);

		CustomerPriceChange change = new CustomerPriceChange(clerk, date, 10, 20, 10, 20, routes);
		events.add(change);

		try {
			Route route = new Route(origin, destination, "Transport firm A", tt, 10, 10, 20, 20, 2, DaysOfWeek.Monday);
			List<Route> routes2 = new ArrayList<Route>();
			routes2.add(route);

			TransportUpdate update = new TransportUpdate(clerk, date, 10, 20, 10, 20, routes2);
			events.add(update);
		} catch (NoDaysToShipException e) {
			e.printStackTrace();
		}


		return events;
	}

	public static void main(String[] args){
		new FileTests();
	}

}
