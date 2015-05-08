package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * The JoinServerPanel class is a JPanel which is represented on 
 * the frame once the player choose to start a game in a server. 
 * JoinServerPanel class is responsible for letting player enter 
 * the server information and then start the game.
 * 
 */
public class TransportCostUpdatePane extends Panel{

	// buttons on the panel
	private int count = 0;
	private String location = "";
	private String origin = "";
	private String destination = "";
	private String priority = "";
	private String transportType = "";
	private static JComboBox comboBoxOrigin;
	private static JComboBox comboBoxDestination;
	private static JComboBox comboBoxPriority;
	private static JComboBox comboBoxTransportTpye;
	private static JTextField textTPNewPricePerGram;
	private static JTextField textTPNewCostPerCubic;
	private static JTextField textTransportFirm;
	
	private JButton reset;
	private JButton update;

	public TransportCostUpdatePane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());	
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);
		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
		String[] distributionCentres = {  "Auckland", "Hamilton", "Rotorua", "Palmerston North",
				"Wellington", "Christchurch","Dunedin"};
		String[] priorityList = {"Air","Standard"};
		String[] TransportTpyeList = {"Land", "Sea","Air"};
		String[] TransportDateList = {"Monday", "Tuesday","Wednessday","Thursday","Friday","Saturday","Sunday"};
		comboBoxOrigin = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxOrigin);
		origin = location;
		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxDestination);
		destination = location;
		JLabel labelTransportFirm= new JLabel("Transport Firm", SwingConstants.CENTER);
		textTransportFirm = new JTextField(20);
		JLabel labelTransportType= new JLabel("Transport Tpye", SwingConstants.CENTER);
		comboBoxTransportTpye = new JComboBox(TransportTpyeList);
		comboBoxListenner(comboBoxTransportTpye);
		transportType = location;
		JLabel labelNewPricePerGram= new JLabel("New price per gram", SwingConstants.CENTER);
		textTPNewPricePerGram = new JTextField(10);
		JLabel labelNewCostPerCB= new JLabel("New price per cubic centimeter", SwingConstants.CENTER);
		textTPNewCostPerCubic = new JTextField(10);
		JLabel labelPriority= new JLabel("Priority", SwingConstants.CENTER);
		comboBoxPriority = new JComboBox(priorityList);
		comboBoxListenner(comboBoxPriority);
		priority = location;
		reset = new JButton("Reset");
		update = new JButton("Update");
		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);
		add(labelTransportFirm);
		add(textTransportFirm);
		add(labelTransportType);
		add(comboBoxTransportTpye);
		add(labelNewPricePerGram);
		add(textTPNewPricePerGram);
		add(labelNewCostPerCB);
		add(textTPNewCostPerCubic);
		add(labelPriority);
		add(comboBoxPriority);
		add(reset);
		add(update);

//		add(textField);
		System.out.println("22222222222222");
	}
	private void comboBoxListenner(JComboBox comboBox){
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				location = (String) ((JComboBox)e.getSource()).getSelectedItem();
				System.out.println(location);
			}
		});
	}
	@Override
	protected void addListenner() {
	}
}
