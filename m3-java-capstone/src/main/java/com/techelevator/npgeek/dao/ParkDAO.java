package com.techelevator.npgeek.dao;

import java.util.List;

import com.techelevator.npgeek.dao.model.Park;

public interface ParkDAO {
	
	/**
	 * Method to retrieve all parks from the database
	 * @return returns a list with all the parks
	 */
	List<Park> getAllParks();
	
	/**
	 * Method to retrieve a park using a park code
	 * @param code - park code of selected park
	 * @return - returns the selected park 
	 */
	Park getParkByParkCode(String code);
}
