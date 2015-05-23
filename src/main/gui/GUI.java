package main.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.controllers.UIController;
import main.logic.Clerk;

/**
 * The GUI is responsible for setting up the KPSmart system user interface. 
 * GUI creates JFrame and added background panel, LoginGUI onto the frame 
 * for starting the system.
 *
 * @author Zhiheng Sun
 */

public class GUI {

	private JFrame frame;	// this is the frame the KPSmart system will be shown on
	private JLayeredPane layeredPane;	// this is used to add panel onto the frame
	private Panel backgroundPanel;
	private Panel backgroundBlank;
	private UIController controller;
	private BusinessFiguresTotal bfp;
	
	// the dimension of the frame
	private static int width = 900;
	private static int height = 770;

	// textFields on all panels
	private JTextField userIdTextF;
	private JPasswordField passwordTextF;

	// the username and password user entered
	public String userId;	
	public String password;
	private Clerk clerk;

	public void setUp() {
		frame = new JFrame();
		frame.setTitle("KPSmart System");
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// add the background image to frame
		layeredPane = new JLayeredPane();
		backgroundPanel = new BackgroundPanel(this);
		backgroundBlank = new BackgroundBlank(this);
		addBGPanel(backgroundPanel);

		frame.setLayeredPane(layeredPane);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );

		userIdTextF = new JTextField(18);
		passwordTextF = new JPasswordField(18);

		LoginGUI loginGUI = new LoginGUI(this);
		addPanel(loginGUI);
	}
	
	public void setUIController(UIController u){
		this.controller = u;
	}
	
	public UIController getUIController(){
		return this.controller;
	}
	
	protected void addPanel(Panel panel){
		layeredPane.add(panel, JLayeredPane.MODAL_LAYER);
		frame.repaint();
	}

	protected void removePanel(Panel panel){
		layeredPane.remove(panel);
		frame.repaint();
	}

	protected void addBGPanel(Panel panel){
		layeredPane.add(panel, JLayeredPane.DEFAULT_LAYER);
	}

	protected Panel getBackgroundPanel(){
		return backgroundPanel;
	}

	protected Panel getBackgroundBlank(){
		return backgroundBlank;
	}

	public JTextField getUserId() {
		return userIdTextF;
	}

	public JPasswordField getPassword() {
		return passwordTextF;
	}

	public void setUsername(String text) {
		userId = text;
	}

	public void setPassword(String text) {
		password = text;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
	public void setCurrentUser(Clerk clerk) {
		this.clerk = clerk;
	}
	
	public String getCurretUsername() {
		return clerk.getName();
	}
	
	public void setBusinessFiguresTotal(BusinessFiguresTotal businessFiguresTotal){
		this.bfp = businessFiguresTotal;
	}
	
	public BusinessFiguresTotal getBusinessFiguresTotal(){
		return bfp;
	}
}
