package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 */
public class TransportDiscontinuedPane extends Panel{

	private JButton reset;
	private JButton update;

	public TransportDiscontinuedPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
		comboBoxOrigin = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxOrigin, "origin");

		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
		comboBoxDestination = new JComboBox(distributionCentres);
		comboBoxListenner(comboBoxDestination, "destination");

		JLabel labelTransportFirm= new JLabel("Transport Firm", SwingConstants.CENTER);
		comboBoxTransportFirm = new JComboBox(TransportFirmList);
		comboBoxListenner(comboBoxTransportFirm, "transportFirm");

		JLabel labelTransportType= new JLabel("Transport Tpye", SwingConstants.CENTER);
		comboBoxTransportTpye = new JComboBox(TransportTpyeList);
		comboBoxListenner(comboBoxTransportTpye, "transportType");

		reset = new JButton("Reset");
		update = new JButton("Update");
		add(labelComboOrigin);
		add(comboBoxOrigin);
		add(labelComboDestination);
		add(comboBoxDestination);
		add(labelTransportFirm);
		add(comboBoxTransportFirm);
		add(labelTransportType);
		add(comboBoxTransportTpye);
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
					System.out.println(toStringTPD());

				}
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
	public String toStringTPD(){
		return("Origin: "+ origin +"  Destination: "+ destination+"Transport Firm: "+ transportFirm+"    Transport Tpye:"+transportType);
	}

}
