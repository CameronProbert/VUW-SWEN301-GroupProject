package main.logic;

import java.util.Map;

import main.events.BusinessEvent;
import main.events.CustomerPriceChange;
import main.events.DeleteRoute;
import main.events.MailDelivery;
import main.events.OpenNewRoute;
import main.events.TransportUpdate;

/**
 * Class designed to create BusinessEvents given a map of user inputed data
 * 
 * @author Cameron Probert
 *
 */
public class EventCreator {

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static BusinessEvent createMailDelivery(Map<String, String> data) {
		MailDelivery event = null;

		return event;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static BusinessEvent createCustPriceChange(Map<String, String> data) {
		CustomerPriceChange event = null;

		return event;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static BusinessEvent createDeleteRoute(Map<String, String> data) {
		DeleteRoute event = null;

		return event;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	public static BusinessEvent createOpenRoute(Map<String, String> data) {
		OpenNewRoute event = null;

		return event;
	}

	/**
	 * This method can both update an existing route's transport costs, or if
	 * the route does not exist it will create it.
	 * 
	 * @param data
	 * @return
	 */
	public static BusinessEvent createTransUpdate(Map<String, String> data) {
		TransportUpdate event = null;

		return event;
	}
}
