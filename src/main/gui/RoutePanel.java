package main.gui;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;

public class RoutePanel extends Panel {
	private boolean DEBUG = true;
	public RoutePanel(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
		this.setPreferredSize(new Dimension(gui.getWidth()*3/5-60,gui.getHeight()-250));

	}

	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setUpComponents() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void addListenner() {
		// TODO Auto-generated method stub
		
	}
}
