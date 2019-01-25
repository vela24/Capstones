package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.model.Park;
import com.techelevator.npgeek.dao.model.Survey;

@Component
public class JDBCSurveyDAO implements SurveyDAO {

	private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	public JDBCSurveyDAO(DataSource data) {
		this.jdbcTemplate = new JdbcTemplate(data);
	}

	@Override
	public void submitSurvey(Survey survey) {
		Long id = getNextId();
		String sql = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, id, survey.getParkCode(), survey.getEmail(), survey.getState(), survey.getActivityLevel());
		survey.setSurveyId(id);
	}
	

	@Override
	public List<Survey> retrieveFavoriteParks() {
		List<Survey> surveyResults = new ArrayList<Survey>();
		
		String sql ="select count(s.parkcode) as surveycount, s.parkcode, parkname " + 
				"from park p " + 
				"JOIN survey_result s on s.parkcode = p.parkcode " + 
				"GROUP BY s.parkcode, parkname " + 
				"order by surveycount desc;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
			surveyResults.add(mapRowToSurvey(results));
		}
		
		return surveyResults;
	}
	
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
	
	private Survey mapRowToSurvey(SqlRowSet result) {
		Survey surveyResults  = new Survey();
		surveyResults.setParkCode(result.getString("parkcode"));
		surveyResults.setSurveyCount(result.getInt("surveycount"));
		surveyResults.setParkName(result.getString("parkname"));
		return surveyResults;
	}
}
