package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.Gum;

public class GumTest {
	@Test
	public void cTest() {
		
		Gum gum = new Gum("Trident", 1.00, "A3");
		String string = gum.toString();
		String noise = gum.getSound();
		Assert.assertEquals("Chew, Chew, Yum!", noise);
		Assert.assertEquals("A3 Trident 1.00", string);
		
		
		
	}

}
