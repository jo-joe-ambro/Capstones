package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory  {
	DecimalFormat numberFormat = new DecimalFormat("#.00");
	private double balance = 0.00;
	private double amount = 0.00;
	List<Snack> purchaseList = new ArrayList();
	private Map<String, List<Snack>> snacks = new HashMap();
	LogWriter writer = new LogWriter();
	public Inventory(String filename){
		try {
			setupInventory(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	
	public void getInventory() {
		for(Entry<String, List<Snack>> entry : snacks.entrySet()) { //added for inventory count
			String key = entry.getKey();
			List<Snack> _snacks = entry.getValue();
			if(_snacks.size() == 0) {
				System.out.println(key + "| Out of Stock" );
				continue;
			} else {
				System.out.println(key+" | "+_snacks.get(0).getName()+" | "+_snacks.get(0).getPrice());
			}
			
			
			
			
		}
		
	}
	
public void setupInventory(String filename) throws IOException {
	
	
		File file = new File(filename);
		
		Scanner scanner = new Scanner(file);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] vending = line.split("\\|");
			String name = vending[1];
			double price = Double.parseDouble(vending[2]);
			String location = vending[0];
			List<Snack> snackers = snacks.get(location);
		
			if(snackers == null) {
				snackers = new ArrayList<Snack>();
				snacks.put(location, snackers);
			}
			if (vending[3].equals("Chip")) {
				for( int i=0;i <5;i++) {
					snackers.add(new Chip(name, price, location));
				}
				
			} else if (vending[3].equals("Candy")) {
				for( int i=0;i <5;i++) {
				snackers.add(new Candy(name, price, location));
				
				}
			} else if (vending[3].equals("Drink")) {
				for( int i=0;i <5;i++) {
				snackers.add(new Drink(name, price, location));	
				}
			} else if (vending[3].equals("Gum")) {
				for( int i=0;i <5;i++) {
				snackers.add(new Gum(name, price, location));	
				
				}
			}

		}
		
	}

		public double getBalance() {
			return balance;
		}
		

	
		public void feedMoney(double addMoney) {
			
			if(addMoney == 1) {
				 balance += Double.parseDouble(VendingMachineCLI.MONEY_ONE);
			}else if (addMoney == 2) {
				balance += Double.parseDouble(VendingMachineCLI.MONEY_TWO);
			}else if (addMoney == 3 ) {
				balance += Double.parseDouble(VendingMachineCLI.MONEY_FIVE);
			}else if (addMoney == 4) {
				 balance += Double.parseDouble(VendingMachineCLI.MONEY_TEN);
			
			}
			writer.writer("Feed Money", addMoney, addMoney);
			System.out.println("Your balance is $" + numberFormat.format(balance));
		
	}
		
		public void completeTransaction() {
		Change thisChange = new Change();
		thisChange.giveChange(balance);
		System.out.println("Here's your change: $" + numberFormat.format(balance));
		writer.writer("GIVE CHANGE:", balance, amount);
		while (purchaseList.size() > 0) {
			Snack purchases = purchaseList.remove(0);
			System.out.println(purchases.getSound());
		}
	}

		public void purchase(String guestSelection) {
			if (! snacks.containsKey(guestSelection)) { 
				System.out.println("Sorry that product does not exist, please choose a valid product");
			} 
			if (snacks.containsKey(guestSelection)) {
				if (snacks.get(guestSelection).size() == 0) {
					System.out.println("Sorry out of stock");
				}
				if (snacks.get(guestSelection).size() >= 1) {
					if (snacks.get(guestSelection).get(0).getPrice() >= 0){
						balance = balance - (snacks.get(guestSelection).get(0).getPrice());
						
						Snack cost = snacks.get(guestSelection).get(0);
						double costOne = cost.getPrice();
						Snack nameOne = snacks.get(guestSelection).get(0);
						String productOne = nameOne.getName() + " " + guestSelection;
						writer.writer(productOne, costOne, balance);
						snacks.get(guestSelection).remove(0);
					} else {
						System.out.println("Sorry tough guy, insert some cash!");
					}
				}
			}
			writer.writer("Purchase", amount, balance);
			System.out.println("Your balance is " + numberFormat.format(balance));
		}


		
}
