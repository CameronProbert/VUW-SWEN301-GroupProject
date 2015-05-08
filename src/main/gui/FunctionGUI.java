package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
	private JButton logOut;
	private JPanel displayPane;
	private JPanel buttonPane;
	private JPanel inforPanel;
    private static JComboBox comboBox;
	private JSplitPane jSplitPanel;
	private String loginType = "";
	public FunctionGUI(GUI gui, String loginType) {
		super(gui);
		this.loginType = loginType;
		setBounds(0, 0, gui.getWidth(), gui.getHeight());	
	}

	@Override
	protected void setUpComponents() {
		JPanel titlePanel = new JPanel(new BorderLayout());
		addImage("image/topImage.jpg", titlePanel, 60);
		titlePanel.setPreferredSize(new Dimension(gui.getWidth(),60));
		JPanel bottomPanel = new JPanel(new BorderLayout());
		addImage("image/bottomImage.jpg", bottomPanel, 60);
		bottomPanel.setPreferredSize(new Dimension(gui.getWidth(),60));
		jSplitPanel = new JSplitPane();

		displayPane = new JPanel(new BorderLayout());
		displayPane.setBorder ( new TitledBorder ( new EtchedBorder (), "" ) );
		displayPane.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()-160));
		buttonPane = new JPanel(new BorderLayout()); // 
		buttonPane.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()-160));
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		inforPanel = new JPanel(new BorderLayout());
		//inforPanel.setOpaque(false);
		inforPanel.setPreferredSize(new Dimension(gui.getWidth()*3/5-25, gui.getHeight()-160));
		inforPanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Business Events" ) );
		//TODO: find a way to set the width of the button 
		mailDelivery = new JButton("Mail Delivery                        ");
		customerPriceUpdate = new JButton("Customer Price Update    ");
		transportCostUpdate = new JButton("Transport Cost Update      ");
		transportDiscontinued = new JButton("Transport Discontinued    ");
		businessEvents = new JButton("Business Events                 ");
		logOut = new JButton("Log Out                                  ");
		//logOut.setBounds(0, 300, 200, 30);
		//logOut.setPreferredSize(new Dimension(300,40));
		mailDelivery.setBackground(Color.white);
		customerPriceUpdate.setBackground(Color.white);
		transportCostUpdate.setBackground(Color.white);
		transportDiscontinued.setBackground(Color.white);
		businessEvents.setBackground(Color.white);
		logOut.setBackground(Color.white);

		//logOut.setSize(200, 30);
		
		buttonPane.add(mailDelivery);
		buttonPane.add(customerPriceUpdate);
		buttonPane.add(transportCostUpdate);
		buttonPane.add(transportDiscontinued);
		buttonPane.add(businessEvents);
		buttonPane.add(logOut);
		jSplitPanel.add(buttonPane, JSplitPane.LEFT);
		jSplitPanel.add(displayPane, JSplitPane.RIGHT);

		//jSplitPanel.add(inforPane, JSplitPane.RIGHT);
		add(titlePanel);
		add(jSplitPanel);
		add(inforPanel);
		add(bottomPanel);

	}

	private void addImage(String iamgeAdd, JPanel panel, int height) {
		// TODO Auto-generated method stub
		ImageIcon topImage = new ImageIcon(iamgeAdd);		
		Image scaledImage = topImage.getImage().getScaledInstance(900,height,Image.SCALE_SMOOTH);
		JLabel jl = new JLabel(new ImageIcon(scaledImage));
		panel.add(jl);
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
					if(loginType.equals("Manager")){
						inforPanel.add(new BusinessEventsPane(gui));
					}
				}
			}
		});
		final Panel p =  this; 
		logOut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {	
				gui.removePanel(p);
				gui.addPanel(new LoginGUI(gui));
				
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

}
