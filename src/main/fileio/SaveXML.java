package main.fileio;

import java.util.List;
import java.io.File;

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
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("saveFile"));

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
		Element mail = doc.createElement("event");
		rootElement.appendChild(mail);

		Attr attr = doc.createAttribute("type");
		attr.setValue("Mail Delivery");
		mail.setAttributeNode(attr);

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
		Element transport = doc.createElement("event");
		rootElement.appendChild(transport);

		Attr attr = doc.createAttribute("type");
		attr.setValue("Transport Update");
		transport.setAttributeNode(attr);

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
		Element delete = doc.createElement("event");
		rootElement.appendChild(delete);

		Attr attr = doc.createAttribute("type");
		attr.setValue("Delete Route");
		delete.setAttributeNode(attr);

		saveRoute(event, delete);
	}

	private void saveOpenNewRoute(OpenNewRoute event) {
		Element save = doc.createElement("event");
		rootElement.appendChild(save);

		Attr attr = doc.createAttribute("type");
		attr.setValue("New Route");
		save.setAttributeNode(attr);

		saveRoute(event, save);

	}

	private void saveCustomerPriceChange(CustomerPriceChange event) {
		Element change = doc.createElement("event");
		rootElement.appendChild(change);

		Attr attr = doc.createAttribute("type");
		attr.setValue("Customer Price Change");
		change.setAttributeNode(attr);

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
			origin.appendChild(doc.createTextNode(r.getOrigin().toString()));
			route.appendChild(origin);

			Element destination = doc.createElement("destination");
			destination.appendChild(doc.createTextNode(r.getDestination().toString()));
			route.appendChild(destination);

			Element transportType = doc.createElement("Transport Type");
			transportType.appendChild(doc.createTextNode(r.getTransportType().toString()));
			route.appendChild(transportType);

			Element averageTime = doc.createElement("Average Time");
			averageTime.appendChild(doc.createTextNode(String.valueOf(r.getAverageTimeToDeliver())));
			route.appendChild(averageTime);

			Element firmName = doc.createElement("Firm Name");
			firmName.appendChild(doc.createTextNode(r.getTransportFirm()));
			route.appendChild(firmName);

			Element gramTransport = doc.createElement("Cost/gram for transport");
			gramTransport.appendChild(doc.createTextNode(String.valueOf(r.getPricePerGramTransport())));
			route.appendChild(gramTransport);

			Element volumeTransport = doc.createElement("Cost/volume for transport");
			volumeTransport.appendChild(doc.createTextNode(String.valueOf(r.getPricePerVolumeTransport())));
			route.appendChild(volumeTransport);

			Element gramCustomer = doc.createElement("Cost/gram to customer");
			gramCustomer.appendChild(doc.createTextNode(String.valueOf(r.getPricePerGramCustomer())));
			route.appendChild(gramCustomer);

			Element volumeCustomer = doc.createElement("Cost/volume to customer");
			volumeCustomer.appendChild(doc.createTextNode(String.valueOf(r.getPricePerVolumeCustomer())));
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
			departureFreq.appendChild(doc.createTextNode(String.valueOf(r.getDepartureFrequency())));
			route.appendChild(departureFreq);

		}


	}

}
