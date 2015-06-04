package main.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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


		comboBoxRouteList();
		reset = new JButton("Reset");
		delete = new JButton("Delete");
		add(comboBoxRoute);
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
					if(selectedRoute==null){
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
