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
	private JButton delete;

	public TransportDiscontinuedPane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

//		JLabel labelComboOrigin = new JLabel("Origin", SwingConstants.CENTER);
//		comboBoxOrigin = new JComboBox(distributionCentres);
//		comboBoxOrigin.setSelectedItem(null);
//		comboBoxListenner(comboBoxOrigin, "origin");
//
//		JLabel labelComboDestination = new JLabel("Destination", SwingConstants.CENTER);
//		comboBoxDestination = new JComboBox(distributionCentres);
//		comboBoxDestination.setSelectedItem(null);
//		comboBoxListenner(comboBoxDestination, "destination");
//
//		JLabel labelTransportFirm= new JLabel("Transport Firm", SwingConstants.CENTER);
//		comboBoxTransportFirm = new JComboBox(TransportFirmList);
//		comboBoxTransportFirm.setSelectedItem(null);
//		comboBoxListenner(comboBoxTransportFirm, "transportFirm");
//
//		JLabel labelTransportType= new JLabel("Transport Tpye", SwingConstants.CENTER);
//		comboBoxTransportType = new JComboBox(TransportTpyeList);
//		comboBoxTransportType.setSelectedItem(null);
//		comboBoxListenner(comboBoxTransportType, "transportType");

		comboBoxRouteList();
		reset = new JButton("Reset");
		delete = new JButton("Delete");
		add(comboBoxRoute);
//		add(labelComboOrigin);
//		add(comboBoxOrigin);
//		add(labelComboDestination);
//		add(comboBoxDestination);
//		add(labelTransportFirm);
//		add(comboBoxTransportFirm);
//		add(labelTransportType);
//		add(comboBoxTransportType);
		add(reset);
		add(delete);
	}

	@Override
	protected void addListenner() {
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == delete){
					addBusinessEvent("transportDiscontinued");

				}
			}
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == reset){
					//comboBoxTransportType.setSelectedItem(null);
					//comboBoxOrigin.setSelectedItem(null);
					//comboBoxTransportFirm.setSelectedItem(null);
					comboBoxRoute.setSelectedItem(null);
					init();
				}
			}
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
//	public String toStringTPD(){
//		return("Origin: "+ origin +"  Destination: "+ destination+"Transport Firm: "+ transportFirm+"    Transport Tpye:"+transportType);
//	}

}
