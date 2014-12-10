import java.util.Scanner;

/*
 * Run the simulator
 */
public class TreeSim {
	
	public static void main(String[] args) {
		
		// prompt user for input
		Scanner scan = new Scanner(System.in);
		int size;
		
		System.out.println("Please enter a size (positive number): ");
		
		while(!scan.hasNextInt()) {
			System.out.println("Please enter a size (positive number): ");
			scan.next();
		}
		
		size = scan.nextInt();
		scan.nextLine();
		

		// create board, populate it, and display it
		Board board = new Board(size);
		board.populateTrees(0.5); // 50% of board will be filled with Trees
		board.populateLumberjacks(0.1); // 10% of board will be filled with Lumberjacks
		board.populateBears(0.02); // 2% of board will be filled with Bears
		System.out.println(board.display());
		
		// start month cycle
		while (scan.hasNextLine()) {
			board.tick();
			System.out.println(board.display());
			scan.nextLine();
		}
		scan.close();
	}
	
}
