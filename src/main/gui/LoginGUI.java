package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import controllers.UIController;

/**
 *
 */
public class LoginGUI extends Panel{

	// buttons on the panel
	private JButton quit;
	private JButton login;
	private String loginType;
	private  JComboBox comboBoxPriority;
	protected UIController controller;

	public LoginGUI(GUI gui) {
		super(gui);
		setBounds(200, 370, 400, 350);
		loginType = "Clerk";
		this.controller = this.gui.getUIController();
	}

	@Override
	protected void setUpComponents() {
		// labels, textFields and button used on joinServerPanel
		//		JLabel nameP = new JLabel("Username : ");
		//		nameP.setPreferredSize(new Dimension(150, 60));
		//		nameP.setFont(new Font("Arial", Font.PLAIN, 20));
		//		nameP.setForeground(new Color(0, 135, 200).brighter());

		//gui.getUsername().setText("Username");
		gui.getUsername().setText("probercame");
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

		gui.getPassword().setText("password1");
		gui.getPassword().setPreferredSize(new Dimension(250, 50));
		gui.getPassword().setFont(new Font("Arial", Font.PLAIN, 20));
		gui.getPassword().setForeground(new Color(130, 130, 130));
		gui.getPassword().setBackground(new Color(50, 50, 50));
		gui.getPassword().setBorder(null);
		gui.getPassword().setEchoChar((char)0);

		String[] priorityList = {"Clerk", "Manager"};
		comboBoxPriority = new JComboBox(priorityList);
		//comboBoxPriority.setPreferredSize(new Dimension(315, 50));
		comboBoxPriority.setFont(new Font("Arial", Font.PLAIN, 20));
		comboBoxPriority.setRenderer(new CustomComboBox());



		comboBoxPriority.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginType = (String) ((JComboBox)e.getSource()).getSelectedItem();
			}
		});




		quit = new JButton("Quit");
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
		add(new JLabel(), BorderLayout.CENTER);


		setButtonStyle(quit, 125, new Color(255,165,0));
		add(space3);
		setButtonStyle(login, 125, new Color(30,144,255));
	}

	@Override
	protected void addListenner() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JButton button = (JButton) ae.getSource();
				if(button == login){	// if button Start is clicked, joinServerPanel will be removed and multiple-player mode game will be started
					System.out.println("username: " + gui.getUsername().getText());
					System.out.println("password: " + gui.getPassword().getText());
					if(controller.checkLogin(gui.getUsername().getText(), gui.getPassword().getText())){
						gui.setUsername(gui.getUsername().getText());
						//						gui.setStrServerNameC(gui.getServerNameC().getText());
						//						if(isIPAdd(gui.getStrServerNameC())){
						//							gui.removePanel(JoinServerPanel.this);
						//							gui.removePanel(gui.getBackgroundPanel());
						//							gui.startGame2();
						//						}
						gui.removePanel(LoginGUI.this);
						gui.removePanel(gui.getBackgroundPanel());
						gui.addBGPanel(gui.getBackgroundBlank());
						gui.addPanel(new FunctionGUI(gui, loginType));


					}
					else{
						//JOptionPane.showMessageDialog(null, "Id or Password can not be empty.", "Error",
								//JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		quit.addActionListener(new ActionListener() {
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
					gui.getPassword().setForeground(new Color(255, 255, 255));
				}
			}
		});

		gui.getPassword().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (gui.getPassword().getText().equals("Password")){
					gui.getPassword().setText("");
					gui.getPassword().setEchoChar('*');
					gui.getPassword().setForeground(new Color(255, 255, 255));
				}
			}

			public void focusLost(FocusEvent e) {}
		});

	}

	static class CustomComboBox extends JLabel implements ListCellRenderer {
		public Component getListCellRendererComponent(
				JList list,
				Object value,
				int index,
				boolean isSelected,
				boolean cellHasFocus) {

			JLabel label = new JLabel(){
				public Dimension getPreferredSize(){
					return new Dimension(258, 50);
				}
			};
			label.setText(String.valueOf(value));

			return label;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
}
