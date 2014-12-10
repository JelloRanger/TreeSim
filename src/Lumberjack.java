import java.awt.Point;


public class Lumberjack extends MovingEntity {

	private final String representation = "L";
	
	public Lumberjack(Point location) {
		super(location);
		numMoves = 3;
	}
	
	@Override
	// return "lumberjack" by default
	// return "lumberjackfoundtree" when lumberjack encounters a tree
	public String onTick() {
		super.onTick();
		move();
		move();
		move();
		return "lumberjack";
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
