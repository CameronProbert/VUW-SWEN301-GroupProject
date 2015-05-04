/**
 * 
 */
package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * @author jacky
 *
 */
@SuppressWarnings("serial")
public class FunctionGUI extends JFrame {
	/**
	 * 
	 */
	private JButton mailDelivery;
	private JButton f2;
	private JButton f3;
	private JButton f4;
	private JButton f5;
	private JButton businessEvents;
	private JButton exit;
	private JPanel displayPanel;
	private int count = 0;
	private String origin = "";
    private static JComboBox comboBox;

	public FunctionGUI() {
		aa();
	}

	public void aa(){
		displayPanel = new JPanel();
		displayPanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
		displayPanel.setPreferredSize(new Dimension(440, 480));
		JPanel buttonPane = new JPanel(); // 
		buttonPane.setPreferredSize(new Dimension(200, 480));
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		mailDelivery = new JButton("Mail Delivery");
		f2 = new JButton("Customer Price Update");
		f3 = new JButton("Transport Cost Update");
		f4 = new JButton("Transport Discontinued");
		f5 = new JButton("f5");
		businessEvents = new JButton("Business Events");
		exit = new JButton("exit");
		buttonPane.add(mailDelivery);
		buttonPane.add(f2);
		buttonPane.add(f3);
		buttonPane.add(f4);
		buttonPane.add(f5);
		buttonPane.add(businessEvents);
		buttonPane.add(exit);
		addListener(displayPanel);

		JFrame frame = new JFrame ();
		frame.add ( displayPanel );
		frame.setTitle("Adventure Game");
		frame.getRootPane().setBackground(Color.BLACK);
		frame.setSize(900,770);
		frame.add(buttonPane, BorderLayout.WEST);
		frame.pack();
		frame.setLocationRelativeTo ( null );
		frame.setVisible(true);
	}
	public void logPane(JPanel displayPanel){
		JTextArea display = new JTextArea ( 20, 30);
		display.setEditable ( false ); // set textArea non-editable
		JScrollPane scroll = new JScrollPane ( display );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		displayPanel.add ( scroll );
	}
	public void mailDeliveryPane(JPanel displayPanel){
		String destination = "";
		
	    String[] distributionCentres = { "Auckland", "Hamilton", "Rotorua", "Palmerston North",
	    		"Wellington", "Christchurch","Dunedin"};
	    comboBox = new JComboBox(distributionCentres);
	    comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				origin = (String) ((JComboBox)e.getSource()).getSelectedItem();
				System.out.println(origin);
			}
		});
	   // distributionCentreList.setSelectedIndex(1);
	    displayPanel.add ( comboBox);


		System.out.println("bbb");

	}
	
	public static void main(String[] arguments) {
		new FunctionGUI();
	}

	public void addListener(JPanel displayPanel){
		System.out.println("aaa");
		mailDelivery.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == mailDelivery){
					mailDeliveryPane(displayPanel);
				}
			}
		});
		businessEvents.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == businessEvents){
					logPane(displayPanel);
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.exit(0);	// if button Exit is clicked, the frame will be closed
			}
		});
	}
	
	

}
