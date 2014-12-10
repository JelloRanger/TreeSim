import java.awt.Point;
import java.util.ArrayList;

/*
 * Represents an entity in the simulation
 */
public abstract class Entity {
	
	protected Point location; // location of entity
	protected int age; // age of entity
	protected final String representation = "E";
	
	// constructor
	public Entity(Point location) {
		this.location = location;
		this.age = 0;
	}
	
	// logic to be handled on a tick event
	// return a string of name of class (lowercase) if nothing noteworthy occurs
	// otherwise return a string detailing the change
	public String onTick() {
		this.age++;
		return "entity";
	}
	
	// return age of entity
	public int getAge() {
		return age;
	}
	
	// return location of entity
	public Point getLocation() {
		return location;
	}
	
	// returns representation of the entity
	public abstract String getRepresentation();
}
