package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.ParkDAO;



public class JDBCParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
 private Park mapRowToPark(SqlRowSet results) {
		Park park;
		park = new Park();
		park.setId(results.getLong("park_id"));
		park.setName(results.getString("name"));
		park.setLocation(results.getString("location"));
		park.setEstablishedDate(results.getDate("establish_date"));
		park.setArea(results.getInt("area"));
		park.setAnnualVisitorsCount(results.getInt("visitors"));
		park.setDescription(results.getString("description"));
		return park;
	}
 

@Override
public List<Park> getAllParks() {
	// TODO Auto-generated method stub
	ArrayList<Park> park1 = new ArrayList<>();
	String sqlGetAllParks = "Select park_id, name, location, establish_date, area, visitors, description from park order by name";	SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
	
	while(results.next()) {
		Park park = mapRowToPark(results);
		park1.add(park);
	}
	return park1;

}

}
