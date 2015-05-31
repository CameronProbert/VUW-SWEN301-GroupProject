package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.controllers.UIController;

/**
 * The Panel class is an abstract class represented many Pane
 * classes shown on the GUI. The Panel class has several methods
 * that could be extended by other Pane classes.
 *
 * @author Zhiheng Sun and Zhaojiang Chang
 */
public abstract class Panel extends JPanel implements PropertyChangeListener {
	protected UIController controller;
	protected GUI gui;	// the GUI that panel is on
	protected double amount = 0;
	protected int amountInt = 0;

	protected NumberFormat amountFormat;
	protected String[] distributionCentres = {  "Auckland", "Hamilton", "Rotorua", "Palmerston North",
			"Wellington", "Christchurch","Dunedin" , "Sydney", "New York"};
	protected String[] priorityList = {"Air","Standard"};
	protected String[] TransportTpyeList = {"Land", "Sea","Air"};
	protected String[] TransportDateList = {"Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	protected String[] TransportFirmList = {"Air NZ", "NZ Post", "Singapore Air", "FedEx"};
	protected static String origin = "";
	protected static String destination = "";
	protected static String priority = "";
	protected static String transportFirm = "";
	protected static String transportType = "";
	protected static String transportDay = "";
	protected static String selected = "";
	protected static JComboBox comboBoxOrigin;
	protected static JComboBox comboBoxDestination;
	protected static JComboBox comboBoxPriority;
	protected static JComboBox comboBoxTransportFirm;
	protected static JComboBox comboBoxTransportType;
	protected static JComboBox comboBoxTransportDay;
	protected static JFormattedTextField textWeight;
	protected static JFormattedTextField textVolume;
	protected static JTextField textTime;
	protected static JFormattedTextField textCustomerNewPricePerGram;
	protected static JFormattedTextField textCustomerNewPricePerCubic;
	protected static JFormattedTextField textTPNewCostPerGram;
	protected static JFormattedTextField textTPNewCostPerCubic;
	protected static JFormattedTextField textTPmaxWeight;
	protected static JFormattedTextField textTPmaxVolume;
	protected static JFormattedTextField textTPFrequency;
	protected static JFormattedTextField textTPDuration;
	protected static boolean isManager;
	protected static List<String> currentEvent = new ArrayList<String>();

	public Panel (GUI gui){
		this.gui = gui;
		// set the panel to transparent and call methods to set up buttons and listener
		setOpaque(false);
		this.controller = this.gui.getUIController();

		setUpComponents();
		this.repaint();
		addListenner();
	}

	/**
	 * The following method initializes the components on it and calls method to set the
	 * style of the buttons
	 */
	protected abstract void setUpComponents();

	/**
	 * The following method adds action listeners onto buttons of the panel
	 */
	protected abstract void addListenner();

	protected void formatToDobuleJTextField(JFormattedTextField textField) {
		// TODO Auto-generated method stub
		textField.setValue(new Double(amount));
		textField.setColumns(10);
		textField.addPropertyChangeListener("value", this);
	}
	protected void formatToIntegerJTextField(JFormattedTextField textField) {
		// TODO Auto-generated method stub
		textField.setValue(new Integer(amountInt));
		textField.setColumns(10);
		textField.addPropertyChangeListener("value", this);
	}


	protected void comboBoxListenner(JComboBox comboBox,final String type){
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				selected= (String) ((JComboBox)e.getSource()).getSelectedItem();
				System.out.println(selected);

				if(type.equals("origin")){
					origin = selected;

				}
				else if(type.equals("destination")){
					destination = selected;

				}
				else if(type.equals("priority")){
					priority = selected;

				}
				else if(type.equals("transportType")){
					transportType = selected;

				}
				else if(type.equals("transportFirm")){
					transportFirm = selected;

				}
				else if(type.equals("transportDay")){
					transportDay = selected;

				}
			}
		});
	}

	public static String getOrigin() {
		return origin;
	}

	public static void setOrigin(String origin) {
		Panel.origin = origin;
	}

	public static String getDestination() {
		return destination;
	}

	public static void setDestination(String destination) {
		Panel.destination = destination;
	}

	public static String getPriority() {
		return priority;
	}

	public static void setPriority(String priority) {
		Panel.priority = priority;
	}

	public static String getTransportType() {
		return transportType;
	}

	public static void setTransportType(String transportType) {
		Panel.transportType = transportType;
	}

	public static String getTransportFirm() {
		return transportFirm;
	}

	public static void setTransportFirm(String transportFirm) {
		Panel.transportFirm = transportFirm;
	}

	public void init(){
		selected = "";
		origin = "";
		destination = "";
		priority = "";
		transportFirm = "";
		transportType = "";
		transportDay = "";
		//textTime.setText("");
		if(textVolume!=null) textVolume.setValue(0.0);
		if(textWeight!=null) textWeight.setValue(0.0);
		if(textTPmaxWeight!=null) textTPmaxWeight.setValue(0.0);
		if(textTPmaxVolume!=null) textTPmaxVolume.setValue(0.0);
		if(textTPFrequency!=null) textTPFrequency.setValue(0);
		if(textTPDuration!=null) textTPDuration.setValue(0);
		if(textCustomerNewPricePerCubic!=null) textCustomerNewPricePerCubic.setValue(0.0);
		if(textCustomerNewPricePerGram!=null) textCustomerNewPricePerGram.setValue(0.0);
		if(textTPNewCostPerGram!=null) textTPNewCostPerGram.setValue(0.0);
		if(textTPNewCostPerCubic!=null) textTPNewCostPerCubic.setValue(0.0);

	}

	public void addBusinessEvent(String type){
		Map<String, String> currentEvent = new HashMap<String, String>();

		if(type.equals("mailDelivery")){
			if(origin.equals("")||destination.equals("")||((Number)textWeight.getValue()).doubleValue()==0.0||
					((Number)textVolume.getValue()).doubleValue()==0.0||priority.equals("")||
					textTime.getText().equals("")){
				return;
			}
		}
		else if (type.equals("customerPriceUpdate")){
			if(origin.equals("")||destination.equals("")||((Number)textCustomerNewPricePerGram.getValue()).doubleValue()==0.0||
					((Number)textCustomerNewPricePerCubic.getValue()).doubleValue()==0.0||priority.equals("")){
				return;
			}
		}
		else if (type.equals("transportCostUpdate")){
			if(origin.equals("")||destination.equals("")||transportFirm.equals("")||transportType.equals("")||
					transportDay.equals("")||((Number)textTPNewCostPerGram.getValue()).doubleValue()==0.0||
					((Number)textTPNewCostPerCubic.getValue()).doubleValue()==0.0||
					((Number)textTPmaxWeight.getValue()).doubleValue()==0.0||
					((Number)textTPmaxVolume.getValue()).doubleValue()==0.0||
					((Number)textTPFrequency.getValue()).doubleValue()==0.0||
					((Number)textTPDuration.getValue()).doubleValue()==0.0){
				return;
			}
		}
		else if (type.equals("transportDiscontinued")){
			if(origin.equals("")||destination.equals("")||transportFirm.equals("")||transportType.equals("")){
				return;
			}
		}

		currentEvent.put("type", type);
		if (!origin.equals("")){
			currentEvent.put("origin", origin);
		}
		if (!destination.equals("")){
			currentEvent.put("destination", destination);
		}
		if (((Number)textWeight.getValue()).doubleValue()!=0.0){
			currentEvent.put("weight", "" + textWeight.getValue());
		}
		if (((Number)textVolume.getValue()).doubleValue()!=0.0){
			currentEvent.put("volume", "" + textVolume.getValue());
		}
		if (!priority.equals("")){
			currentEvent.put("priority", priority);
		}
		if (!textTime.getText().equals("")){
			currentEvent.put("time", textTime.getText());
		}
		if (((Number)textCustomerNewPricePerGram.getValue()).doubleValue()!=0.0){
			currentEvent.put("customerNewPricePerGram", "" + textCustomerNewPricePerGram.getValue());
		}
		if (((Number)textCustomerNewPricePerCubic.getValue()).doubleValue()!=0.0){
			currentEvent.put("customerNewPricePerCubic", "" + textCustomerNewPricePerCubic.getValue());
		}
		if (!transportFirm.equals("")){
			currentEvent.put("transportFirm", transportFirm);
		}
		if (!transportType.equals("")){
			currentEvent.put("transportType", transportType);
		}
		if (!transportDay.equals("")){
			currentEvent.put("transportDay", transportDay);
		}
		if (((Number)textTPNewCostPerGram.getValue()).doubleValue()!=0.0){
			currentEvent.put("tpNewCostPerGram", "" + textTPNewCostPerGram.getValue());
		}
		if (((Number)textTPNewCostPerCubic.getValue()).doubleValue()!=0.0){
			currentEvent.put("tpNewCostPerCubic", "" + textTPNewCostPerCubic.getValue());
		}
		if (((Number)textTPmaxWeight.getValue()).doubleValue()!=0.0){
			currentEvent.put("maxWeight", "" + textTPmaxWeight.getValue());
		}
		if (((Number)textTPmaxVolume.getValue()).doubleValue()!=0.0){
			currentEvent.put("maxVolume", "" + textTPmaxVolume.getValue());
		}
		if (((Number)textTPFrequency.getValue()).doubleValue()!=0.0){
			currentEvent.put("frequency", "" + textTPFrequency.getValue());
		}
		if (((Number)textTPDuration.getValue()).doubleValue()!=0.0){
			currentEvent.put("duration", "" + textTPDuration.getValue());
		}
		currentEvent.put("clerkName", gui.getCurretUsername());

		System.out.println(currentEvent);
		controller.addEvent(currentEvent);
	}
}
