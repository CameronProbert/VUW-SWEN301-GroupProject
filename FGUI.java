package main.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLayeredPane;

public class FGUI extends JPanel {

	private static JComboBox comboBox;
	private static JTextField textField;
	private JButton mailDelivery;
	private JButton f2;
	private JButton f3;
	private JButton f4;
	private JButton f5;
	private JButton businessEvents;
	private JButton exit;
	private JPanel displayPane;
	private int count = 0;
	private String origin = "";
	private JFrame f;
	private JLayeredPane layeredPane;	// this is used to add panel onto the frame



	// Create a form with the fields
	public FGUI() {
		super(new BorderLayout());
		aa();
	}

	public void aa(){
		f = new JFrame("KPSmart");
		layeredPane = new JLayeredPane();
		f.setSize(900,770);
		
		f.setLayeredPane(layeredPane);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		displayPane = new JPanel(new GridLayout(1,1));
		displayPane.setBackground(Color.blue);

//		displayPane.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
//		displayPane.setPreferredSize(new Dimension(440, 480));
//		displayPane.setLayout(new BorderLayout());
		layeredPane.add(displayPane, JLayeredPane.DEFAULT_LAYER);

//		JPanel buttonPane = new JPanel(); // 
//		buttonPane.setPreferredSize(new Dimension(200, 480));
//		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
//		mailDelivery = new JButton("Mail Delivery");
//		f2 = new JButton("Customer Price Update");
//		f3 = new JButton("Transport Cost Update");
//		f4 = new JButton("Transport Discontinued");
//		f5 = new JButton("f5");
//		businessEvents = new JButton("Business Events");
//		exit = new JButton("exit");
//		buttonPane.add(mailDelivery);
//		buttonPane.add(f2);
//		buttonPane.add(f3);
//		buttonPane.add(f4);
//		buttonPane.add(f5);
//		buttonPane.add(businessEvents);
//		buttonPane.add(exit);
		//layeredPane.add(buttonPane, JLayeredPane.MODAL_LAYER);
		f.repaint();

//		JPanel labelPanel = new JPanel();
//		labelPanel.setBackground(Color.green);
//		displayPane.add(labelPanel, BorderLayout.WEST);

		

		// Show the frame
		//f.add ( displayPane );
		f.setTitle("KPSmart");
		//f.getRootPane().setBackground(Color.BLACK);
		//f.add(buttonPane, BorderLayout.WEST);
		f.pack();
		
		addListener();

	}

	public static void main(String[] args) {
		final FGUI form = new FGUI();

	}

	private static void createIban(String selectedItem, String text) {
		// Do stuff with your data
		System.out.println("Im in createIban with the values: " + selectedItem + " and " + text);
	}

	public JPanel mailDeliveryPane(){
		System.out.println("aaa");
		// Panel for the labels
		//JPanel labelPanel = new JPanel(new GridLayout(2, 1)); // 2 rows 1 column
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(Color.green);
		labelPanel.setPreferredSize(new Dimension(140, 180));

		// Panel for the fields
		//JPanel fieldPanel = new JPanel(new GridLayout(2, 1)); // 2 rows 1 column
//		JPanel fieldPanel = new JPanel();
//		fieldPanel.setBackground(Color.black);
		// Combobox
	//	JLabel labelCombo = new JLabel("Bank Code");

		// Options in the combobox
	//	String[] options = {  "Auckland", "Hamilton", "Rotorua", "Palmerston North",
	//			"Wellington", "Christchurch","Dunedin"};
	//	comboBox = new JComboBox(options);
	//	comboBox.addActionListener(new ActionListener() {
		//	@Override
		//	public void actionPerformed(ActionEvent e) {
				// Do something when you select a value
	//		}
	//	});

		// Textfield
		//JLabel labelTextField = new JLabel("Bank account number");
		textField = new JTextField();

		// Add labels
		//labelPanel.add(labelCombo);
		//labelPanel.add(labelTextField);

		// Add fields
		//fieldPanel.add(comboBox);
		//fieldPanel.add(textField);
//		f.getContentPane().add(displayPane, BorderLayout.NORTH);
		//JButton submit = new JButton("Submit Form");
		//submit.addActionListener(new ActionListener() {
		//	@Override
		//	public void actionPerformed(ActionEvent e) {
		//		createIban((String) comboBox.getSelectedItem(), textField.getText());
		//	}
		//});
		// Panel with the button
		//displayPane.add(submit);
		//displayPane.add(submit);
		//f.getContentPane().add(displayPane, BorderLayout.WEST);
		//displayPane.add(submit, BorderLayout.NORTH);
		//displayPane.add(fieldPanel, BorderLayout.EAST);
		return labelPanel;
	}
	public void logPane(){
		JTextArea display = new JTextArea ( 20, 30);
		display.setEditable ( false ); // set textArea non-editable
		JScrollPane scroll = new JScrollPane ( display );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		displayPane.add ( scroll );
	}
	public void addListener(){
		mailDelivery.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == mailDelivery){
					f.add(mailDeliveryPane(), BorderLayout.WEST);
					f.repaint();

				}
			}
		});
		businessEvents.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == businessEvents){
					logPane();
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