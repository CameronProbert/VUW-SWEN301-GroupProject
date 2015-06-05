package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * The TransportDiscontinuedPane class is a JPanel which is added onto the JFrame
 * once the Transport Discontinued button is clicked. TransportDiscontinuedPane class is
 * responsible for letting the user select the transport they want to remove
 * and pass the information into the system.
 *
 * @author zhaojiang chang
 *
 */
public class MailReceivedPane extends Panel{

	private JButton reset;
	private JButton update;

	/**
	 * Create the TransportDiscontinuedPane by passing the gui it is on
	 *
	 * @param gui the gui the TransportDiscontinuedPane is on
	 */
	public MailReceivedPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		JLabel labelComboMail = new JLabel("Mail Received", SwingConstants.CENTER);
		comboBoxMailDel = new JComboBox(getLocations());
		comboBoxMailDel.setSelectedItem(null);
		comboBoxListenner(comboBoxMailDel, "mailReceived");
		JLabel labelMailDelTime = new JLabel("Mail Delvery Time", SwingConstants.CENTER);
		JLabel mailDelTime = new JLabel();

		mailDelTime.setText("");
		mailDelTime.setPreferredSize(new Dimension(250, 50));
		mailDelTime.setFont(new Font("Arial", Font.PLAIN, 15));
		mailDelTime.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		reset = new JButton("Reset");
		update = new JButton("Update");
		
		add(labelComboMail);
		add(comboBoxMailDel);
		add(labelMailDelTime);
		add(mailDelTime);
		add(reset);
		add(update);
	}

	@Override
	protected void addListenner() {
		update.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == update){
					if(selectedMailReceived==null){
						JOptionPane.showMessageDialog(null, "Please select a route", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					else{
						int g = JOptionPane.YES_NO_OPTION;
						int response = JOptionPane.showConfirmDialog(null, "Do you want to discontinue a transport?", "Discontinue Transport", g);
						if(response == JOptionPane.YES_OPTION){
							addBusinessEvent("transportDiscontinued");
							refreshRouteList();
						}
					}

				}
			}
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if(button == reset){
					int g = JOptionPane.YES_NO_OPTION;
					int response = JOptionPane.showConfirmDialog(null, "Do you want to reset Values?", "Reset values", g);
					if(response == JOptionPane.YES_OPTION){
						comboBoxRoute.setSelectedItem(null);
						init();
					}
				}
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
}
