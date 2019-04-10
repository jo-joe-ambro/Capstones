package com.techelevator;


import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCparkDAO implements ParkDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCparkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	
	@Override
	public List<Park> getPark() {
		List<Park> parks = new ArrayList<>();
		String sqlGetAllParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);
		while(results.next()) {
			Park thePark = new Park();
			thePark.setName(results.getString("name"));
			thePark.setLocation(results.getString("location"));
			thePark.setArea(results.getInt("area"));
			thePark.setVisitors(results.getInt("visitors"));
			thePark.setDescription(results.getString("description"));
			thePark.setEstablish_date(results.getDate("establish_date").toLocalDate());
			parks.add(thePark);
		}
		return parks;
	}
	
	@Override
	public List<Park> searchParkByName(String nameSearch){
		List<Park> park2 = new ArrayList<>();
		
		String sqlSearchParkById = "SELECT * FROM park WHERE upper(name) LIKE ? OR lower(name) LIKE ? OR name LIKE ?";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sqlSearchParkById, "%"+nameSearch+"%", "%"+nameSearch+"%", "%"+nameSearch+"%");
		
		while(row.next()) {
			Park park = new Park();
			park.setPark_id(row.getLong("park_id"));
			park.setName(row.getString("name"));
			park.setLocation(row.getString("location"));
			park.setEstablish_date(row.getDate("establish_date").toLocalDate());
			park.setArea(row.getInt("area"));
			park.setVisitors(row.getInt("visitors"));
			park.setDescription(row.getString("description"));
			park2.add(park);
			
		}
		return park2;
	}
	
	public Park createpark(Long park_id, String name, String location,  LocalDate established_date, int area, int visitors, String description) {
		String sqlGetNextInt = "SELECT nextval('park_park_id_seq')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetNextInt);
		results = jdbcTemplate.queryForRowSet(sqlGetNextInt);
		results.next();
		Long id =  results.getLong(1);
		
		String sqlCreatePark = "INSERT INTO park(park_id, name, location, establish_date, area, visitors, description)" 
				+ "VALUES (?,?,?,?,?,?)";
		
		Park newPark = new Park();
		newPark.setPark_id(id);
		newPark.setName(name);
		newPark.setEstablish_date(established_date);
		newPark.setArea(area);
		newPark.setVisitors(visitors);
		newPark.setDescription(description);
		
		
		
		return newPark;
	}
	
	

}
