import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


public class Bear extends MovingEntity {

	protected int lumberjacksEaten;
	
	private final String representation = "B";
	
	public Bear(Point location, int boardAge) {
		super(location, boardAge);
		numMoves = 5;
		lumberjacksEaten = 0;
	}

	@Override
	// return "bear" by default
	// return "bearfoundlumberjack" when bear finds a lumberjack
	public String onTick(Board board) {
		super.onTick(board);

		return "bear";
	}
	
	@Override
	// return true if lumberjack can continue moving
	// return false if tree/elder tree encountered and movement stops
	protected boolean move(Board board) {
		
		// grab adjacent entities
		ArrayList<Entity> adjSpots = board.getAdjacentEntities(location);
		Collections.shuffle(adjSpots); // shuffle adj list to simulate randomness
		
		// select first open slot to move to
		// (can't move to sapling, tree, elder tree, or other bear spots)
		for (int i = 0; i < adjSpots.size(); i++) {
			Entity nextSpot = adjSpots.get(i);
			
			// next spot is empty space, move there
			if (nextSpot.getRepresentation().equals(" ")) {
				board.moveEntity(this, nextSpot.getLocation());
				return true; // continue moving if moves left
			}
			
			// next spot is lumberjack, eat him/her
			else if (nextSpot.getRepresentation().equals("L")) {
				
				// increment lumberjacks eaten
				lumberjacksEaten++;
				
				// move there
				board.moveEntity(this, nextSpot.getLocation());
				return false; // stop moving
			}
		}
		
		// no lumberjacks or empty spaces available, can't move no matter how many moves we have left
		return false;
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
