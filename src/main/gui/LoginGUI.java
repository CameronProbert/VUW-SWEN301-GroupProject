package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * The JoinServerPanel class is a JPanel which is represented on 
 * the frame once the player choose to start a game in a server. 
 * JoinServerPanel class is responsible for letting player enter 
 * the server information and then start the game.
 * 
 */
public class LoginGUI extends Panel{

	// buttons on the panel
	private JButton logout;
	private JButton login;

	private  JComboBox comboBoxPriority;
	
	public LoginGUI(GUI gui) {
		super(gui);
		setBounds(200, 370, 400, 350);	
	}

	@Override
	protected void setUpComponents() {
		// labels, textFields and button used on joinServerPanel
		//		JLabel nameP = new JLabel("Username : ");
		//		nameP.setPreferredSize(new Dimension(150, 60));
		//		nameP.setFont(new Font("Arial", Font.PLAIN, 20));
		//		nameP.setForeground(new Color(0, 135, 200).brighter());

		gui.getUsername().setText("Username");
		gui.getUsername().setPreferredSize(new Dimension(250, 50));
		gui.getUsername().setFont(new Font("Arial", Font.PLAIN, 20));
		gui.getUsername().setForeground(new Color(130, 130, 130));
		gui.getUsername().setBackground(new Color(50, 50, 50));
		//		gui.getUsername().setHorizontalAlignment(JTextField.CENTER);
		gui.getUsername().setBorder(null);

		//		JLabel name = new JLabel("Password : ");
		//		name.setPreferredSize(new Dimension(150, 60));
		//		name.setFont(new Font("Arial", Font.PLAIN, 20));
		//		name.setForeground(new Color(0, 135, 200).brighter());

		gui.getPassword().setText("Password");
		gui.getPassword().setPreferredSize(new Dimension(250, 50));
		gui.getPassword().setFont(new Font("Arial", Font.PLAIN, 20));
		gui.getPassword().setForeground(new Color(130, 130, 130));
		gui.getPassword().setBackground(new Color(50, 50, 50));
		gui.getPassword().setBorder(null);

		
		
		
		
		String[] priorityList = {"Clerk", "Manager"};
		comboBoxPriority = new JComboBox(priorityList);
		//comboBoxPriority.setPreferredSize(new Dimension(315, 50));
		comboBoxPriority.setRenderer(new CustomComboBox());
		
		
		
		comboBoxPriority.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println((String) ((JComboBox)e.getSource()).getSelectedItem());
			}
		});
		
		
		
		
		logout = new JButton("Quit");
		login = new JButton("Login");

		JLabel space1 = new JLabel("");
		space1.setPreferredSize(new Dimension(350, 15));

		JLabel space2 = new JLabel("");
		space2.setPreferredSize(new Dimension(350, 15));

		JLabel space3 = new JLabel("");
		space3.setPreferredSize(new Dimension(20, 60));

		add(gui.getUsername());
		add(space1);
		add(gui.getPassword());
		add(space2);
		
		
		add(comboBoxPriority);
		add(new JLabel("Hello"), BorderLayout.CENTER);
		
		
		setButtonStyle(logout, 137, Color.RED);
		add(space3);
		setButtonStyle(login, 137, Color.GREEN);
	}

	@Override
	protected void addListenner() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JButton button = (JButton) ae.getSource();
				if(button == login){	// if button Start is clicked, joinServerPanel will be removed and multiple-player mode game will be started
					if(!gui.getUsername().getText().equals("Username") && !gui.getPassword().getText().equals("Password")
							&& !gui.getUsername().getText().equals("") && !gui.getPassword().getText().equals("")){
						gui.setUsername(gui.getUsername().getText());
						//						gui.setStrServerNameC(gui.getServerNameC().getText());
						//						if(isIPAdd(gui.getStrServerNameC())){
						//							gui.removePanel(JoinServerPanel.this);
						//							gui.removePanel(gui.getBackgroundPanel());					
						//							gui.startGame2();
						//						}
						System.out.println("username: " + gui.getUsername().getText());
						System.out.println("password: " + gui.getPassword().getText());
						gui.removePanel(LoginGUI.this);
						gui.addPanel(new FunctionGUI(gui));

					}}}
		});
		
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				System.exit(0);	// if button exit is clicked, the frame will be closed
			}
		});

		gui.getUsername().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (gui.getUsername().getText().equals("Username")){
					gui.getUsername().setText("");
					gui.getUsername().setForeground(new Color(255, 255, 255));
				}
			}
		});

		gui.getPassword().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (gui.getPassword().getText().equals("Password")){
					gui.getPassword().setText("");
				}
			}
		});

		gui.getPassword().getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changeColor();
			}

			public void insertUpdate(DocumentEvent e) {
				changeColor();
			}

			public void removeUpdate(DocumentEvent e) {
				changeColor();
			}

			public void changeColor() {
				gui.getPassword().setForeground(new Color(255, 255, 255));
			}
		});
	}
	
	
	   static class CustomComboBox extends JLabel implements ListCellRenderer {

	        @Override
	        public Component getListCellRendererComponent(
	                JList list, 
	                Object value, 
	                int index, 
	                boolean isSelected, 
	                boolean cellHasFocus) {

	            JLabel label = new JLabel(){
	                public Dimension getPreferredSize(){
	                    return new Dimension(200, 100);
	                }
	            };
	            label.setText(String.valueOf(value));

	            return label;
	        }
	    }  
}
