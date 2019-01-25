package com.techelevator.npgeek.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.DAOIntegrationTest;

public class SurveyDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCSurveyDAO jdbcSurveyDAO;
	
	@Before
	public void initializeSubject() {
		jdbcSurveyDAO = new JDBCSurveyDAO(getDataSource());
	}
	
	@Test
	public void testSubmitSurvey_inserts_survey() {
		
	}

}
