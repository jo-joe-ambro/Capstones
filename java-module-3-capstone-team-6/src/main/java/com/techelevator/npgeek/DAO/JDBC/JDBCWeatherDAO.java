package com.techelevator.npgeek.DAO.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.DAO.WeatherDAO;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired 
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> getAllWeather(String parkcode) {
		String sql="SELECT * "
				+ "FROM weather WHERE parkcode = ?;";
		SqlRowSet results =jdbcTemplate.queryForRowSet(sql, parkcode);
		
		List<Weather> newWeather = new ArrayList<Weather>();
		while(results.next()) {
			newWeather.add(mapRowToWeather(results));
		}
		return newWeather;
	}

	private Weather mapRowToWeather(SqlRowSet row) {
		String parkCode = row.getString("parkcode");
		int day = row.getInt("fivedayforecastvalue");
		int low = row.getInt("low");
		int high = row.getInt("high");
		String forecast = row.getString("forecast");
		return new Weather(parkCode, day, low, high, forecast);
	}
}
