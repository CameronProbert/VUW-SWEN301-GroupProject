package main.gui;

import java.beans.PropertyChangeEvent;

import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 * BUsinessEvents 
 * @author zhaojiang chang
 */
 
public class BusinessEventsPane extends Panel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	// buttons on the panel


	public BusinessEventsPane(GUI gui) {
		super(gui);
		setBounds(300, 0,gui.getWidth()*3/5-25, gui.getHeight()-160);
		setBorder ( new TitledBorder ( new EtchedBorder (), "Business Events" ) );
	}

	@Override
	protected void setUpComponents() {

	}

	@Override
	protected void addListenner() {
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}
}
