package com.techelevator.npgeek;

public class Utility {
	
	private Utility() {
	}
	
	public static int convertToCelsius(int F) {
		int C;
		C = (((F - 32) * 5) / 9);
		return C;
	}

}
