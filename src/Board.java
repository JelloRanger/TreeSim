import java.awt.Point;
import java.util.ArrayList;



public class Board {

	private int size; // size of board (n x n)
	private int age; // number of ticks for the board
	private Entity[][] entities; // two dimensional list of all entities on the board
	
	public Board(int size) {
		this.size = size;
		this.age = 0;
		entities = new Entity[size][size];
	}
	
	// populate the board
	public void populate() { 
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Entity e = new Entity(new Point(i,j));
				entities[i][j] = e;
			}
		}
	}
	
	// display the board
	public String display() {
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buffer.append(entities[i][j].getRepresentation());
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	
	// return dimensions of board
	public int getSize() {
		return this.size;
	}
	
}
