package Simulator;

import javax.swing.*;

public class Main
{	
	/* Statische variable voor een Simulator instantie. */
	public static Simulator simulator;
	
	/* Statische variable voor een Thread instantie. */
	public static RunThread thread;
	
	
	/* De main methode die word aangeroepen op het moment dat de applicatie word gestart. */
	public static void main (final String[] args)
	{
		/* Een nieuwe JFrame aanmaken. */
		final JFrame parent = new JFrame();
		
		/* Default hoogte van het venster. */
		int height	= 80;
		
		/* Default breedte van het venster. */
		int width	= 120;
		
		
		/* Controleren of er een cijfer in gevuld is of niet. */
		try
		{ 
			/* Het ingevulde nummer proberen op te halen. */
			height	= Integer.valueOf (JOptionPane.showInputDialog (parent, "Enter the heigth of the gird", null));
		}
		catch (NumberFormatException e)
		{
			/* Er is geen geldig nummer ingevuld, dus een default hoogte blijft zoals eerder ingesteld. */
		}
		
		
		/* Controleren of er een cijfer in gevuld is of niet. */
		try
		{ 
			/* Het ingevulde nummer proberen op te halen. */
			width	= Integer.valueOf (JOptionPane.showInputDialog (parent, "Enter the width of the grid", null));
		}
		catch (NumberFormatException e)
		{
			/* Er is geen geldig nummer ingevuld, dus een default breedte blijft zoals eerder ingesteld. */
		}
		
		
		/* Nieuwe simulator maken met de ingevulde (of default) gegevens. */
		Main.simulator = new Simulator (height, width);
		
		/* De Thread variabele initialiseren. */
		Main.thread = new RunThread ();
	}
	
	
	/* Methode om de Simulator instantie op te roepen. */
	public static Simulator getSimulator ()
	{
		return simulator;
	}
	
	
	/* Methode om de Thread instantie op te roepen. */
	public static RunThread getThread ()
	{
		return thread;
	}
}
