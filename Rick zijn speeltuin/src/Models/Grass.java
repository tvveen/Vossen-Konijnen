package Models;

import java.util.List;
import java.util.Random;
import Other.*;
import Display.*;

/**
 * A simple model of a grass.
 * Grass age, move, breed, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Grass extends Animal
{
    // Characteristics shared by all Grass (class variables).

    // The age at which a Grass can start to breed.
    private static int BREEDING_AGE = 3;
    // The age to which a Grass can live.
    private static int MAX_AGE = 12;
    // The likelihood of a Grass breeding.
    private static double BREEDING_PROBABILITY = 0.25;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 4;
    // A shared random number generator to control breeding.
    private static Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    
    // The Grass's age.
    private int age;

    /**
     * Create a new Grass. A Grass may be created with age
     * zero (a new born) or with a random age.
     * 
     * @param randomAge If true, the Grass will have a random age.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Grass(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        age = 0;
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
        }
    }
    
    /**
     * This is what the Grass does most of the time - it runs 
     * around. Sometimes it will breed or die of old age.
     * @param newGrass A list to return newly born Grass.
     */
    public void act(List<Actor> newGrass)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newGrass);            
            // Try to move into a free location.
            Location newLocation = getField().freeAdjacentLocation(getLocation());
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Increase the age.
     * This could result in the Grass's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Check whether or not this Grass is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newGrass A list to return newly born Grass.
     */
    private void giveBirth(List<Actor> newGrass)
    {
        // New Grass are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Grass young = new Grass(false, field, loc);
            newGrass.add(young);
        }
    }
        
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    private int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= BREEDING_PROBABILITY) {
            births = rand.nextInt(MAX_LITTER_SIZE) + 1;
        }
        return births;
    }

    /**
     * A Grass can breed if it has reached the breeding age.
     * @return true if the Grass can breed, false otherwise.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    
    // Defineer dmv de GUI settings de standaard waarden van grass
    public static void setBreedingAge(int breedingAge)
    {
    	BREEDING_AGE = breedingAge;
    }
    
    public static void setMaxAge(int maxAge)
    {
    	MAX_AGE = maxAge;
    }
    
    public static void setBreedingProbability(double probability)
    {
    	BREEDING_PROBABILITY = probability;
    }
    
    public static void setMaxLitterSize(int max)
    {
    	MAX_LITTER_SIZE = max;
    }
}
