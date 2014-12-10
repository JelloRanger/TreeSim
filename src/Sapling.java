import java.awt.Point;


public class Sapling extends Entity {

	private final String representation = ".";
	
	// constructor
	public Sapling(Point location) {
		super(location);
	}
	
	@Override
	// return "nochange" by default
	// return "tree" when age hits 12
	public String onTick() {
		super.onTick();
		
		if (age == 12) {
			return "tree";
		}
		return "nochange";
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
