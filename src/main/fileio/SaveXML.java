package main.fileio;

import java.util.List;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.events.*;
import main.logic.Route;
import main.logic.Route.DaysOfWeek;

public class SaveXML {

	private Document doc;
	private Element rootElement;


	public SaveXML(List<BusinessEvent> events) {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();

			rootElement = doc.createElement("business events");
			doc.appendChild(rootElement);


			for(BusinessEvent event: events){
				if(event instanceof CustomerPriceChange){
					saveCustomerPriceChange((CustomerPriceChange)event);
				}
				else if (event instanceof OpenNewRoute){
					saveOpenNewRoute((OpenNewRoute)event);
				}
				else if (event instanceof DeleteRoute){
					saveDeleteRoute((DeleteRoute)event);
				}
				else if (event instanceof TransportUpdate){
					saveTransportUpdate((TransportUpdate)event);
				}
				else if (event instanceof MailDelivery){
					saveMailDelivery((MailDelivery)event);
				}
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}





	}

	private void saveMailDelivery(MailDelivery event) {
		// staff elements
		Element mail = doc.createElement("Mail Delivery");
		rootElement.appendChild(mail);

		saveRoute(event, mail);

		Element origin = doc.createElement("origin");
		origin.appendChild(doc.createTextNode(event.getOrigin()));
		mail.appendChild(origin);

		Element destination = doc.createElement("destination");
		destination.appendChild(doc.createTextNode(event.getDestination()));
		mail.appendChild(destination);

		Element weight = doc.createElement("weight");
		weight.appendChild(doc.createTextNode(String.valueOf(event.getWeight())));
		mail.appendChild(weight);

		Element volume = doc.createElement("volume");
		volume.appendChild(doc.createTextNode(String.valueOf(event.getVolume())));
		mail.appendChild(volume);

		Element priority = doc.createElement("priority");
		priority.appendChild(doc.createTextNode(String.valueOf(event.getPriority())));
		mail.appendChild(priority);

		Element revenue = doc.createElement("revenue");
		revenue.appendChild(doc.createTextNode(String.valueOf(event.getRevenue())));
		mail.appendChild(revenue);

		Element timeTaken = doc.createElement("time taken");
		timeTaken.appendChild(doc.createTextNode(String.valueOf(event.getTimeTaken())));
		mail.appendChild(timeTaken);

		// TODO also need to get whether it has been received or not

	}

	private void saveTransportUpdate(TransportUpdate event) {
		Element transport = doc.createElement("Transport Update");
		rootElement.appendChild(transport);

		saveRoute(event, transport);

		Element oldPPGram = doc.createElement("old price/gram");
		oldPPGram.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerGram())));
		transport.appendChild(oldPPGram);

		Element newPPGram = doc.createElement("new price/gram");
		newPPGram.appendChild(doc.createTextNode(String.valueOf(event.getNewPricePerGram())));
		transport.appendChild(newPPGram);

		Element oldPPVolume = doc.createElement("old price/volume");
		oldPPVolume.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerVolume())));
		transport.appendChild(oldPPVolume);

		Element newPPVolume = doc.createElement("new price/volume");
		newPPVolume.appendChild(doc.createTextNode(String.valueOf(event.getNewPricePerVolume())));
		transport.appendChild(newPPVolume);

	}

	private void saveDeleteRoute(DeleteRoute event) {
		Element delete = doc.createElement("Delete Route");
		rootElement.appendChild(delete);

		saveRoute(event, delete);
	}

	private void saveOpenNewRoute(OpenNewRoute event) {
		Element save = doc.createElement("Save Route");
		rootElement.appendChild(save);

		saveRoute(event, save);

	}

	private void saveCustomerPriceChange(CustomerPriceChange event) {
		Element change = doc.createElement("Customer Price Change");
		rootElement.appendChild(change);

		saveRoute(event, change);

		Element oldPPGram = doc.createElement("old price/gram");
		oldPPGram.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerGram())));
		change.appendChild(oldPPGram);

		Element newPPGram = doc.createElement("new price/gram");
		newPPGram.appendChild(doc.createTextNode(String.valueOf(event.getNewPricePerGram())));
		change.appendChild(newPPGram);

		Element oldPPVolume = doc.createElement("old price/volume");
		oldPPVolume.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerVolume())));
		change.appendChild(oldPPVolume);

		Element newPPVolume = doc.createElement("new price/volume");
		newPPVolume.appendChild(doc.createTextNode(String.valueOf(event.getNewPricePerVolume())));
		change.appendChild(newPPVolume);

	}

	private void saveRoute(BusinessEvent event, Element elm){

		for(Route r: event.getRoutes()){
			// TODO will need to go through and uncomment a lot of this also check for ints that need to be parsed to Strings

			Element route = doc.createElement("route");
			elm.appendChild(route);

			Element origin = doc.createElement("origin");
			//origin.appendChild(doc.createTextNode(event.getRoute().getOrigin()));
			route.appendChild(origin);

			Element destination = doc.createElement("destination");
			//destination.appendChild(doc.createTextNode(event.getRoute().getDestination()));
			route.appendChild(destination);

			Element transportType = doc.createElement("Transport Type");
			//transportType.appendChild(doc.createTextNode(event.getRoute().getTransportType()));
			route.appendChild(transportType);

			Element averageTime = doc.createElement("Average Time");
			//averageTime.appendChild(doc.createTextNode(event.getRoute().getAverageTime()));
			route.appendChild(averageTime);

			Element firmName = doc.createElement("Firm Name");
			//firmName.appendChild(doc.createTextNode(event.getRoute().getFirmName()));
			route.appendChild(firmName);

			Element gramTransport = doc.createElement("Cost/gram for transport");
			//gramTransport.appendChild(doc.createTextNode(event.getRoute().getPricePerGramTransport()));
			route.appendChild(gramTransport);

			Element volumeTransport = doc.createElement("Cost/volume for transport");
			//volumeTransport.appendChild(doc.createTextNode(event.getRoute().getPricePerVolumeTransport()));
			route.appendChild(volumeTransport);

			Element gramCustomer = doc.createElement("Cost/gram to customer");
			//gramCustomer.appendChild(doc.createTextNode(event.getRoute().getPricePerGramCustomer()));
			route.appendChild(gramCustomer);

			Element volumeCustomer = doc.createElement("Cost/volume to customer");
			//volumeCustomer.appendChild(doc.createTextNode(event.getRoute().getPricePerVolumeCustomer()));
			route.appendChild(volumeCustomer);

			// TODO this may not be the best way to display this information
			Element departureDays = doc.createElement("Departure Days");
			route.appendChild(departureDays);
			for(DaysOfWeek day : r.getDays()){
				Element dayElm = doc.createElement("day");
				dayElm.appendChild(doc.createTextNode(day.toString()));
				departureDays.appendChild(dayElm);
			}

			Element departureFreq = doc.createElement("Departure Frequency");
			//departureFreq.appendChild(doc.createTextNode(event.getRoute().getDepartureFrequency()));
			route.appendChild(departureFreq);

		}


	}

}
