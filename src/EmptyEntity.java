import java.awt.Point;


public class EmptyEntity extends Entity {

	protected final String representation = " ";
	
	public EmptyEntity(Point location) {
		super(location);
	}

	@Override
	public String getRepresentation() {
		return representation;
	}

}
