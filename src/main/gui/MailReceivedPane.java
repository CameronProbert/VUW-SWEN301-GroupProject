package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.events.MailDelivery;

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
		comboBoxMailDel = new JComboBox(mailDelList());
		comboBoxMailDel.setSelectedItem(null);
		comboBoxListenner(comboBoxMailDel, "mailReceived");
		update = new JButton("Update");
		JTextField blankSpace = new JTextField(20);
		blankSpace.setEnabled(false);
		blankSpace.setEditable(false);
		blankSpace.setBackground(SystemColor.controlHighlight);
		blankSpace.disable();
		add(labelComboMail);
		add(comboBoxMailDel);
		add(update);
		add(blankSpace);
	}

	@Override
	protected void addListenner() {
		update.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == update){
					if(comboBoxMailDel.getSelectedItem()==null){
						JOptionPane.showMessageDialog(null, "Please select from Mail Delivery list", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					else{
						int g = JOptionPane.YES_NO_OPTION;
						int response = JOptionPane.showConfirmDialog(null, "Do you want to update the mail delivery time?", "Mail delivery time update", g);
						if(response == JOptionPane.YES_OPTION){
							for (MailDelivery md: controller.getMailDeliveries()) {
								if(md.toString().equals(selectedMailReceived)){
									controller.updateMailDelTime(md);

								}
							}
						}
					}

				}
			}
		});
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	}
}
