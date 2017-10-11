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
	private int intOutput =0;
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
	    Computer player2 = new Computer(1,board);
	    board.startState();
		System.out.println("test one player works");
	}
	
	private void monteCarloSim() {
		int count = 0;
		Computer player1 = new Computer(2,board);
	    Computer player2 = new Computer(1,board);
	    board.startState();
	    System.out.println("Welcome to the Monte Carlo Simulator... \nPlease input a valid positive int for the desired amount of test results\nAmmount of runs: ");
	    for(int i = getIntInput(System.in);i>0;i--) {
	    player1.turn(board.getBoard());
	    player2.turn(board.getBoard());
	    count++;
	    }
	    System.out.println("the count is: "+ count);
		
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
	
	
	
	public int getIntInput(InputStream in) {
		Scanner scanner = new Scanner(in);
		if(scanner.hasNextInt()) {
			intOutput = scanner.nextInt();
			if(intOutput > 0)return intOutput;
			else {System.out.println("you cannot run zero or negative ammounts of tests\nplease put in a positive int");getIntInput(in);}
		}
		else if((scanner.next() instanceof String)) {
			System.out.println("trace 0");
			System.out.println("this value is not a positive int\nPlease input an acceptable value and try again");
			getIntInput(in);
		}
		else {
			System.out.println("trace 1");
			System.out.println("this value is not a positive int\nPlease input an acceptable value and try again");
			getIntInput(in);
		}
		return intOutput;
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
