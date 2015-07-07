import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Run the simulator
 */
public class TreeSim extends JPanel {
	
	private static Board board;
	private static int size;
	private final int WIDTH = 16;
	private final static int TICKS = 10;
	
	public static void main(String[] args) throws InterruptedException {
		
		// prompt user for input
		Scanner scan = new Scanner(System.in);
		//int size;
		
		System.out.println("Please enter a size (positive number): ");
		
		while(!scan.hasNextInt()) {
			System.out.println("Please enter a size (positive number): ");
			scan.next();
		}
		
		size = scan.nextInt();
		scan.nextLine();
		
		// initialize GUI
		JFrame window = new JFrame("TreeSim");
		TreeSim treeSim = new TreeSim();
		treeSim.setBackground(new Color(185, 122, 87));
		window.add(treeSim);
		window.pack();
		window.setLocationRelativeTo(null); // center window on screen
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		

		// create board, populate it, and display it
		board = new Board(size);
		board.populateTrees(0.5); // 50% of board will be filled with Trees
		board.populateLumberjacks(0.1); // 10% of board will be filled with Lumberjacks
		board.populateBears(0.02); // 2% of board will be filled with Bears
		
		//System.out.println(board.display());
		
		window.repaint(); // update GUI

		scan.close();
		
		// start month cycle (go for 400 years)
		while (board.getAge() < 4800) {
			board.tick();
			
			Thread.sleep(TICKS);
			//System.out.println(board.display());
			
			// update GUI
			window.repaint();
		}
	
		
	}
	
	// set size of GUI window
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH*(size)+10, WIDTH*(size+1)+10);
	}
	
	// draw the current iteration of the simulation
	private void drawSim(Graphics g, String text, int x, int y) {
        
		g.setFont(new Font("Courier", Font.PLAIN, 12));
        
        String simulationText = "";
       
        boolean firstTime= true;
        for (String line : text.split("\n")) {
        	
        	if (firstTime) {
        		// display the current month
        		g.drawString(line,x,y);
        		firstTime = false;
        	}
        	else
        		// add the rest of the simulation text
        		simulationText += line;
        }
        
        // the current selected symbol for the entity
        String symbol = "";
        
        // load the entity images
        BufferedImage seedImg = null;
    	BufferedImage treeImg = null;
    	BufferedImage elderTreeImg = null;
    	BufferedImage lumberjackImg = null;
    	BufferedImage bearImg = null;
        try {
        	seedImg = ImageIO.read(new File("src/seed.png"));
        	treeImg = ImageIO.read(new File("src/tree-75-16.gif"));
        	elderTreeImg = ImageIO.read(new File("src/tree-49-16.gif"));
        	lumberjackImg = ImageIO.read(new File("src/axe.gif"));
        	bearImg = ImageIO.read(new File("src/bear-2-16.png"));
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
        
        // iterate through all the entities and draw their respective icons
        for (int i= 0; i < size; i++) {
        	for (int j = 0; j < size; j++) {
        		
        		symbol = Character.toString(simulationText.charAt(j+i*size));
        		switch (symbol) {
        		case ".":
        			g.drawImage(seedImg, x + WIDTH * (j % size), y + WIDTH * (i % (size)), null);
        			break;
        		case "L":
        			g.drawImage(lumberjackImg, x + WIDTH * (j % size), y + WIDTH * (i % (size)), null);
        			break;
        		case "B":
        			g.drawImage(bearImg, x + WIDTH * (j % size), y + WIDTH * (i % (size)), null);
        			break;
        		case "^":
        			g.drawImage(treeImg, x + WIDTH * (j % size), y + WIDTH * (i % (size)), null);
        			break;
        		case "$":
        			g.drawImage(elderTreeImg, x + WIDTH * (j % size), y + WIDTH * (i % (size)), null);
        			break;
        		default:
        			break;
        		}
        	}
        }
    }
	
	// return the simulation board
	public static Board getBoard() {
		return board;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawSim(g, getBoard().display(), 10, 10);
	}
	
}
