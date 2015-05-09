package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controllers.UIController;

/**
 * FunctionGUI have all the function buttons
 * this class is going to add all buttons and add Action Listener
 * for each button.
 * @author zhaojiang chang
 */
 
public class FunctionGUI extends Panel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected UIController controller;
	// buttons on the panel
	private JButton mailDelivery;
	private JButton customerPriceUpdate;
	private JButton transportCostUpdate;
	private JButton transportDiscontinued;
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
		this.controller = this.gui.getUIController();
	}
/**
 * setup all all buttons, panel and label on the functionGUI panel
 */
	@Override
	protected void setUpComponents() {
		//titlePanel 
		JPanel titlePanel = new JPanel(new BorderLayout());
		addImage("image/topImage.jpg", titlePanel, 60);
		titlePanel.setPreferredSize(new Dimension(gui.getWidth(),60));
		//bottomPanel
		JPanel bottomPanel = new JPanel(new BorderLayout());
		addImage("image/bottomImage.jpg", bottomPanel, 60);
		bottomPanel.setPreferredSize(new Dimension(gui.getWidth(),60));
		//buttonpane and displaypane
		jSplitPanel = new JSplitPane();
		displayPane = new JPanel(new BorderLayout());
		displayPane.setBorder ( new TitledBorder ( new EtchedBorder (), "" ) );
		displayPane.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()-160));
		buttonPane = new JPanel(new BorderLayout()); //
		buttonPane.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()-160));
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		//business events panel
		inforPanel = new JPanel(new BorderLayout());
		inforPanel.setPreferredSize(new Dimension(gui.getWidth()*3/5-25, gui.getHeight()-160));
		inforPanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Business Events" ) );
		//TODO: find a way to set the width of the button
		//buttons
		mailDelivery = new JButton("Mail Delivery                        ");
		customerPriceUpdate = new JButton("Customer Price Update    ");
		transportCostUpdate = new JButton("Transport Cost Update      ");
		transportDiscontinued = new JButton("Transport Discontinued    ");
		businessEvents = new JButton("Business Events                 ");
		logOut = new JButton("Log Out                                  ");
		mailDelivery.setBackground(Color.white);
		customerPriceUpdate.setBackground(Color.white);
		transportCostUpdate.setBackground(Color.white);
		transportDiscontinued.setBackground(Color.white);
		businessEvents.setBackground(Color.white);
		logOut.setBackground(Color.white);
		//add buttons to panel
		buttonPane.add(mailDelivery);
		buttonPane.add(customerPriceUpdate);
		buttonPane.add(transportCostUpdate);
		buttonPane.add(transportDiscontinued);
		buttonPane.add(businessEvents);
		buttonPane.add(logOut);
		//add buttonpane and displayPane to split panel
		jSplitPanel.add(buttonPane, JSplitPane.LEFT);
		jSplitPanel.add(displayPane, JSplitPane.RIGHT);
		//add all panels to functionGUI panel
		add(titlePanel);
		add(jSplitPanel);
		add(inforPanel);
		add(bottomPanel);

	}
	/**
	 * add image on to the panel
	 * @param iamgeAdd image address
	 * @param panel 
	 * @param height height of the panel
	 */
	private void addImage(String imageAdd, JPanel panel, int height) {
		// TODO Auto-generated method stub
		ImageIcon topImage = new ImageIcon(imageAdd);
		Image scaledImage = topImage.getImage().getScaledInstance(900,height,Image.SCALE_SMOOTH);
		JLabel jl = new JLabel(new ImageIcon(scaledImage));
		panel.add(jl);
	}
	
	@Override
	protected void addListenner() {
		mailDelivery.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if(button == mailDelivery){
					displayPane.setVisible(false);
					init();
					jSplitPanel.add(new MailDeliveryPane(gui), JSplitPane.RIGHT);
				}
			}
		});
		transportCostUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if(button == transportCostUpdate){
					init();
					displayPane.setVisible(false);
					jSplitPanel.add(new TransportCostUpdatePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		customerPriceUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if(button == customerPriceUpdate){
					init();
					displayPane.setVisible(false);
					jSplitPanel.add(new CustomerPriceUpdatePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		transportDiscontinued.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
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
				JButton button = (JButton) e.getSource();
				if(button == businessEvents){
					if(loginType.equals("Manager")){
						init();
						inforPanel.add(new BusinessEventsPane(gui));
					}
				}
			}
		});
		final Panel p =  this;
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.removePanel(p);
				gui.removePanel(gui.getBackgroundBlank());
				gui.addBGPanel(gui.getBackgroundPanel());
				gui.addPanel(new LoginGUI(gui));
				controller.logOut();

			}
		});
	}
	/**
	 * big text area may need for business event
	 */
	public void logPane(){
		JTextArea display = new JTextArea ( 20, 30);
		display.setEditable ( false ); // set textArea non-editable
		JScrollPane scroll = new JScrollPane ( display );
		scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setBounds(200, 200, 200, 200);
		displayPane.add ( scroll );
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
	/**
	 * initialize all fields, when click the button 
	 */
	private void init(){
		origin = "";
		destination = "";
		priority = "";
		transportType = "";
		transportFirm = "";
		selected = "";
	}

}
