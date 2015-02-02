package Models;

import java.util.List;
import java.util.Iterator;
import java.util.Random;
import Other.*;
import Display.*;

/**
 * A simple model of a wolf.
 * wolves age, move, eat foxes/rabbits, and die.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Wolf extends Animal
{
    // Characteristics shared by all wolves (class variables).
    
    // The age at which a wolf can start to breed.
    private static int BREEDING_AGE = 6;
    // The age to which a wolf can live.
    private static int MAX_AGE = 200;
    // The likelihood of a wolf breeding.
    private static double BREEDING_PROBABILITY = 0.02;
    // The maximum number of births.
    private static int MAX_LITTER_SIZE = 4;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a wolf can go before it has to eat again.
    private static int RABBIT_FOOD_VALUE = 5;
    // The food value of a single fox. In effect, this is the
    // number of steps a wolf can go before it has to eat again.
    private static int FOX_FOOD_VALUE = 14;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The wolf's age.
    private int age;
    // The wolf's food level, which is increased by eating.
    private int foodLevel;
        

    /**
     * Create a wolf. A wolf can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the wolf will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Wolf(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            age = rand.nextInt(MAX_AGE);
            //foodLevel = rand.nextInt(RABBIT_FOOD_VALUE) + rand.nextInt(FOX_FOOD_VALUE);
            foodLevel = rand.nextInt(FOX_FOOD_VALUE);
        }
        else {
            age = 0;
            //foodLevel = RABBIT_FOOD_VALUE + FOX_FOOD_VALUE;
            foodLevel = FOX_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the wolf does most of the time: it hunts.
     * In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newWolves A list to return newly born wolves.
     */
    public void act(List<Actor> newWolves)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newWolves);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
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
     * Increase the age. This could result in the wolf's death.
     */
    private void incrementAge()
    {
        age++;
        if(age > MAX_AGE) {
            setDead();
        }
    }
    
    /**
     * Make this wolf more hungry. This could result in the wolf's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits/foxes adjacent to the current location.
     * Only the first live animal is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }
            else if(animal instanceof Fox) {
            	Fox fox = (Fox) animal;
            	if(fox.isAlive()) {
            		fox.setDead();
            		foodLevel = FOX_FOOD_VALUE;
            		return where;
            	}
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this wolf is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newWolves A list to return newly born wolves.
     */
    private void giveBirth(List<Actor> newWolves)
    {
        // New wolves are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Wolf young = new Wolf(false, field, loc);
            newWolves.add(young);
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
     * A wolf can breed if it has reached the breeding age.
     */
    private boolean canBreed()
    {
        return age >= BREEDING_AGE;
    }
    
    
    
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
        
    public static void setRabbitFoodValue(int food)
    {
    	RABBIT_FOOD_VALUE = food;
    }
    
}
