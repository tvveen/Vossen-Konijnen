package Display;

import java.util.HashMap;

import Other.*;

/**
 * This class collects and provides some statistical data on the state 
 * of a field. It is flexible: it will create and maintain a counter 
 * for any class of object that is found within the field.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
@SuppressWarnings("rawtypes")
public class FieldStats
{
    // Counters for each type of entity (fox, rabbit, etc.) in the simulation.
	private HashMap<Class, Counter> counters;
    // Whether the counters are currently up to date.
    private boolean countsValid;

    
    public static int foxCount;
    public static int rabbitCount;
    public static int wolfCount;
    public static int grassCount;
    
    
    /**
     * Construct a FieldStats object.
     */
    public FieldStats()
    {
        // Set up a collection for counters for each type of animal that
        // we might find
        counters = new HashMap<Class, Counter>();
        countsValid = true;
    }

    
    
    
    public HashMap<Class, Counter> getCounters ()
    {
    	return this.counters;
    }
    
    
    
    
    /**
     * Get details of what is in the field.
     * @return A string describing what is in the field.
     */
    public String getPopulationDetails(Field field)
    {
        StringBuffer buffer = new StringBuffer();
        if(!countsValid) {
            generateCounts(field);
        }
        for(Class key : counters.keySet()) {
            Counter info = counters.get(key);
            String entityName;
            switch (info.getName()) {
            	case "Models.Rabbit":	entityName = "Rabbit";
            							break;
            	case "Models.Fox":		entityName = "Fox";
            							break;
            	case "Models.Grass":	entityName = "Grass";
            							break;
            	case "Models.Wolf":		entityName = "Wolf";
            							break;
            	case "Models.Hunter":	entityName = "Hunter";
            							break;
            	default:				entityName = info.getName();
            							break;
            }
            buffer.append(entityName);
            buffer.append(": ");
            buffer.append(info.getCount());
            buffer.append(' ');
            
            if (info.getName() == "Models.Rabbit")
            	rabbitCount = info.getCount();
            
            if (info.getName() == "Models.Fox")
            	foxCount = info.getCount();
            
            if (info.getName() == "Models.Grass")
            	grassCount = info.getCount();
        }
        return buffer.toString();
    }
    
    /**
     * Invalidate the current set of statistics; reset all 
     * counts to zero.
     */
    public void reset()
    {
        countsValid = false;
        for(Class key : counters.keySet()) {
            Counter count = counters.get(key);
            count.reset();
        }
    }

    /**
     * Increment the count for one class of animal.
     * @param animalClass The class of animal to increment.
     */
    public void incrementCount(Class animalClass)
    {
        Counter count = counters.get(animalClass);
        if(count == null) {
            // We do not have a counter for this species yet.
            // Create one.
            count = new Counter(animalClass.getName());
            counters.put(animalClass, count);
        }
        count.increment();
    }

    /**
     * Indicate that an animal count has been completed.
     */
    public void countFinished()
    {
        countsValid = true;
    }

    /**
     * Determine whether the simulation is still viable.
     * I.e., should it continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        // How many counts are non-zero.
        int nonZero = 0;
        if(!countsValid) {
            generateCounts(field);
        }
        for(Class key : counters.keySet()) {
            Counter info = counters.get(key);
            if(info.getCount() > 0) {
                nonZero++;
            }
        }
        return nonZero > 1;
    }
    
    /**
     * Generate counts of the number of foxes and rabbits.
     * These are not kept up to date as foxes and rabbits
     * are placed in the field, but only when a request
     * is made for the information.
     * @param field The field to generate the stats for.
     */
    private void generateCounts(Field field)
    {
        reset();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    incrementCount(animal.getClass());
                }
            }
        }
        countsValid = true;
    }
}
