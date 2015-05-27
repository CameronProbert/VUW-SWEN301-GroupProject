package main.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The BusinessEvent class is a JPanel which show business event
 *
 * @author zhaojiang chang & zhiheng sun
 *
 */
public class BusinessEventPane extends Panel {

	// value labels on the panel
	private Map<String, String> eventsLabels;
	private static Map<String, String> currentEvent;

	public BusinessEventPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
		this.setPreferredSize(new Dimension(gui.getWidth()*3/5-40,gui.getHeight()-25));

		eventsLabels = new HashMap<String, String>();
		currentEvent = new HashMap<String, String>();
		initialEventsLabels();
	}
	
	private void initialEventsLabels() {
		eventsLabels.put("origin", "Origin");
		eventsLabels.put("destination", "Destination");
		eventsLabels.put("weight", "Weight");
		eventsLabels.put("volume", "Volume");
		eventsLabels.put("priority", "Priority");
		eventsLabels.put("time", "Time");
		eventsLabels.put("customerNewPricePerGram", "New Price Per Gram");
		eventsLabels.put("customerNewPricePerCubic", "New Price Per Cubic");
		eventsLabels.put("transportFirm", "Transport Firm");
		eventsLabels.put("transportType", "Transport Type");
		eventsLabels.put("transportDay", "Transport Day");
		eventsLabels.put("tpNewCostPerGram", "New Cost Per Gram");
		eventsLabels.put("tpNewCostPerCubic", "New Cost Per Cubic");
		eventsLabels.put("maxWeight", "Max Weight");
		eventsLabels.put("maxVolume", "Max Volume");
		eventsLabels.put("frequency", "Frequency Transpot Departs");
		eventsLabels.put("duration", "Duration Of The Trip");
	} 

	public static Map<String, String> getEvent() {
		return currentEvent;
	}

	public static void setEvent(Map<String, String> event) {
		System.out.println("add new events on business event pane");
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void setUpComponents() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void addListenner() {
		// TODO Auto-generated method stub

	}

}