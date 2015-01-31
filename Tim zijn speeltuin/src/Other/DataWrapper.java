package Other;

import java.awt.Color;

public class DataWrapper
{
	/* Private methodes aanmaken die de DataWrapper vast houd. */
    private Counter counter;
    private Color color;
	
    /* Constructer, waarbij een Counter en Color object worden mee gegeven. */
    public DataWrapper (Counter counter, Color color)
    {
    	/* De objecten opslaan in het huidige object. */
    	this.counter = counter;
        this.color = color;
    }
    
    
    /* Getter methode voor counter. */
    public Counter getCounter ()
    {
    	return this.counter;
    }
    
    
    /* Getter moethode voor color. */
    public Color getColor ()
    {
    	return this.color;
    }
}
