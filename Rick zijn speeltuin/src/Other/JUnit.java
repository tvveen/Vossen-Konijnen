package Other;

//import static org.junit.Assert.*;
import org.junit.Test;

import Simulator.Main;
//import Simulator.Simulator;

public class JUnit {

	/**
	 * Test if the simulation can be compiled with the default parameters
	 */
	@Test
	public void testMain() {
		String[] testArgs = new String[] {};
		Main.main(testArgs);
	}
	
	@Test
	public void testSimulateOneStep() {
		//Simulator.simulateOneStep();
	}

}
