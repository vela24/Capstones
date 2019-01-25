package com.techelevator.npgeek.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.dao.model.Park;

public class ParkDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCParkDAO jdbcParkDAO;
	
	private String park1Sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "+ 
			"climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, " + 
			"parkdescription ,entryfee, numberofanimalspecies) VALUES ();";
	
	private String park2Sql = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "+ 
			"climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, " + 
			"parkdescription ,entryfee, numberofanimalspecies) VALUES ();"; 
	
	//setup for testing - initialize parkDAO
	@Before
	public void initializeSubject() {
		jdbcParkDAO = new JDBCParkDAO(getDataSource());
	}
	
	
	/**
	 * Test getAllParks()
	 */
	@Test
	public void getAllParks_returns_allParks() {
		//ARRANGE
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.execute(park1Sql);
		jdbcTemplate.execute(park2Sql);
		
		//ACT
		List<Park> results = jdbcParkDAO.getAllParks();
		
		//ASSERT
		assertEquals(2, results.size());
		Park expectedPark = new Park();
		expectedPark.setParkCode("YNP");
		expectedPark.setParkName("Yellowstone National Park");
		
		assertParkContains(expectedPark, results);
		
	}
	
	/**
	 * Method to test that the list contains the correct parks
	 * @param expectedPark park that is expected 
	 * @param actualParks list of parks retrieved 
	 */
	private void assertParkContains(Park expectedPark, List<Park> actualParks) {
		Park resolvedPark = null;
		
		//traverse list and check if expectedPark's name matches a name in the list - if so break out of loop
		for(Park pk : actualParks) {
			if(expectedPark.getParkName().equals(pk.getParkName())) {
				resolvedPark = pk;
				break;
			}
		}
		
		//if the resolvedPark object is still null then fail the test 
		if(resolvedPark == null) {
			fail(String.format("The collection does not contain the expected park %s", expectedPark.getParkName()));
			return;
		}
		
		//check that both parkCode's match
		assertEquals(expectedPark.getParkCode(), resolvedPark.getParkCode());	
	}
}
