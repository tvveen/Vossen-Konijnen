package Simulator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RunThread implements Runnable
{
	/* Private boolean om na te gaan of de thread moet runnen of niet. */
	private boolean runThread = false;
	
	private int steps = 0;
	
	
	/* Methode om the thread te starten. */
	public void startThread ()
	{
		runThread = true;
		
			if (Thread.currentThread().isAlive())
				new Thread(this).start();
		
		JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), "Thread started");
	}
	
	
	/* Methode om de thread te stoppen. */
	public void stopThread ()
	{
		runThread = false;
		
		JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), "Thread stopt");
	}
	
	public void resetThread()
	{
		runThread = false;
		Main.getSimulator().reset();
	}
	
	/* De methode die word uitgevoerd door de thread. */
	public void run()
	{			
		if(steps == 0){
			while (runThread)
			{
				Main.getSimulator().simulateOneStep ();
			}
		}
		else{
			while (runThread && steps > 0)
			{
				Main.getSimulator().simulateOneStep ();
				steps--;
			}
		}
		runThread = false;
	}
	
	public void runStep(int steps){
		runThread = true;
		this.steps = steps;
		
		if (Thread.currentThread().isAlive()){
			new Thread(this).start();
		}
	
	JOptionPane.showMessageDialog(new JFrame("JOptionPane showMessageDialog example"), steps + " step(s)");
	}
}
