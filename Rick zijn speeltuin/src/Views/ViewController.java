package Views;

import java.util.HashMap;
import Other.DataWrapper;
import Simulator.Main;

@SuppressWarnings("rawtypes")
public class ViewController
{
	/* Alle Views worden hier gedeclareerd en geinitaliseerd. */
	 public static PieChart pieChart = new PieChart (460, 200);
	 
	 
	 /* De methoe thread, die de huidige data ophaalt, en alle views update. */
	 public static void thread ()
	 {
		/* De data ophalen. */
		HashMap<Class, DataWrapper> data = Main.getSimulator().getView().getCurrentData();
		 
		/* De PieChart updaten. */
		pieChart.update(data);
	 }
}
