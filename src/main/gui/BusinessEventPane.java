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
//	private String[][] mailDeliveryLabels;
//	private String[][] customerPriceUpdateLabels;
//	private String[][] transportCostUpdateLabels;
//	private String[][] transportDiscontinuedLabels;
	//private static Map<String, String> currentEvent;

	public BusinessEventPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
//		this.setPreferredSize(new Dimension(gui.getWidth()*3/5-40,gui.getHeight()-25));
	}

	@Override
	protected void setUpComponents() {
		currentEvent = new ArrayList<String>();
		currentEvent = controller.getCurrentEvent();

//		mailDeliveryLabels = new String[6][2];
//		customerPriceUpdateLabels = new String[5][2];
//		transportCostUpdateLabels = new String[11][2];
//		transportDiscontinuedLabels = new String[4][2];

		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		addLabels();
//		if (currentEvent.get("type").equals("mailDelivery")){
//			addLabels(mailDeliveryLabels);
//		} else if (currentEvent.get("type").equals("customerPriceUpdate")){
//			addLabels(customerPriceUpdateLabels);
//		} else if (currentEvent.get("type").equals("transportCostUpdate")){
//			addLabels(transportCostUpdateLabels);
//		} else if (currentEvent.get("type").equals("transportDiscontinued")){
//			addLabels(transportDiscontinuedLabels);
//		}
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

	private void addLabels() {
		for (int i = 0; i < currentEvent.size(); i++) {
			JLabel value = new JLabel(currentEvent.get(i), SwingConstants.LEFT);
			value.setFont(new Font("Dialog", Font.PLAIN, 14));
			add(value);
		}
	}

	public static void setEvent(Map<String, String> event) {
		System.out.println("add new events on business event pane");
	}

	@Override
	protected void addListenner() {}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {}
}