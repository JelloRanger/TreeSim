import java.awt.Point;


public class Lumberjack extends MovingEntity {

	private final String representation = "L";
	
	public Lumberjack(Point location) {
		super(location);
		numMoves = 3;
	}
	
	@Override
	// return "nochange" by default
	// return "treefound" when lumberjack encounters a tree
	public String onTick() {
		super.onTick();
		move();
		move();
		move();
		return "nochange";
	}
	
	@Override
	protected void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
