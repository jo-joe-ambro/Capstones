package com.techelevator;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;




public class JDBCcampgroundDAO implements CampgroundDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public JDBCcampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getCampgrounds(String campSearch) {
		ArrayList<Campground> listOfCampgrounds = new ArrayList<>();
		
		String sqlGetListOfCampgrounds = "SELECT * FROM campground JOIN park ON park.park_id=campground.park_id"
				+ "  WHERE upper(park.name) LIKE ? OR lower(park.name) LIKE ? OR park.name LIKE ?"
				+ " ORDER BY campground.name";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetListOfCampgrounds, "%"+campSearch+"%", "%"+campSearch+"%", "%"+campSearch+"%");
		while (results.next()) {
			Campground theCampground = mapRowToCampground(results);
			listOfCampgrounds.add(theCampground);
		}
		return listOfCampgrounds;
	}

	
	
	
	public List<Campground> searchCampgroundByName(String nameSearch) {
		List<Campground> listOfCampgrounds = new ArrayList<>();
		
		String sqlStatement = "SELECT * FROM campground WHERE name LIKE ? OR upper(name) LIKE ? OR lower(name) LIKE ?"; 
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStatement, "%"+nameSearch+"%", "%"+nameSearch+"%", "%"+nameSearch+"%");
		
		while (results.next()) {
			Campground campground = mapRowToCampground(results);
			listOfCampgrounds.add(campground);
		}
		return listOfCampgrounds;
	}
	
	public Campground createCampground(Campground newCampground) {
		String sqlCreateCampground = "INSERT INTO campground(campground_id, park_id, open_from_mm, open_to_mm, daily_fee) VALUES(?,?,?,?,?);";
		newCampground.setCampground_id(getNextCampgroundId());
		jdbcTemplate.update(sqlCreateCampground, newCampground.getCampground_id(), newCampground.getPark_id(), newCampground.getName(), newCampground.getOpen_from_mm(), newCampground.getOpen_to_mm(), newCampground.getDaily_fee());
		return newCampground;
	}
	
	
	private long getNextCampgroundId() {
		SqlRowSet nextCampgroundResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_campground_id')");
		if(nextCampgroundResult.next()) {
			return nextCampgroundResult.getLong(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new employee!!!");
		}
	}
		
		
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground theCampground;
		theCampground = new Campground();
		theCampground.setCampground_id(results.getLong("campground_id"));
		theCampground.setName(results.getString("name"));
		theCampground.setPark_id(results.getLong("park_id"));
		theCampground.setOpen_from_mm(results.getString("open_from_mm"));
		theCampground.setOpen_to_mm(results.getString("open_to_mm"));
		theCampground.setDaily_fee(results.getDouble("daily_fee"));
		return theCampground;
	}
				
}
