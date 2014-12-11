import java.awt.Point;

/*
 * Represents an Entity that can move
 */
public abstract class MovingEntity extends Entity {

	protected int numMoves; // number of moves the entity can take per tick
	
	protected int monthMoved; // keep track of most recent month that we moved
							  // (to prevent multiple move calls as we iterate over board)
	
	protected static final String representation = "M";
	
	public MovingEntity(Point location, int boardAge) {
		super(location);
		numMoves = 0;
		monthMoved = boardAge;
	}
	
	@Override
	public String onTick(Board board) {
		super.onTick(board);
		monthMoved += 1;
		
		// move multiple times per tick
		for (int i = 0; i < numMoves; i++) {
			
			// if tree is cut, stop moving for the month
			if (!move(board)) {
				break;
			}
		}
		
		return "movingentity";
	}
	
	@Override
	// return true if we haven't moved yet this month
	public boolean canMove(int boardAge) {
		if (boardAge == monthMoved) {
			return false;
		}
		
		return true;
	}
	
	// logic to handle an entity moving once
	// (possible to move multiple times per tick)
	protected abstract boolean move(Board board);

}
