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

	private static JFormattedTextField textWeight;
	private static JFormattedTextField textVolume;
	private static JTextField textTime;
	private JButton reset;
	private JButton add;
	private JButton setDate;

	public MailDeliveryPane(GUI gui) {
		super(gui);
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

		JLabel labelWeight= new JLabel("Weight", SwingConstants.CENTER);
		textWeight = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textWeight);

		JLabel labelVolume= new JLabel("Volume", SwingConstants.CENTER);
		textVolume = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textVolume);

		JLabel labelPriority= new JLabel("Priority", SwingConstants.CENTER);
		comboBoxPriority = new JComboBox(priorityList);
		comboBoxListenner(comboBoxPriority, "priority");

		JLabel labelCurrentTime= new JLabel("Time of entry into the system", SwingConstants.CENTER);
		textTime = new JTextField(20);
		textTime.disable();
		textTime.setDisabledTextColor(Color.black);

		reset = new JButton("Reset");
		add = new JButton("Add");
		setDate = new JButton("Set Date and Time");
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
		add(labelCurrentTime);
		add(textTime);
		add(setDate);
		add(reset);
		add(add);

	}

	@Override
	protected void addListenner() {
		setDate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == setDate){
					Date currentDate = new Date();
					textTime.setText(currentDate.toString());
				}
			}
		});
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == add){
					System.out.println(toStringMD());

				}
			}
		});

	}
//	public String getMDOrigin() {
//		return origin;
//	}
//
//	public String getMDDestination() {
//		return destination;
//	}
//
//	public String getMDPriority() {
//		return priority;
//	}

	public static double getMDTextWeight() {
		return ((Number)textWeight.getValue()).doubleValue();
	}

	public static double getMDTextVolume() {
		return ((Number)textVolume.getValue()).doubleValue();
	}

	public static String getMDTextTime() {
		return textTime.getText();
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
	public String toStringMD(){
		return("Origin: "+ origin +"  Destination: "+ destination+"  Priority: "+priority+"   Volume: "+ ((Number)textVolume.getValue()).doubleValue()+"    Weight:"+
				((Number)textWeight.getValue()).doubleValue()+"    Date/Time:"+textTime.getText());
	}
}
