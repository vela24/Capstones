package com.techelevator.campground.model;

import java.util.Date;
import java.util.List;

public interface ReservationDAO  {
	List<Reservation>getReservationByRange(Date arrivalDate, Date departureDate, long campgroundID);
	
	Reservation createReservation(Reservation newReservation) ;
	
	long getSiteID(long siteNumber, long campgroundID);
	
	List<Reservation>getReservationById(long reservationId, long parkId);
	
}
