import java.awt.Point;


public class Bear extends MovingEntity {

	private final String representation = "B";
	
	public Bear(Point location) {
		super(location);
		numMoves = 5;
	}

	@Override
	// return "nochange" by default
	// return "lumberjackfound" when bear finds a lumberjack
	public String onTick() {
		super.onTick();
		move();
		move();
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
