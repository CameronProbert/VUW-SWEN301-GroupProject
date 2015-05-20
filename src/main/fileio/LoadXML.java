package main.fileio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

import main.events.*;
import main.logic.InvalidLocationException;
import main.logic.Location;
import main.logic.NoDaysToShipException;
import main.logic.Route;
import main.logic.Route.DaysOfWeek;
import main.logic.Route.TransportType;

public class LoadXML {

	private List<BusinessEvent> events = new ArrayList<BusinessEvent>();
	private Set<Route> finalRoutes = new HashSet<Route>();
	private Set<Location> locations = new HashSet<Location>();


	public LoadXML(){
		try {

			File fXmlFile = new File("xml/saveFile");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("event");

			//System.out.println("----------------------------");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					List<Route> routes = readRoutes(eElement);

					String clerk = eElement.getElementsByTagName("clerk").item(0).getTextContent();
					String date = eElement.getElementsByTagName("date").item(0).getTextContent();

					String type = eElement.getAttribute("type");

					if(type.equals("Mail Delivery")){

						String origin = eElement.getElementsByTagName("origin").item(0).getTextContent();
						String destination = eElement.getElementsByTagName("destination").item(0).getTextContent();
						String weight = eElement.getElementsByTagName("weight").item(0).getTextContent();
						String volume = eElement.getElementsByTagName("volume").item(0).getTextContent();
						String priority = eElement.getElementsByTagName("priority").item(0).getTextContent();
						String revenue = eElement.getElementsByTagName("revenue").item(0).getTextContent();
						String timeTaken = eElement.getElementsByTagName("timeTaken").item(0).getTextContent();

						// create a mail delivery given the route and the strings found. Some strings will need to be converted to integers etc

						for(Route route: routes){
							Route r = findRoute(route);
							if(r==null)finalRoutes.add(route);
							else{
								r.addDeliveryTime(Double.parseDouble(timeTaken));
								routes.clear();
								routes.add(r);
							}
						}
						MailDelivery mail = new MailDelivery(clerk, date, origin, destination, Double.parseDouble(weight),
								Double.parseDouble(volume), Double.parseDouble(priority), Double.parseDouble(revenue), Double.parseDouble(timeTaken), routes);
						events.add(mail);

					}
					else if(type.equals("Transport Update")){

						String oldPPG = eElement.getElementsByTagName("oldPricePGram").item(0).getTextContent();
						String newPPG = eElement.getElementsByTagName("newPricePGram").item(0).getTextContent();
						String oldPPV = eElement.getElementsByTagName("oldPricePVolume").item(0).getTextContent();
						String newPPV = eElement.getElementsByTagName("newPricePVolume").item(0).getTextContent();




						routes.get(0).setPricePerGramTransport(Double.parseDouble(oldPPG));
						routes.get(0).setPricePerVolumeTransport(Double.parseDouble(oldPPV));

						Route r = findRoute(routes.get(0));
						System.out.println("-------------route read in ---------------------");
						System.out.println(routes.get(0).toString());
						System.out.println("-------------route currently stored ---------------------");
						for(Route ro: finalRoutes){
							System.out.println(ro.toString());
						}
						if(r!=null){
							r.setPricePerGramTransport(Double.parseDouble(newPPG));
							r.setPricePerVolumeTransport(Double.parseDouble(newPPV));
							System.out.println("-------------route editted transport ---------------------");
							routes.clear();
							routes.add(r);
						}

						// create a transport update event
						// create a route using the old values. Find the route in the list and modify it
						TransportUpdate transport = new TransportUpdate(clerk, date, Double.parseDouble(oldPPG), Double.parseDouble(newPPG),
								Double.parseDouble(oldPPV), Double.parseDouble(newPPV), routes);
						events.add(transport);
					}
					else if(type.equals("Delete Route")){
						// create a delete route event
						// delete the route from the route
						DeleteRoute delete = new DeleteRoute(clerk, date, routes);
						events.add(delete);

						removeRoute(routes.get(0));

					}
					else if(type.equals("New Route")){

						//create a new route event
						// add the route to the list of routes
						OpenNewRoute create = new OpenNewRoute(clerk, date, routes);
						events.add(create);

						finalRoutes.add(routes.get(0));
						//System.out.println("---------------------------------------route added");

					}
					else if(type.equals("Customer Price Change")){

						String oldPPG = eElement.getElementsByTagName("oldPricePGram").item(0).getTextContent();
						String newPPG = eElement.getElementsByTagName("newPricePGram").item(0).getTextContent();
						String oldPPV = eElement.getElementsByTagName("oldPricePVolume").item(0).getTextContent();
						String newPPV = eElement.getElementsByTagName("newPricePVolume").item(0).getTextContent();



						routes.get(0).setPricePerGramCustomer(Double.parseDouble(oldPPG));
						routes.get(0).setPricePerVolumeCustomer(Double.parseDouble(oldPPV));

						Route r = findRoute(routes.get(0));
						if(r!=null){
							r.setPricePerGramCustomer(Double.parseDouble(newPPG));
							r.setPricePerVolumeCustomer(Double.parseDouble(newPPV));
							//System.out.println("-------------route editted-------------customer");
							routes = new ArrayList<Route>();
							routes.add(r);
							//System.out.println(r.toString());
						}
						else{
							//System.out.println("-------------route not found-------------customer");
						}
						//System.out.println("length of final routes is " + finalRoutes.size());
						// create a customer price update event
						// create a route using the old values. Find the route in the list and modify it
						CustomerPriceChange change = new CustomerPriceChange(clerk, date, Double.parseDouble(oldPPG),
								Double.parseDouble(newPPG), Double.parseDouble(oldPPV), Double.parseDouble(newPPV), routes);
						events.add(change);
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeRoute(Route route) {
		Route r = null;
		for(Route rIter: finalRoutes){
			if(rIter.equals(route)) r = rIter;
		}
		if(r!=null)finalRoutes.remove(r);
	}

	private Route findRoute(Route route) {
		for(Route r : finalRoutes){
			//System.out.println("length of final routes is " + finalRoutes.size());
			if(route.equals(r))return r;
		}
		return null;
	}

	private List<Route> readRoutes(Element eElement) {
		List<Route> routes = new ArrayList<Route>();

		NodeList nList = eElement.getElementsByTagName("route");

		for(int i=0; i<nList.getLength(); i++){

			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element route = (Element) nNode;

				String origin = eElement.getElementsByTagName("origin").item(0).getTextContent();
				String destination = eElement.getElementsByTagName("destination").item(0).getTextContent();
				String transportType = eElement.getElementsByTagName("transportType").item(0).getTextContent();
				TransportType tranType = null;
				for(TransportType t: TransportType.values()){
					if(transportType.equals(t.toString())) tranType=t;
				}
				//String averageTime = eElement.getElementsByTagName("average time").item(0).getTextContent();
				String firmName = eElement.getElementsByTagName("firmName").item(0).getTextContent();
				String CPGTran = eElement.getElementsByTagName("costPGramForTransport").item(0).getTextContent();
				String CPVTran = eElement.getElementsByTagName("costPVolumeForTransport").item(0).getTextContent();
				String CPGCust = eElement.getElementsByTagName("costPGramToCustomer").item(0).getTextContent();
				String CPVCust = eElement.getElementsByTagName("costPVolumeToCustomer").item(0).getTextContent();
				String depFreq = eElement.getElementsByTagName("departureFrequency").item(0).getTextContent();

				DaysOfWeek[] days = getDays(route);
				// make a new route add to list
				Location or = getLocation(origin);
				Location des = getLocation(destination);
				try {
					Route r;
					r = new Route(or, des, firmName, tranType, Double.parseDouble(CPGTran),
							Double.parseDouble(CPVTran), Double.parseDouble(CPGCust),
							Double.parseDouble(CPVCust), Double.parseDouble(depFreq), days);

					routes.add(r);

				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (NoDaysToShipException e) {
					e.printStackTrace();
				} catch (InvalidLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return routes;
	}

	private Location getLocation(String origin) {
		for(Location location: locations){
			if(location.equals(origin)){
				return location;
			}
		}
		Location loc = new Location(origin);
		locations.add(loc);
		return loc;

	}

	private DaysOfWeek[] getDays(Element route) {
		List<DaysOfWeek> days = new ArrayList<DaysOfWeek>();
		NodeList nList = route.getElementsByTagName("departureDays");
		for(int i=0; i<nList.getLength(); i++){
			Node day = nList.item(i);
			if (day.getNodeType() == Node.ELEMENT_NODE) {
				Element dayElm = (Element) day;
				DaysOfWeek dWeek = null;
				String dayOfWeek = dayElm.getElementsByTagName("day").item(0).getTextContent();

				for(DaysOfWeek d: DaysOfWeek.values()){
					if(dayOfWeek.equals(d.toString())){
						dWeek = d;
					}
				}
				days.add(dWeek);
			}
		}
		DaysOfWeek[] dayDel = new DaysOfWeek[days.size()];
		for(int i=0; i<days.size(); i++){
			dayDel[i] = days.get(i);
		}
		return dayDel;
	}

	public Set<Route> getRoutes(){
		return finalRoutes;
	}

	public List<BusinessEvent> getEvents() {
		return events;
	}

	public Set<Location> getLocations() {
		return locations;
	}

}
