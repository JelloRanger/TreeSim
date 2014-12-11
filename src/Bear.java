import java.awt.Point;


public class Bear extends MovingEntity {

	private final String representation = "B";
	
	public Bear(Point location, int boardAge) {
		super(location, boardAge);
		numMoves = 5;
	}

	@Override
	// return "bear" by default
	// return "bearfoundlumberjack" when bear finds a lumberjack
	public String onTick(Board board) {
		super.onTick(board);
		/*move();
		move();
		move();
		move();
		move();*/
		return "bear";
	}
	
	@Override
	protected boolean move(Board board) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
