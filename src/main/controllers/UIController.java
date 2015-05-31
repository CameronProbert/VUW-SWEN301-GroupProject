package main.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import main.gui.GUI;
import main.gui.Panel;
import main.logic.Clerk;
import main.logic.InvalidLoginException;
import main.logic.Monitor;
import main.logic.Route;

public class UIController {
	private static GUI gui;
	private static Monitor monitor;
	private Clerk clerk;
	//test only
	private List<String>event;

	public UIController(GUI gui, Monitor monitor){
		this.gui = gui;
		this.monitor = monitor;
		this.event = new ArrayList<String>();
	}

	/**
	 * check Logs in a user if they have the correct credentials
	 * and pass current user to gui
	 * @param id
	 * @param password
	 * @return true if log in correctly
	 */
	public Clerk checkLogin(String id, String password){

		try {
			clerk = monitor.logIn(id, password);
			if(clerk!=null){
				gui.setCurrentUser(clerk);
				return clerk;
			}

		} catch (InvalidLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * after click log out button in function panel
	 * call logout method in monitor class to set current user to null
	 */
	public void logOut(){
		monitor.logOut();
	}
	/**
	 *
	 */
	public boolean addNewUser(String id, String password, String name, boolean isManager){
		return (monitor.makeNewUser(id, password, name, isManager));
	}
	public boolean removeNewUser(String id, String password, String name){
		//return (monitor.makeNewUser(id, password, name));
		return true;
	}
	public void addEvent(Route r, Map<String, String> eventInfo) {
		monitor.saveEvent(eventInfo);
	}

	public List<String> getMostRecentEvent(){
		System.out.println("get current event from monitor.......");
		return monitor.getMostRecentEvent();
		//		Map<String, String> m = new HashMap<String, String>();
		//		m.put("type", "mailDelivery");
		//		m.put("origin", "auckland");
	}

	public List<String> getNextEvent(){
		System.out.println("get next event from  monitor.......");
		System.out.println(monitor.nextEvent());
		return monitor.nextEvent();
	}

	public List<String> getPreviousEvent(){
		System.out.println("get previous event from  monitor.......");
		return monitor.previousEvent();
		//		Map<String, String> m = new HashMap<String, String>();
		//		m.put("type", "mailDelivery");
		//		m.put("origin", "wellington");
	}
	public Set<Route> getRoutes(){
		return monitor.getRoutes();
	}
	public void setSingleTransportFigures(double revenue, double expenditure, int numOfEvents, double averageDeliveryTime){
		gui.getBusinessFiguresPane().setRevenue(revenue);
		gui.getBusinessFiguresPane().setExpend(expenditure);
		gui.getBusinessFiguresPane().setEvents(numOfEvents);
		gui.getBusinessFiguresPane().setAverageTime(averageDeliveryTime);
	}
	public void setTotalTransportFigures(double revenue, double expenditure, int numOfEvents){
		gui.getBusinessFiguresTotal().setRevenue(revenue);
		gui.getBusinessFiguresTotal().setExpend(expenditure);
		gui.getBusinessFiguresTotal().setEvents(numOfEvents);
	}

}
