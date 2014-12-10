import java.awt.Point;
import java.util.ArrayList;

/*
 * Represents an entity in the simulation
 */
public class Entity {
	
	private Point location; // location of entity
	private ArrayList<Entity> adjEntities; // list of adjacent entities (diagonal included)
	private int age; // age of entity
	private static final String representation = "^";
	
	// constructor
	public Entity(Point location) {
		this.location = location;
		this.adjEntities = new ArrayList<Entity>();
		this.age = 0;
	}
	
	// logic to be handled on a tick event
	public void onTick() {
		this.age++;
	}
	
	// returns representation of the entity
	public String getRepresentation() {
		return representation;
	}
}
