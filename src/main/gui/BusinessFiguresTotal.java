package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The BusinessFiguresTotal class is a JPanel which is added
 * on the button panel to show users the business figures.   
 * BusinessFiguresTotal class is responsible for directly 
 * letting users know the key general business figures.
 *
 * * @author Zhiheng Sun
 * 
 */
public class BusinessFiguresTotal extends Panel {

	// disabled textfields on the panel
	private static JFormattedTextField revenue;
	private static JFormattedTextField expenditure;
	private static JFormattedTextField events;

	public BusinessFiguresTotal(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		// labels and textfields for the business figures
		JLabel title = new JLabel("Business Figures  ", SwingConstants.CENTER);
		title.setFont(new Font("Arial", Font.PLAIN, 18));

		JLabel labelRevenue= new JLabel(" Total Revenue", SwingConstants.LEFT);
		revenue = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(revenue);

		JLabel labelExpend= new JLabel(" Total Expenditure", SwingConstants.LEFT);
		expenditure = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(expenditure);

		JLabel labelEvents= new JLabel(" Total Number of Events", SwingConstants.LEFT);
		events = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(events);

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

		// add the labels and textfields onto the panel
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(title);
		add(labelRevenue);
		add(revenue);
		add(labelExpend);
		add(expenditure);
		add(labelEvents);
		add(events);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e ) {}

	@Override
	protected void addListenner() {}
	
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
}
