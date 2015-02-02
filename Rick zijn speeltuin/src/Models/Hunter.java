package Models;

import java.util.Iterator;
import java.util.List;

import Display.Field;
import Other.Location;

public class Hunter implements Actor
{
	/* Toegestaande aantal objecten voordat de hunter er iets aan mag doen */
	private static final int MAX_GRASS_ENTITIES = 1500;
	private static final int MAX_RABBIT_ENTITIES = 1000;
	private static final int MAX_FOX_ENTITIES = 1000;
	private static final int MAX_WOLF_ENTITIES = 300;
	
	/* Het veld waar de hunter zich op bevind. */
	private Field field;
	
	/* De hunter zijn locatie in het veld. */
	private Location location;
	
	/* Of een hunter leeft of niet. */
	private boolean alive;
  
  
  /**
   * Create a new hunter object. 
   * @param field The field currently occupied.
   * @param location The location within the field.
   */
  public Hunter(Field field, Location location)
  {
      this.field = field;
      this.location = location;
  }

  /**
   * Check whether the hunter is alive or not.
   * @return true if the hunter is still alive.
   */
  public boolean isAlive()
  {
  	return alive;
  }
  
  
  /**
   * Dit simuleert de hunter zijn 'leven'. Hij zoekt naar eten, en vertrapt gras onderweg.
   * 
   * @param field The field currently occupied.
   * @param newbears A list to return newly born hunters.
   */
  public void act(List<Actor> newHunters)
  {
	  /* Kijken of er een dier of gras om hem heen is. */
      Location newLocation = findAnimal();
      
      /* Dit was niet het geval. */
      if (newLocation == null)
      { 
          /* Dus naar een nieuwe, lege, vak gaan. */
          newLocation = getField().freeAdjacentLocation (getLocation ());
      }
      
      /* Controleren of het mogelijk is om te lopen. */
      if (newLocation != null)
      {
    	  /* De hunter heeft plek om heen te gaan. */
          setLocation(newLocation);
      }
      else
      {
    	  /* Er was geen plek meer voor de hunter. */
    	  setDead ();
      }
  }


  /**
   * Look for an animal adjacent to the current location.
   * Only the first live animal is shoot.
   * @return Where an animal is found, or null if it wasn't.
   */
  private Location findAnimal()
  {
     Field field				= getField();
     List<Location> adjacent	= field.adjacentLocations(getLocation());
     Iterator<Location> it		= adjacent.iterator();
      	  
      while (it.hasNext ())
      {
    	  Location where	= it.next();
    	  Object actor		= field.getObjectAt(where);
    	  
    	  	/* Kijken of het object een Rabbit is. */
    	  	if (actor instanceof Rabbit)
    	  	{
    	  		/* Dit bleek het geval te zijn, dus maak van de actor object, een rabbit object. */
    	  		Rabbit rabbit = (Rabbit) actor;
  	  		
    	  			/* Kijken of de rabit leeft. */
	  	  			if (rabbit.isAlive ()) 
	  	  			{
	  	  				/* Kijken of het toegestaan is om te jagen op rabbits. */
	  	  				if (Display.FieldStats.rabbitCount >= MAX_RABBIT_ENTITIES)
	  	  				{
	  	  					/* Rabit doden. */
	  	  					rabbit.setDead ();
	  	  				}
	  	  				
  	  					/* Naar de plek gaan waar de rabbit stond. */
	  	  				return where;
	  	  			}
    	  	}
    	  	/* Kijken of het object een Fox is. */
    	  	else if (actor instanceof Fox)
		  	{
    	  		/* Dit bleek het geval te zijn, dus maak van de actor object, een fox object. */
		  		Fox fox = (Fox) actor;
		  		
		  			/* Kijken of de fox leeft. */
		  			if (fox.isAlive ()) 
		  			{
		  				/* Kijken of het toegestaan is om te jagen op foxxes. */
		  				if (Display.FieldStats.foxCount >= MAX_FOX_ENTITIES)
		  				{
		  					/* Fox doden. */
		  					fox.setDead ();
		  				}		  	
		  				
	  					/* Naar de plek gaan waar de fox stond. */
	  					return where;
		  			}
		  	}   
    	  	/* Kijken of het object een Grass is. */
		  	else if (actor instanceof Grass)
		  	{
		  		/* Dit bleek het geval te zijn, dus maak van de actor object, een grass object. */
		  		Grass grass = (Grass) actor;
		  		
		  			/* Kijken of het grass leeft. */
		  			if (grass.isAlive ()) 
		  			{
		  				/* Kijken of het toegestaan is om gras kapot te trappen. */
		  				if (Display.FieldStats.grassCount >= MAX_GRASS_ENTITIES)
		  				{
		  					/* Gras kapot maken. */
		  					grass.setDead ();
		  				}
	  					
	  					/* Naar de plek gaan waar het gras lag. */
	  					return where;
		  			}
		  	}   
    	  	/* Kijken of het object een Wolf is. */
		  	else if (actor instanceof Wolf)
		  	{
		  		/* Dit bleek het geval te zijn, dus maak van de actor object, een wolf object. */
		  		Wolf wolf = (Wolf) actor;
		  		
		  			/* Kijken of de wolf leeft. */
		  			if (wolf.isAlive ()) 
		  			{
		  				/* Kijken of het toegestaan is om te jagen op wolves. */
		  				if (Display.FieldStats.wolfCount >= MAX_WOLF_ENTITIES)
		  				{
		  					/* Wolf doden. */
		  					wolf.setDead ();
		  				}
	  					
	  					/* Naar de plek gaan waar de wolf stond. */
	  					return where;
		  			}
		  	}
      }
      
      return null;
  }
  
  /**
   * Indicate that the hunter is no longer alive.
   * It is removed from the field.
   */
  public void setDead()
  {
//      alive = false;
      if(location != null) {
          field.clear(location);
          location = null;
          field = null;
      }
  }
  
  /**
   * Return the hunter's location.
   * @return The hunter's location.
   */
  public Location getLocation()
  {
      return location;
  }
  
  /**
   * Place the hunter at the new location in the given field.
   * @param newLocation The hunter's new location.
   */
  public void setLocation(Location newLocation)
  {
      if(location != null) {
          field.clear(location);
      }
      location = newLocation;
      field.place(this, newLocation);
  }
  
  /**
   * Return the hunter's field.
   * @return Field the hunter's field.
   */
  public Field getField()
  {
      return field;
  }   
}
