package com.techelevator.JunitTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Weather;

public class DetailsControllerTest {
	
	@Before
	public void before() {
	Park park = new Park();	
	String parkCode = "ZZZ";
	int day = 1;
	int lowTemp = 32;
	int highTemp = 80;
	String forecast = "sunny";
	Weather weather = new Weather(parkCode, day, lowTemp, highTemp, forecast);
	}
	
	@After
	public void after() {
	}
	
	@Test
	public void change_temps_to_celsius() {
		
	}

}
