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
public class BusinessEventsPane extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// buttons on the panel


	public BusinessEventsPane(GUI gui) {
		super(gui);
		setBounds(300, 0,gui.getWidth()*3/5-25, gui.getHeight()-160);	
		setBorder ( new TitledBorder ( new EtchedBorder (), "Business Events" ) );
	}

	@Override
	protected void setUpComponents() {
		
	}

	@Override
	protected void addListenner() {
	}
}