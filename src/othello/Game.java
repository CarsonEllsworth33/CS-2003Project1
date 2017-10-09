/**
 * 
 */
package othello;
import java.io.InputStream;
import java.util.Scanner;
/**
 * @author ellsc
 *
 */
public class Game {
	
	private String input;
	private Board board = new Board();
	
	/**
	 * this method allows the user take a string out of stream
	 * @param in
	 * @return a string of what ever is put into the stream
	 */
	public String getStringInput(InputStream in) {
		Scanner scanner = new Scanner(in);
		input = scanner.nextLine();
		return input;
	}
	
	private void twoPlayerGame() {
		Player player1 = new Player(2);
	    Player player2 = new Player(1);
		board.startState();
		System.out.println("Welcome players to the game of Othello");
		System.out.println("Let's play!");
		while(board.getGameOver() == false){
			board.takeTurn(player1);
			board.winCondition();
			if(board.getGameOver() == true) {continue;}//if player 1 quits this will make it so that player 2 doesn't have to go through an entire move to end the game
			board.takeTurn(player2);
			board.winCondition();
			}
		this.endGame();
	}
	
	private void onePlayerGame() {
		Player player1 = new Player(2);
	    Computer player2 = new Computer(1);
		// TODO Auto-generated method stub
		System.out.println("test one player works");
	}
	private void monteCarloSim() {
		Computer player1 = new Computer(2);
	    Computer player2 = new Computer(1);
		// TODO Auto-generated method stub
		System.out.println("test sim works");
	}
	
	
	public void gameModeSelection() {
		System.out.println("Welcome to the game selection menu for othello here are your options (when deciding do not type in the quotes \"\")");
		System.out.println(":type in \"Sim\" to run the Monte Carlo simulator");
		System.out.println(":type in \"Multiplayer\" to run the original two player game");
		System.out.println(":type in \"Singleplayer\" to run the single player game against a computer");
		String getSelection = getStringInput(System.in);
		switch(getSelection) {
		case "Sim":case "sim":
			monteCarloSim();
			break;
		case "multiplayer":case "Multiplayer"://original 2 player game
			twoPlayerGame();
			break;
		case "singleplayer": case "Singleplayer":
			onePlayerGame();
			break;
		default:
			System.out.println("you must have entered in invalid input...\nmake sure you spelled everything correctly please");
			gameModeSelection();
		}//end of switch statement
	}
	
	
	public void endGame() {
			board.Score();
			}
	
	/**
	 * main function to run othello
	 * @param args
	 */
	public static void main(String args[])
	{//start main
		Game othello = new Game();
		othello.gameModeSelection();;
	}//end main
	
	
	
}
