package com.techelevator.npgeek.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.dao.model.Weather;

public class WeatherDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCWeatherDAO jdbcWeatherDAO;
	
	private String weatherSql1 = "INSERT INTO weather parkcode, fivedayforecastvalue, low, high, forecast "
			+ "VALUES ('YNP', 1, 23, 43, 'cloudy');";
	private String weatherSql2 = "INSERT INTO weather parkcode, fivedayforecastvalue, low, high, forecast " 
			+ "VALUES ('ENP', 3, 70, 81, 'partly cloudy');";
	
	//intialize DAO weather object
	@Before
	public void initializeSubject() {
		jdbcWeatherDAO = new JDBCWeatherDAO(getDataSource());
	}
	
	/**
	 * Test getWeatherByParkCode
	 */
	@Test
	public void getWeatherByParkCode_returns_weatherByParkCode() {
		//ARRANGE
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		jdbcTemplate.execute(weatherSql1);
		jdbcTemplate.execute(weatherSql2);
		String parkCode = "YNP";
		
		//ACT
		List<Weather> results = jdbcWeatherDAO.getFiveDayForecast(parkCode);
		
		//ASSERT
		assertEquals(2, results.size());
		Weather expectedWeather = new Weather();
		expectedWeather.setParkcode("YNP");
		expectedWeather.setDay(1);
		expectedWeather.setForecast("cloudy");
		
		assertWeatherContains(expectedWeather, results);
	}
	
	
	/**
	 * The test will check if the expectedWeather object is in the list of weather objects
	 * @param expectedWeather expected weather object
	 * @param actualWeather list of weather objects
	 */
	private void assertWeatherContains(Weather expectedWeather, List<Weather> actualWeather) {
		Weather resolvedWeather = null;
		
		for(Weather w : actualWeather) {
			if(expectedWeather.getForecast().equals(w.getForecast())) {
				resolvedWeather = w;
				break;
			}
		}
		
		
		if(resolvedWeather == null) {
			fail(String.format("The collection does not contain the expected forecast %s", expectedWeather.getForecast()));
			return;
		}
		
		assertEquals(expectedWeather.getDay(), resolvedWeather.getDay());
	}

}
