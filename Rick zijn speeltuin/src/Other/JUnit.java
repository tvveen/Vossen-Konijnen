package Other;

//import static org.junit.Assert.*;
import org.junit.Test;

import Simulator.Main;
import Simulator.Simulator;

public class JUnit {

	/**
	 * Test of de gehele simulatie kan worden gecompileerd door middel van de Main methode
	 */
	@Test
	public void testMain() {
		String[] testArgs = new String[] {};
		Main.main(testArgs);
	}
	
	/**
	 * Test of de simulatie één stap kan simuleren
	 */
	@Test
	public void testSimulateOneStep() {
		Simulator simulator = new Simulator();
		simulator.simulateOneStep();
	}

}
