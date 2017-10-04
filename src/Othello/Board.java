package Othello;
public class Board {
	private final int BOARD_WIDTH = 8;
	private final int BOARD_HEIGHT = 8;
	private int previous_pop = 0;
	private int current_pop = 0;
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
			this.updateBoard();
			return;
		}
		if(this.checkTurnValidity(p) == true) {
		board[p.getRowMove()][p.getColumnMove()].setState(p.getId());	
		}
		else {
		this.takeTurn(p);
		}
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
	
	
	
	public boolean checkTurnValidity(Player p) {
		previous_pop = this.flipCheck(previous_pop, p);
		if(board[p.getRowMove()][p.getColumnMove()].getState() != 0) {
			System.out.println("Invalid move\nenter coorinates for a non occupied space");
			return false;
		}
		if(this.nextTo(p) == false) {
			System.out.println("Invalid move\nenter coordinates for a space with discs next to it");
			return false;
		}
		
		current_pop = this.flipCheck(current_pop, p);
		System.out.println(previous_pop);
		System.out.println(current_pop);
		if(current_pop == previous_pop) {
			System.out.println("Please place a disc in a spot that will flip at least one opposing disc");
			return false;
		}
		return true;
	}
	
	
	
	
	/**
	 * reports the pieces next to the player from the possible at maximum 8 spaces surrounding the players disc 
	 * @param p
	 */
	public boolean nextTo(Player p) {
		boolean validMove = false;
		//checking if upper left corner next to player is in play and then if so then it checks to see if it belongs to another player
		if(p.getRowMove()-1 >= 0 && p.getColumnMove()-1 >= 0 && board[p.getRowMove()-1][p.getColumnMove()-1].getState() == p.otherPlayer()) {
			this.checkLRDiagUp(p);
			validMove = true;
		}
		//checking if upper right corner next to player is in play and then if so then it checks to see if it belongs to another player
		if(p.getRowMove()-1 >=0  && p.getColumnMove()+1 < 8 && board[p.getRowMove()-1][p.getColumnMove()+1].getState() == p.otherPlayer()) {
			this.checkRLDiagUp(p);
			validMove = true;
		}
		//checking if cell above the player is in play and then if so then it checks to see if it belongs to another player
		if(p.getRowMove()-1 >= 0 && p.getColumnMove() >= 0 && board[p.getRowMove()-1][p.getColumnMove()].getState() == p.otherPlayer()) {
			this.checkColumnUp(p);
			validMove = true;
		}
		//checking if lower left corner next to player is in play and then if so then it checks to see if it belongs to another player
		if(p.getRowMove()+1 < 8 && p.getColumnMove() -1 >= 0 && board[p.getRowMove()+1][p.getColumnMove()-1].getState() == p.otherPlayer()) {
			this.checkRLDiagDown(p);
			validMove = true;
		}
		//checking if lower right corner next to player is in play and then if so then it checks to see if it belongs to another player
		if(p.getRowMove()+1 < 8 && p.getColumnMove() +1 < 8 && board[p.getRowMove()+1][p.getColumnMove()+1].getState() == p.otherPlayer()) {
			this.checkLRDiagDown(p);
			validMove = true;
		}
		//checking if cell below the player is in play and then if so then it checks to see if it belongs to another player
		if(p.getRowMove()+1 < 8 && p.getColumnMove() >= 0 && board[p.getRowMove()+1][p.getColumnMove()].getState() == p.otherPlayer()) {
			this.checkColumnDown(p);
			validMove = true;
		}
		//checking if cell to the left of the player is in play and then if so then it checks to see if it belongs to another player
		if(p.getColumnMove()-1 >= 0 && board[p.getRowMove()][p.getColumnMove()-1].getState() == p.otherPlayer()) {
			this.checkRowLeft(p);
			validMove = true;
		}
		//checking if cell to the right of the player is in play and then if so then it checks to see if it belongs to another player
		if(p.getColumnMove()+1 < 8 && board[p.getRowMove()][p.getColumnMove()+1].getState() == p.otherPlayer()) {
			this.checkRowRight(p);
			validMove = true;
		}
		return validMove;
	}
	
	
	/**
	 * checks the row the player is in starting to the right of the player for another player disc
	 * @param p
	 */
	public void checkRowRight(Player p) {//start Row
		int counter =  p.getColumnMove() + 1;//check right	
		while( p.getRowMove() < 8 && counter < 8) {//start while loop
			if(board[p.getRowMove()][counter].getState() == p.getId()) {
				this.flipRowRight(p, p.getRowMove(), counter);
			}
			counter++;		
		}
	}
	
	private boolean flipRowRight(Player p , int row, int column) {//start Row
		int counter =  p.getColumnMove() + 1;//check right	
		int flipCount = 0;
		while(counter < column) {//start while loop
			board[row][counter].setState(p.getId());
			counter++;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * checks the row the player is in starting to the left of the player 
	 * @param p
	 */
	public void checkRowLeft(Player p) {
		int Lcounter = p.getColumnMove() - 1;//check left
		while(p.getRowMove() >=0 && Lcounter >=0) {//start while loop
			if(board[p.getRowMove()][Lcounter].getState() == p.getId()) {
				this.flipRowLeft(p, p.getRowMove(), Lcounter);
			}
			Lcounter--;
		}
	}
	
	private boolean flipRowLeft(Player p , int row, int column) {//start Row
		int Lcounter =  p.getColumnMove() - 1;//check right	
		int flipCount = 0;
		while(Lcounter > column) {//start while loop
			board[row][Lcounter].setState(p.getId());
			Lcounter--;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * checks the column the player is in starting below the player 
	 * @param p
	 */
	public void checkColumnDown(Player p) {//start checkColumn
		int counter =  p.getRowMove() + 1;//check right
		while(counter < 8) {
			if(board[counter][p.getColumnMove()].getState() == p.getId()) {
				this.flipColumnDown(p,counter,p.getColumnMove());
			}	
			counter++;	
		}
	}
	
	private boolean flipColumnDown(Player p , int row, int column) {//start Row
		int counter =  p.getRowMove() + 1;//check right	
		int flipCount = 0;
		while(counter < row) {//start while loop
			board[counter][column].setState(p.getId());
			counter++;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * checks the column the player is in starting above the player 
	 * @param p
	 */
	public void checkColumnUp(Player p) {
		int Lcounter = p.getRowMove() - 1;//check l
		while(Lcounter >= 0) {//start while loop
			if(board[Lcounter][p.getColumnMove()].getState() == p.getId()) {
				this.flipColumnUp(p, Lcounter, p.getColumnMove());
			}
			Lcounter--;
		}
	}
	
	private boolean flipColumnUp(Player p , int row, int column) {//start Row
		int counter =  p.getRowMove() - 1;//check right	
		int flipCount = 0;
		while(counter > row) {//start while loop
			board[counter][column].setState(p.getId());
			counter--;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * This checks the Diagonal From right to left starting on the upper right corner of the player move
	 * @param p
	 */
	public void checkRLDiagUp(Player p) {//start checkRLDiag
		int RowCounter =  p.getRowMove() - 1;//checking row above
		int ColumnCounter = p.getColumnMove() + 1;//checking column to the right
		while( ColumnCounter < 8 && RowCounter >=0) {//start while loop for going center to right
			if(board[RowCounter][ColumnCounter].getState() == p.getId()) {
				this.flipRLDiagUp(p,RowCounter,ColumnCounter);
			}
			RowCounter--;
			ColumnCounter++;
		}
	}
	
	private boolean flipRLDiagUp(Player p , int row, int column) {//start Row
		int RowCounter =  p.getRowMove() - 1;//checking row above
		int ColumnCounter = p.getColumnMove() + 1;//checking column to the right
		int flipCount = 0;
		while(RowCounter > row && ColumnCounter < column  ) {//start while loop
			board[RowCounter][ColumnCounter].setState(p.getId());
			RowCounter--;
			ColumnCounter++;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * This checks the Diagonal From right to left starting on the bottom left corner of the player move
	 * @param p
	 */
	public void checkRLDiagDown(Player p) {
		int LColumnCounter = p.getColumnMove() - 1;
		int LRowCounter = p.getRowMove() + 1;
		while(LColumnCounter >= 0 && LRowCounter <8) {
			if(board[LRowCounter][LColumnCounter].getState() == p.getId()) {
				this.flipRLDiagDown(p, LRowCounter, LColumnCounter);
			}
			LRowCounter++;
			LColumnCounter--;
		}
	}
	
	private boolean flipRLDiagDown(Player p , int row, int column) {//start Row
		int RowCounter =  p.getRowMove() + 1;//checking row above
		int ColumnCounter = p.getColumnMove() - 1;//checking column to the right
		int flipCount = 0;
		while(RowCounter < row && ColumnCounter >= column  ) {//start while loop
			board[RowCounter][ColumnCounter].setState(p.getId());
			RowCounter++;
			ColumnCounter--;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * This checks the Diagonal From left to right starting on the bottom right corner of the player move
	 * @param p
	 */
	public void checkLRDiagDown(Player p) {												
		int RowCounter =  p.getRowMove() + 1;										
		int ColumnCounter = p.getColumnMove() + 1;									
		while( ColumnCounter < 8 && RowCounter < 8) {//start while loop for going center to right
			if(board[RowCounter][ColumnCounter].getState() == p.getId()) {
				this.flipLRDiagDown(p, RowCounter, ColumnCounter);
			}
			RowCounter++;
			ColumnCounter++;
		}
	}
	
	private boolean flipLRDiagDown(Player p , int row, int column) {//start Row
		int RowCounter =  p.getRowMove() + 1;//checking row above
		int ColumnCounter = p.getColumnMove() + 1;//checking column to the right
		int flipCount = 0;
		while(RowCounter < row && ColumnCounter < column  ) {//start while loop
			board[RowCounter][ColumnCounter].setState(p.getId());
			RowCounter--;
			ColumnCounter++;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * This checks the Diagonal From left to right starting on the upper left corner of the player move
	 * @param p
	 */
	public void checkLRDiagUp(Player p) {
		int LColumnCounter = p.getColumnMove() - 1;//check left
		int LRowCounter = p.getRowMove() - 1;//check left
		while(LColumnCounter >=0 && LRowCounter >=0) {//start while loop	
			if(board[LRowCounter][LColumnCounter].getState() == p.getId()) {
				this.flipLRDiagUp(p, LRowCounter, LColumnCounter);
			}
			LRowCounter--;
			LColumnCounter--;
		}
	}

	
	private boolean flipLRDiagUp(Player p , int row, int column) {//start Row
		int RowCounter =  p.getRowMove()-1;//checking row above
		int ColumnCounter = p.getColumnMove()-1;//checking column to the right
		int flipCount = 0;
		while(RowCounter > row && ColumnCounter > column  ) {//start while loop
			board[RowCounter][ColumnCounter].setState(p.getId());
			RowCounter--;
			ColumnCounter++;
		}
		if(flipCount == 0) {
			return false;
		}
		return true;
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
	public int Score(Player p) {
		int score = 0;
		for(int row = 0; row < BOARD_HEIGHT; row++) {
			for(int column = 0; column < BOARD_WIDTH; column++) {
				if(board[row][column].getState() == p.getId()) score++;
			}
		}
		return score;
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
		if(nuetralSpace == 0 ) {
			System.out.println("GAME OVER!!! THAT WAS FUN!!!");
			this.gameOver = true;
		}
		if(playerOne == 0 ) {
			this.toString();
			System.out.println("GAME OVER!!! THAT WAS FUN!!!");
			this.gameOver = true;
		}
		if(playerTwo == 0 ) {
			this.toString();
			System.out.println("GAME OVER!!! THAT WAS FUN!!!");
			this.gameOver = true;
		}
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