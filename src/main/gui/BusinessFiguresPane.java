package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Date;

import javax.swing.BorderFactory;
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
 * @author Zhiheng Sun
 *
 */
public class BusinessFiguresPane extends Panel {

	// value labels on the panel
	private static JLabel revenue;
	private static JLabel expenditure;
	private static JLabel events;
	private static JLabel averageTime;

	public BusinessFiguresPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
		gui.setBusinessFiguresPane(this);

	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		// create labels for the business figures
		isBusinessFigures = true;
		comboBoxRouteList();

		JLabel labelRevenue= new JLabel(" Revenue", SwingConstants.LEFT);
		revenue = new JLabel();

		JLabel labelExpend= new JLabel(" Expenditure", SwingConstants.LEFT);
		expenditure = new JLabel();

		JLabel labelEvents= new JLabel(" Number of Events", SwingConstants.LEFT);
		events = new JLabel();

		JLabel labelAverageTime = new JLabel(" Average Delivery Time", SwingConstants.LEFT);
		averageTime = new JLabel();

		// format value labels shown business figures
		//revenue.disable();
		revenue.setText(" 0.0");
		//revenue.setDisabledTextColor(Color.BLACK);
		revenue.setPreferredSize(new Dimension(250, 50));
		revenue.setFont(new Font("Arial", Font.PLAIN, 15));
		revenue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		//expenditure.disable();
		expenditure.setText(" 0.0");
		//expenditure.setDisabledTextColor(Color.BLACK);
		expenditure.setPreferredSize(new Dimension(250, 50));
		expenditure.setFont(new Font("Arial", Font.PLAIN, 15));
		expenditure.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		//events.disable();
		events.setText(" 0");
		//events.setDisabledTextColor(Color.BLACK);
		events.setPreferredSize(new Dimension(250, 50));
		events.setFont(new Font("Arial", Font.PLAIN, 15));
		events.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		//averageTime.disable();
		averageTime.setText(" 0.0");
		//averageTime.setDisabledTextColor(Color.BLACK);
		averageTime.setPreferredSize(new Dimension(250, 50));
		averageTime.setFont(new Font("Arial", Font.PLAIN, 15));
		averageTime.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		// add the labels onto the panel
		add(comboBoxRoute);
		add(labelRevenue);
		add(revenue);
		add(labelExpend);
		add(expenditure);
		add(labelEvents);
		add(events);
		add(labelAverageTime);
		add(averageTime);
	}
	@Override
	protected void addListenner() {}

	@Override
	public void propertyChange(PropertyChangeEvent e ) {}

	// controller calls to set the updated revenue
	public void setRevenue(double r){
		revenue.setText("" + r);
	}

	// controller calls to set the updated expenditure
	public void setExpend(double e){
		expenditure.setText("" + e);
	}

	// controller calls to set the updated events
	public void setEvents(double e){
		events.setText("" + e);
	}

	// controller calls to set the updated events
	public void setAverageTime(double a){
		averageTime.setText("" + a);
	}
}
