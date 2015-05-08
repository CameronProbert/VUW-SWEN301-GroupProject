package main.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The BackgroundPanel class is a JPanel which is represented 
 * as the background image on the frame. 
 * 
 */
public class BackgroundPanel extends Panel{
	
	private ImageIcon background;
	
	public BackgroundPanel(GUI gui) {
		super(gui);
		setBounds(0, 0, 900, 770);
	}

	@Override
	protected void setUpComponents() {
		background = new ImageIcon("bg.jpg");
		Image scaledImage = background.getImage().getScaledInstance(900, 770, Image.SCALE_SMOOTH);
		JLabel jl = new JLabel(new ImageIcon(scaledImage));
		add(jl);
	}

	@Override
	protected void addListenner() {}
}
