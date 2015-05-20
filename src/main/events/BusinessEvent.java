package main.events;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Element;

import main.logic.Clerk;
import main.logic.Route;
import main.logic.Route.DaysOfWeek;
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
	public abstract Element toXML(Document doc);

	protected void routesToXML(Document doc, BusinessEvent event, Element elm){
		for(Route r: event.getRoutes()){
			// TODO will need to go through and uncomment a lot of this also check for ints that need to be parsed to Strings

			Element route = doc.createElement("route");
			elm.appendChild(route);

			Element origin = doc.createElement("origin");
			origin.appendChild(doc.createTextNode(r.getOrigin().getName()));
			route.appendChild(origin);

			Element destination = doc.createElement("destination");
			destination.appendChild(doc.createTextNode(r.getDestination().getName()));
			route.appendChild(destination);

			Element transportType = doc.createElement("transportType");
			transportType.appendChild(doc.createTextNode(r.getTransportType().toString()));
			route.appendChild(transportType);

			// TODO this has changed need to revise
			//Element averageTime = doc.createElement("averageTime");
			//averageTime.appendChild(doc.createTextNode(String.valueOf(r.getAverageTimeToDeliver())));
			//route.appendChild(averageTime);

			Element firmName = doc.createElement("firmName");
			firmName.appendChild(doc.createTextNode(r.getTransportFirm()));
			route.appendChild(firmName);

			Element gramTransport = doc.createElement("costPGramForTransport");
			gramTransport.appendChild(doc.createTextNode(String.valueOf(r.getPricePerGramTransport())));
			route.appendChild(gramTransport);

			Element volumeTransport = doc.createElement("costPVolumeForTransport");
			volumeTransport.appendChild(doc.createTextNode(String.valueOf(r.getPricePerVolumeTransport())));
			route.appendChild(volumeTransport);

			Element gramCustomer = doc.createElement("costPGramToCustomer");
			gramCustomer.appendChild(doc.createTextNode(String.valueOf(r.getPricePerGramCustomer())));
			route.appendChild(gramCustomer);

			Element volumeCustomer = doc.createElement("costPVolumeToCustomer");
			volumeCustomer.appendChild(doc.createTextNode(String.valueOf(r.getPricePerVolumeCustomer())));
			route.appendChild(volumeCustomer);

			// TODO this may not be the best way to display this information
			Element departureDays = doc.createElement("departureDays");
			route.appendChild(departureDays);
			for(DaysOfWeek day : r.getDays()){
				Element dayElm = doc.createElement("day");
				dayElm.appendChild(doc.createTextNode(day.toString()));
				departureDays.appendChild(dayElm);
			}

			Element departureFreq = doc.createElement("departureFrequency");
			departureFreq.appendChild(doc.createTextNode(String.valueOf(r.getDepartureFrequency())));
			route.appendChild(departureFreq);

		}
	}

	/**
	 * returns the routes associated with this business event
	 * @return
	 */
	public List<Route> getRoutes(){
		return this.routes;
	}

	/**
	 * returns a long desciption of all the routes
	 * @return
	 */
	public String stringRoutes(){
		String routeString = "";
		for(Route r: routes){
			routeString +=  r.toString() + "\n";
		}
		return routeString;
	}


	public String description(){
		if(this instanceof MailDelivery){
			return toString();
		}
		if(routes.size()==1){
			return toString() +"Route affected : \n------------------------------------\n"+ stringRoutes();
		}
		return toString() +"Routes affected : \n------------------------------------\n"+ stringRoutes();
	}

}
