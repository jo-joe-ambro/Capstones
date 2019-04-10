package com.techelevator;



public abstract class Snack {

	private String name; 
	private double price;
	private String location;
	
	public Snack(String name, double price, String location) {
		this.name = name;
		this.price = price;
		this.location = location;
		
	}
	public abstract String getSound();
	
	@Override
	public String toString() {
		String priceString = String.format("%.2f", price);
		String items = location + " " + name + " " + priceString;
		return items;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getLocation() {
		return location;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
