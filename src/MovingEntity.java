import java.awt.Point;

/*
 * Represents an Entity that can move
 */
public abstract class MovingEntity extends Entity {

	protected int numMoves; // number of moves the entity can take per tick
	
	protected static final String representation = "M";
	
	public MovingEntity(Point location) {
		super(location);
		numMoves = 0;
	}
	
	@Override
	public String onTick() {
		super.onTick();
		return "movingentity";
	}
	
	// logic to handle an entity moving once
	// (possible to move multiple times per tick)
	protected abstract void move();

}
