package main.controllers;

import javax.swing.JOptionPane;

import main.gui.FunctionGUI;
import main.gui.GUI;
import main.gui.Panel;
import main.logic.Clerk;
import main.logic.InvalidLoginException;
import main.logic.Monitor;

public class UIController {
	private static FunctionGUI functionGUI;
	private static GUI gui;
	private static Monitor monitor;
	private Clerk clerk;
	public UIController(GUI gui, Monitor monitor){
		this.gui = gui;
		this.monitor = monitor;
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
	
	public void updateRevenue(double revenue){
		//setRevenue(revenue);
	}
	public void updateExpenditure(double expenditure){
		//setExpend(expenditure);
	}
	public void setNumberOfEvents(int events){
//		setEvents(events);
	}
}
