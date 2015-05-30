package tests;

import org.junit.Test;

import tests.fileio.FileTests;
import tests.fileio.LogHandlerTests;
import tests.logic.RouteTests;

/**
 * Runs all the tests
 * @author Cameron Probert
 *
 */
public class RunTests {

	public void main(String[] args) {
		runAllTests();
	}

	@Test
	public void runAllTests(){
		new FileTests();
		new RouteTests();
		new LogHandlerTests();
	}

}
