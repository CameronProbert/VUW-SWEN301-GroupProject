package tests.fileio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.events.BusinessEvent;
import main.events.OpenNewRoute;
import main.fileio.LogHandler;
import main.logic.InvalidLocationException;
import main.logic.NoDaysToShipException;
import main.logic.Route;

public class LogHandlerTests {

private BusinessEvent event;
private LogHandler handler;

	public LogHandlerTests(){
		checkToBackBreaks();
		checkToForwardBreaks();

		checkEmptyFile();
	}

	@Test
	public void checkEmptyFile() {
		handler = new LogHandler(true); // load empty file

		List<BusinessEvent> events = FileTests.eventsOne();
		handler.newEvent(events.get(0));




	}

	@Test
	public void checkToBackBreaks() {
		handler = new LogHandler();
		event  = handler.getCurrentEvent();

		event = handler.getPreviousEvent();
		assertFalse(event==null);
		event = handler.getPreviousEvent();
		assertFalse(event==null);
		event = handler.getPreviousEvent();
		assertFalse(event==null);
		event = handler.getPreviousEvent();
		assertTrue(event==null);
	}

	@Test
	public void checkToForwardBreaks() {
		handler = new LogHandler();
		event  = handler.getCurrentEvent();

		event = handler.getNextEvent();
		assertNull(event);
		event = handler.getPreviousEvent();
		assertFalse(event==null);

	}

	private void printEvent(BusinessEvent e){
		for(String s: e.description()){
			System.out.println(s);
		}
	}

	public static void main(String[] args){
		new LogHandlerTests();
	}

}
