package com.techelevator.JDBCTests;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.DAO.JDBC.JDBCSurveyDAO;



public class JDBCSurveyDAOTest extends DAOIntegrationTest {
	private JDBCSurveyDAO dao;
	
	@Before
	public void setup() {
		Park park = makeTestPark(TEST_PARK_PARKCODE, TEST_PARK_PARKNAME, TEST_PARK_STATE, 
				TEST_PARK_ACREAGE, TEST_PARK_ELEVATION, TEST_PARK_NUMOFCAMPSITES, TEST_PARK_MILESOFTRAIL,  
				TEST_PARK_CLIMATE, TEST_PARK_YEARFOUNDED, TEST_PARK_ANNUALVISITORS, TEST_PARK_QUOTE, 
				TEST_PARK_QUOTESOURCE, TEST_PARK_DESCRIPTION, TEST_PARK_ENTRYFEE, TEST_PARK_NUMOFSPECIES);
		insertPark(park);
		dao = new JDBCSurveyDAO(getDataSource());
	}
	
	@Test
	public void put_survey_into_table() {
		Survey survey = makeTestSurvey(TEST_SURVEY_PARKCODE,  
				TEST_SURVEY_EMAIL, TEST_SURVEY_EMAILCONFIRMATION, TEST_SURVEY_STATE, TEST_SURVEY_ACTIVITYLEVEL);
		dao.create(survey);
		List<Survey> listOfSurveys = dao.getAllSurveys();
		assertSurveysAreEqual(survey, listOfSurveys.get(listOfSurveys.size() - 1));
	}
	
	@Test
	public void get_count_and_park_name() {

		Survey survey = makeTestSurvey(TEST_SURVEY_PARKCODE,  
				TEST_SURVEY_EMAIL, TEST_SURVEY_EMAILCONFIRMATION, TEST_SURVEY_STATE, TEST_SURVEY_ACTIVITYLEVEL);
		dao.create(survey);
		Map<String, Integer> surveyResults = dao.getSurveyResults();
		
	}
	
	
	private void assertSurveysAreEqual(Survey expected, Survey actual) {
		Assert.assertEquals(expected.getParkCode(), actual.getParkCode());
		Assert.assertEquals(expected.getSurveyID(), actual.getSurveyID());
		Assert.assertEquals(expected.getActivityLevel(), actual.getActivityLevel());
		Assert.assertEquals(expected.getEmail(), actual.getEmail());
		Assert.assertEquals(expected.getEmailConfirmation(), actual.getEmailConfirmation());
		Assert.assertEquals(expected.getState(), actual.getState());
	}

}
