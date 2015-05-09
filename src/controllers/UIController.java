package controllers;

import main.gui.FunctionGUI;
import main.gui.Panel;
import main.logic.InvalidLoginException;
import main.logic.Monitor;

public class UIController {
	private static FunctionGUI functionGUI;
	private static Panel panel;
	private static Monitor monitor;

	public UIController(FunctionGUI functionGUI){
		this.functionGUI = functionGUI;
	}
	public void getMDOrigin(){

	}
	public boolean checkLogin(String id, String password){
		try {
			monitor.logIn(id, password);

		} catch (InvalidLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		return true;
	}

	public void logOut(){
		monitor.logOut();
	}
}
