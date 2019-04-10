package com.techelevator;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Change {
	
	private int numberOfQuarters;
	private int numberOfDimes;
	private int numberOfNickles;
	public double money;
	
	
	public void giveChange(double balance) {
		Scanner input = new Scanner(System.in);
		String userInput;
		
		balance = (balance * 100);
		numberOfQuarters = ((int)balance / 25);
		balance = balance - (numberOfQuarters * 25);
		numberOfDimes = ((int) balance / 10);
		balance = balance - (numberOfDimes * 10);
		numberOfNickles = ((int) balance / 5);
		
		System.out.println(numberOfQuarters+ " Quarters" + "\n" + numberOfDimes + " Dimes" + "\n" + numberOfNickles + " Nickels");
		System.out.println();
		
	}

}
