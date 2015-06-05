package main.gui;

import java.awt.Image;
import java.beans.PropertyChangeEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The MailDeliveryPane class is a JPanel which is added onto the JFrame
 * once the Mail Delivery button is clicked. MailDeliveryPane class is
 * responsible for letting the user enter the information of the new delivery
 * and pass the information into the system.
 *
 * @author zhaojiang chang
 *
 */
public class BackgroundBlank extends Panel{

	private ImageIcon background;

	public BackgroundBlank(GUI gui) {
		super(gui);
		setBounds(0, 0, 900, 770);
	}

	@Override
	protected void setUpComponents() {
		background = new ImageIcon("image/bgBlank.jpg");
		Image scaledImage = background.getImage().getScaledInstance(900, 770, Image.SCALE_SMOOTH);
		JLabel jl = new JLabel(new ImageIcon(scaledImage));
		add(jl);
	}

	@Override
	protected void addListenner() {}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

	}
}
