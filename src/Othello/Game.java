/**
 * 
 */
package Othello;
import java.io.InputStream;
import java.util.Scanner;
/**
 * @author ellsc
 *
 */
public class Game {
	
	private String input;
	/**
	 * creates an instance of Board to play the game
	 */
	public Game() {
		
		Board board = new Board();
		board.startState();
		board.toString();
		System.out.println("Welcome players to the game of Othello\nPlease enter a name for player 1: ");
		this.getInput(System.in);
		Player player1 = new Player(input, 1);
		System.out.println("Please enter a name for player 2: ");
		this.getInput(System.in);
		Player player2 = new Player(input, 2);
		System.out.println("Let's play Othello!");
		//need win con to move forward
		
		
	}
	/**
	 * this method allows the user take a string out of stream
	 * @param in
	 * @return a string of what ever is put into the stream
	 */
	public String getInput(InputStream in) {
		Scanner scanner = new Scanner(in);
		input = scanner.nextLine();
		return input;
	}

	/**
	 * main function to run othello
	 * @param args
	 */
	public static void main(String args[])
	{//start main
		Game test = new Game();
		
	}//end main
	
	
	
}
