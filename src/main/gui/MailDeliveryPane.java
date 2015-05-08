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
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
public class MailDeliveryPane extends Panel{

	// buttons on the panel
	private int count = 0;
	private String location = "";
	private String origin = "";
	private String destination = "";
	private String priority = "";
	private static JComboBox comboBoxOrigin;
	private static JComboBox comboBoxDestination;
	private static JComboBox comboBoxPriority;
	private static JTextField textWeight;
	private static JTextField textVolume;
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
		String[] distributionCentres = {  "Auckland", "Hamilton", "Rotorua", "Palmerston North",
				"Wellington", "Christchurch","Dunedin"};
		String[] priorityList = {"Standard", "Air"};
		comboBoxOrigin = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxOrigin);
		origin = location;
		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxDestination);
		destination = location;
		// Textfield
		JLabel labelWeight= new JLabel("Weight", SwingConstants.CENTER);
		textWeight = new JTextField(10);
		JLabel labelVolume= new JLabel("Volume", SwingConstants.CENTER);
		textVolume = new JTextField(10);
		JLabel labelPriority= new JLabel("Priority", SwingConstants.CENTER);
		comboBoxPriority = new JComboBox(priorityList);
		comboBoxListenner(comboBoxPriority);
		priority = location;
		JLabel labelCurrentTime= new JLabel("Time of entry into the system", SwingConstants.CENTER);
		textTime = new JTextField(20);
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

	}
}
