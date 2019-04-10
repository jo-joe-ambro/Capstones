package com.techelevator.npgeek.DAO;

import java.util.List;
import java.util.Map;

import com.techelevator.npgeek.Survey;

public interface SurveyDAO {

	List<Survey> getAllSurveys();
	void create(Survey newSurveyForm);
	Map getSurveyResults();
}
