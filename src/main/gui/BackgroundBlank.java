package main.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Main;

/**
 * The BackgroundBlank class is a JPanel which is added onto the JFrame
 * as the background of the main GUI. 
 *
 * @author zhaojiang chang
 *
 */
public class BackgroundBlank extends Panel{

	private ImageIcon background;

	/**
	 * Create the BackgroundBlank by passing the gui it is on
	 *
	 * @param gui the gui the BackgroundBlank is on
	 */
	public BackgroundBlank(GUI gui) {
		super(gui);
		setBounds(0, 0, 900, 770);
	}

	@Override
	protected void setUpComponents() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Main.class.getResource("/resources/image/bgBlank.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		background = new ImageIcon(img);
		Image scaledImage = background.getImage().getScaledInstance(900, 770, Image.SCALE_SMOOTH);
		JLabel jl = new JLabel(new ImageIcon(scaledImage));
		add(jl);
	}

	@Override
	protected void addListenner() {}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {}
}
