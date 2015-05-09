package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The BusinessFiguresPane class is a JPanel which is added
 * on the display panel to show users the business figures.   
 * BusinessFiguresPane class is responsible for letting users 
 * choose a route and displaying the corresponding business 
 * figures.
 *
 * * @author Zhiheng Sun
 * 
 */
public class BusinessFiguresPane extends Panel {

	// disabled textfields on the panel
	private static JFormattedTextField revenue;
	private static JFormattedTextField expenditure;
	private static JFormattedTextField events;
	private static JFormattedTextField averageTime;

	public BusinessFiguresPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		// labels and textfields for the business figures
		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
		comboBoxOrigin = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxOrigin, "origin");

		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxDestination, "destination");

		JLabel labelPriority= new JLabel("Priority", SwingConstants.CENTER);
		comboBoxPriority = new JComboBox(priorityList);
		comboBoxListenner(comboBoxPriority, "priority");

		JLabel labelRevenue= new JLabel(" Revenue", SwingConstants.LEFT);
		revenue = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(revenue);

		JLabel labelExpend= new JLabel(" Expenditure", SwingConstants.LEFT);
		expenditure = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(expenditure);

		JLabel labelEvents= new JLabel(" Number of Events", SwingConstants.LEFT);
		events = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(events);

		JLabel labelAverageTime = new JLabel(" Average Delivery Time", SwingConstants.LEFT);
		averageTime = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(averageTime);

		// disabled textfields shown business figures
		revenue.disable();
		revenue.setText("0.0");  
		revenue.setDisabledTextColor(Color.BLACK);
		revenue.setPreferredSize(new Dimension(250, 50));
		revenue.setFont(new Font("Arial", Font.PLAIN, 15));

		expenditure.disable();
		expenditure.setText("0.0");  
		expenditure.setDisabledTextColor(Color.BLACK);
		expenditure.setPreferredSize(new Dimension(250, 50));
		expenditure.setFont(new Font("Arial", Font.PLAIN, 15));

		events.disable();
		events.setText("0"); 
		events.setDisabledTextColor(Color.BLACK);
		events.setPreferredSize(new Dimension(250, 50));
		events.setFont(new Font("Arial", Font.PLAIN, 15));

		averageTime.disable();
		averageTime.setText("0.0"); 
		averageTime.setDisabledTextColor(Color.BLACK);
		averageTime.setPreferredSize(new Dimension(250, 50));
		averageTime.setFont(new Font("Arial", Font.PLAIN, 15));

		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);		
		add(labelPriority);
		add(comboBoxPriority);
		add(labelRevenue);
		add(revenue);
		add(labelExpend);
		add(expenditure);
		add(labelEvents);
		add(events);
		add(labelAverageTime);
		add(averageTime);
	}

	protected void comboBoxListenner(JComboBox comboBox, final String type){
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selected = (String) ((JComboBox)e.getSource()).getSelectedItem();

				if(type.equals("origin")){
					origin = selected;
				} else if(type.equals("destination")){
					destination = selected;
				} else if(type.equals("priority")){
					priority = selected;
				}
				
				if (!origin.equals("") && !destination.equals("") && !priority.equals("")){
					System.out.println("@@@@@@@");
					// call a controller method and pass origin, destination and priority 
					// that method is supposed to get the business figures and call the following set methods.
				}
			}
		});
	}

	@Override
	protected void addListenner() {}

	@Override
	public void propertyChange(PropertyChangeEvent e ) {}

	// controller calls to set the updated revenue
	protected void setRevenue(double r){
		revenue.setText("" + r);
	}

	// controller calls to set the updated expenditure
	protected void setExpend(double e){
		expenditure.setText("" + e);
	}

	// controller calls to set the updated events
	protected void setEvents(double e){
		events.setText("" + e);
	}

	// controller calls to set the updated events
	protected void setAverageTime(double a){
		averageTime.setText("" + a);
	}
}
