package main.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The BusinessEvent class is a JPanel which show business event
 *
 * @author zhaojiang chang & zhiheng sun
 *
 */
public class BusinessEventTab extends Panel {



	public BusinessEventTab(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);
		addLabels();
	}

	protected void addLabels() {
		for (int i = 0; i < currentEvent.size(); i++) {
			JLabel value = new JLabel(currentEvent.get(i), SwingConstants.LEFT);
			value.setFont(new Font("Dialog", Font.PLAIN, 14));
			add(value);
		}
	}

	public static void setEvent(List<String> event) {
		System.out.println("add new events on business event pane");
	}

	@Override
	protected void addListenner() {}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {}
}