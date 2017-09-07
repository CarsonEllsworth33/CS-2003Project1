package Othello;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {

	private String name;
	private int id;
	private int Arow,Acolumn;
	public Player(String name, int id) {
		this.setName(name);
		this.setId(id);
		
		System.out.println("Welcome " + name + " you are player " + id);
	}
	public int getRowMove() {
		return Arow;
	}
	public int getColumnMove() {
		return Acolumn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * this function will allow the player to place a disc down in a possible coordinate spot
	 * @param in
	 * @return
	 * @throws Exception outOfRange if values are not between 1-8
	 */
	public void turn(InputStream in){
		System.out.println("Player " + id + " it is your turn, please put in two whole numbers seperated by a space(do not include \"\" in your answer)\nas your move... for example \"8 5\" would place your piece on the 8th row 5th column");
		Scanner scanner = new Scanner(in);
		int row = 0,column = 0;
		try {
			row = scanner.nextInt();
			column = scanner.nextInt();
			if(row > 8 || row <= 0 ||column <= 0 || column > 8) {
				System.out.println("this value is not an int between 1 - 8\nPlease input an acceptable value and try again");
				System.out.println("trace");
				turn(in);
				return;
			}
		}
		catch (InputMismatchException e) {
			System.out.println("this value is not an int between 1 - 8\nPlease input an acceptable value and try again");
			turn(in);
			return;
		}
		
		Arow = row;
		Acolumn = column;
		
	}
}
