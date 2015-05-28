package main.gui;

import javax.swing.JFormattedTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Customer Price Update Panel
 * 
 *  @author zhaojiang chang
 *
 */
public class CustomerPriceUpdatePane extends Panel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton reset;
	private JButton update;

	public CustomerPriceUpdatePane(GUI gui) {
		super(gui);
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

		JLabel labelPriority= new JLabel("Priority", SwingConstants.CENTER);
		comboBoxPriority = new JComboBox(priorityList);
		comboBoxPriority.setSelectedItem(null);
		comboBoxListenner(comboBoxPriority, "priority");

		JLabel labelNewPricePerGram= new JLabel("New price per gram", SwingConstants.CENTER);
		textCustomerNewPricePerGram = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textCustomerNewPricePerGram);

		JLabel labelNewPricePerCB= new JLabel("New price per cubic centimeter", SwingConstants.CENTER);
		textCustomerNewPricePerCubic = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textCustomerNewPricePerCubic);

		reset = new JButton("Reset");
		update = new JButton("Update");
		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);
		add(labelPriority);
		add(comboBoxPriority);
		add(labelNewPricePerGram);
		add(textCustomerNewPricePerGram);
		add(labelNewPricePerCB);
		add(textCustomerNewPricePerCubic);
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
					addBusinessEvent("customerPriceUpdate");
				}
			}
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == reset){
					comboBoxDestination.setSelectedItem(null);
					comboBoxTransportFirm.setSelectedItem(null);
					comboBoxPriority.setSelectedItem(null);
					init();
				}
			}
		});
	}
	@Override
	public void propertyChange(PropertyChangeEvent e ) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == textCustomerNewPricePerGram) {
			amount = ((Number)textCustomerNewPricePerGram.getValue()).doubleValue();
		}  else if (source == textCustomerNewPricePerCubic) {
			amount = ((Number)textCustomerNewPricePerCubic.getValue()).doubleValue();
		}
	}

	public static double getCPUTextCustomerNewPricePerGram() {
		return ((Number)textCustomerNewPricePerGram.getValue()).doubleValue();
	}

	public static double getCPUTextNewPricePerCB() {
		return ((Number)textCustomerNewPricePerCubic.getValue()).doubleValue();
	}
	public String toStringCPU(){
		return("Origin: "+ origin +"  Destination: "+ destination+"  Priority: "+priority+ "   New price per gram: "+
				((Number)textCustomerNewPricePerGram.getValue()).doubleValue()+"   New price per cubic centimeter:"+((Number)textCustomerNewPricePerCubic.getValue()).doubleValue());
	}

}
