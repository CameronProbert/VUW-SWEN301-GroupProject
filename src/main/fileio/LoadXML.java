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

public class LoadXML {

	private List<BusinessEvent> events = new ArrayList<BusinessEvent>();


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

			NodeList nList = doc.getElementsByTagName("Event");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

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

					}
					else if(type.equals("Delete Route")){

					}
					else if(type.equals("Save Route")){

					}
					else if(type.equals("Customer Price Change")){

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}






	private List<Route> readRoutes(Element eElement) {
		// TODO read in the routes and return them. Will often be one route but just loop through all
		return null;
	}






	public List<BusinessEvent> getEvent() {
		// TODO Auto-generated method stub
		return events;
	}

}
