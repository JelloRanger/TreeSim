import java.awt.Point;
import java.util.ArrayList;

/*
 * Represents a tree that can spawn Saplings
 */
public class Tree extends Entity {

	// chance tree can spawn a sapling
	private final double saplingSpawnRate = 0.1;
	private static final String representation = "^";
	
	public Tree(Point location) {
		super(location);
	}

	@Override
	public void onTick() {
		super.onTick();
	}

	@Override
	public String getRepresentation() {
		return representation;
	}
	
}
