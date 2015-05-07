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



			// set attribute to staff element
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			//staff.setAttributeNode(attr);

			// shorten way
			// staff.setAttribute("id", "1");

			// firstname elements
			Element firstname = doc.createElement("firstname");
			firstname.appendChild(doc.createTextNode("yong"));
			//staff.appendChild(firstname);

			// lastname elements
			Element lastname = doc.createElement("lastname");
			lastname.appendChild(doc.createTextNode("mook kim"));
			//staff.appendChild(lastname);

			// nickname elements
			Element nickname = doc.createElement("nickname");
			nickname.appendChild(doc.createTextNode("mkyong"));
			//staff.appendChild(nickname);

			// salary elements
			Element salary = doc.createElement("salary");
			salary.appendChild(doc.createTextNode("100000"));
			//staff.appendChild(salary);

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
		Element mail = doc.createElement("Staff");
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


	}

	private void saveTransportUpdate(TransportUpdate event) {
		// TODO Auto-generated method stub

	}

	private void saveDeleteRoute(DeleteRoute event) {
		// TODO Auto-generated method stub

	}

	private void saveOpenNewRoute(OpenNewRoute event) {
		// TODO Auto-generated method stub

	}

	private void saveCustomerPriceChange(CustomerPriceChange event) {
		// TODO Auto-generated method stub

	}

	private void saveRoute(BusinessEvent event, Element elm){

	}

}
