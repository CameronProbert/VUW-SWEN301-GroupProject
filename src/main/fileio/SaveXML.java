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
	private List<BusinessEvent> events;

	public boolean save(List<BusinessEvent> events){
		this.events = events;
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			doc = docBuilder.newDocument();

			rootElement = doc.createElement("businessEvents");
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
			StreamResult result = new StreamResult(new File("xml/saveFile"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		}
		return true;
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

		Element timeTaken = doc.createElement("timeTaken");
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

		Element oldPPGram = doc.createElement("oldPricePGram");
		oldPPGram.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerGram())));
		transport.appendChild(oldPPGram);

		Element newPPGram = doc.createElement("newPricePGram");
		newPPGram.appendChild(doc.createTextNode(String.valueOf(event.getNewPricePerGram())));
		transport.appendChild(newPPGram);

		Element oldPPVolume = doc.createElement("oldPricePVolume");
		oldPPVolume.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerVolume())));
		transport.appendChild(oldPPVolume);

		Element newPPVolume = doc.createElement("newPricePVolume");
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

		Element oldPPGram = doc.createElement("oldPricePGram");
		oldPPGram.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerGram())));
		change.appendChild(oldPPGram);

		Element newPPGram = doc.createElement("newPricePGram");
		newPPGram.appendChild(doc.createTextNode(String.valueOf(event.getNewPricePerGram())));
		change.appendChild(newPPGram);

		Element oldPPVolume = doc.createElement("oldPricePVolume");
		oldPPVolume.appendChild(doc.createTextNode(String.valueOf(event.getOldPricePerVolume())));
		change.appendChild(oldPPVolume);

		Element newPPVolume = doc.createElement("newPricePVolume");
		newPPVolume.appendChild(doc.createTextNode(String.valueOf(event.getNewPricePerVolume())));
		change.appendChild(newPPVolume);

	}

	private void saveRoute(BusinessEvent event, Element elm){

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

}
