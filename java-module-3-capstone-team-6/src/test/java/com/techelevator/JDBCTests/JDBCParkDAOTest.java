package com.techelevator.JDBCTests;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.DAO.JDBC.JDBCParkDAO;



public class JDBCParkDAOTest extends DAOIntegrationTest {
	private JDBCParkDAO dao;
	
	@Before
	public void setup() {
		dao = new JDBCParkDAO(getDataSource());
	}
	
	@Test
	public void get_all_parks() {
		Park park = makeTestPark(TEST_PARK_PARKCODE, TEST_PARK_PARKNAME, TEST_PARK_STATE, 
				TEST_PARK_ACREAGE, TEST_PARK_ELEVATION, TEST_PARK_NUMOFCAMPSITES, TEST_PARK_MILESOFTRAIL,  
				TEST_PARK_CLIMATE, TEST_PARK_YEARFOUNDED, TEST_PARK_ANNUALVISITORS, TEST_PARK_QUOTE, 
				TEST_PARK_QUOTESOURCE, TEST_PARK_DESCRIPTION, TEST_PARK_ENTRYFEE, TEST_PARK_NUMOFSPECIES);
		insertPark(park);
		List<Park> listOfParks = dao.getAllParks();
		assertParksAreEqual(park, listOfParks.get(listOfParks.size() - 1));
	}
	
	@Test
	public void get_park_by_parkcode() {
		Park park = makeTestPark(TEST_PARK_PARKCODE, TEST_PARK_PARKNAME, TEST_PARK_STATE, 
				TEST_PARK_ACREAGE, TEST_PARK_ELEVATION, TEST_PARK_NUMOFCAMPSITES, TEST_PARK_MILESOFTRAIL,  
				TEST_PARK_CLIMATE, TEST_PARK_YEARFOUNDED, TEST_PARK_ANNUALVISITORS, TEST_PARK_QUOTE, 
				TEST_PARK_QUOTESOURCE, TEST_PARK_DESCRIPTION, TEST_PARK_ENTRYFEE, TEST_PARK_NUMOFSPECIES);
		insertPark(park);
		Park savedPark = dao.getPark(park.getParkCode());
		assertParksAreEqual(park, savedPark);
	}
	
	private void assertParksAreEqual(Park expected, Park actual) {
		Assert.assertEquals(expected.getParkCode(), actual.getParkCode());
		Assert.assertEquals(expected.getParkName(), actual.getParkName());
		Assert.assertEquals(expected.getState(), actual.getState());
		Assert.assertEquals(expected.getAcreage(), actual.getAcreage());
		Assert.assertEquals(expected.getElevation(), actual.getElevation());
		Assert.assertTrue(Math.abs(expected.getAcreage() - actual.getAcreage()) == 0);
		Assert.assertEquals(expected.getNumOfCampsites(), actual.getNumOfCampsites());
		Assert.assertEquals(expected.getClimate(), actual.getClimate());
		Assert.assertEquals(expected.getYearFounded(), actual.getYearFounded());
		Assert.assertEquals(expected.getAnnualVisitors(), actual.getAnnualVisitors());
		Assert.assertEquals(expected.getQuote(), actual.getQuote());
		Assert.assertEquals(expected.getQuoteSource(), actual.getQuoteSource());
		Assert.assertEquals(expected.getDescription(), actual.getDescription());
		Assert.assertEquals(expected.getEntryFee(), actual.getEntryFee());
		Assert.assertEquals(expected.getNumOfSpecies(), actual.getNumOfSpecies());
	}

}
