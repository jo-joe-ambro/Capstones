package com.techelevator;

public class Chip extends Snack {
	public Chip(String name, double price, String location) {
		super(name, price, location);
	}

	public String getSound() {
		return "Crunch, Crunch, Yum!";
	}
	

}
