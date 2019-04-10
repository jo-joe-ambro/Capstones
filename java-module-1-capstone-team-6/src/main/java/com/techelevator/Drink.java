package com.techelevator;

import java.math.BigDecimal;
import java.util.HashMap;

public class Drink extends Snack {
	

	public Drink(String name, double price, String location) {
		super(name, price, location);	
	}
		
	public String getSound() {
		return "Glug, Glug, Yum!";
	}
}
