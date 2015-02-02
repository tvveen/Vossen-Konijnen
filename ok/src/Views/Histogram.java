package Views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

import Other.DataWrapper;

@SuppressWarnings("rawtypes")
public class Histogram extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	/* De breedte en hoogte van de diagram. */
	private int width;
	private int height;
	
	/* HashMap om data in op te slaan. */
	private HashMap<Class, DataWrapper> data;
	
	/* Een margin instellen, om ruimte om de diagram te creëren. */
	private final int MARGIN = 40;
	
	
	/* Indien de constructer leeg is, roep hem opnieuw aan met de default gegevens. */
	public Histogram ()
	{
		this (600, 400);
	}
	
	
	/* Constructer waarbij de breedte en hoogte worden ingesteld. */
	public Histogram (int width, int height)
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
	
	
	/* Methode om de diagram te tekenen op het scherm. */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		/* Benodigde variables instellen. */
		int height	= 0;
		int width	= this.width / data.size();
		int blok	= 0;
		int total	= 0;
		
		
		/* De data HashMap doorlopen. */
		for (Class c: this.data.keySet())
		{
			/* Het total variable aanpassen en het totaal, van dit model, erbij op tellen. */
			total += this.data.get(c).getCounter().getCount();
		}
		
		/* Een loop maken die de data doorloopt, en van elke entry een balk tekent. */
		for (Class c : this.data.keySet())
		{
			/*  De hoogte word bepaald door het aantal van dit model / het totaal aantal van alle modellen * de ingestelde hoogte. */
			height = (int)((double)this.height * ((double)this.data.get(c).getCounter().getCount() / (double)total));
			
			/* Het blok kleuren met de ingestelde kleur voor dit model. */
			g.setColor (this.data.get(c).getColor());
			
			/* De balk daadwerkelijk tekenen. */
			g.fillRect (width * blok + MARGIN, this.height - height - MARGIN, width - 1, height);
			
			/* De kleur van de omleining instellen. */
			g.setColor (Color.BLACK);
			
			/* De omleining om de balk heen tekenen. */
			g.drawRect (width * blok + MARGIN, this.height - height - MARGIN, width - 1, height);
			
			/* Één bij blok optellen, zodat de volgende blok naast de vorige komt te staan. */
			blok++;
		}
	}
	
}
