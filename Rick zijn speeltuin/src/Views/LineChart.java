package Views;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JPanel;

import Other.DataWrapper;

@SuppressWarnings("rawtypes")
public class LineChart extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	/* De breedte en hoogte van de diagram. */
	private int width;
	private int height;
	
	/* Maximale aantal punten per Model. */
	private int maxDots	= 50;
	
	private final int MARGIN = 20;
	
	/* HashMap om data in op te slaan. */
	private HashMap<Class, DataWrapper> data;
	
	/* HashMap om de line chart data in op te slaan per class. */
	private HashMap<Class, LineChartData> chartData = new HashMap<Class, LineChartData>();
	
	
	/* Indien de constructer leeg is, roep hem opnieuw aan met de default gegevens. */
	public LineChart ()
	{
		this (600, 400);
	}
	
	
	/* Constructer waarbij de breedte en hoogte worden ingesteld. */
	public LineChart (int width, int height)
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
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		
		/* Variables setten. */
		int total	= 0;
		int posX	= 0;
		
		
		/* De lijn van links naar rechts tekenen. */
		g.drawLine (MARGIN, this.height - MARGIN, this.width, this.height - MARGIN);
		
		/* De lijn van boven naar beneden tekenen. */
		g.drawLine (MARGIN, MARGIN, MARGIN, this.height - MARGIN);
		
		
			/* De data HashMap doorlopen. */
			for (Class c: this.data.keySet())
			{
				/* Het total variable aanpassen en het totaal, van dit model, erbij op tellen. */
				total += this.data.get(c).getCounter().getCount();
			}
			
			
			/* Elke class loopen. */
			for (Class c: this.data.keySet())
			{
				/* Kijken of de geselecteerde class al in de chartData staat. */
				if (!this.chartData.containsKey(c))
				{
					/* Dit bleek niet het geval, dus word die nu aangemaakt. */
					this.chartData.put(c, new LineChartData (this.maxDots));
				}
				
				/* De nieuwe X positie bepalen van de eerst volgende dot. */
				posX = (int)(((double)this.height - (MARGIN * 2)) * ((double)this.data.get(c).getCounter().getCount() / (double)total)) + MARGIN;
				posX = this.height - posX;
				
				/* De nieuwe dot aan de data toevoegen. */
				this.chartData.get(c).newDot (posX);
			}
			
			
			/* Graphics2D object aanmaken, zodat we de lijn dikte kunnen veranderen. */
			Graphics2D g2 = (Graphics2D) g;
			
			
			/* Nogmaals een loop maken. */
			for (Class c: this.data.keySet())
			{
				/* Tijdelijke variables setten. */
				int previousDotY	= 0;
				int previousDotX	= 0;
				
				int dotCount		= 0;
				
				/* Kleur van de lijn setten naar de kleur die bij het model hoort. */
				g2.setColor (this.data.get(c).getColor()); 
				
				/* Lijn dikte zetten naar 2. */
				g2.setStroke(new BasicStroke(2));
				
					/* Een loop die alle dots langs gaat. */
					for (Integer dotX: this.chartData.get(c).getDots())
					{
						/* De dot locatie bepalen. */
						int dotY = ((this.width / this.maxDots) * dotCount) + MARGIN;
						
							/* Kijken of er eerder een dot locatie was. */
							if (previousDotY != 0)
							{
								/* De lijn daadwerkelijk tekenen. */
								g2.drawLine (previousDotY, previousDotX, dotY, dotX);
							}
					    
						/* Variables setten voor volgende loop run. */
						previousDotY = dotY;
						previousDotX = dotX;
						
						/* …Èn bij count op. */
						dotCount++;
					}
			}
	}
}
