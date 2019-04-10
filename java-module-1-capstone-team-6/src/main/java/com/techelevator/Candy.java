package com.techelevator;

public class Candy extends Snack{
	public Candy(String name, double price, String location) {
		super(name, price, location);
	}
	
	
	public String getSound() {
		return "Munch, Munch, Yum!";
	}

}
