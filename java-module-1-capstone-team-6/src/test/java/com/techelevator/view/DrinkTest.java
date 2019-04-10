package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.Drink;

public class DrinkTest {
	
	@Test
	public void cTest() {
		
		Drink coke = new Drink("Coke", 1.00, "A3");
		String string = coke.toString();
		String noise = coke.getSound();
		Assert.assertEquals("Glug, Glug, Yum!", noise);
		Assert.assertEquals("A3 Coke 1.00", string);
		
		
		
	}
}



