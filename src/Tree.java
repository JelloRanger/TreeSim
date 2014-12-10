import java.awt.Point;
import java.util.ArrayList;

/*
 * Represents a tree that can spawn Saplings
 */
public class Tree extends Entity {

	protected final double saplingSpawnRate = 0.1; // chance tree can spawn a sapling
	protected final int lumberYield = 1; // amount of lumber a tree generates when cut
	
	protected final String representation = "^";
	
	// constructor
	public Tree(Point location) {
		super(location);
	}
	
	// constructor with age argument
	public Tree(Point location, int age) {
		super(location);
		this.age = age;
	}

	@Override
	// return "nochange" by default
	// return "eldertree" when tree age hits 12
	public String onTick() {
		super.onTick();
		
		if (this.age == 12) {
			return "eldertree";
		}
		return "nochange";
	}

	@Override
	public String getRepresentation() {
		return representation;
	}
	
	public int getLumberYield() {
		return lumberYield;
	}
	
}
