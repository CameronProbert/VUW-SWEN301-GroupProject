package main.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.logic.Clerk;

import controllers.UIController;



/**
 * The GUI is responsible for setting up the KPSmart system entry. This class generates
 * different options to let player choose different game mode. The class also
 * records players's information and pass to other classes.
 *
 */

public class GUI {

	private JFrame frame;	// this is the frame the KPSmart system will be shown on
	private JLayeredPane layeredPane;	// this is used to add panel onto the frame
	private Panel backgroundPanel;
	private Panel backgroundBlank;
	private UIController controller;
	// the dimension of the frame
	private static int width = 900;
	private static int height = 770;

	// textFields on all panels
	private JTextField usernameTextF;
	private JPasswordField passwordTextF;

	public String username;	// the entered name of the player in multiple-player mode
	public String password;
	private Clerk clerk;

	public GUI() {
		//setUp();
	}

	public void setUp() {
		frame = new JFrame();
		frame.setTitle("KPSmart System");
		frame.getRootPane().setBackground(new Color(141, 174, 240));
//		frame.getRootPane().setBackground(new Color(115, 129, 240));
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

		usernameTextF = new JTextField(18);
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

	public JTextField getUsername() {
		return usernameTextF;
	}

	public JPasswordField getPassword() {
		return passwordTextF;
	}

	public void setUsername(String text) {
		username = text;
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

	public static void main(String[] args){
		new GUI();
	}

	public void setCurrentUser(Clerk clerk) {
		// TODO Auto-generated method stub
		this.clerk = clerk;
		
	}
}
