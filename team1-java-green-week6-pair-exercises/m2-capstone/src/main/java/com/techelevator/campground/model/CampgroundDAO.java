package com.techelevator.campground.model;

import java.util.List;

public interface CampgroundDAO {
  List<Campground>getCampsByParkID(Long id);
	
}


//public List<Campground> getAllCampGrounds(Long id);
//
//public List<Campground> searchByName(String name, Long id);