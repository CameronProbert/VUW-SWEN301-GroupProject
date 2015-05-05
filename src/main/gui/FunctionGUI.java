package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * The JoinServerPanel class is a JPanel which is represented on 
 * the frame once the player choose to start a game in a server. 
 * JoinServerPanel class is responsible for letting player enter 
 * the server information and then start the game.
 * 
 */
public class FunctionGUI extends Panel{

	// buttons on the panel
	private JButton mailDelivery;
	private JButton customerPriceUpdate;
	private JButton transportCostUpdate;
	private JButton transportDiscontinued;
	private JButton f5;
	private JButton businessEvents;
	private JButton exit;
	private JPanel displayPane;
	private JPanel buttonPane;
	private JPanel inforPane;
	private int count = 0;
	private String origin = "";
	private String destination = "";
    private static JComboBox comboBox;
	private JButton reset;
	private JButton login;
	private JSplitPane jSplitPanel;
	public FunctionGUI(GUI gui) {
		super(gui);
		setBounds(0, 0, gui.getWidth(), gui.getHeight());	
	}

	@Override
	protected void setUpComponents() {
		jSplitPanel = new JSplitPane();
		displayPane = new JPanel(new BorderLayout());
		displayPane.setBorder ( new TitledBorder ( new EtchedBorder (), "Display Area" ) );
		displayPane.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()));
		buttonPane = new JPanel(new BorderLayout()); // 
		buttonPane.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()));
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		inforPane = new JPanel(new BorderLayout());
		inforPane.setPreferredSize(new Dimension(gui.getWidth()*3/5-25, gui.getHeight()));

		mailDelivery = new JButton("Mail Delivery");
		customerPriceUpdate = new JButton("Customer Price Update");
		transportCostUpdate = new JButton("Transport Cost Update");
		transportDiscontinued = new JButton("Transport Discontinued");
		f5 = new JButton("f5");
		businessEvents = new JButton("Business Events");
		exit = new JButton("exit");
		buttonPane.add(mailDelivery);
		buttonPane.add(customerPriceUpdate);
		buttonPane.add(transportCostUpdate);
		buttonPane.add(transportDiscontinued);
		buttonPane.add(f5);
		buttonPane.add(businessEvents);
		buttonPane.add(exit);
		jSplitPanel.add(buttonPane, JSplitPane.LEFT);
		jSplitPanel.add(displayPane, JSplitPane.RIGHT);
		//jSplitPanel.add(inforPane, JSplitPane.RIGHT);
		add(jSplitPanel);
		add(inforPane);

	}

	@Override
	protected void addListenner() {
		mailDelivery.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == mailDelivery){
					displayPane.setVisible(false);
					jSplitPanel.add(new MailDeliveryPane(gui), JSplitPane.RIGHT);
				}
			}
		});
		transportCostUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == transportCostUpdate){
					displayPane.setVisible(false);
					jSplitPanel.add(new TransportCostUpdatePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		customerPriceUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == customerPriceUpdate){
					displayPane.setVisible(false);
					jSplitPanel.add(new CustomerPriceUpdatePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		transportDiscontinued.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == transportDiscontinued){
					displayPane.setVisible(false);
					jSplitPanel.add(new TransportDiscontinuedPane(gui), JSplitPane.RIGHT);
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
	public void logPane(){
		JTextArea display = new JTextArea ( 20, 30);
		display.setEditable ( false ); // set textArea non-editable
		JScrollPane scroll = new JScrollPane ( display );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setBounds(200, 200, 200, 200);	
		displayPane.add ( scroll );
	}
//	public void mailDeliveryPane(){
//	    String[] distributionCentres = { "Auckland", "Hamilton", "Rotorua", "Palmerston North",
//	    		"Wellington", "Christchurch","Dunedin"};
//	    comboBox = new JComboBox(distributionCentres);
//	    comboBox.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				origin = (String) ((JComboBox)e.getSource()).getSelectedItem();
//				System.out.println(origin);
//			}
//		});
	   // distributionCentreList.setSelectedIndex(1);
//	    displayPane.add ( comboBox);
//		System.out.println("bbb");
//
//	}
}
