package Simulator;

public class RunThread implements Runnable
{
	/* Private boolean om na te gaan of de thread moet runnen of niet. */
	private boolean runThread = false;
	
	
	/* Private int, waarin een bepaald aantal steps geplaatst kunnen worden. */
	private int steps = 0;
	
	
	
	/* Methode om the thread te starten. */
	public void startThread ()
	{
		/* Controleren of de thread al draait. Zo ja, return zodat er niet een nieuwe thread geopend word. */
		if (this.runThread)
			return;
		
		/* Thread laten starten door boolean op true te zetten. */
		this.runThread = true;
		
			/* De thread daadwerkelijk maken en starten. */
			if (Thread.currentThread().isAlive())
				new Thread(this).start();
	}
	
	
	
	/* Methode om de thread te stoppen. */
	public void stopThread ()
	{
		/* Thread stoppen door de boolean op false te zetten. */
		this.runThread = false;
	}
	
	
	
	/* Methode om de simulatie een bepaalde aantal steps te laten draaien. */
	public void runStep (int steps)
	{
		/* Controleren of de thread al draait. Zo ja, return zodat er niet een nieuwe thread geopend word. */
		if (this.runThread)
			return;
		
		/* Aantal steps instellen. */
		this.steps = steps;
		
		/* Thread starten. */
		this.startThread ();
	}
	
	
	
	/* Methode om de simulatie te resetten. */
	public void resetThread ()
	{
		/* De thread op false zetten. */
		this.runThread = false;
		
		/* De simulatie resetten. */
		Main.getSimulator().reset();
	}
	
	
	
	/* De methode die word uitgevoerd door de thread. */
	@Override
	public void run ()
	{
		/* Kijken of de thread oneindig moet draaien of niet. */
		if (steps == 0)
		{
			/* Een loop maken, die net zo lang draait tot runThread op false word gezet. */
			while (runThread)
			{
				/* Daadwerkelijk een stap vooruit */
				Main.getSimulator().simulateOneStep ();
			}
		}
		else
		{
			/* Een loop maken, die net zo lang draait tot runThread false is, of het aantal steps 0 is. */
			while (runThread && steps > 0)
			{
				/* Daadwerkelijk een stap vooruit */
				Main.getSimulator().simulateOneStep ();
				
				/* Steps - 1. */
				steps--;
			}
			
			/* De thread stoppen, zodat hij opnieuw kan worden gestart. */
			stopThread ();
		}
	}
}
