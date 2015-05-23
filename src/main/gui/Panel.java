package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.controllers.UIController;

/**
 * The Panel class is an abstract class represented many Pane 
 * classes shown on the GUI. The Panel class has several methods 
 * that could be extended by other Pane classes. 
 *
 * @author Zhiheng Sun and Zhaojiang Chang
 */
public abstract class Panel extends JPanel implements PropertyChangeListener {
	protected UIController controller;
	protected GUI gui;	// the GUI that panel is on
	protected double amount = 0;
	protected NumberFormat amountFormat;
	protected String[] distributionCentres = {  "Auckland", "Hamilton", "Rotorua", "Palmerston North",
			"Wellington", "Christchurch","Dunedin"};
	protected String[] priorityList = {"Air","Standard"};
	protected String[] TransportTpyeList = {"Land", "Sea","Air"};
	protected String[] TransportDateList = {"Monday", "Tuesday","Wednessday","Thursday","Friday","Saturday","Sunday"};
	protected String[] TransportFirmList = {"Air NZ", "NZ Post"};
	protected static String origin = "";
	protected static String destination = "";
	protected static String priority = "";
	protected static String transportType = "";
	protected static String transportFirm = "";
	protected static String selected = "";
	protected static JComboBox comboBoxOrigin;
	protected static JComboBox comboBoxDestination;
	protected static JComboBox comboBoxPriority;
	protected static JComboBox comboBoxTransportTpye;
	protected static JComboBox comboBoxTransportFirm;

	public Panel (GUI gui){
		this.gui = gui;
		// set the panel to transparent and call methods to set up buttons and listener
		setOpaque(false);
		setUpComponents();
		addListenner();
		//this.controller = this.gui.getUIController();
	}

	/**
	 * The following method initializes the components on it and calls method to set the
	 * style of the buttons
	 */
	protected abstract void setUpComponents();

	/**
	 * The following method adds action listeners onto buttons of the panel
	 */
	protected abstract void addListenner();

	/**
	 * The following method sets the button style by the given
	 * characteristics and adds the button onto the panel
	 * @param button	the given button to set style on
	 * @param buttonWidth	the given width of the button
	 * @param defaultColor	the default color of the given button
	 */
	protected void setButtonStyle (final JButton button, int buttonWidth, final Color defaultColor){
		// set the button size and font
		button.setPreferredSize(new Dimension(buttonWidth, 45));
		button.setFont(new Font("Arial", Font.PLAIN, 30));
		//		button.setForeground(defaultColor);
		button.setBackground(defaultColor);
		button.setHorizontalTextPosition(SwingConstants.CENTER);

		button.setBackground(defaultColor);
		button.setForeground(Color.WHITE);
		// set the button to transparent
		button.setBorder(null);
		button.setOpaque(true);
		button.setContentAreaFilled(true);
		button.setBorderPainted(true);
		button.setFocusPainted(true);

		// add mouseListener onto the button
		button.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {
				//				button.setForeground(defaultColor);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				//				button.setForeground(new Color(100, 200, 100).brighter());
			}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		// add the button to the panel
		add(button);
	}
	protected void formatToDobuleJTextField(JFormattedTextField textField) {
		// TODO Auto-generated method stub
		textField.setValue(new Double(amount));
		textField.setColumns(10);
		textField.addPropertyChangeListener("value", this);
	}

	protected void comboBoxListenner(JComboBox comboBox,final String type){
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				selected= (String) ((JComboBox)e.getSource()).getSelectedItem();
				System.out.println(selected);

				if(type.equals("origin")){
					origin = selected;

				}
				else if(type.equals("destination")){
					destination = selected;
					//System.out.println(destination);

				}
				else if(type.equals("priority")){
					priority = selected;
					//System.out.println(priority);

				}
				else if(type.equals("transportType")){
					transportType = selected;
				}
				else if(type.equals("transportFirm")){
					transportFirm = selected;
				}

			}
		});
	}

	public static String getOrigin() {
		return origin;
	}

	public static void setOrigin(String origin) {
		Panel.origin = origin;
	}

	public static String getDestination() {
		return destination;
	}

	public static void setDestination(String destination) {
		Panel.destination = destination;
	}

	public static String getPriority() {
		return priority;
	}

	public static void setPriority(String priority) {
		Panel.priority = priority;
	}

	public static String getTransportType() {
		return transportType;
	}

	public static void setTransportType(String transportType) {
		Panel.transportType = transportType;
	}

	public static String getTransportFirm() {
		return transportFirm;
	}

	public static void setTransportFirm(String transportFirm) {
		Panel.transportFirm = transportFirm;
	}

}
