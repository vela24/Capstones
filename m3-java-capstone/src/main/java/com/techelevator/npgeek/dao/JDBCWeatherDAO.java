package com.techelevator.npgeek.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.dao.model.Weather;

@Component
public class JDBCWeatherDAO implements WeatherDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCWeatherDAO(DataSource data) {
		this.jdbcTemplate = new JdbcTemplate(data);
	}

	
	@Override
	public List<Weather> getFiveDayForecast(String parkCode) {
		List<Weather> weather = new ArrayList<Weather>();
		String sql = "SELECT w.parkcode, fivedayforecastvalue, low, high, forecast "
				+ "FROM weather w "
				+ "JOIN park p on p.parkcode = w.parkcode where w.parkcode = ? "
				+ "ORDER BY fivedayforecastvalue ASC;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkCode);
		
		while(results.next()) {
			weather.add(mapRowtoWeather(results));
		}
		
		return weather;
	}
	
	
	private Weather mapRowtoWeather(SqlRowSet results) {
		Weather daily = new Weather();
		daily.setParkcode(results.getString("parkcode"));
		daily.setDay(results.getInt("fivedayforecastvalue"));
		daily.setHighTemp(results.getDouble("high"));
		daily.setLowTemp(results.getDouble("low"));
		daily.setForecast(results.getString("forecast"));
		
		return daily;
	}

}
