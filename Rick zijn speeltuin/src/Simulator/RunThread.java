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
		runThread = true;
		
			if (Thread.currentThread().isAlive())
				new Thread(this).start();
		
		//JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), "startThread");
	}
	
	
	
	/* Methode om de thread te stoppen. */
	public void stopThread ()
	{
		runThread = false;
		
		//JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), "stopThread");
	}
	
	
	
	/* Methode om de simulatie een bepaalde aantal steps te laten draaien. */
	public void runStep (int steps)
	{
		this.runThread	= true;
		this.steps		= steps;
		
			if (Thread.currentThread().isAlive())
				new Thread(this).start();
	
		//JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), steps + " step(s)");
	}
	
	
	
	/* Methode om de simulatie te resetten. */
	public void resetThread ()
	{
		/* De thread op false zetten. */
		runThread = false;
		
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
		}
	}
}
