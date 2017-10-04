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
	private Board board = new Board();
	private Player player1 = new Player(2);
	private Player player2 = new Player(1);
	/**
	 * creates an instance of Board to play the game
	 */
	public Game() {
		
		
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
	public void StartGame() {
		board.startState();
		System.out.println("Welcome players to the game of Othello");
		System.out.println("Let's play!");
		while(board.getGameOver() == false){
			board.takeTurn(player1);
			board.winCondition();
			if(board.getGameOver() == true) continue;//if player 1 quits this will make it so that player 2 doesn't have to go through an entire move to end the game
			board.takeTurn(player2);
			board.winCondition();
			//break;//test win condition
		}
		this.endGame();
		
	}
	public void endGame() {
		if(board.Score(player1)>board.Score(player2))
		{
			System.out.println("Congragulations Player 1 you win!!!");
			System.out.println("You beat Player 2 by " + (board.Score(player1) - board.Score(player2) + " points"));
			System.out.println("you are definitly a much better player");
		}
		else if(board.Score(player2)>board.Score(player1))
		{
			System.out.println("Congragulations Player 2 you win!!!");
			System.out.println("You beat Player 1 by " + (board.Score(player2) - board.Score(player1) + " points"));
			System.out.println("you are definitly a much better player");
		}
		else {
			System.out.println("You two tied!!! no thats not acceptable PLAY AGAIN");
			Game Hellthello = new Game();
		}
	
	}
	
	/**
	 * main function to run othello
	 * @param args
	 */
	public static void main(String args[])
	{//start main
		Game othello = new Game();
		othello.StartGame();
	}//end main
	
	
	
}
