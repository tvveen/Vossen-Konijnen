package Views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import Models.*;
import Other.Counter;
import Other.DataWrapper;

@SuppressWarnings("rawtypes")
public class PieChart extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	
	private HashMap<Class, DataWrapper> data;
	
	
	
	public PieChart ()
	{
		this (600, 400);
	}
	
	
	public PieChart (int width, int height)
	{	
		this.width = width;
		this.height = height;
	}
	
	
	public void update (HashMap<Class, DataWrapper> data)
	{
		this.data = data;
		
		this.repaint ();
	}
	

	
	/**
	 * maak de piechart
	 * @param g Graphic component
	 */
	@Override
	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		
		int total		= 0;
		int startAngle	= 0;
		int arcAngle	= 0;
		
		HashMap<Color, Integer> stats = new HashMap<Color, Integer>();
		
			for (Class c: this.data.keySet())
			{
				int getCount = this.data.get(c).getCounter().getCount();
				
				stats.put (this.data.get(c).getColor(), getCount);
				
				total += getCount;
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

		// kleurt de piechart
		for (Color color : stats.keySet())
		{
			if (stats.get(color) > 0)
			{
				//	teller van een kleeur delen door de totaal en keer 360 graden
				arcAngle = (stats.get(color) * 360/ total) ;
				g.setColor(color);
				//	draw de piechart
				g.fillArc(10, 50, width, height, startAngle, arcAngle);
				//	start angle van de volgende kleur
				startAngle += arcAngle + 1;
			}
		}

		//	paint de outline van de piechart
		g.setColor(Color.BLACK);
		g.drawArc(10, 50, width, height, 0, 360);
		
	}

}
