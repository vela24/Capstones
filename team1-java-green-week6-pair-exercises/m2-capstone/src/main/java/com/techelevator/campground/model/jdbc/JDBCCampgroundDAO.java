package com.techelevator.campground.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.campground.model.Campground;
import com.techelevator.campground.model.CampgroundDAO;
import com.techelevator.campground.model.Park;
import com.techelevator.campground.model.Reservation;

public class JDBCCampgroundDAO implements CampgroundDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
@Override
	public List<Campground> getCampsByParkID(Long parkID) {
		ArrayList<Campground> camp = new ArrayList<>();
		String sql = "Select row_number() OVER(order by c.name) as row_key, c.campground_id, c.name, " + 
				" to_char(to_timestamp (c.open_from_mm::text, 'MM'), 'Month') from_month, " +
				"to_char(to_timestamp (c.open_to_mm::text, 'MM'), 'Month') to_month, " + 
				"c.daily_fee " + 
				"from campground c, park p where c.park_id = p.park_id and c.park_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkID);
		while(results.next()) {
			Campground campground = mapRowToCamp(results);
			camp.add(campground);
		}
		return camp;	
	}

	
	private Campground mapRowToCamp(SqlRowSet results) {
		Campground camp;
		camp = new Campground();
		camp.setKey(results.getLong("row_key"));
		camp.setId(results.getLong("campground_id"));
		camp.setName(results.getString("name"));
		camp.setOpenFrom(results.getString("from_month"));
		camp.setOpenTo(results.getString("to_month"));
		camp.setDailyFee(results.getDouble("daily_fee"));
		return camp;
	}

	
	

}
