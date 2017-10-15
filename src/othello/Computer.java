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
	/**
	 * this is the main method for running the computer class which allows it to do all of its necessary functions to make a move
	 * @param gameBoard
	 */
	public void turn(Disc[][] gameBoard) {
		findBounds(gameBoard);
		this.movesLeft();
		if(!list.isEmpty()) {
			Random rand = new Random();
			//chooses a pseudo-random neutral disc from the list as its spot to play
			int index = rand.nextInt(list.size());
			//gets the values of the row and column from index
			Arow = list.get(index).getRow();
			Acolumn = list.get(index).getColumn();
			//System.out.printf("%s player chose the row %d and column %d%n",this.getPlayerColor(),Arow,Acolumn);
			board.checkMoves(this);
		}
		list.clear();
	}
	
	/**
	 * this function is meant to find the available spots to play 
	 */
	private void findBounds(Disc[][] gameBoard) {
		for(int row = 0; board.isInBounds(row);row++) {
			for(int column = 0; board.isInBounds(column); column++) {
				if(gameBoard[row][column].getState() != 0) {
					if(gameBoard[row][column].getState() == this.otherPlayer()) {
						manageNextTo(gameBoard,row,column);
						
					}
				}
			}
		}
	}
	/**
	 * this function finds the empty spots around an already occupied disc
	 * @param gameBoard
	 */
	private void manageNextTo(Disc[][] gameBoard,int row,int column) {
		int zero = 0;
		int one = 1;
		if(this.nextTo(row, column, this, zero, one,gameBoard)) {//checking to the right
			if(board.validateMove(row, column, this, zero, -one))list.add(gameBoard[row+zero][column+one]);
		}
		if(this.nextTo(row, column, this, zero, -one,gameBoard)){//checking to the left
			if(board.validateMove(row, column, this, zero, one))list.add(gameBoard[row+zero][column-one]);
		}
		if(this.nextTo(row, column, this, one, zero,gameBoard)) {//checking down
			if(board.validateMove(row, column, this, -one, zero))list.add(gameBoard[row+one][column+zero]);
		}
		if(this.nextTo(row, column, this, -one, zero,gameBoard)){//checking up
			if(board.validateMove(row, column, this, one, zero))list.add(gameBoard[row-one][column+zero]);
		}
		if(this.nextTo(row, column, this, one, one,gameBoard)) {//checking down right
			if(board.validateMove(row, column, this, -one, -one))list.add(gameBoard[row+one][column+one]);
		}
		if(this.nextTo(row, column, this, one, -one,gameBoard)){//checking down left
			if(board.validateMove(row, column, this, -one, one))list.add(gameBoard[row+one][column-one]);
		}
		if(this.nextTo(row, column, this, -one, one,gameBoard)){//checking up right
			if(board.validateMove(row, column, this, one, -one))list.add(gameBoard[row-one][column+one]);
		}
		if(this.nextTo(row, column, this, -one, -one,gameBoard)) {//checking up left
			if(board.validateMove(row, column, this, one, one))list.add(gameBoard[row-one][column-one]);
		}
	}
	/**
	 * checks all board spots containing a disc to look for empty spaces around it returns true is a direction contains an empty space
	 * @param row
	 * @param column
	 * @param p
	 * @param dRow
	 * @param dColumn
	 * @param gameBoard
	 * @return boolean
	 */
	public boolean nextTo(int row, int column, Computer p, int dRow, int dColumn, Disc[][] gameBoard) {
		row+=dRow;
		column+= dColumn;
		if(!board.isInBounds(row, column))return false;//checking board bounds
		if(gameBoard[row][column].getState() == 0)return true;//checks to see if it is neutral
		return false;
	}
	/**
	 * returns the value of the row that the computer has chosen
	 * @return Integer
	 */
	public int getRowMove() {
		return Arow;
	}
	/**
	 * returns the value of the column that the computer has chosen
	 * @return Integer
	 */
	public int getColumnMove() {
		return Acolumn;
	}
	/**
	 * checks to see if there is any available moves left and if there is not then the game ends
	 */
	public void movesLeft() {
		if(list.size() == 0)board.gameOver();;
	}
}
	