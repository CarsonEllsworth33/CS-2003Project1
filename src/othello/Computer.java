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
		findBounds(gameBoard);
		Random rand = new Random();
		int index = rand.nextInt(list.size());
		System.out.printf("%s player chose the row %d and column %d%n",this.getPlayerColor(),list.get(index).getRow(),list.get(index).getColumn());
		
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
		board.toString();
		System.out.println(list.toString());
	}
	/**
	 * this function finds the empty spots around an already occupied disc
	 * @param gameBoard
	 * @return
	 */
	private void manageNextTo(Disc[][] gameBoard,int row,int column) {
		if(this.nextTo(row, column, this, 0, 1,gameBoard)) {/*checking to the right*/
			list.add(gameBoard[row+0][column+1]);
		}
		if(this.nextTo(row, column, this, 0, -1,gameBoard)){/*checking to the left*/
			list.add(gameBoard[row+0][column-1]);
		}
		if(this.nextTo(row, column, this, 1, 0,gameBoard)) {/*checking down*/
			list.add(gameBoard[row+1][column+0]);
		}
		if(this.nextTo(row, column, this, -1, 0,gameBoard)){/*checking up*/
			list.add(gameBoard[row-1][column+0]);
		}
		if(this.nextTo(row, column, this, 1, 1,gameBoard)) {/*checking down right*/
			list.add(gameBoard[row+1][column+1]);
		}
		if(this.nextTo(row, column, this, 1, -1,gameBoard)){/*checking down left*/
			list.add(gameBoard[row+1][column-1]);
		}
		if(this.nextTo(row, column, this, -1, 1,gameBoard)){/*checking up right*/
			list.add(gameBoard[row-1][column+1]);
		}
		if(this.nextTo(row, column, this, -1, -1,gameBoard)) {/*checking up left*/
			list.add(gameBoard[row-1][column-1]);
		}
	}
	/**
	 * checks the surrounding discs of selected piece called after the piece has been determined
	 * this function is a replacement for valid move
	 * @param currRow
	 * @param currColumn
	 */
	public boolean nextTo(int row, int column, Computer p, int dRow, int dColumn, Disc[][] gameBoard) {
		row+=dRow;
		column+= dColumn;
		if(!board.isInBounds(row, column))return false;//checking board bounds
		for(;board.isInBounds(row,column); row+=dRow,column+=dColumn) {
			if(gameBoard[row][column].getState() == 0)return true;
			if(gameBoard[row][column].getState() == p.getId())return false;//checks to see if it is empty
		}
		return false;
	}
	
	public int getRowMove() {
		return Arow;
	}
	public int getColumnMove() {
		return Acolumn;
	}
}
	