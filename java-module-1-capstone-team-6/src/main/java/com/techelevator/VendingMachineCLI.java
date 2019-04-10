package com.techelevator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI  {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items\n";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_BUY = "Buy";
	private static final String PURCHASE_MENU_OPTION_DONE = "DONE?";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_BUY,
			PURCHASE_MENU_OPTION_DONE };

	public static final String MONEY_ONE = "1.00";
	public static final String MONEY_TWO = "2.00";
	public static final String MONEY_FIVE = "5.00";
	public static final String MONEY_TEN = "10.00";
	public static final String MONEY_EXIT = "EXIT FEED MONEY";
	public static final String[] MONEY_OPTIONS = { MONEY_ONE, MONEY_TWO, MONEY_FIVE, MONEY_TEN, MONEY_EXIT };

	private Menu menu;
	private Inventory vm;

	public VendingMachineCLI(Menu menu)  {
		this.menu = menu;
		this.vm = new Inventory("vendingmachine.csv");

	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vm.getInventory(); // getting our map
			 
			}else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				while (true) {
					
					
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						while (true) {
							String moneyMenuChoice = (String) menu.getChoiceFromOptions(MONEY_OPTIONS);// if choose is
																										// feed money
							if (moneyMenuChoice.equals(MONEY_ONE)) {
								vm.feedMoney(1);
								
							} else if (moneyMenuChoice.equals(MONEY_TWO)) {
								vm.feedMoney(2);
								
							} else if (moneyMenuChoice.equals(MONEY_FIVE)) {
								vm.feedMoney(3);
																// placing each argument and assigning to each number
							} else if (moneyMenuChoice.equals(MONEY_TEN)) {
								vm.feedMoney(4);
								
							} else if (moneyMenuChoice.equals(MONEY_EXIT)) {
								break;
							}
						}
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_BUY)) {
						vm.getInventory();
						System.out.println("Please input your selection");
						Scanner selection =new Scanner (System.in);
						String guestSelection = selection.nextLine();
						vm.purchase(guestSelection);
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_DONE)) {
						
						vm.completeTransaction();
						break;
					}
				}
			}	
		}
	}
	
	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}


