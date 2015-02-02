package Simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Display.SplashScreen;

public class Main
{	
	/* Statische variable voor een Simulator instantie. */
	public static Simulator simulator;
	
	/* Statische variable voor een Thread instantie. */
	public static RunThread thread;
	
	/* Statische variable voor standaard simulator hoogte. */
	public static int simulatorHeight = 80;
	
	/* Statische variable voor standaard simulator breedte. */
	public static int simulatorWidth = 120;
	
	/* Statische variable voor de splashScreen object. */
	private static JFrame splashScreen;
	
	
	/* De main methode die word aangeroepen op het moment dat de applicatie word gestart. */
	public static void main (final String[] args)
	{
		/* Een nieuwe JFrame aanmaken. */
		Main.splashScreen = new JFrame();
				
		/* Geef de splashscreen een titel. */
		Main.splashScreen.setTitle ("Vossen & Konijnen project");
				
		/* Set te splashscreen hoogte en breedte. */
		Main.splashScreen.setSize (790, 580);
				
		/* Maak een nieuw label. */
		JLabel frameHeightLabel = new JLabel ("Enter height of grid:");
		frameHeightLabel.setBounds (10, 490, 150, 30);
		
		/* Maak een nieuw tekstveld. */
		final JTextField frameHeight = new JTextField ();
		frameHeight.setBounds (130, 490, 125, 30);		
		
		/* Maak een nieuw label. */
		JLabel frameWidthLabel = new JLabel ("Enter width of grid:");
		frameWidthLabel.setBounds (275, 490, 150, 30);
		
		/* Maak een nieuw tekstveld. */
		final JTextField frameWidth = new JTextField ();
		frameWidth.setBounds (385, 490, 150, 30);
		
		/* Maak een nieuwe knop. */
		JButton start = new JButton ("Start Simulation");
		start.setBounds(580, 490, 140, 30);
		
		/* De actionlistener voor de knop. */
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent resetValue)
			{
				/* Als de hoogte invoer een int is, set die in de static variable, zo nee, houd de default waardes. */
				try
				{ 
					/* Het ingevulde nummer proberen op te halen. */
					Main.simulatorHeight = Integer.valueOf (frameHeight.getText ());
				}
				catch (NumberFormatException e)
				{
					/* Er is geen geldig nummer ingevuld, dus een default hoogte blijft zoals eerder ingesteld. */
				}
				
				/* Als de breedte invoer een int is, set die in de static variable, zo nee, houd de default waardes. */
				try
				{ 
					/* Het ingevulde nummer proberen op te halen. */
					Main.simulatorWidth = Integer.valueOf (frameWidth.getText ());
				}
				catch (NumberFormatException e)
				{
					/* Er is geen geldig nummer ingevuld, dus een default breedte blijft zoals eerder ingesteld. */
				}
				
				/* De splashscreen weghalen. */
				Main.splashScreen.setVisible (false);
				
				/* Nieuwe simulator maken met de ingevulde (of default) gegevens. */
				Main.simulator = new Simulator (Main.simulatorHeight, Main.simulatorWidth);
				
				/* De Thread variabele initialiseren. */
				Main.thread = new RunThread ();
			}
		});
		
		/* Alle objecten toevoegen aan de JFrame. */
		Main.splashScreen.add (frameHeightLabel);
		Main.splashScreen.add (frameHeight);		
		Main.splashScreen.add (frameWidthLabel);
		Main.splashScreen.add (frameWidth);
		Main.splashScreen.add (start);
		
		/* Plaatje inporteren. */
		Main.splashScreen.add (new SplashScreen ());
		
		/* De JFrame op het scherm toveren. */
		Main.splashScreen.setVisible (true);
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
