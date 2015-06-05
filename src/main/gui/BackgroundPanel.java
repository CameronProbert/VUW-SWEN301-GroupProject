package main.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Main;

/**
 * The BackgroundPanel class is a JPanel which is added onto the JFrame
 * as the background of the LoginGUI panel. 
 *
 * @author Zhiheng Sun
 *
 */
public class BackgroundPanel extends Panel{

	private ImageIcon background;

	/**
	 * Create the BackgroundPanel by passing the gui it is on
	 *
	 * @param gui the gui the BackgroundPanel is on
	 */
	public BackgroundPanel(GUI gui) {
		super(gui);
		setBounds(0, 0, 900, 770);
	}

	@Override
	protected void setUpComponents() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Main.class.getResource("/resources/image/bgLogin.jpg"));
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
	public void propertyChange(PropertyChangeEvent evt) {}
}
