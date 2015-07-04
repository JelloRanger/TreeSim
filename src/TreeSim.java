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
	
	static Board board;
	static int size;
	
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
		
		JFrame window = new JFrame("TreeSim");
		window.add(new TreeSim());
		window.pack();
		window.setLocationRelativeTo(null); // center window on screen
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Dimension d = new Dimension(10* (size+1), 10 * size);
		//window.setPreferredSize(d);
		window.setVisible(true);
		

		// create board, populate it, and display it
		board = new Board(size);
		board.populateTrees(0.5); // 50% of board will be filled with Trees
		board.populateLumberjacks(0.1); // 10% of board will be filled with Lumberjacks
		board.populateBears(0.02); // 2% of board will be filled with Bears
		System.out.println(board.display());
		window.repaint();

		scan.close();
		
		// start month cycle (go for 400 years)
		while (board.getAge() < 4800) {
			board.tick();
			
			Thread.sleep(25);
			System.out.println(board.display());
			
			window.repaint();
		}
	
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(16*(size)+10, 16*(size+1)+10);
	}
	
	private void drawString(Graphics g, String text, int x, int y) {
        g.setFont(new Font("Courier", Font.PLAIN, 12));
		/*for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());*/
        
        int l = g.getFontMetrics().getHeight();
        
        
        String newString = "";
        String letter = "";
        boolean firstTime= true;
        for (String line : text.split("\n")) {
        	
        	if (firstTime) {
        		g.drawString(line,x,y);
        		firstTime = false;
        	}
        	else
        		newString += line;
        }
        
        for (int i= 0; i < size; i++) {
        	for (int j = 0; j < size; j++) {
        		letter = Character.toString(newString.charAt(j+i*size));
        		
        		BufferedImage image = null;
        		
        		switch (letter) {
        		case ".":
        			//g.setColor(Color.green);
        			//g.drawString(letter, x + 7 * (j % size), y+l+ l * (i % (size)));
        			try {
						image = ImageIO.read(new File("src/seed.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			g.drawImage(image, x + 16 * (j % size), y+ 16 * (i % (size)), null);
        			break;
        		case "L":
        			//g.setColor(Color.darkGray);
        			//g.drawString(letter, x + 7 * (j % size), y+l+ l * (i % (size)));
        			
					try {
						image = ImageIO.read(new File("src/axe.gif"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			g.drawImage(image, x + 16 * (j % size), y+ 16 * (i % (size)), null);
        			break;
        		case "B":
        			//g.setColor(Color.red);
        			//g.drawString(letter, x + 7 * (j % size), y+l+ l * (i % (size)));
					try {
						image = ImageIO.read(new File("src/bear-2-16.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			g.drawImage(image, x + 16 * (j % size), y+ 16 * (i % (size)), null);
        			break;
        		case "^":
        			//g.setColor(new Color(0, 219, 40));
        			//g.fillRect(x + 7 * (j % size), y+ l * (i % (size)), 7, 17);
        			try {
						image = ImageIO.read(new File("src/tree-75-16.gif"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			g.drawImage(image, x + 16 * (j % size), y+ 16 * (i % (size)), null);
        			break;
        		case "$":
        			//g.setColor(new Color(13, 119, 0));
        			//g.fillRect(x + 7 * (j % size), y+ l * (i % (size)), 7, 17);
        			try {
						image = ImageIO.read(new File("src/tree-49-16.gif"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			g.drawImage(image, x + 16 * (j % size), y+ 16 * (i % (size)), null);
        			break;
        		default:
        			g.setColor(Color.black);
        			g.drawString(letter, x + 16 * (j % size), y+ 16 * (i % (size)));
        			break;
        		}
        	}
        }
    }
	
	public static Board getBoard() {
		return board;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawString(g, getBoard().display(), 10, 10);
	}
	
}
