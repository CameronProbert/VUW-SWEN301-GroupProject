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

/**
 */
public class TransportCostUpdatePane extends Panel{

	// buttons on the panel
	private int count = 0;
	private static boolean updateButtonClicked = false;
	private JButton reset;
	private JButton update;

	public TransportCostUpdatePane(GUI gui) {
		super(gui);
		updateButtonClicked = false;
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
		comboBoxOrigin = new JComboBox(distributionCentres);
		comboBoxOrigin.setSelectedItem(null);
		comboBoxListenner(comboBoxOrigin, "origin");

		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(distributionCentres);
		comboBoxDestination.setSelectedItem(null);
		comboBoxListenner(comboBoxDestination, "destination");

		JLabel labelTransportFirm= new JLabel("Transport Firm", SwingConstants.CENTER);
		comboBoxTransportFirm = new JComboBox(TransportFirmList);
		comboBoxTransportFirm.setSelectedItem(null);
		comboBoxListenner(comboBoxTransportFirm, "transportFirm");

		//transportFirm = selected;

		JLabel labelTransportType= new JLabel("Transport Tpye", SwingConstants.CENTER);
		comboBoxTransportType = new JComboBox(TransportTpyeList);
		comboBoxTransportType.setSelectedItem(null);
		comboBoxListenner(comboBoxTransportType, "transportType");

		JLabel labelNewPricePerGram= new JLabel("New price per gram", SwingConstants.CENTER);
		textTPNewCostPerGram = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewCostPerGram);

		JLabel labelNewCostPerCB= new JLabel("New price per cubic centimeter", SwingConstants.CENTER);
		textTPNewCostPerCubic = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewCostPerCubic);

		JLabel labelmaxWeight= new JLabel("Max weight", SwingConstants.CENTER);
		textTPmaxWeight = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPmaxWeight);

		JLabel labelmaxVolume= new JLabel("Max volume", SwingConstants.CENTER);
		textTPmaxVolume = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPmaxVolume);

		JLabel labelDay= new JLabel("Transpot Day", SwingConstants.CENTER);
		comboBoxTransportDay = new JComboBox(TransportDateList);
		comboBoxTransportDay.setSelectedItem(null);
		comboBoxListenner(comboBoxTransportDay, "transportDay");

		JLabel labelFrequency= new JLabel("Frequency transport departs", SwingConstants.CENTER);
		textTPFrequency = new JFormattedTextField(amountFormat);
		formatToIntegerJTextField(textTPFrequency);

		JLabel labelDuration= new JLabel("Duration of the trip", SwingConstants.CENTER);
		textTPDuration = new JFormattedTextField(amountFormat);
		formatToIntegerJTextField(textTPDuration);

		reset = new JButton("Reset");
		update = new JButton("Update");
		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);
		add(labelTransportFirm);
		add(comboBoxTransportFirm);
		add(labelTransportType);
		add(comboBoxTransportType);
		add(labelNewPricePerGram);
		add(textTPNewCostPerGram);
		add(labelNewCostPerCB);
		add(textTPNewCostPerCubic);
		add(labelmaxWeight);
		add(textTPmaxWeight);
		add(labelmaxVolume);
		add(textTPmaxVolume);
		add(labelDay);
		add(comboBoxTransportDay);
		add(labelFrequency);
		add(textTPFrequency);
		add(labelDuration);
		add(textTPDuration);

		add(reset);
		add(update);
	}

	@Override
	protected void addListenner() {
		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == update){
					updateButtonClicked = true;
					addBusinessEvent("transportCostUpdate");
					System.out.println("Origin: "+ origin +"  Destination: "+ destination+"  Transport Day: "+transportDay+"   New Price Per Gram: "+ ((Number)textTPNewCostPerGram.getValue()).doubleValue()
							+"    New Price Per Gram:"+ ((Number)textTPNewCostPerCubic.getValue()).doubleValue() +"   Transport Firm: "+transportFirm+"   transport Type:  "+ transportType
							+" max Weight:  "+ ((Number)textTPmaxWeight.getValue()).doubleValue()+" max Volume:  "+ ((Number)textTPmaxVolume.getValue()).doubleValue()+" Frequency:  "+ ((Number)textTPFrequency.getValue()).doubleValue()+"  Duration:  "+ ((Number)textTPDuration.getValue()).doubleValue()
				);
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
