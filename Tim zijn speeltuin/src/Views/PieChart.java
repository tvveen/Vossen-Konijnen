package Views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;
import Other.DataWrapper;

@SuppressWarnings("rawtypes")
public class PieChart extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	/* De breedte en hoogte van de diagram. */
	private int width;
	private int height;
	
	/* HashMap om data in op te slaan. */
	private HashMap<Class, DataWrapper> data;
	
	
	/* Indien de constructer leeg is, roep hem opnieuw aan met de default gegevens. */
	public PieChart ()
	{
		this (600, 400);
	}
	
	
	/* Constructer waarbij de breedte en hoogte worden ingesteld. */
	public PieChart (int width, int height)
	{	
		this.width = width;
		this.height = height;
	}
	
	
	/* Methode om de gegevens van het veld op te slaan in dit object. */
	public void update (HashMap<Class, DataWrapper> data)
	{
		/* De data opslaan. */
		this.data = data;
		
		/* Nadat de data opgeslagen is, repaint de chart opnieuw zodat de nieuwe data direct zichtbaar is. */
		this.repaint ();
	}
	
	
	/*
		if (c == Fox.class)
			System.out.println ("Fox!");
		else
		if (c == Rabbit.class)
			System.out.println ("Rabbit!");
		else
			System.out.println ("Unknown! :(");
	*/

	
	/* Methode om de taart diagram te tekenen op het scherm. */
	@Override
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		
		/* Benodigde variables instellen. */
		int total		= 0;
		int startAngle	= 0;
		int arcAngle	= 0;
		
		/* Tijdelijke nieuwe HashMap aanmaken. */
		HashMap<Color, Integer> stats = new HashMap<Color, Integer>();
		
			/* De data HashMap doorlopen. */
			for (Class c: this.data.keySet())
			{
				/* Het totaal aantal objecten van een Model ophalen. */
				int getCount = this.data.get(c).getCounter().getCount();
				
				/* Nieuwe entry in de stats HashMap plaatsen, waarbij het totaal aantal objecten met de kleur word gecombineerd. */
				stats.put (this.data.get(c).getColor(), getCount);
				
				/* Het total variable aanpassen, en het eerderberekende totaal erbij op tellen. */
				total += getCount;
			}
			
			
			/* Kijken of het totaal wel boven de 0 ligt. Zo nee, return zodat er niks word gerekent. */
			if (total == 0)
			{
				/* Return */
				return;
			}
			
			
			/* De boven gemaakte hashmap doorloopen. */
			for (Color color : stats.keySet())
			{
				/* Het aantal objecten van dit bepaald model * 360 (1 hele ronde), en dat gedeeldoor het totaal aantal. */
				arcAngle = (stats.get (color) * 360 / total) ;
				
				/* De kleur setten die is meegegeven in de data. */
				g.setColor (color);
				
				/* Het taart stukje van de diagram tekenen. */
				g.fillArc (10, 50, width, height, startAngle, arcAngle);
				
				/* De start hoek met het boven uitgerekende optellen, en daar nog één bij op voor de borders. */
				startAngle += arcAngle + 1;
			}

		/* De omlijning van de diagram tekenen. */
		/* De kleur zwart instellen. */
		g.setColor (Color.BLACK);
		
		/* Daadwerkelijk de lijn tekenen. */
		g.drawArc (10, 50, width, height, 0, 360);
	}
}
