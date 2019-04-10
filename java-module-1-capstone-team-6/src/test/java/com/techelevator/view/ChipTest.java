package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.Chip;

public class ChipTest {
	
	@Test
	public void cTest() {
		
		Chip lays = new Chip("Lays", 1.00, "A3");
		String string = lays.toString();
		String noise = lays.getSound();
		Assert.assertEquals("Crunch, Crunch, Yum!", noise);
		Assert.assertEquals("A3 Lays 1.00", string);
		
		
		
	}
}


