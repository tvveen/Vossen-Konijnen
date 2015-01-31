package Views;

import java.util.ArrayList;
import java.util.List;

public class LineChartData
{
	/* Een (Array)List maken om de data in op te slaan. */
	private List<Integer> data;
	
	/* Het maximum aantal dots wat een LineChart mag hebben. */
	int maxDots;
	
	/* Constructer, waarbij het aantal maxDots word mee gegeve. */
	public LineChartData (int maxDots)
	{
		/* Max dots instellen. */
		this.maxDots	= maxDots;
		
		/* Data list initialiseren. */
		this.data		= new ArrayList<Integer>();
	}
	
	
	/* Methode om het aantal dots van dit object terug te geven. */
	public int countDots ()
	{
		return this.data.size ();
	}
	
	
	/* Methode om een nieuwe dot toe te voegen, waarbij de X mee gegeven word. */
	public void newDot (int x)
	{
		/* Kijken of dit object aan het dot limiet zit. Zo ja, verwijder de eerste. */
		if (this.countDots() == this.maxDots)
		{
			this.data.remove(0);
		}
		
		/* Nieuwe dot toevoegen. */
		this.data.add (x);
	}
	
	
	/* Methode om alle dots terug te krijgen. */
	public List<Integer> getDots ()
	{
		return this.data;
	}
}
