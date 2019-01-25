package com.techelevator.campground.model.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.Reservation;
import com.techelevator.campground.model.ReservationDAO;


public class JDBCReservationDAO implements ReservationDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	public long getSiteID(long siteNumber, long campgroundID) {
		long site_id = 0;
		String sql = "select s.site_id from site s, campground c "+
		"where s.campground_id = c.campground_id and s.site_number = ? "+
		"and s.campground_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,siteNumber,campgroundID);
		
		while(results.next()) {
			return results.getLong("site_id");
		}

		return site_id;
		
	}
	@Override
	public Reservation createReservation(Reservation newReservation) {
		String sqlInsertDepartment = "insert into reservation (reservation_id, site_id, name, from_date, to_date, create_Date) VALUES(?, ?, ?, ?, ?, CURRENT_DATE)";
		newReservation.setReservationId(getNextId());
		jdbcTemplate.update(sqlInsertDepartment, newReservation.getReservationId(), newReservation.getSiteId(), newReservation.getName(), newReservation.getFromDate(), newReservation.getToDate());
		return newReservation;
	}	
	@Override
	public List<Reservation>getReservationByRange(Date arrivalDate, Date departureDate, long campgroundID) {
		ArrayList<Reservation> reservationList = new ArrayList<>();

		String sql = "select site.site_id, site_number, max_occupancy, accessible, max_rv_length, utilities, daily_fee, " + 
				"(?::date - ?::date) no_of_days " + 
				"from site " + 
				"join campground on campground.campground_id = site.campground_id " + 
				"where site.campground_id = ? and site_id not in (select site_id from reservation where from_date >= ? and to_date <= ?) limit 5" ; 
	
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql,departureDate,arrivalDate,campgroundID,arrivalDate,departureDate);
		
		while(results.next()) {
			Reservation reservation = mapRowToReservation(results);
			reservationList.add(reservation);
		}
		return reservationList;		
	}

	@Override
	public List<Reservation>getReservationById(long reservationId, long parkId) {
		ArrayList<Reservation> reservationAvailList = new ArrayList<>();

		String sql = "Select r.reservation_id, r.name, r.from_date, r.to_date, c.name camp_name  from reservation r, site s, campground c " + 
				"where s.site_id = r.site_id and s.campground_id = c.campground_id and r.reservation_id = ? and c.park_id = ?" ; 
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reservationId, parkId);
		
		while(results.next()) {
			Reservation reservation = mapRowToConfirmedReserv(results);
			reservationAvailList.add(reservation);
		}
		return reservationAvailList;		
	}

	private long getNextId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the reservation");
		}
	}
	
	 private Reservation mapRowToReservation(SqlRowSet results) {
		 double totalCost = 0;
			Reservation reservation;
			reservation = new Reservation();
			reservation.setSiteId(results.getLong("site_id"));
			reservation.setSiteNumber(results.getLong("site_number"));			
			reservation.setMaxOccupancy(results.getLong("max_occupancy"));			
			reservation.setAccessible(results.getBoolean("accessible"));
			reservation.setMaxRVLength(results.getLong("max_rv_length"));
			reservation.setUtilities(results.getBoolean("utilities"));
			reservation.setDailyFee(results.getDouble("daily_fee"));
			reservation.setNumberOfDays(results.getLong("no_of_days"));
			totalCost = (results.getDouble("daily_fee") * results.getLong("no_of_days"));
			reservation.setTotalCost(new BigDecimal(totalCost));
			return reservation;
		}
	 
	 private Reservation mapRowToConfirmedReserv(SqlRowSet results) {
			Reservation reservation;
			reservation = new Reservation();
			reservation.setReservationId(results.getLong("reservation_Id")); 
			reservation.setName(results.getString("name")); 
			reservation.setFromDate(results.getDate("from_date")); 
			reservation.setToDate(results.getDate("to_date")); 
			reservation.setCampName(results.getString("camp_name"));
			return reservation;
		}	 
	 
/*	@Override
	public Reservation getsiteId(long reservationId) {
		Reservation site = null; 
		String sql = "";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reservationId);
		if (results.next()) {
			site = mapRowToSite(results);
		}
		return site;	
	}
	
	private Reservation mapRowToSite(SqlRowSet results) {
		Reservation site;
		site = new Reservation();
		site.getsetId(results.getLong(""));
		
		return site;
	}
*/
}
