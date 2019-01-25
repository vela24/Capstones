package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.model.Park;

@Component
public class JDBCParkDAO implements ParkDAO {
	
	 private JdbcTemplate jdbcTemplate;
	 
	@Autowired
	public JDBCParkDAO(DataSource data) {
		this.jdbcTemplate = new JdbcTemplate(data);
	}


	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<>();
		
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, "
				+ "yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee,  numberofanimalspecies "
				+ "FROM park";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while(results.next()) {
			parks.add(mapToPark(results));
		}
		return parks;
	}
	

	@Override
	public Park getParkByParkCode(String code) {
		Park park = null;
		String sql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, "
				+ "yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee,  numberofanimalspecies "
				+ "FROM park "
				+ "WHERE parkcode = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, code);
		
		if(results.next()) {
			park = mapToPark(results);
		}
			
		return park;
	}
	
	/**
	 * Map SqlRowSet results to a Park object
	 * @param result returned from query
	 * @return park as an object 
	 */
	private Park mapToPark(SqlRowSet result) {
		Park park = new Park();
		
		park.setParkCode(result.getString("parkcode"));
		park.setParkName(result.getString("parkname"));
		park.setState(result.getString("state"));
		park.setAcreage(result.getLong("acreage"));
		park.setElevationInFeet(result.getLong("elevationinfeet"));
		park.setMilesOfTrail(result.getDouble("milesoftrail"));
		park.setNumOfCampsites(result.getLong("numberofcampsites"));
		park.setClimate(result.getString("climate"));
		park.setYearFounded(result.getLong("yearfounded"));
		park.setAnnualVisitorCount(result.getLong("annualvisitorcount"));
		park.setQuote(result.getString("inspirationalquote"));
		park.setQuoteSource(result.getString("inspirationalquotesource"));
		park.setDescription(result.getString("parkdescription"));
		park.setEntryFee(result.getLong("entryfee"));
		park.setNumOfAnimalSpecies(result.getLong("numberofanimalspecies"));
		
		return park;
	}


}
