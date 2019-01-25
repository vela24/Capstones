package com.techelevator.IntegrationTest;

//import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.jdbc.JDBCCampgroundDAO;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.jdbc.JDBCParkDAO;

import com.techelevator.campground.model.Site;
import com.techelevator.campground.model.jdbc.JDBCSiteDAO;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.jdbc.JDBCReservationDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class JDBCCampgroundAllDAOIntegrationTest {

private static final String TEST_PARK = "YellowStone";	
private static final String TEST_CAMPGROUND = "Greenwoods";
private static final String TEST_SITE = "Woodbine";
//private static final String TEST_RESERVATION = "Mickey and Family";
	
	
	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private JDBCParkDAO dao1;
    private JDBCCampgroundDAO dao2;	
    private JDBCReservationDAO dao3;


	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
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
	
	@Before
	public void setup() {
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//jdbcTemplate.update(TEST_DEPARTMENT);
		dao1 = new JDBCParkDAO(dataSource);
		dao2 = new JDBCCampgroundDAO(dataSource);
		dao3 = new JDBCReservationDAO(dataSource);
	}	

	@Before
	public void truncateTables() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		int numberOfRowsAffected = template.update("TRUNCATE TABLE PARK CASCADE;");
		System.out.printf("%d records have been truncated.", numberOfRowsAffected);
	}
	
	@Test
	public void testPark() {
		String sqlInsertPark = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (1, 'YellowStone', 'Wyoming', '1919-01-01' , 87654, 3297487, 'Yellowstone National Park is famous for its geysers and hot springs.')";
		String sqlInsertCampground = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (1, 1 , 'Greenwoods', 01 , 09, 35.00)";
		String sqlInsertSite = "INSERT INTO site(site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) VALUES (1, 1 , 1, 6, true, 0, false)";

	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    jdbcTemplate.update(sqlInsertPark);
	    jdbcTemplate.update(sqlInsertCampground);
	    jdbcTemplate.update(sqlInsertSite);
		 List<Park> parkList = dao1.getAllParks();
		assertNotNull(parkList);
		assertEquals(1,parkList.size());
	}
	@Test
	public void testCamp() {
		String sqlInsertPark = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) VALUES (1, 'YellowStone', 'Wyoming', '1919-01-01' , 87654, 3297487, 'Yellowstone National Park is famous for its geysers and hot springs.')";
		String sqlInsertCampground = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (1, 1 , 'Greenwoods', 01 , 09, 35.00)";
		String sqlInsertSite = "INSERT INTO site(site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) VALUES (1, 1 , 1, 6, true, 0, false)";

	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	    jdbcTemplate.update(sqlInsertPark);
	    jdbcTemplate.update(sqlInsertCampground);
	    jdbcTemplate.update(sqlInsertSite);
		 List<Campground> campList = dao2.getCampsByParkID(new Long(1));
		assertNotNull(campList);
		assertEquals(1,campList.size());
	}	
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

}


