package tests;

import tests.fileio.FileTests;
import tests.fileio.LogHandlerTests;
import tests.logic.RouteTests;

/**
 * Runs all the tests
 * @author Cameron Probert
 *
 */
public class RunTests {

	public static void main(String[] args) {
		new FileTests();
		new RouteTests();
		new LogHandlerTests();
	}

}
