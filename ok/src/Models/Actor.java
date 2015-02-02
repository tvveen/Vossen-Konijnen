package Models;

import java.util.List;

public interface Actor {
	public void act(List<Actor> newActors);
	
	public boolean isAlive ();
}
