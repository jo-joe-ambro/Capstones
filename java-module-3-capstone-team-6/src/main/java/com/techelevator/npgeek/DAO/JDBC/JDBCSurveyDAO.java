package com.techelevator.npgeek.DAO.JDBC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.Survey;
import com.techelevator.npgeek.DAO.SurveyDAO;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
	
	@Override
	public List<Survey> getAllSurveys(){
		String sql ="SELECT surveyid, parkcode, emailaddress, state, "
				+ "activitylevel  FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		List<Survey> newSurveyForm = new ArrayList<Survey>();
		
		while(results.next()){
			newSurveyForm.add(mapRowToSurvey(results));
		}
		return newSurveyForm;
	}
	
	private Survey mapRowToSurvey(SqlRowSet row) {
		Long surveyId = row.getLong("surveyid");
		String parkCode = row.getString("parkcode");
		String emailAddress =row.getString("emailaddress");
		String state = row.getString("state");
		String activityLevel =row.getString("activitylevel");
		Survey newSurvey = new Survey();
		
		newSurvey.setSurveyID(surveyId);
		newSurvey.setParkCode(parkCode);
		newSurvey.setEmail(emailAddress);
		newSurvey.setEmailConfirmation(emailAddress);
		newSurvey.setState(state);
		newSurvey.setActivityLevel(activityLevel);
		
		return newSurvey;
	}


	@Override
	public void create(Survey newSurveyForm) {
		String sql ="INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, "
				+ "activitylevel) VALUES(?,?,?,?,?)";
		newSurveyForm.setSurveyID(getNextID());  
		jdbcTemplate.update(sql, newSurveyForm.getSurveyID(), newSurveyForm.getParkCode(),
		newSurveyForm.getEmail(), newSurveyForm.getState(), newSurveyForm.getActivityLevel());
		
	}
	@Override
	public Map<String, Integer> getSurveyResults() {
		String sql = "SELECT COUNT(*), park.parkname FROM survey_result " + 
				"JOIN park ON park.parkCode = survey_result.parkcode " +
				"GROUP BY park.parkname ORDER BY COUNT DESC, park.parkname;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		Map<String, Integer> surveyResults = new LinkedHashMap<String, Integer>();
		while(results.next()) {
			String key = results.getString("parkname");
			int value = results.getInt("count");
			surveyResults.put(key, value);
		}
		return surveyResults;
	}
	
	

	private long getNextID() {
		SqlRowSet nextResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_surveyid')");
		if(nextResult.next()) {
			return nextResult.getLong(1);
		}else {
			throw new RuntimeException("Something went wrong while getting an id for the new user");
		}
	}
}
