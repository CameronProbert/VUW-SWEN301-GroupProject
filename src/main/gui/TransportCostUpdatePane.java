package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 */
public class TransportCostUpdatePane extends Panel{

	// buttons on the panel
	private int count = 0;




	private static JFormattedTextField textTPNewPricePerGram;
	private static JFormattedTextField textTPNewCostPerCubic;
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
		comboBoxListenner(comboBoxOrigin, "origin");

		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxDestination, "destination");

		JLabel labelTransportFirm= new JLabel("Transport Firm", SwingConstants.CENTER);
		comboBoxTransportFirm = new JComboBox(TransportFirmList);
		//transportFirm = selected;

		JLabel labelTransportType= new JLabel("Transport Tpye", SwingConstants.CENTER);
		comboBoxTransportTpye = new JComboBox(TransportTpyeList);
		comboBoxListenner(comboBoxTransportTpye, "transportType");

		JLabel labelNewPricePerGram= new JLabel("New price per gram", SwingConstants.CENTER);
		textTPNewPricePerGram = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewPricePerGram);

		JLabel labelNewCostPerCB= new JLabel("New price per cubic centimeter", SwingConstants.CENTER);
		textTPNewCostPerCubic = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewCostPerCubic);

		JLabel labelPriority= new JLabel("Priority", SwingConstants.CENTER);
		comboBoxPriority = new JComboBox(priorityList);
		comboBoxListenner(comboBoxPriority, "priority");

		reset = new JButton("Reset");
		update = new JButton("Update");
		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);
		add(labelTransportFirm);
		add(comboBoxTransportFirm);
		add(labelTransportType);
		add(comboBoxTransportTpye);
		add(labelNewPricePerGram);
		add(textTPNewPricePerGram);
		add(labelNewCostPerCB);
		add(textTPNewCostPerCubic);
		add(labelPriority);
		add(comboBoxPriority);
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
					System.out.println(toStringTPU());
				}
			}
		});
	}

	public static double getTPCNewPricePerGram() {
		return ((Number)textTPNewPricePerGram.getValue()).doubleValue();
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
	        else if (source == textTPNewPricePerGram) {
	            amount = ((Number)textTPNewPricePerGram.getValue()).doubleValue();
	        }

	}
	public String toStringTPU(){
		return("Origin: "+ origin +"  Destination: "+ destination+"  Priority: "+priority+"   New Price Per Gram: "+ ((Number)textTPNewPricePerGram.getValue()).doubleValue()
				+"    New Price Per Gram:"+ ((Number)textTPNewCostPerCubic.getValue()).doubleValue() +"   Transport Firm: "+transportFirm+"   transport Type:  "+ transportType);
	}
}
