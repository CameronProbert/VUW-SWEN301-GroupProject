package main.fileio;

import java.util.ArrayList;
import java.util.List;

import main.events.BusinessEvent;

/**
 * This class reads in all the business events and writes all the events to file
 * This class also keeps track of the currently being displayed event and will pass on the current and 
 * change the evnt to the previous or next
 * 
 * @author Francine
 *
 */
public class LogHandler {

	private List<BusinessEvent> events = new ArrayList<BusinessEvent>();
	private BusinessEvent current;
	
	public LogHandler(){
		loadEvents();
	}
	
	/** takes a new event from the main class and writes it to file
	 * TODO should the current event reset to the start of the list every time a new event is created?
	 * 
	 * @param event
	 * @return
	 */
	public boolean newEvent(BusinessEvent event){
		events.add(event);
		writeToFile();
		return true; // change this return false if unsuccessful
	}
	
	/**
	 * returns the current event
	 * @return
	 */
	public BusinessEvent getCurrentEvent(){
		return current;
	}
	
	/**
	 * Deletes contents of file then rewrites all the events to file. 
	 * This will need to be called every time an event is completed.
	 * TODO change to only append the newer events to the file or an event at a time as they are created?
	 */
	public void writeToFile(){
		// delete entire contents of the file
		for(BusinessEvent event: events){
			String xml = event.toXML();
			// write the xml string to the file
		}
	}
	
	/**
	 *  returns an event given a date (which includes a time) that the event happens. 
	 *  Probably obsolete and will not be used
	 */
	public BusinessEvent getEvent(String date){
		for(BusinessEvent event: events){
			if(event.dateDone().equals(date)){
				return event;
			}
		}
		return null;
	}
	
	/**
	 *  move the current event back and return it
	 * @return
	 */
	public BusinessEvent getPreviousEvent(){
		if (events.size()==0){
			return null;
		}
		if (events.indexOf(current)==0){
			return null;
		}
		int index = events.indexOf(current);
		index++;
		current = events.get(index);
		return current;
	}
	
	/**
	 *  move the current event forward and return it
	 * @return
	 */
	public BusinessEvent getNextEvent(){
		if (events.size()==0){
			return null;
		}
		if (events.indexOf(current)==events.size()-1){
			return null;
		}
		int index = events.indexOf(current);
		index--;
		current = events.get(index);
		return current;
	}
	
	/**
	 *  done initially when program is opened
	 * 
	 */
	private void loadEvents(){
		// if file is empty make empty event lists and an empty current
		// open file
		// read file
		// make business events - populate this.events
		// set current event to last in list
	}
}
