package main.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The BusinessEvent class is a JPanel which show business event
 *
 * @author zhaojiang chang
 *
 */
public class BusinessEventPane extends Panel {

	// value labels on the panel
	private static JLabel revenue;
	private static JLabel expenditure;
	private static JLabel events;
	private static List<String>event = new ArrayList<String>();

	public BusinessEventPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
		this.setPreferredSize(new Dimension(gui.getWidth()*3/5-40,gui.getHeight()-250));

	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);
	}

	@Override
	public void propertyChange(PropertyChangeEvent e ) {}

	@Override
	protected void addListenner() {}

	// controller calls to set the updated revenue
	public void setRevenue(double r){
		revenue.setText("" + r);
	}

	// controller calls to set the updated expenditure
	public void setExpend(double e){
		expenditure.setText("" + e);
	}

	// controller calls to set the updated events
	public void setEvents(int e){
		events.setText("" + e);
	}

	public static List<String> getEvent() {
		return event;
	}

	public static void setEvent(List<String> event) {
		BusinessEventPane.event = event;
		System.out.println("add new events on business event pane");
	}
	
}