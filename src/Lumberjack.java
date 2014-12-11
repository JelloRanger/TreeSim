import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


public class Lumberjack extends MovingEntity {

	private final String representation = "L";
	
	private int lumberCollected;
	
	public Lumberjack(Point location, int boardAge) {
		super(location, boardAge);
		numMoves = 3;
		lumberCollected = 0;
	}
	
	@Override
	// return "lumberjack" by default
	// return "lumberjackfoundtree" when lumberjack encounters a tree
	public String onTick(Board board) {
		super.onTick(board);
		
		// move multiple times per tick
		for (int i = 0; i < numMoves; i++) {
			
			// if tree is cut, stop moving for the month
			if (!move(board)) {
				break;
			}
		}
		
		return "lumberjack";
	}
	
	@Override
	// return true if lumberjack can continue moving
	// return false if tree/elder tree encountered and movement stops
	protected boolean move(Board board) {
		
		// grab adjacent entities
		ArrayList<Entity> adjSpots = board.getAdjacentEntities(location);
		Collections.shuffle(adjSpots); // shuffle adj list to simulate randomness
		
		// select first open slot to move to
		// (can't move to sapling, bear, or other lumberjack spots
		for (int i = 0; i < adjSpots.size(); i++) {
			Entity nextSpot = adjSpots.get(i);
			
			// next spot is empty space, move there
			if (nextSpot.getRepresentation().equals(" ")) {
				board.moveEntity(this, nextSpot.getLocation());
				return true; // continue moving if moves left
			}
			
			// next spot is tree, cut it down
			else if (nextSpot.getRepresentation().equals("^") || nextSpot.getRepresentation().equals("$")) {
				
				// collect lumber
				lumberCollected += ((Tree) nextSpot).getLumberYield();
				
				// move there
				board.moveEntity(this, nextSpot.getLocation());
				return false; // stop moving
			}
		}
		
		// no tree or empty spaces available, can't move no matter how many moves we have left
		return false;
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
