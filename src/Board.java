import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


/*
 * Represents the two dimensional plane that the simulation 
 * will take place on (standard square grid)
 */
public class Board {

	private int size; // size of board (n x n)
	private int age; // number of ticks for the board
	private Entity[][] entities; // two dimensional list of all entities on the board
	private double spotsRemaining; // amount of board unfilled by entities
	
	private int lumberCollectedThisYear;
	
	// representation for empty space
	private final String emptyRepresentation = " ";
	
	public Board(int size) {
		this.size = size;
		this.age = 0;
		this.entities = new Entity[size][size];
		this.spotsRemaining = size * size;
		this.lumberCollectedThisYear = 0;
		
		// populate board with EmptyEntities
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				entities[i][j] = new EmptyEntity(new Point(i,j));
			}
		}
	}
	
	// populate the board with Trees
	public boolean populateTrees(double rate) {
		
		// create list of all positions in board
		ArrayList<Point> positions = new ArrayList<Point>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				 
				// make sure space is empty
				if (entities[i][j].getRepresentation().equals(" ")) {
					positions.add(new Point(i,j));
				}
			}
		}
		
		// shuffle list (to simulate randomness, avoiding duplicates)
		// initialize a count variable to keep track of where we are at in the shuffled list
		Collections.shuffle(positions);
		int count = 0;
		
		// populate board with lumberjacks (according to initial tree pop rate)
		while (count < rate * size * size && spotsRemaining > 0) {
			entities[positions.get(count).x][positions.get(count).y] = new Tree(new Point(positions.get(count).x, positions.get(count).y));
			count++;
			spotsRemaining--;
		}
		
		return true;
	}
	
	// populate the board with Lumberjacks
	public boolean populateLumberjacks(double rate) {
			
		// create list of all positions in board
		ArrayList<Point> positions = new ArrayList<Point>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				 
				// make sure space is empty
				if (entities[i][j].getRepresentation().equals(" ")) {
					positions.add(new Point(i,j));
				}
			}
		}
		
		// shuffle list (to simulate randomness, avoiding duplicates)
		// initialize a count variable to keep track of where we are at in the shuffled list
		Collections.shuffle(positions);
		int count = 0;
		
		// populate board with lumberjacks (according to initial tree pop rate)
		while (count < rate * size * size && spotsRemaining > 0) {
			entities[positions.get(count).x][positions.get(count).y] = new Lumberjack(new Point(positions.get(count).x, positions.get(count).y), age);
			count++;
			spotsRemaining--;
		}
		
		return true;
	}
	
	// populate the board with Lumberjacks
	public boolean populateBears(double rate) {
			
		// create list of all positions in board
		ArrayList<Point> positions = new ArrayList<Point>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				 
				// make sure space is empty
				if (entities[i][j].getRepresentation().equals(" ")) {
					positions.add(new Point(i,j));
				}
			}
		}
		
		// shuffle list (to simulate randomness, avoiding duplicates)
		// initialize a count variable to keep track of where we are at in the shuffled list
		Collections.shuffle(positions);
		int count = 0;
		
		// populate board with lumberjacks (according to initial tree pop rate)
		while (count < rate * size * size && spotsRemaining > 0) {
			entities[positions.get(count).x][positions.get(count).y] = new Bear(new Point(positions.get(count).x, positions.get(count).y), age);
			count++;
			spotsRemaining--;
		}
		
		return true;
	}
	
	// display the board
	public String display() {
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Month " + age + ":\n"); // display age of simulation 
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				
				// add a space if selection is empty
				if (entities[i][j] == null) {
					buffer.append(emptyRepresentation);
				}
				
				// otherwise add the representation String for the entity
				else {
					buffer.append(entities[i][j].getRepresentation());
				}
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	
	// return adjacent entities (diagonal included)
	public ArrayList<Entity> getAdjacentEntities(Point p) {
		ArrayList<Entity> adjEntities = new ArrayList<Entity>();
		
		// add adjacent entities 
		// (anywhere from 3 to 8 adjacent entities due to placement on board)
		if (p.x > 0 && p.y > 0)
			adjEntities.add(entities[p.x - 1][p.y - 1]);
		if (p.x > 0)
			adjEntities.add(entities[p.x - 1][p.y]);
		if (p.y > 0)
			adjEntities.add(entities[p.x][p.y - 1]);
		if (p.x < size - 1 && p.y < size - 1)
			adjEntities.add(entities[p.x + 1][p.y + 1]);
		if (p.x < size - 1)
			adjEntities.add(entities[p.x + 1][p.y]);
		if (p.y < size - 1)
			adjEntities.add(entities[p.x][p.y + 1]);
		if (p.x < size - 1 && p.y > 0)
			adjEntities.add(entities[p.x + 1][p.y - 1]);
		if (p.x > 0 && p.y < size - 1)
			adjEntities.add(entities[p.x - 1][p.y + 1]);
		
		return adjEntities;
	}
	
	
	// move a moving entity to a given point and replace its old position with EmptyEntity
	// return false if out of bounds
	public boolean moveEntity(MovingEntity me, Point p) {
		
		// boundary check
		if (p.x >= size || p.y >= size || p.x < 0 || p.y < 0) {
			return false;
		}
		
		// replace old entity location with EmptyEntity
		entities[me.getLocation().x][me.getLocation().y] = new EmptyEntity(me.getLocation());
		
		// check if we have an additional empty space
		// (other entity aside from EmptyEntity was 'eaten')
		if (!entities[p.x][p.y].getRepresentation().equals(" ")) {
			spotsRemaining++; // increment spotsRemaining, as we now have a new empty space
		}
		
		// move entity
		entities[p.x][p.y] = me;
		me.setLocation(p);
		
		return true;
	}
	
	// tick all the entities in the board
	public void tick() {
		
		// increment board's age
		age++;
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (entities[i][j] != null) {
					
					String event = "";
					
					// check for event occurring
					// (only tick entity if it's non-moving or hasn't moved this cycle yet)
					if (entities[i][j].canMove(age)) {
						event = entities[i][j].onTick(this);
					}
						
					// tree grows into an elder tree
					if (event.equals("treetoeldertree")) {
						entities[i][j] = new ElderTree(new Point(i,j), entities[i][j].getAge());
					}
					
					// sapling grows into a tree
					else if (event.equals("saplingtotree")) {
						entities[i][j] = new Tree(new Point(i,j), entities[i][j].getAge());
					}
					
					// entity is a tree (saplingtotree event included, because it is now a tree)
					if (event.equals("tree") || event.equals("saplingtotree") || event.equals("eldertree") || event.equals("treetoeldertree")) {
						
						// check if tree has a chance to spawn a sapling
						if (Math.random() <= ((Tree) entities[i][j]).getSaplingSpawnRate()) {
							
							
							saplingSpawnEvent(i,j);
						}
					}
				}
			}
		}
		
		// handle logic that occurs every year
		if (age % 12 == 0) {
			
			// if more lumber was collected compared to number of lumberjacks, hire more
			ArrayList<Entity> lumberjacks = getLumberjacks();
			int numLumberjacks = lumberjacks.size();
			if (lumberCollectedThisYear >= numLumberjacks) {
				populateLumberjacks(Math.ceil((lumberCollectedThisYear - numLumberjacks) / 10.0) * (1.0 / (double) (size*size)) );
			}
			
			// remove a random lumberjack if less lumber was collected compared to # of lumberjacks
			// (never reduce to 0 though)
			else if (numLumberjacks > 1) {
				
				// remove 1 random lumberjack
				Collections.shuffle(lumberjacks); // shuffle list to get randomness
				entities[lumberjacks.get(0).getLocation().x][lumberjacks.get(0).getLocation().y] = new EmptyEntity(lumberjacks.get(0).getLocation());
				spotsRemaining++; // one more spot is now available
			}
			
			lumberCollectedThisYear = 0;// clear lumber collected per year
		}
	}
	
	public void saplingSpawnEvent(int i, int j) {
		// find a random empty location from adjacent spots
		ArrayList<Entity> adjSpots = getAdjacentEntities(new Point(i,j));
		Collections.shuffle(adjSpots); // shuffle list of adjacent spots
		
		// pick first spot in list that's empty, and create a sapling there
		// break when this occurs (only one sapling per tree when spawn event occurs)
		for (int b = 0; b < adjSpots.size(); b++) {
			
			// if empty, spawn a sapling there
			if (adjSpots.get(b).getRepresentation().equals(" ")) {
				entities[adjSpots.get(b).getLocation().x][adjSpots.get(b).getLocation().y] = new Sapling(adjSpots.get(b).getLocation());
				spotsRemaining--;
				break; // break, sapling has been created
			}
		}
	}
	
	// return entity at given point
	public Entity getEntity(Point p) {
		
		// boundary check
		if (p.x >= size || p.y >= size || p.x < 0 || p.y < 0) {
			return null;
		}
		
		return entities[p.x][p.y];
	}
	
	// return list of Lumberjack entities on the board
	public ArrayList<Entity> getLumberjacks() {
		
		// store result here
		ArrayList<Entity> result = new ArrayList<Entity>();
		
		// iterate through board counting lumberjacks
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (entities[i][j].getRepresentation().equals("L")) {
					result.add(entities[i][j]);
				}
			}
		}
		
		return result;
	}
	
	// increment lumber collected
	public void addLumberCollected(int amount) {
		lumberCollectedThisYear += amount;
	}
	
	// return dimensions of board
	public int getSize() {
		return this.size;
	}
	
}
