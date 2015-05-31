package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.logic.Location;
import main.logic.Route;

/**
 */
public class CreateRoutePane extends Panel{

	// buttons on the panel
	private int count = 0;
	private static boolean updateButtonClicked = false;
	private JButton reset;
	private JButton add;

	public CreateRoutePane(GUI gui) {
		super(gui);
		updateButtonClicked = false;
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		String[] origins = new String[controller.getRoutes().size()];
		int i = 0;
		for( Route s: controller.getRoutes()){
			origins[i] = s.getOrigin().getName();
			i++;
		}
		String[] destinatiions = new String[controller.getRoutes().size()];
		int j = 0;
		for( Route s: controller.getRoutes()){
			destinatiions[j] = s.getDestination().getName();
			j++;
		}
		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
		comboBoxOrigin = new JComboBox(origins);
		comboBoxOrigin.setEditable(true);
		comboBoxOrigin.setSelectedItem(null);
		comboBoxListenner(comboBoxOrigin, "origin");

		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(destinatiions);
		comboBoxDestination.setEditable(true);
		comboBoxDestination.setSelectedItem(null);
		comboBoxListenner(comboBoxDestination, "destination");

		JLabel labelTransportFirm= new JLabel("Transport Firm", SwingConstants.CENTER);
		comboBoxTransportFirm = new JComboBox(getTransportFirms());
		comboBoxTransportFirm.setEditable(true);
		comboBoxTransportFirm.setSelectedItem(null);
		comboBoxListenner(comboBoxTransportFirm, "transportFirm");

		JLabel labelTransportType= new JLabel("Transport Tpye", SwingConstants.CENTER);
		comboBoxTransportType = new JComboBox(getTransportTypes());
		comboBoxTransportType.setEditable(true);
		comboBoxTransportType.setSelectedItem(null);
		comboBoxListenner(comboBoxTransportType, "transportType");

		JLabel labelTransportPricePerGram= new JLabel("Transport price per gram", SwingConstants.CENTER);
		textTPNewCostPerGram = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewCostPerGram);

		JLabel labelTransportCostPerCB= new JLabel("Transport price per cubic centimeter", SwingConstants.CENTER);
		textTPNewCostPerCubic = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewCostPerCubic);

		JLabel labelCustomerPricePerGram= new JLabel("Customer price per gram", SwingConstants.CENTER);
		textCustomerNewPricePerGram = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textCustomerNewPricePerGram);

		JLabel labelCustomerCostPerCB= new JLabel("Customer price per cubic centimeter", SwingConstants.CENTER);
		textCustomerNewPricePerCubic = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textCustomerNewPricePerCubic);
//
//		JLabel labelmaxWeight= new JLabel("Max weight", SwingConstants.CENTER);
//		textTPmaxWeight = new JFormattedTextField(amountFormat);
//		formatToDobuleJTextField(textTPmaxWeight);
//
//		JLabel labelmaxVolume= new JLabel("Max volume", SwingConstants.CENTER);
//		textTPmaxVolume = new JFormattedTextField(amountFormat);
//		formatToDobuleJTextField(textTPmaxVolume);

		JLabel labelFrequency= new JLabel("Frequency transport departs", SwingConstants.CENTER);
		textTPFrequency = new JFormattedTextField(amountFormat);
		formatToIntegerJTextField(textTPFrequency);

		JLabel labelDay= new JLabel("Transpot Day", SwingConstants.CENTER);
		comboBoxTransportDay = new JComboBox(TransportDateList);
		comboBoxTransportDay.setSelectedItem(null);
		comboBoxListenner(comboBoxTransportDay, "transportDay");



//		JLabel labelDuration= new JLabel("Duration of the trip", SwingConstants.CENTER);
//		textTPDuration = new JFormattedTextField(amountFormat);
//		formatToIntegerJTextField(textTPDuration);

		reset = new JButton("Reset");
		add = new JButton("Add");
		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);
		add(labelTransportFirm);
		add(comboBoxTransportFirm);
		add(labelTransportType);
		add(comboBoxTransportType);
		add(labelTransportPricePerGram);
		add(textTPNewCostPerGram);
		add(labelTransportCostPerCB);
		add(textTPNewCostPerCubic);
		add(labelCustomerPricePerGram);
		add(textCustomerNewPricePerGram);
		add(labelCustomerCostPerCB);
		add(textCustomerNewPricePerCubic);
		add(labelFrequency);
		add(textTPFrequency);
		add(labelDay);
		add(comboBoxTransportDay);

//		add(labelDuration);
//		add(textTPDuration);

		add(reset);
		add(add);
	}

	@Override
	protected void addListenner() {
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == add){
					//updateButtonClicked = true;
					addBusinessEvent("createRoute");
				}
			}
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == reset){
					init();
					comboBoxOrigin.setSelectedItem(null);
					comboBoxDestination.setSelectedItem(null);
					comboBoxTransportFirm.setSelectedItem(null);
					comboBoxTransportType.setSelectedItem(null);
					comboBoxTransportDay.setSelectedItem(null);
				}
			}
		});
	}

	public static double getTPCNewPricePerGram() {
		return ((Number)textTPNewCostPerGram.getValue()).doubleValue();
	}

	public static double getTPCNewCostPerCubic() {
		return ((Number)textTPNewCostPerCubic.getValue()).doubleValue();
	}
	public static boolean updateClicked(){
		if(updateButtonClicked==true){
			return true;
		}
		return false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == textTPNewCostPerCubic) {
			amount = ((Number)textTPNewCostPerCubic.getValue()).doubleValue();
		}
		else if (source == textTPNewCostPerGram) {
			amount = ((Number)textTPNewCostPerGram.getValue()).doubleValue();
		}
		else if (source == textTPmaxWeight) {
			amount = ((Number)textTPmaxWeight.getValue()).doubleValue();
		}
		else if (source == textTPmaxVolume) {
			amount = ((Number)textTPmaxVolume.getValue()).doubleValue();
		}
		else if (source == textTPFrequency) {
			amount = ((Number)textTPFrequency.getValue()).intValue();
		}
		else if (source == textTPDuration) {
			amount = ((Number)textTPDuration.getValue()).intValue();
		}
	}
	public String toStringTPU(){
		return("Origin: "+ origin +"  Destination: "+ destination+"  Priority: "+priority+"   New Price Per Gram: "+ ((Number)textTPNewCostPerGram.getValue()).doubleValue()
				+"    New Price Per Gram:"+ ((Number)textTPNewCostPerCubic.getValue()).doubleValue() +"   Transport Firm: "+transportFirm+"   transport Type:  "+ transportType
				+" max Weight:  "+ textTPmaxWeight+" max Volume:  "+ textTPmaxVolume+" Frequency:  "+ textTPFrequency+"  Duration:  "+ textTPDuration);
	}
}
