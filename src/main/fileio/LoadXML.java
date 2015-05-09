package main.fileio;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

import main.events.BusinessEvent;
import main.logic.Route;
import main.logic.Route.DaysOfWeek;

public class LoadXML {

	private List<BusinessEvent> events = new ArrayList<BusinessEvent>();
	private List<Route> routes = new ArrayList<Route>();


	public LoadXML(){
		try {

			File fXmlFile = new File("saveFile");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("event");

			System.out.println("----------------------------");

			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					List<Route> route = readRoutes(eElement);

					String type = eElement.getAttribute("type");

					if(type.equals("Mail Delivery")){

						String origin = eElement.getElementsByTagName("origin").item(0).getTextContent();
						String destination = eElement.getElementsByTagName("destination").item(0).getTextContent();
						String weight = eElement.getElementsByTagName("weight").item(0).getTextContent();
						String volume = eElement.getElementsByTagName("volume").item(0).getTextContent();
						String priority = eElement.getElementsByTagName("priority").item(0).getTextContent();
						String revenue = eElement.getElementsByTagName("revenue").item(0).getTextContent();
						String timeTaken = eElement.getElementsByTagName("time taken").item(0).getTextContent();

						// create a mail delivery given the route and the strings found. Some strings will need to be converted to integers etc


					}
					else if(type.equals("Transport Update")){

						String oldPPG = eElement.getElementsByTagName("old price/gram").item(0).getTextContent();
						String newPPG = eElement.getElementsByTagName("new price/gram").item(0).getTextContent();
						String oldPPV = eElement.getElementsByTagName("old price/volume").item(0).getTextContent();
						String newPPV = eElement.getElementsByTagName("new price/volume").item(0).getTextContent();

						// create a transport update event
						// create a route using the old values. Find the route in the list and modify it

					}
					else if(type.equals("Delete Route")){

						// create a delete route event
						// delete the route from the route


					}
					else if(type.equals("New Route")){

						//create a new route event
						// add the route to the list of routes


					}
					else if(type.equals("Customer Price Change")){

						String oldPPG = eElement.getElementsByTagName("old price/gram").item(0).getTextContent();
						String newPPG = eElement.getElementsByTagName("new price/gram").item(0).getTextContent();
						String oldPPV = eElement.getElementsByTagName("old price/volume").item(0).getTextContent();
						String newPPV = eElement.getElementsByTagName("new price/volume").item(0).getTextContent();

						// create a customer price update event
						// create a route using the old values. Find the route in the list and modify it
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}






	private List<Route> readRoutes(Element eElement) {
		// TODO read in the routes and return them. Will often be one route but just loop through all

		NodeList nList = eElement.getElementsByTagName("route");

		for(int i=0; i<nList.getLength(); i++){

			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element route = (Element) nNode;

				String origin = eElement.getElementsByTagName("origin").item(0).getTextContent();
				String transportType = eElement.getElementsByTagName("transport type").item(0).getTextContent();
				String averageTime = eElement.getElementsByTagName("average time").item(0).getTextContent();
				String firmName = eElement.getElementsByTagName("firm name").item(0).getTextContent();
				String CPGTran = eElement.getElementsByTagName("cost/gram for transport").item(0).getTextContent();
				String CPVTran = eElement.getElementsByTagName("cost/volume for transport").item(0).getTextContent();
				String CPGCust = eElement.getElementsByTagName("cost/gram to customer").item(0).getTextContent();
				String CPVCust = eElement.getElementsByTagName("cost/volume to customer").item(0).getTextContent();
				String depFreq = eElement.getElementsByTagName("departure frequency").item(0).getTextContent();

				List<DaysOfWeek> days = getDays(route);
				// make a new route add to list
			}

		}


		return null;
	}

	private List<DaysOfWeek> getDays(Element route) {

		List<DaysOfWeek> days = new ArrayList<DaysOfWeek>();
		NodeList nList = route.getElementsByTagName("departure days");
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

		return days;
	}






	private List<Route> getRoutes(){
		return routes;
	}

	public List<BusinessEvent> getEvent() {
		return events;
	}

}
