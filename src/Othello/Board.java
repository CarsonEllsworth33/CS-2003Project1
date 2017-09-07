package Othello;
import java.util.Arrays;
public class Board {
	private final int BOARD_WIDTH = 8;
	private final int BOARD_HEIGHT = 8;
	private Disc[][] board = new Disc[BOARD_HEIGHT][BOARD_WIDTH];
	/**
	 * so far it only prints out the board later it will populate with initial discs
	 */
	public Board()
	{//start board
	this.populate();
	}//start board
	
	public int getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}

	public int getBOARD_HEIGHT() {
		return BOARD_HEIGHT;
	}
	/**
	 * populates the board with neutral discs
	 */
	public void populate() {
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			
			for(int column = 0; column < BOARD_WIDTH; column++) {
				board[row][column] = new Disc(0);
			}//end of column for loop
			
		}//end of row for loop
	}
	/**
	 * overrides standard toString to print out the board
	 */
	public String toString() {
		System.out.print("   ");
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			System.out.printf("%-4d",i+1);
		}
		System.out.println();
		String noval = "_%s_|"; //value used for when no disc is in cell
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			System.out.print(row + 1 +"|");
			for(int column = 0; column < BOARD_WIDTH; column++) {
				System.out.printf(noval,board[row][column].getCurrentColor());
			}//end of column for loop
			System.out.println();
		}//end of row for loop
		
		return board.toString();
	}
	
	public void takeTurn(Player p) {
		board[p.getRowMove()][p.getColumnMove()].setState(p.getId());
		this.updateBoard();
	}
	
	/**
	 * Method that currently rebuilds the board, but will eventually refresh the board after moves
	 */
	public void updateBoard() {
	this.toString();
	}//end of update board method
	
	
	public void startState() {
		board[3][3] = new Disc(1);
		board[3][4] = new Disc(2);
		board[4][4] = new Disc(1);
		board[4][3] = new Disc(2);
		
	}
	
}//end of Board class
