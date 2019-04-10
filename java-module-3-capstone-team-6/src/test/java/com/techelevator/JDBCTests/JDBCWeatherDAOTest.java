package com.techelevator.JDBCTests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.DAO.JDBC.JDBCWeatherDAO;



public class JDBCWeatherDAOTest extends DAOIntegrationTest {
	private JDBCWeatherDAO dao;
	
	@Before
	public void setup() {
		Park park = makeTestPark(TEST_PARK_PARKCODE, TEST_PARK_PARKNAME, TEST_PARK_STATE, 
				TEST_PARK_ACREAGE, TEST_PARK_ELEVATION, TEST_PARK_NUMOFCAMPSITES, TEST_PARK_MILESOFTRAIL,  
				TEST_PARK_CLIMATE, TEST_PARK_YEARFOUNDED, TEST_PARK_ANNUALVISITORS, TEST_PARK_QUOTE, 
				TEST_PARK_QUOTESOURCE, TEST_PARK_DESCRIPTION, TEST_PARK_ENTRYFEE, TEST_PARK_NUMOFSPECIES);
		insertPark(park);
		dao = new JDBCWeatherDAO(getDataSource());
	}
	
	@Test
	public void get_all_weather_from_park() {
		Weather weather = makeTestWeather(TEST_WEATHER_FORECASTIMAGE, TEST_WEATHER_RECOMMENDATION);
		insertWeather(weather);
		List<Weather> listOfWeather = dao.getAllWeather(TEST_WEATHER_PARKCODE);
		assertWeathersAreEqual(weather, listOfWeather.get(listOfWeather.size() - 1));
	}
	
	private void assertWeathersAreEqual(Weather expected, Weather actual) {
		Assert.assertEquals(expected.getParkCode(), actual.getParkCode());
		Assert.assertEquals(expected.getDay(), actual.getDay());
		Assert.assertEquals(expected.getLowTemp(), actual.getLowTemp());
		Assert.assertEquals(expected.getHighTemp(), actual.getHighTemp());
		Assert.assertEquals(expected.getForecast(), actual.getForecast());
	}

}
