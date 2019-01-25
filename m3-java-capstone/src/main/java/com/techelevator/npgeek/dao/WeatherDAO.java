package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.npgeek.dao.model.Weather;

public interface WeatherDAO {
	
	/**
	 * Method to retrieve for the 5 day forecast for a specific park by parkcode
	 * @param parkCode - parkcode of the requested park's weather
	 * @return - return the weather associated with the parkcode parameter
	 */
	List<Weather> getFiveDayForecast(String parkCode);

}
