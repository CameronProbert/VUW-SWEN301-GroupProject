package main;

import main.logic.Monitor;

/**
 * This is the entry point of the program, it is an empty class, used just to
 * start the program
 * 
 * @author Cameron Probert
 *
 */
public class Main {
	public static void main(String[] arguments) {
		boolean verbose;
		if (arguments.length > 0) {
			if (arguments[0].equals("-verbose")) {
				verbose = true;
			} else {
				System.out.println();
				verbose = false;
			}
		} else {
			verbose = false;
		}
		new Monitor(verbose);
	}
}
