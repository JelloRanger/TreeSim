import java.awt.Point;


public class ElderTree extends Tree {

	protected final double saplingSpawnRate = 0.2; // chance elder tree can spawn a sapling
	protected final int lumberYield = 2; // amount of lumber an elder tree generates when cut
	
	protected final String representation = "$";
	
	// constructor
	public ElderTree(Point location) {
		super(location);
	}
	
	// constructor with age argument
	public ElderTree(Point location, int age) {
		super(location, age);
	}
	
	@Override
	// return "eldertree" always
	public String onTick(Board board) {
		age++;
		return "eldertree";
	}

	@Override
	public String getRepresentation() {
		return representation;
	}
	
	@Override
	public double getSaplingSpawnRate() {
		return saplingSpawnRate;
	}
	
	public int getLumberYield() {
		return lumberYield;
	}
	

}
