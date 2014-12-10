import java.awt.Point;


public class Sapling extends Entity {

	private final String representation = ".";
	
	// constructor
	public Sapling(Point location) {
		super(location);
	}
	
	@Override
	// return "sapling" by default
	// return "saplingtotree" when age hits 12
	public String onTick() {
		super.onTick();
		
		if (age == 12) {
			return "saplingtotree";
		}
		return "sapling";
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
