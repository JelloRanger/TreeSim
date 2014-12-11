import java.awt.Point;


public class Sapling extends Entity {

	private final int ageToBecomeTree = 12;
	
	private final String representation = ".";
	
	// constructor
	public Sapling(Point location) {
		super(location);
	}
	
	@Override
	// return "sapling" by default
	// return "saplingtotree" when age hits 12
	public String onTick(Board board) {
		super.onTick(board);
		
		if (age == ageToBecomeTree) {
			return "saplingtotree";
		}
		return "sapling";
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
