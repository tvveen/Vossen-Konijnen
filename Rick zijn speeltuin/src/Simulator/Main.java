package Simulator;

public class Main
{	
	/* Statische variable voor een Simulator instantie. */
	public static Simulator simulator;
	
	/* Statische variable voor een Thread instantie. */
	public static RunThread thread;
	
	
	/* Main methode om de applicatie te starten en een Simulator instantie aan te maken. */
	public static void main (String[] args)
	{
		/* De Simulator variabele initialiseren. */
		Main.simulator = new Simulator ();
		
		/* De Thread variabele initialiseren. */
		Main.thread = new RunThread ();
	}
	
	
	/* Methode om de Simulator instantie op te roepen. */
	public static Simulator getSimulator ()
	{
		return simulator;
	}
	
	
	public static RunThread getThread ()
	{
		return thread;
	}
}
