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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.controllers.UIController;
import main.logic.Location;
import main.logic.Route;

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
	private Route selectedRoute = null;
	private String selectedRouteString = "";

	protected NumberFormat amountFormat;
	protected String[] priorityList = {"Air","Standard"};
	protected String[] TransportDateList = {"Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	protected static String origin = "";
	protected static String destination = "";
	protected static String priority = "";
	protected static String transportFirm = "";
	protected static String transportType = "";
	protected static String transportDay = "";
	protected static String selected = "";
	protected static JComboBox comboBoxRoute;
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
	private Map<String, Route> routeMap;
	protected JLabel labelComboOrigin;
	protected JLabel labelComboDestination;
	protected boolean isBusinessFigures = false;



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

	protected void comboBoxRouteList(){
		String[] routeKey = new String[controller.getRoutes().size()];
		Route[] routeValue = new Route[controller.getRoutes().size()];
		int i = 0;
		for(Route r: controller.getRoutes()){
			routeKey[i] = r.toString();
			i++;
		}
		i = 0;
		for(Route r: controller.getRoutes()){
			routeValue[i] = r;
			i++;
		}
		routeMap = new HashMap<String, Route>();
		for(int j = 0; j<routeKey.length; j++){
			routeMap.put(routeKey[j], routeValue[j]);
		}

		comboBoxRoute = new JComboBox(routeKey);
		comboBoxRoute.setSelectedItem(null);
		comboBoxListenner(comboBoxRoute, "route");
	}
	protected void comboBoxListenner(JComboBox comboBox,final String type){
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selected=(String)((JComboBox)e.getSource()).getSelectedItem();

				if(type.equals("origin")){
					origin = selected;

				}
				else if(type.equals("destination")){
					destination =  selected;

				}
				else if(type.equals("priority")){
					priority =  selected;

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
				else if(type.equals("route")){
					selectedRouteString = selected;
					selectedRoute = routeMap.get(selectedRouteString);
					if(isBusinessFigures){

					}
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
		//		origin = "";
		//		destination = "";
		//		priority = "";
		transportFirm = "";
		transportType = "";
		transportDay = "";
		selectedRoute = null;
		selectedRouteString = "";
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
		currentEvent.put("clerkName", gui.getCurretUsername());
		currentEvent.put("type", type);


		if(type.equals("mailDelivery")){
			if(origin.equals("")||destination.equals("")||((Number)textWeight.getValue()).doubleValue()==0.0||
					((Number)textVolume.getValue()).doubleValue()==0.0||priority.equals("")||
					textTime.getText().equals("")){
				return;
			}
			else{
				currentEvent.put("origin", origin);
				currentEvent.put("destination", destination);
				currentEvent.put("weight", "" + textWeight.getValue());
				currentEvent.put("volume", "" + textVolume.getValue());
				currentEvent.put("priority", priority);
				currentEvent.put("time", textTime.getText());
				controller.addEvent(null, currentEvent);
			}
		}
		else if (type.equals("customerPriceUpdate")){
			if(selectedRoute==null||((Number)textCustomerNewPricePerGram.getValue()).doubleValue()==0.0||
					((Number)textCustomerNewPricePerCubic.getValue()).doubleValue()==0.0){
				return;
			}
			else{
				currentEvent.put("customerNewPricePerGram", "" + textCustomerNewPricePerGram.getValue());
				currentEvent.put("customerNewPricePerCubic", "" + textCustomerNewPricePerCubic.getValue());
				controller.addEvent(selectedRoute, currentEvent);

			}
		}
		else if (type.equals("transportCostUpdate")){
			if(selectedRoute==null||((Number)textTPNewCostPerGram.getValue()).doubleValue()==0.0||
					((Number)textTPNewCostPerCubic.getValue()).doubleValue()==0.0){
				return;
			}
			else{
				currentEvent.put("transportNewCostPerGram", "" + textTPNewCostPerGram.getValue());
				currentEvent.put("transportNewCostPerCubic", "" + textTPNewCostPerCubic.getValue());
				controller.addEvent(selectedRoute, currentEvent);
			}
		}
		else if (type.equals("transportDiscontinued")){
			if(selectedRoute==null){
				return;
			}
			else{
				controller.addEvent(selectedRoute, currentEvent);
			}
		}
		else if (type.equals("createRoute")){
			if(origin.equals("")||destination.equals("")||transportFirm.equals("")||transportType.equals("")||
					transportDay.equals("")||((Number)textTPNewCostPerGram.getValue()).doubleValue()==0.0||
					((Number)textTPNewCostPerCubic.getValue()).doubleValue()==0.0||
					((Number)textCustomerNewPricePerGram.getValue()).doubleValue()==0.0||
					((Number)textCustomerNewPricePerCubic.getValue()).doubleValue()==0.0||
					((Number)textTPFrequency.getValue()).doubleValue()==0.0){
				return;
			}else{
				currentEvent.put("origin", origin);
				currentEvent.put("destination", destination);
				currentEvent.put("transportFirm", transportFirm);
				currentEvent.put("transportType", transportType);
				currentEvent.put("transportDay", transportDay);
				currentEvent.put("transportsCostPerGram", "" + textTPNewCostPerGram.getValue());
				currentEvent.put("transportsCostPerCubic", "" + textTPNewCostPerCubic.getValue());
				currentEvent.put("customerPricePerGram", "" + textCustomerNewPricePerGram.getValue());
				currentEvent.put("customerPricePerCubic", "" + textCustomerNewPricePerCubic.getValue());
				currentEvent.put("frequency", "" + textTPFrequency.getValue());
				controller.addEvent(selectedRoute, currentEvent);
			}

		}



	}
	protected void createOriginAndDestination(){
		Location[] origins = new Location[controller.getRoutes().size()];
		int i = 0;
		for( Route s: controller.getRoutes()){
			origins[i] = s.getOrigin();
			i++;
		}
		Location[] destinatiions = new Location[controller.getRoutes().size()];
		i = 0;
		for( Route s: controller.getRoutes()){
			destinatiions[i] = s.getDestination();
			i++;
		}
		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
		comboBoxOrigin = new JComboBox(origins);
		comboBoxOrigin.setEditable(true);
		comboBoxOrigin.setSelectedItem(null);
		comboBoxListenner(comboBoxOrigin, "origin");

		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(destinatiions);
		comboBoxDestination.setEnabled(true);
		comboBoxDestination.setSelectedItem(null);

		comboBoxListenner(comboBoxDestination, "destination");
	}
	protected String[] getTransportFirms(){
		List<String> firms = new ArrayList<String>();
		for(Route r: controller.getRoutes()){
			if(!firms.contains(r.getTransportFirm())){
				firms.add(r.getTransportFirm());
			}
		}
		String[] fs = new String[firms.size()];
		for(int i = 0; i<firms.size(); i++){
			fs[i] = firms.get(i);
		}
		return fs;
	}
	protected String[] getTransportTypes(){
		List<String> types = new ArrayList<String>();
		for(Route r: controller.getRoutes()){
			if(!types.contains(r.getTransportType().toString())){
				types.add(r.getTransportType().toString());
			}
		}
		String[] fs = new String[types.size()];
		for(int i = 0; i<types.size(); i++){
			fs[i] = types.get(i);
		}
		return fs;
	}
}
