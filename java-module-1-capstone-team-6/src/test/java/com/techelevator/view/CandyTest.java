package com.techelevator.view;

import java.util.zip.Adler32;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.Candy;
public class CandyTest {

	@Test
	public void cTest() {
		
		Candy snickers = new Candy("Snickers", 1.00, "A3");
		String string = snickers.toString();
		String noise = snickers.getSound();
		Assert.assertEquals("Munch, Munch, Yum!", noise);
		Assert.assertEquals("A3 Snickers 1.00", string);
		
		
		
	}
}
