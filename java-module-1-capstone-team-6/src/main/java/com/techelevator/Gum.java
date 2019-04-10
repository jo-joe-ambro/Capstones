package com.techelevator;



public class Gum extends Snack {

	public Gum(String name, double price, String location) {
		super(name, price, location);
	}

	public String getSound() {
		return "Chew, Chew, Yum!";
	}
}
