package Models;

import java.util.List;

import Other.Location;

public interface Actor {
	public void act(List<Actor> newActors);
	
	public boolean isAlive ();
	
	void setLocation(Location newLocation);
}
