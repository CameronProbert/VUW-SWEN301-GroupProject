package main.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
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
	private String[][] mailDeliveryLabels;
	private String[][] customerPriceUpdateLabels;
	private String[][] transportCostUpdateLabels;
	private String[][] transportDiscontinuedLabels;
	//private static Map<String, String> currentEvent;

	public BusinessEventPane(GUI gui) {
		super(gui);
	}

	@Override
	protected void setUpComponents() {
		//currentEvent = new HashMap<String, String>();
		//currentEvent = controller.getCurrentEvent();
			
		mailDeliveryLabels = new String[6][2];
		customerPriceUpdateLabels = new String[5][2];
		transportCostUpdateLabels = new String[11][2];
		transportDiscontinuedLabels = new String[4][2];	
		initialEventsLabels();
		
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);
		
		if (currentEvent.get("type").equals("mailDelivery")){
			addLabels(mailDeliveryLabels);
		} else if (currentEvent.get("type").equals("customerPriceUpdate")){
			addLabels(customerPriceUpdateLabels);
		} else if (currentEvent.get("type").equals("transportCostUpdate")){
			addLabels(transportCostUpdateLabels);
		} else if (currentEvent.get("type").equals("transportDiscontinued")){
			addLabels(transportDiscontinuedLabels);
		}
	}
	
//	@Override
//	protected void setUpComponents() {
//		this.setLayout(new GridLayout(20,2));
//		this.setAlignmentX(LEFT_ALIGNMENT);
//		
//		mailDeliveryLabels = new String[6][2];
//		customerPriceUpdateLabels = new String[5][2];
//		transportCostUpdateLabels = new String[11][2];
//		transportDiscontinuedLabels = new String[4][2];	
//		initialEventsLabels();
//		
//		currentEvent = new HashMap<String, String>();
//		currentEvent = controller.getCurrentEvent();
//		createNewLabels();
//	}
//	
//	private void createNewLabels(){
//		Component[] components = this.getComponents();
//		for (int i = 0; i < components.length; i++){
//			remove(components[i]);	
//		}
//		
//		if (currentEvent.get("type").equals("mailDelivery")){
//			addLabels(mailDeliveryLabels);
//		} else if (currentEvent.get("type").equals("customerPriceUpdate")){
//			addLabels(customerPriceUpdateLabels);
//		} else if (currentEvent.get("type").equals("transportCostUpdate")){
//			addLabels(transportCostUpdateLabels);
//		} else if (currentEvent.get("type").equals("transportDiscontinued")){
//			addLabels(transportDiscontinuedLabels);
//		}
//	}
	
	private void addLabels(String[][] labels) {
		for (int i = 0; i < labels.length; i++) {
			JLabel field = new JLabel(labels[i][1], SwingConstants.LEFT);
			field.setFont(new Font("Dialog", Font.PLAIN, 14));
			JLabel value = new JLabel(currentEvent.get(labels[i][0]), SwingConstants.LEFT);
			value.setFont(new Font("Dialog", Font.PLAIN, 14));
			add(field);
			add(value);
		}
	}

	public static void setEvent(Map<String, String> event) {
		System.out.println("add new events on business event pane");
	}

	private void initialEventsLabels() {
		mailDeliveryLabels[0][0] = "origin";
		mailDeliveryLabels[0][1] = "Origin";
		mailDeliveryLabels[1][0] = "destination";
		mailDeliveryLabels[1][1] = "Destination";
		mailDeliveryLabels[2][0] = "weight";
		mailDeliveryLabels[2][1] = "Weight";
		mailDeliveryLabels[3][0] = "volume";
		mailDeliveryLabels[3][1] = "Volume";
		mailDeliveryLabels[4][0] = "priority";
		mailDeliveryLabels[4][1] = "Priority";
		mailDeliveryLabels[5][0] = "time";
		mailDeliveryLabels[5][1] = "Time";

		customerPriceUpdateLabels[0][0] = "origin";
		customerPriceUpdateLabels[0][1] = "Origin";
		customerPriceUpdateLabels[1][0] = "destination";
		customerPriceUpdateLabels[1][1] = "Destination";
		customerPriceUpdateLabels[2][0] = "priority";
		customerPriceUpdateLabels[2][1] = "Priority";
		customerPriceUpdateLabels[3][0] = "customerNewPricePerGram";
		customerPriceUpdateLabels[3][1] = "New Price Per Gram";
		customerPriceUpdateLabels[4][0] = "customerNewPricePerCubic";
		customerPriceUpdateLabels[4][1] = "New Price Per Cubic";

		transportCostUpdateLabels[0][0] = "origin";
		transportCostUpdateLabels[0][1] = "Origin";
		transportCostUpdateLabels[1][0] = "destination";
		transportCostUpdateLabels[1][1] = "Destination";
		transportCostUpdateLabels[2][0] = "transportFirm";
		transportCostUpdateLabels[2][1] = "Transport Firm";
		transportCostUpdateLabels[3][0] = "transportType";
		transportCostUpdateLabels[3][1] = "Transport Type";
		transportCostUpdateLabels[4][0] = "tpNewCostPerGram";
		transportCostUpdateLabels[4][1] = "New Cost Per Gram";
		transportCostUpdateLabels[5][0] = "tpNewCostPerCubic";
		transportCostUpdateLabels[5][1] = "New Cost Per Cubic";
		transportCostUpdateLabels[6][0] = "maxWeight";
		transportCostUpdateLabels[6][1] = "Max Weight";
		transportCostUpdateLabels[7][0] = "maxVolume";
		transportCostUpdateLabels[7][1] = "Max Volume";
		transportCostUpdateLabels[8][0] = "transportDay";
		transportCostUpdateLabels[8][1] = "Transport Day";
		transportCostUpdateLabels[9][0] = "frequency";
		transportCostUpdateLabels[9][1] = "Frequency Transpot Departs";
		transportCostUpdateLabels[10][0] = "duration";
		transportCostUpdateLabels[10][1] = "Duration Of The Trip";
		
		transportDiscontinuedLabels[0][0] = "origin";
		transportDiscontinuedLabels[0][1] = "Origin";
		transportDiscontinuedLabels[1][0] = "destination";
		transportDiscontinuedLabels[1][1] = "Destination";
		transportDiscontinuedLabels[2][0] = "transportFirm";
		transportDiscontinuedLabels[2][1] = "Transport Firm";
		transportDiscontinuedLabels[3][0] = "transportType";
		transportDiscontinuedLabels[3][1] = "Transport Type";
	} 
	
	@Override
	protected void addListenner() {}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {}
}