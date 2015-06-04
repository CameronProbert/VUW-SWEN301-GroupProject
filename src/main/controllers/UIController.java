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
	
	public void updateRevenue(double revenue){
		gui.getBusinessFiguresTotal().setRevenue(revenue);
	}
	
	public void updateExpenditure(double expenditure){
		gui.getBusinessFiguresTotal().setExpend(expenditure);
	}
	
	public void setNumberOfEvents(int events){
		gui.getBusinessFiguresTotal().setEvents(events);
	}
	
	public void addEvent(Map<String, String> eventInfo) {
		//monitor.addEvent(eventInfo);
		System.out.println("call monitor to add a new event.......");
	}
	
	public Map<String, String> getCurrentEvent(){
		System.out.println("get current event from monitor.......");
		//return monitor.getCurrentEvent();
		Map<String, String> m = new HashMap<String, String>();
		m.put("type", "mailDelivery");
		m.put("origin", "auckland");
		return m;
	}
	
	public Map<String, String> getNextEvent(){
		System.out.println("get next event from  monitor.......");
		//return monitor.nextEvent();
		return null;
	}
	
	public Map<String, String> getPreviousEvent(){
		System.out.println("get previous event from  monitor.......");
		//return monitor.previousEvent();
		Map<String, String> m = new HashMap<String, String>();
		m.put("type", "mailDelivery");
		m.put("origin", "wellington");
		return m;
	}

	public void updateBusinessEvents() {
		monitor.calculateBusinessFigures();
	}


}
