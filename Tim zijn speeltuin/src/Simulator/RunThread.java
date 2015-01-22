package Simulator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RunThread implements Runnable
{
	/* Private boolean om na te gaan of de thread moet runnen of niet. */
	private boolean runThread = false;
	
	
	/* Methode om the thread te starten. */
	public void startThread ()
	{
		runThread = true;
		
			if (Thread.currentThread().isAlive())
				new Thread(this).start();
		
		JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), "startThread");
	}
	
	
	/* Methode om de thread te stoppen. */
	public void stopThread ()
	{
		runThread = false;
		
		JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), "stopThread");
	}
	
	
	/* De methode die word uitgevoerd door de thread. */
	@Override
	public void run ()
	{
		while (runThread)
		{
			Main.getSimulator().simulateOneStep ();
		}
	}
}
