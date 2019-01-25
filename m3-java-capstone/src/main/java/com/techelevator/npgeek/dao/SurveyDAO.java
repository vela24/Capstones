package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.npgeek.dao.model.Park;
import com.techelevator.npgeek.dao.model.Survey;

public interface SurveyDAO {

	/**
	 * Inserts a survey into the database 
	 * @param survey -survey being entered
	 */
	void submitSurvey(Survey survey);
	
	/**
	 * Returns all the parks that have been saved in a survey
	 * @return List with all the favorite parks
	 */
	List<Survey> retrieveFavoriteParks();
}
