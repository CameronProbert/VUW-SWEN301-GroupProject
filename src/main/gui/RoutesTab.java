package main.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The BusinessFiguresTotal class is a JPanel which is added
 * on the button panel to show users the business figures.
 * BusinessFiguresTotal class is responsible for directly
 * letting users know the key general business figures.
 *
 * @author Zhiheng Sun
 *
 */
public class RoutesTab extends Panel implements MouseListener{

	// value labels on the panel
	private static JLabel routes;
	private static JLabel expenditure;
	private static JLabel events;

	public RoutesTab(GUI gui) {
		super(gui);
		setBounds(300, 0, gui.getWidth()*3/4-10, gui.getHeight());
		this.setPreferredSize(new Dimension(gui.getWidth()*3/5-60,gui.getHeight()-250));

	}

	@Override
	protected void setUpComponents() {
		this.setLayout(new GridLayout(20,2));
		this.setAlignmentX(LEFT_ALIGNMENT);

		JLabel labelRevenue= new JLabel("Route List", SwingConstants.LEFT);
		labelRevenue.setFont(new Font("Dialog", Font.PLAIN, 14));
		routes = new JLabel();


		add(labelRevenue);
		addLabels();
	}
	protected void addLabels() {
		for (int i = 0; i < routeList().size(); i++) {
			JLabel value = new JLabel(i+" : "+routeList().get(i), SwingConstants.LEFT);
			value.setFont(new Font("Dialog", Font.PLAIN, 14));
			add(value);
		}
	}
	@Override
	public void propertyChange(PropertyChangeEvent e ) {}

	@Override
	protected void addListenner() {
//		for(int i = 0; i<this.getComponents().length; i++){
//			if(this.getComponents()[i] instanceof JLabel){
//				this.getComponents()[i].addMouseListener(new MouseMotionAdapter() {
//			        //override the method
//			        public void mouseDragged(MouseEvent arg0) {
//			                     // to do .........................
//			        }
//			    });
//			}
//		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}