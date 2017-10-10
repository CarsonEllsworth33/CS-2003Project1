package othello;
public class Board {
	private final int BOARD_WIDTH = 8;
	private final int BOARD_HEIGHT = 8;
	private int flippedDisc = 0;
	private int blackScore = 0;
	private int whiteScore = 0;
	private boolean gameOver = false;
	private Disc[][] board = new Disc[BOARD_HEIGHT][BOARD_WIDTH];
	/**
	 * so far it only prints out the board later it will populate with initial discs
	 */
	public Board()
	{//start board
	this.populate();
	}//start board
	
	
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
		String cell = "_%s_|"; 
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			System.out.print(row + 1 +"|");
			for(int column = 0; column < BOARD_WIDTH; column++) {
				System.out.printf(cell,board[row][column].getCurrentColor());
			}//end of column for loop
			System.out.println();
		}//end of row for loop	
		return board.toString();
	}
	
	
	
	/**
	 * makes the board aware that a player passed by reference is going to claim a neutral disc
	 * @param p
	 */
	public void takeTurn(Player p) {
		this.updateBoard();
		p.turn(System.in);
		if(p.getPassCon() == true) {
			System.out.println();//makes new line to 
			return;
		}
		if(p.getQuitCon() == true) {
			this.quitGame(p);
			this.gameOver = true;
			//this.updateBoard();
			return;
		}
		flippedDisc = 0;//resets the value of flippedDisc to see if any discs are claimed as player
		if(this.checkMoves(p) == true) {
			return;
		}
		else {
		this.takeTurn(p);
		}
		
	}
	
	private boolean checkMoves(Player p) {
		if(board[p.getRowMove()][p.getColumnMove()].getState() != 0)return false;
		if(board[p.getRowMove()][p.getColumnMove()].getState() == p.getId())return false;
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, 0, 1)) {//checking to the right
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, 0, 1);}
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, 0, -1)){//checking to the left
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, 0, -1);}
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, 1, 0)) {//checking down
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, 1, 0);}
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, -1, 0)){//checking up
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, -1, 0);}
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, 1, 1)) {//checking down right
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, 1, 1);}
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, 1, -1)){//checking down left
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, 1, -1);}
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, -1, 1)){//checking up right
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, -1, 1);}
		if(this.validMove(p.getRowMove(), p.getColumnMove(), p, -1, -1)) {//checking up left
			this.flipDirection(p.getRowMove(), p.getColumnMove(), p, -1, -1);}
		if(flippedDisc == 0) {return false;}//if no change then the move is not a valid one
		return true;
	}
	
	/**
	 * method for quitting the game if a player is done
	 * the board must quit with a reference to a player
	 * @param p
	 */
	public void quitGame(Player p) {
		if(p.getId() == 1) {
			for(int row = 0; row < BOARD_HEIGHT; row++) {
				for(int column = 0; column < BOARD_WIDTH; column++) {
					board[row][column].setState(2);
				}
			}
		}
		else {
			for(int row = 0; row < BOARD_HEIGHT; row++) {
				for(int column = 0; column < BOARD_WIDTH; column++) {
					board[row][column].setState(1);
				}
			}
		}
	}
	
	
	public int flipCheck(int pop,Player p) {
		int playerOneDiscs = 0;
		int playerTwoDiscs = 0;
		
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			for(int column = 0; column < BOARD_WIDTH; column++) {//counts how many spaces are unclaimed
				if(board[row][column].getState() == 1) playerOneDiscs++;
				if(board[row][column].getState() == 2) playerTwoDiscs++;
			}
		}
		if(p.getId() == 1) {
			pop = playerOneDiscs;
		}
		else if(p.getId() == 2) {
			pop = playerTwoDiscs;
		}
		return pop;
	}
	
	/**
	 * reports the score of a player passed by reference
	 * @param p
	 * @return Score of player p
	 */
	public void Score() {
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			for(int column = 0; column < BOARD_WIDTH; column++) {
				if(board[row][column].getCurrentColor() == "B") blackScore++;
				if(board[row][column].getCurrentColor() == "W") whiteScore++;
			}
		}
		System.out.printf("player 1(black) scored %d and player 2(white) scored %d%nthat was a fun game",this.getBlackScore(),this.getWhiteScore());
	}
	/**
	 * this method checks for any neutral discs left in the array
	 */
	public void winCondition(){
		int nuetralSpace = 0;
		int playerOne = 0;
		int playerTwo = 0;
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			for(int column = 0; column < BOARD_WIDTH; column++) {//counts how many spaces are unclaimed
				if(board[row][column].getState() == 0) nuetralSpace++;
				if(board[row][column].getState() == 1) playerOne++;
				if(board[row][column].getState() == 2) playerTwo++;
			}
			
		}
		if(playerOne == 0 ||nuetralSpace == 0||playerTwo == 0 ) {
			this.toString();
			System.out.println("GAME OVER!!! THAT WAS FUN!!!");
			this.gameOver = true;
		}
		
	}

	
	public boolean isInBounds(int num) {
		if(num >= 8 || num <0) return false;
		else return true;
	}
	/**
	 * checks to see whether the player piece is in the board bounds
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isInBounds(int row, int column) {
		if((row < 8 || row >=0 ) && (column < 8 || column >= 0)) return true;
		else return false;
	}
	
	public boolean validMove(int row, int column, Player p, int dRow, int dColumn) {
		System.out.println("spot is empty");
		row+=dRow;
		column+=dColumn;
		if(!this.isInBounds(row, column))return false;//checking board bounds
		for(;isInBounds(row,column); row+=dRow,column+=dColumn) {
			if(board[row][column].getState() == p.getId())return true;
			if(board[row][column].getState() == 0)return false;//checks to see if it is empty
		}
		return false;
	}
	private void flipDirection(int row, int column, Player p, int dRow, int dColumn) {
		board[row][column].setState(p.getId());
		for(;board[row+dRow][column+dColumn].getState() != p.getId(); row+=dRow,column+=dColumn) {
			board[row+dRow][column+dColumn].setState(p.getId());
			flippedDisc++;
		}
	}
	/**
	 * Method that refresh the board after moves
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
	
	public int getBlackScore() {
		return blackScore;
	}

	public int getWhiteScore() {
		return whiteScore;
	}
	
	public int getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}

	public int getBOARD_HEIGHT() {
		return BOARD_HEIGHT;
	}
	public boolean getGameOver() {
		return gameOver;
	}
	
}//end of Board class