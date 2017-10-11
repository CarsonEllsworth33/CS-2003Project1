/*
 * What if you change the list data type to another type to fit different but more relative data in such as 
 * board[row][column].getState or maybe make a get location if possible inside either the board or disc class
 * either way i need to be able to pull up the exact coordinates of the available spaces then put those coordinates in a list of some sort
 * 
 */


package othello;
import java.util.*;
public class Computer extends Player {
	private Board board;
	private int Arow,Acolumn;
	private List<Disc> list = new LinkedList<Disc>();
	public Computer(int id, Board board) {
		super(id);
		this.board = board;
	}
	public void turn(Disc[][] gameBoard) {
		Random rand = new Random();
		//this.findBounds(board.getBoard());
		Arow = rand.nextInt(8);
		Acolumn = rand.nextInt(8);
		System.out.printf("%s player chose the row %d and column %d%n",this.getPlayerColor(),this.getRowMove(),this.getColumnMove());
		this.findBounds(gameBoard);
	}
	
	/**
	 * this function is meant to find the available spots to play 
	 */
	private void findBounds(Disc[][] gameBoard) {
		for(int row = 0; board.isInBounds(row);row++) {
			for(int column = 0; board.isInBounds(column); column++) {
				if(gameBoard[row][column].getState() != 0) {
					if(gameBoard[row][column].getState() != this.getId()) {
						manageNextTo(gameBoard,row,column);
						
					}
				}
			}
		}
		System.out.println(list.toString());
		board.toString();
	}
	/**
	 * this function finds the empty spots around an already occupied disc
	 * @param gameBoard
	 * @return
	 */
	private void manageNextTo(Disc[][] gameBoard,int row,int column) {
		if(board.validMove(row, column, this, 0, 1)) {/*checking to the right*/
			list.add(gameBoard[row+0][column+1]);
		}
		if(board.validMove(row, column, this, 0, -1)){/*checking to the left*/
			list.add(gameBoard[row+0][column-1]);
		}
		if(board.validMove(row, column, this, 1, 0)) {/*checking down*/
			list.add(gameBoard[row+1][column+0]);
		}
		if(board.validMove(row, column, this, -1, 0)){/*checking up*/
			list.add(gameBoard[row-1][column+0]);
		}
		if(board.validMove(row, column, this, 1, 1)) {/*checking down right*/
			list.add(gameBoard[row+1][column+1]);
		}
		if(board.validMove(row, column, this, 1, -1)){/*checking down left*/
			list.add(gameBoard[row+1][column-1]);
		}
		if(board.validMove(row, column, this, -1, 1)){/*checking up right*/
			list.add(gameBoard[row-1][column+1]);
		}
		if(board.validMove(row, column, this, -1, -1)) {/*checking up left*/
			list.add(gameBoard[row-1][column-1]);
		}
	}
	
	public int getRowMove() {
		return Arow;
	}
	public int getColumnMove() {
		return Acolumn;
	}
}
	