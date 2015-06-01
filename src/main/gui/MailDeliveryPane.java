package main.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.logic.Location;
import main.logic.Route;
import java.awt.SystemColor;

/**
 * The JoinServerPanel class is a JPanel which is represented on
 * the frame once the player choose to start a game in a server.
 * JoinServerPanel class is responsible for letting player enter
 * the server information and then start the game.
 *
 */
public class MailDeliveryPane extends Panel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// buttons on the panel

	private JButton reset;
	private JButton add;

	public MailDeliveryPane(GUI gui) {
		super(gui);
		setBackground(SystemColor.window);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
		comboBoxOrigin = new JComboBox(getOrigins());
		//comboBoxOrigin.setEditable(true);
		comboBoxOrigin.setSelectedItem(null);
		comboBoxListenner(comboBoxOrigin, "origin");

		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(getDestinations());
		//comboBoxDestination.setEnabled(true);
		comboBoxDestination.setSelectedItem(null);

		comboBoxListenner(comboBoxDestination, "destination");

		JLabel labelWeight= new JLabel("Weight", SwingConstants.CENTER);
		textWeight = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textWeight);

		JLabel labelVolume= new JLabel("Volume", SwingConstants.CENTER);
		textVolume = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textVolume);

		JLabel labelPriority= new JLabel("Priority", SwingConstants.CENTER);
		comboBoxPriority = new JComboBox(priorityList);
		comboBoxPriority.setSelectedItem(null);
		comboBoxListenner(comboBoxPriority, "priority");
		JTextField textTime = new JTextField(20);
		textTime.setEnabled(false);
		textTime.setEditable(false);
		textTime.setBackground(SystemColor.controlHighlight);
		textTime.disable();

		reset = new JButton("Reset");
		add = new JButton("Add");
		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);
		add(labelWeight);
		add(textWeight);
		add(labelVolume);
		add(textVolume);
		add(labelPriority);
		add(comboBoxPriority);
		add(reset);
		add(add);
		add(textTime);


	}

	@Override
	protected void addListenner() {
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == add){
					addBusinessEvent("mailDelivery");
				}
			}
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == reset){
					comboBoxOrigin.setSelectedItem(null);
					comboBoxDestination.setSelectedItem(null);
					init();
				}
			}
		});
	}

	public static double getMDTextWeight() {
		return ((Number)textWeight.getValue()).doubleValue();
	}

	public static double getMDTextVolume() {
		return ((Number)textVolume.getValue()).doubleValue();
	}

	@Override
	public void propertyChange(PropertyChangeEvent e ) {
		// TODO Auto-generated method stub
		 Object source = e.getSource();
	        if (source == textWeight) {
	            amount = ((Number)textWeight.getValue()).doubleValue();
	        }  else if (source == textVolume) {
	            amount = ((Number)textVolume.getValue()).doubleValue();
	        }
	}

}
