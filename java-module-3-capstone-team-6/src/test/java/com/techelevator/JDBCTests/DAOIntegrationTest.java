package com.techelevator.JDBCTests;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.DAO.JDBC.JDBCParkDAO;

public abstract class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	
	//Test Park
	protected static final String TEST_PARK_PARKCODE = "ZZZ";
	protected static final String TEST_PARK_PARKNAME = "Cheese Park";
	protected static final String TEST_PARK_STATE = "Hotdog State";
	protected static final int TEST_PARK_ACREAGE = 999999999;
	protected static final int TEST_PARK_ELEVATION = 888888888;
	protected static final double TEST_PARK_MILESOFTRAIL = 777777777.0;
	protected static final int TEST_PARK_NUMOFCAMPSITES = 666666666;
	protected static final String TEST_PARK_CLIMATE = "Temperate";
	protected static final int TEST_PARK_YEARFOUNDED = 1999;
	protected static final int TEST_PARK_ANNUALVISITORS = 555555555;
	protected static final String TEST_PARK_QUOTE = "They're greeeaaaattt!";
	protected static final String TEST_PARK_QUOTESOURCE = "Tony the Tiger";
	protected static final String TEST_PARK_DESCRIPTION = "Pretty lame";
	protected static final int TEST_PARK_ENTRYFEE = 444444444;
	protected static final int TEST_PARK_NUMOFSPECIES = 333333333;
	
	//TEST SURVEY
	protected static final long TEST_SURVEY_SURVEYID = 999999;
	protected static final String TEST_SURVEY_PARKCODE = "ZZZ";
	protected static final String TEST_SURVEY_EMAIL = "choutsug@buzzvirale.xyz";
	protected static final String TEST_SURVEY_EMAILCONFIRMATION = "choutsug@buzzvirale.xyz";
	protected static final String TEST_SURVEY_STATE = "Frankfurt Land";
	protected static final String TEST_SURVEY_ACTIVITYLEVEL = "active";
	
	//TEST WEATHER
	protected static final String TEST_WEATHER_PARKCODE = "ZZZ";
	protected static final int TEST_WEATHER_DAY = 1;
	protected static final int TEST_WEATHER_LOWTEMP = 20;
	protected static final int TEST_WEATHER_HIGHTTEMP = 80;
	protected static final String TEST_WEATHER_FORECAST = "cloudy";
	protected static final String TEST_WEATHER_RECOMMENDATION = "";
	protected static final String TEST_WEATHER_FORECASTIMAGE = TEST_WEATHER_FORECAST;
	
	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections 
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}
	
	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	/* This method provides access to the DataSource for subclasses so that 
	 * they can instantiate a DAO for testing */
	public DataSource getDataSource() {
		return dataSource;
	}
	
	protected Park makeTestPark(String parkCode, String parkName, String state, int acreage, 
					int elevation, int numOfCampsites, double milesOfTrail, String climate, 
					int yearFounded, int annualVisitors, String quote, String quoteSource,
					String description, int entryFee, int numOfSpecies) {
		Park testPark = new Park();
		testPark.setParkCode(parkCode);
		testPark.setParkName(parkName);
		testPark.setState(state);
		testPark.setAcreage(acreage);
		testPark.setElevation(elevation);
		testPark.setMilesOfTrail(milesOfTrail);
		testPark.setNumOfCampsites(numOfCampsites);
		testPark.setClimate(climate);
		testPark.setYearFounded(yearFounded);
		testPark.setAnnualVisitors(annualVisitors);
		testPark.setQuote(quote);
		testPark.setQuoteSource(quoteSource);
		testPark.setDescription(description);
		testPark.setEntryFee(entryFee);
		testPark.setNumOfSpecies(numOfSpecies);
		return testPark;
	}
	
	public void insertPark(Park park) {
		String sql = "INSERT INTO park (parkcode, parkname, " + 
				"state, acreage, elevationinfeet, milesoftrail, numberofcampsites, " + 
				"climate, yearfounded, annualvisitorcount, inspirationalquote, " + 
				"inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sql, park.getParkCode(), park.getParkName(), park.getState(), park.getAcreage(), 
				park.getElevation(), park.getMilesOfTrail(), park.getNumOfCampsites(), park.getClimate(), 
				park.getYearFounded(), park.getAnnualVisitors(), park.getQuote(), park.getQuoteSource(), 
				park.getDescription(), park.getEntryFee(), park.getNumOfSpecies());	
	}

	protected Survey makeTestSurvey(String parkCode, String email, String emailConfirmation, 
				String state, String activityLevel) {
		Survey testSurvey = new Survey();
		testSurvey.setParkCode(parkCode);
		testSurvey.setEmail(email);
		testSurvey.setEmailConfirmation(emailConfirmation);
		testSurvey.setState(state);
		testSurvey.setActivityLevel(activityLevel);
		return testSurvey;
	}
	

	protected Weather makeTestWeather(String forcastImage, String recommendation) {
		Weather testWeather = new Weather(TEST_WEATHER_PARKCODE, TEST_WEATHER_DAY,TEST_WEATHER_LOWTEMP,
				TEST_WEATHER_HIGHTTEMP, TEST_WEATHER_FORECAST);
		testWeather.setForecastImage(forcastImage);
		testWeather.setRecommendation(recommendation);
		return testWeather;
	}
	
	public void insertWeather(Weather weather) {
		String sql = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) " +
					"VALUES (?, ?, ?, ?, ?);";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.update(sql, weather.getParkCode(), weather.getDay(), weather.getLowTemp(), weather.getHighTemp(), 
				weather.getForecast());
	}
	
}
