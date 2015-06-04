package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.BorderFactory;
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

import main.controllers.UIController;

import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import javax.swing.UIManager;

import java.awt.Button;
import java.awt.SystemColor;

import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

import java.awt.Scrollbar;

import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	// buttons on the panel
	private Button mailDelivery;
	private Button customerPriceUpdate;
	private Button transportCostUpdate;
	private Button transportDiscontinued;
	private Button businessFiguresRoute;
	private Button businessFiguresLocation;

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
	private static String permission = "Clerk";
	private JTabbedPane tabbedPane;
	private JPanel businessEventTab;
	private JPanel businessFigureTab;
	private JPanel eventPane;
	private Button previousEventButton;
	private Button nextEventButton;
	private Button newestEventButton;
	private Button createRoute;
	private List<String> event;
	private JTable table;
	private Button button_1;
	private Button button_2;
	private Button button_3;
	private Button button_4;
	private Button button_5;
	private BusinessEventPane businessEventPane ;

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
		jSplitPanel.setPreferredSize(new Dimension(gui.getWidth()*3/6, gui.getHeight()-160));
		displayPanel = new JPanel(new BorderLayout());
		displayPanel.setBorder ( new LineBorder(new Color(0, 0, 0)) );
		displayPanel.setPreferredSize(new Dimension(gui.getWidth()*1/6+60, gui.getHeight()-160));
		buttonPanel = new JPanel(new BorderLayout()); //
		buttonPanel.setPreferredSize(new Dimension(gui.getWidth()*1/6+80, gui.getHeight()-160));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		//business events panel
		inforPanel = new JPanel(new BorderLayout());
		inforPanel.setPreferredSize(new Dimension(gui.getWidth()*3/6-50, gui.getHeight()-160));
		inforPanel.setBorder ( new TitledBorder ( new EtchedBorder () ) );
		//TODO: find a way to set the width of the button
		logOut = new JButton("Log Out");
		exit = new JButton("Exit");
		addUser = new JButton("Add User");
		removeUser = new JButton("Remove User");
		// add label and buttons onto bottomPanel
		String staff ="";
		if(isManager){
			staff = "Manager";
		}else{
			staff = "Clerk";
		}

		JLabel usernameP = new JLabel(staff +":  "+ gui.getCurretUsername());
		usernameP.setFont(new Font("Arial", Font.PLAIN, 18));
		bottomPanel.add(usernameP);
		if(isManager){
			bottomPanel.add(addUser);
			bottomPanel.add(removeUser);
		}

		bottomPanel.add(logOut);
		bottomPanel.add(exit);

		//addImage("image/bottomImage.jpg", bottomPanel, 60);
		//add buttonPanel and displayPanel to split panel
		jSplitPanel.add(buttonPanel, JSplitPane.LEFT);

		mailDelivery = new Button("Mail Delivery");
		mailDelivery.setFont(new Font("Dialog", Font.BOLD, 12));
		mailDelivery.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(mailDelivery, BorderLayout.WEST);

		customerPriceUpdate = new Button("Customer Price Update");
		customerPriceUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		customerPriceUpdate.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(customerPriceUpdate, BorderLayout.SOUTH);

		transportCostUpdate = new Button("Transport Cost Update");
		transportCostUpdate.setFont(new Font("Dialog", Font.BOLD, 12));
		transportCostUpdate.setBackground(Color.LIGHT_GRAY);
		transportCostUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		buttonPanel.add(transportCostUpdate, BorderLayout.CENTER);

		createRoute = new Button("Create Route");
		createRoute.setFont(new Font("Dialog", Font.BOLD, 12));
		createRoute.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(createRoute, BorderLayout.SOUTH);

		transportDiscontinued = new Button("Transport Discontinued");
		transportDiscontinued.setFont(new Font("Dialog", Font.BOLD, 12));
		transportDiscontinued.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(transportDiscontinued, BorderLayout.SOUTH);

		businessFiguresRoute = new Button("Business Figures Route");
		businessFiguresRoute.setFont(new Font("Dialog", Font.BOLD, 12));
		businessFiguresRoute.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(businessFiguresRoute, BorderLayout.NORTH);

		businessFiguresLocation = new Button("Business Figures Location");
		businessFiguresLocation.setFont(new Font("Dialog", Font.BOLD, 12));
		businessFiguresLocation.setBackground(Color.LIGHT_GRAY);
		buttonPanel.add(businessFiguresLocation, BorderLayout.CENTER);
		
		button_2 = new Button("");
		buttonPanel.add(button_2, BorderLayout.SOUTH);
		button_2.disable();

		button_3 = new Button("");
		buttonPanel.add(button_3, BorderLayout.SOUTH);
		button_3.disable();

		button_4 = new Button("");
		buttonPanel.add(button_4, BorderLayout.SOUTH);
		button_4.disable();

		button_5 = new Button("");
		buttonPanel.add(button_5, BorderLayout.SOUTH);


		button_5.disable();

		jSplitPanel.add(displayPanel, JSplitPane.RIGHT);
		//add all panels to functionGUI panel
		add(titlePanel);
		add(jSplitPanel);
		add(inforPanel);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(gui.getWidth()*3/5,gui.getHeight()-160));
		inforPanel.add(tabbedPane, BorderLayout.NORTH);

		businessFigureTab = new JPanel(new BorderLayout());
		businessFigureTab.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Business Figures", null, businessFigureTab, null);

		if(isManager){
			businessEventTab = new JPanel(new BorderLayout());
			tabbedPane.addTab("Business Events", null, businessEventTab, null);
			eventPane = new JPanel();
			eventPane.setPreferredSize(new Dimension(gui.getWidth()*3/5,gui.getHeight()-220));
			businessEventTab.add(eventPane, BorderLayout.NORTH);
			currentEvent = controller.getMostRecentEvent();

			businessEventPane = new BusinessEventPane(gui);
			businessEventPane.setPreferredSize(new Dimension(gui.getWidth()*2/5,gui.getHeight()-300));
			previousEventButton = new Button("Previous Event");
			previousEventButton.setBackground(Color.LIGHT_GRAY);
			previousEventButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentEvent = controller.getPreviousEvent();
					//					if(currentEvent.get(0).equalsIgnoreCase("No data to display")){
					//						return;
					//					}
					eventPane.remove(businessEventPane);
					eventPane.repaint();
					businessEventPane = new BusinessEventPane(gui);
					businessEventPane.setPreferredSize(new Dimension(gui.getWidth()*2/5,gui.getHeight()-300));
					eventPane.add(businessEventPane);
					repaint();
				}
			});
			eventPane.add(previousEventButton);

			nextEventButton = new Button("Next Event");
			nextEventButton.setBackground(Color.LIGHT_GRAY);
			nextEventButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentEvent = controller.getNextEvent();
					//					if(currentEvent.get(0).equalsIgnoreCase("No data to display")){
					//						return;
					//					}
					eventPane.remove(businessEventPane);
					eventPane.repaint();
					businessEventPane = new BusinessEventPane(gui);
					businessEventPane.setPreferredSize(new Dimension(gui.getWidth()*2/5,gui.getHeight()-300));
					eventPane.add(businessEventPane);
					repaint();
				}
			});
			eventPane.add(nextEventButton);

			newestEventButton = new Button("Newest Event");
			newestEventButton.setBackground(Color.LIGHT_GRAY);
			newestEventButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentEvent = controller.getMostRecentEvent();
					//					if(currentEvent.get(0).equalsIgnoreCase("No data to display")){
					//						return;
					//					}
					eventPane.remove(businessEventPane);
					eventPane.repaint();
					businessEventPane = new BusinessEventPane(gui);
					businessEventPane.setPreferredSize(new Dimension(gui.getWidth()*2/5,gui.getHeight()-300));
					eventPane.add(businessEventPane);
					repaint();
				}
			});
			eventPane.add(newestEventButton);
			eventPane.add(businessEventPane);
		}
		add(bottomPanel);
		BusinessFiguresTotal businessFiguresTotal = new BusinessFiguresTotal(gui);
		businessFigureTab.add(businessFiguresTotal);

		table = new JTable( new MyTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(gui.getWidth()*3/5, gui.getWidth()));

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
				Button button = (Button) e.getSource();
				if(button == mailDelivery){
					buttonColorSwitch("Mail Delivery", button);
					displayPanel.setVisible(false);
					init();
					jSplitPanel.add(new MailDeliveryPane(gui), JSplitPane.RIGHT);
				}
			}


		});
		transportCostUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if(button == transportCostUpdate){
					buttonColorSwitch("Transport Cost Update", button);
					init();
					displayPanel.setVisible(false);
					jSplitPanel.add(new TransportCostUpdatePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		createRoute.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if(button == createRoute){
					buttonColorSwitch("Create Route", button);
					init();
					displayPanel.setVisible(false);
					jSplitPanel.add(new CreateRoutePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		customerPriceUpdate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if(button == customerPriceUpdate){
					buttonColorSwitch("Customer Price Update", button);
					init();
					displayPanel.setVisible(false);
					jSplitPanel.add(new CustomerPriceUpdatePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		transportDiscontinued.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if(button == transportDiscontinued){
					buttonColorSwitch("Transport Discontinued", button);
					init();
					displayPanel.setVisible(false);
					jSplitPanel.add(new TransportDiscontinuedPane(gui), JSplitPane.RIGHT);
				}
			}
		});
		businessFiguresRoute.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if(button == businessFiguresRoute){
					buttonColorSwitch("Business Figures Route", button);
					init();
					displayPanel.setVisible(false);
					jSplitPanel.add(new BusinessFiguresRoutePane(gui), JSplitPane.RIGHT);
				}
			}
		});
		businessFiguresLocation.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Button button = (Button) e.getSource();
				if(button == businessFiguresLocation){
					buttonColorSwitch("Business Figures Location", button);
					init();
					displayPanel.setVisible(false);
					jSplitPanel.add(new BusinessFiguresLocationPane(gui), JSplitPane.RIGHT);
				}
			}
		});
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField id = new JTextField();
				JTextField password = new JTextField();
				JTextField confirmPassword = new JTextField();
				JTextField name = new JTextField();
				String[] permissionChoices = {"Clerk","Manager"};
				JComboBox comboBoxPermission = new JComboBox(permissionChoices);

				Object[] message = {
						"ID", id,
						"Password", password,
						"Confirm Password", confirmPassword,
						"Name", name,
						"Permission", comboBoxPermission
				};
				inputDialog(id, password, confirmPassword, name, comboBoxPermission, message);
			}

			// show the input dialog
			public void inputDialog(JTextField id, JTextField password, JTextField confirmPassword,  JTextField name, JComboBox comboBoxPermission, Object[] message){

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
					} else {
						confirmPassword.setBackground(Color.WHITE);
					}

					// check whether password and confirmPassword are consistent
					if(!password.getText().equals(confirmPassword.getText()) || password.getText().equals("password not match") || confirmPassword.getText().equals("password not match")){
						password.setText("password not match");
						password.setBackground(Color.LIGHT_GRAY);
						confirmPassword.setText("password not match");
						confirmPassword.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else {
						if (!password.getText().equals("cannot be empty")){
							password.setBackground(Color.WHITE);
							confirmPassword.setBackground(Color.WHITE);
						}
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

					// allow user to choose permission for the new user and save the selected permission
					comboBoxPermission.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							permission = (String) ((JComboBox)e.getSource()).getSelectedItem();
						}
					});

					if (isFailed){	// input is invalid
						inputDialog(id, password, confirmPassword, name, comboBoxPermission, message);
					} else {	// input is valid
						// username is invalid
						if (!controller.addNewUser(id.getText(), password.getText(), name.getText(), (permission.equalsIgnoreCase("Manager"))? true: false)) {
							id.setText("ID has already been taken");
							id.setBackground(Color.LIGHT_GRAY);
							inputDialog(id, password, confirmPassword, name, comboBoxPermission, message);
						} else {
							JOptionPane.showMessageDialog(null, "User added!");
						}
					}
				}
			}
		});

		removeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField id = new JTextField();

				Object[] message = {
						"ID", id
				};
				inputDialog(id, message);
			}

			// show the input dialog
			public void inputDialog(JTextField id, Object[] message){

				int option = JOptionPane.showConfirmDialog(null, message, "Remove User", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					boolean isFailed = false;

					// check whether id is empty or not
					if(id.getText().equals("") || id.getText().equals("cannot be empty")){
						id.setText("cannot be empty");
						id.setBackground(Color.LIGHT_GRAY);
						isFailed = true;
					} else {
						id.setBackground(Color.WHITE);
					}

					if (isFailed){	// input is invalid
						inputDialog(id, message);
					} else {	// input is valid
						// username is invalid
						if (!controller.removeNewUser(id.getText())) {
							JOptionPane.showMessageDialog(null, "Invalid Username.", "Error",
									JOptionPane.ERROR_MESSAGE);
							id.setText("");
							inputDialog(id, message);
						} else {
							JOptionPane.showMessageDialog(null, "User removed!");
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
					isManager =false;
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
	class MyTableModel extends AbstractTableModel {
		final String[] columnNames = { "Origin", "Destination", "Carrier",
				"Transport method", "Category" };
		final Object[][] data = {
				{ "Auckland", "Wellington", "NZ Post", "Land", "Domestic"},
				{ "Auckland", "Christchurch", "NZ Post", "Land", "Domestic"},
				{ "Wellington", "Rotorua", "NZ Post", "Land", "Domestic"},
				{ "Auckland", "Sydney", "FedEx", "Air", "International"},
				{ "Auckland", "New York", "FedEx", "Air", "International"} };


		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {

			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}
		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print(" row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print(" " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}
	private void buttonColorSwitch(String buttonName, Button b) {
		// TODO Auto-generated method stub
		for(Component c: buttonPanel.getComponents()){
			if(c instanceof Button &&
					!((Button) c).getLabel().equals("")){
				if(((Button) c).getLabel().equals(buttonName)){
					b.setBackground(Color.GRAY);
				}

				else{
					((Button) c).setBackground(Color.LIGHT_GRAY);
				}
			}
		}


	}
}
