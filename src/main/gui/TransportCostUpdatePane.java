package main.gui;

import javax.swing.JFormattedTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Transport Cost Update Panel
 *
 *  @author zhaojiang chang
 *
 */
public class TransportCostUpdatePane extends Panel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton reset;
	private JButton update;

	public TransportCostUpdatePane(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		comboBoxRouteList();

		JLabel labelNewPricePerGram= new JLabel("New price per gram", SwingConstants.CENTER);
		textTPNewCostPerGram = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewCostPerGram);

		JLabel labelNewPricePerCB= new JLabel("New price per cubic centimeter", SwingConstants.CENTER);
		textTPNewCostPerCubic = new JFormattedTextField(amountFormat);
		formatToDobuleJTextField(textTPNewCostPerCubic);

		reset = new JButton("Reset");
		update = new JButton("Update");
		add(comboBoxRoute);
		add(labelNewPricePerGram);
		add(textTPNewCostPerGram);
		add(labelNewPricePerCB);
		add(textTPNewCostPerCubic);
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
					addBusinessEvent("transportCostUpdate");
				}
			}
		});
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton) e.getSource();
				if(button == reset){
					comboBoxRoute.setSelectedItem(null);
					init();
				}
			}
		});
	}
	@Override
	public void propertyChange(PropertyChangeEvent e ) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == textCustomerNewPricePerGram) {
			amount = ((Number)textCustomerNewPricePerGram.getValue()).doubleValue();
		}  else if (source == textCustomerNewPricePerCubic) {
			amount = ((Number)textCustomerNewPricePerCubic.getValue()).doubleValue();
		}
	}

	public static double getCPUTextCustomerNewPricePerGram() {
		return ((Number)textCustomerNewPricePerGram.getValue()).doubleValue();
	}

	public static double getCPUTextNewPricePerCB() {
		return ((Number)textCustomerNewPricePerCubic.getValue()).doubleValue();
	}
	public String toStringCPU(){
		return("Origin: "+ origin +"  Destination: "+ destination+"  Priority: "+priority+ "   New price per gram: "+
				((Number)textCustomerNewPricePerGram.getValue()).doubleValue()+"   New price per cubic centimeter:"+((Number)textCustomerNewPricePerCubic.getValue()).doubleValue());
	}

}
