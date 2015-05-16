package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
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
	private JButton businessFigures;
	private JButton logOut;
	private JButton exit;
	private JButton addUser;
	private JButton removeUser;
	private JPanel displayPanel;
	private JPanel buttonPanel;
	private JPanel inforPanel;
	private static JComboBox comboBox;
	private JSplitPane jSplitPanel;
	private String loginType = "";
	public FunctionGUI(GUI gui) {
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
		JPanel bottomPanel = new JPanel(new FlowLayout(1,6,15));
		//bottomPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		bottomPanel.setPreferredSize(new Dimension(gui.getWidth(),60));

		//buttonPanel and displayPanel
		jSplitPanel = new JSplitPane();
		displayPanel = new JPanel(new BorderLayout());
		displayPanel.setBorder ( new TitledBorder ( new EtchedBorder (), "" ) );
		displayPanel.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()-160));
		buttonPanel = new JPanel(new BorderLayout()); //
		buttonPanel.setPreferredSize(new Dimension(gui.getWidth()*1/5, gui.getHeight()-160));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
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
		businessFigures = new JButton("Business Figures                 ");
		logOut = new JButton("Log Out");
		exit = new JButton("Exit");
		addUser = new JButton("Add User");
		removeUser = new JButton("Remove User");
		mailDelivery.setBackground(Color.white);
		customerPriceUpdate.setBackground(Color.white);
		transportCostUpdate.setBackground(Color.white);
		transportDiscontinued.setBackground(Color.white);
		businessEvents.setBackground(Color.white);
		businessFigures.setBackground(Color.white);
		//logOut.setBackground(Color.white);
		//add buttons to panel
		buttonPanel.add(mailDelivery);
		buttonPanel.add(customerPriceUpdate);
		buttonPanel.add(transportCostUpdate);
		buttonPanel.add(transportDiscontinued);
		buttonPanel.add(businessEvents);
		buttonPanel.add(businessFigures);
		buttonPanel.add(new BusinessFiguresTotal(gui));

		// add label and buttons onto bottomPanel
		JLabel usernameP = new JLabel("User: " + gui.getCurretUsername());
		usernameP.setFont(new Font("Arial", Font.PLAIN, 18));
		bottomPanel.add(usernameP);
		bottomPanel.add(addUser);
		bottomPanel.add(removeUser);
		bottomPanel.add(logOut);
		bottomPanel.add(exit);

		//addImage("image/bottomImage.jpg", bottomPanel, 60);
		//add buttonPanel and displayPanel to split panel
		jSplitPanel.add(buttonPanel, JSplitPane.LEFT);
		jSplitPanel.add(displayPanel, JSplitPane.RIGHT);
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
					displayPanel.setVisible(false);
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
					displayPanel.setVisible(false);
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
					displayPanel.setVisible(false);
					jSplitPanel.add(new CustomerPriceUpdatePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		transportDiscontinued.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if(button == transportDiscontinued){
					displayPanel.setVisible(false);
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
		businessFigures.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if(button == businessFigures){
					init();
					displayPanel.setVisible(false);
					jSplitPanel.add(new BusinessFiguresPane(gui), JSplitPane.RIGHT);
				}
			}
		});
		
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create textfields used on the dialog
				JTextField id = new JTextField();
				JPasswordField password = new JPasswordField();
				JPasswordField confirmPassword = new JPasswordField();
				JTextField name = new JTextField();
				JTextField isClerk = new JTextField();
				
				Object[] message = {
						"ID", id,
						"Password", password,
						"Confirm Password", confirmPassword,
						"Name", name,
						"Clerk?(Y/N)", isClerk
				};
				inputDialog(id, password, confirmPassword, name, isClerk, message);
			}

			// show the input dialog
			public void inputDialog(JTextField id, JTextField password, JTextField confirmPassword,  JTextField name, JTextField isClerk, Object[] message){

				int option = JOptionPane.showConfirmDialog(null, message, "Add New User", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					boolean isFailed = false;

					// check whether password is empty or not
					if(password.getText().equals("") || password.getText().equals("cannot be empty")){
						password.setText("cannot be empty");
						password.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else {
						password.setBackground(Color.WHITE);
					}

					// check whether confirmPassword is empty or not
					if(confirmPassword.getText().equals("") || confirmPassword.getText().equals("cannot be empty")){
						confirmPassword.setText("cannot be empty");
						confirmPassword.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					}  else {
						confirmPassword.setBackground(Color.WHITE);
					}
					
					// check whether password and confirmPassword are consistant
					if(!password.getText().equals(confirmPassword.getText()) || password.getText().equals("password not match") || confirmPassword.getText().equals("password not match")){
						password.setText("password not match");
						password.setBackground(Color.LIGHT_GRAY);
						confirmPassword.setText("password not match");
						confirmPassword.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else {
						password.setBackground(Color.WHITE);
						confirmPassword.setBackground(Color.WHITE);
					}

					// check whether id is empty or not
					if(id.getText().equals("") || id.getText().equals("cannot be empty")){
						id.setText("cannot be empty");
						id.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else {
						id.setBackground(Color.WHITE);
					}

					// check whether name is empty or not
					if(name.getText().equals("") ||  name.getText().equals("cannot be empty")){
						name.setText("cannot be empty");
						name.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else {
						name.setBackground(Color.WHITE);
					}

					// check whether isClerk is empty or not and whether isClerk is something rather than y nad n
					if (isClerk.getText().equals("") || isClerk.getText().equals("cannot be empty")){
						isClerk.setText("cannot be empty");
						isClerk.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else if(!isClerk.getText().equalsIgnoreCase("y") && !isClerk.getText().equalsIgnoreCase("n") || isClerk.getText().equals("can only be y or n")){
						isClerk.setText("can only be y or n");
						isClerk.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else {
						isClerk.setBackground(Color.WHITE);
					}

					if (isFailed){	// input is invalid
						inputDialog(id, password, confirmPassword, name, isClerk, message);
					} else {	// input is valid
						// username is invalid
						if (!controller.addNewUser(id.getText(), password.getText(), name.getText(), (isClerk.getText().equalsIgnoreCase("y"))? true: false)) {	
							id.setText("ID has already been taken");
							id.setBackground(Color.LIGHT_GRAY);
							inputDialog(id, password, confirmPassword, name, isClerk, message);
						}
					}
				} 
			}
		});

		final Panel p =  this;
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int g = JOptionPane.YES_NO_OPTION;
				int response = JOptionPane.showConfirmDialog(null, "Are your sure you want to log out?", "Log out", g);
				if(response == JOptionPane.YES_OPTION){
					gui.removePanel(p);
					gui.removePanel(gui.getBackgroundBlank());
					gui.addBGPanel(gui.getBackgroundPanel());
					gui.addPanel(new LoginGUI(gui));
					controller.logOut();
				}
			}
		});
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int g = JOptionPane.YES_NO_OPTION;
				int response = JOptionPane.showConfirmDialog(null, "Are your sure you want to exit KPSmart?", "Exit", g);
				if(response == JOptionPane.YES_OPTION){
					System.exit(0);
				}
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
		displayPanel.add ( scroll );
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {}

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
