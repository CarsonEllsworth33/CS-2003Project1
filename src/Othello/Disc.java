package Othello;

public class Disc {
	private int discState;
	private String white = "W";
	private String black = "B";
	private String currentColor = "_";
	
	
	public Disc (int id) {
		setState(id);
	}
	/**
	 * This method takes in the current state of a disc and flips the disc to the opposite color
	 * @param currentState
	 */
	public void flip(int currentState) 
	{//start of flip
		if (currentState == 1)//white flipping to black
		{
			currentState = 2;
		}
		else//black flipping to white 
		{
			currentState = 1;
		}
	}//end of flip
	
	/**
	 * 
	 * @return State of the selected disc
	 */
	public int getState()
	{
		return discState;
	}
	public String getCurrentColor() {
		return currentColor;
	}
	
	/**
	 * Must put in a 1 or 0 for white or black respectively
	 * @param State
	 * @return
	 */
	public void setState(int State)
	{//start of setState
		switch(State) 
		{
		case 0:
			discState = State;
			break;
		case 1: 
			discState = State;
			currentColor = white;
			break;
		case 2:
			discState = State;
			currentColor = black;
			break;
		default:
			System.out.println("INVALID ARGUMENT");
			
		}
	}//end of setState
	/**
	 * this method makes the board appear empty
	 * @return
	 */
	
		
	}//end of class Disc
	

