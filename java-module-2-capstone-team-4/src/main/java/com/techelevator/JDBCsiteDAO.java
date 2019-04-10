package com.techelevator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



public class JDBCsiteDAO implements SiteDAO {
	
	public JDBCsiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate jdbcTemplate;
	
	
	public List<Site> returnListOfSitesByStartAndEndDate (Date inputStartDate, Date inputEndDate) {
		List<Site> listOfSites = new ArrayList<>();
		String sqlStatement = "SELECT * FROM site JOIN site ON site.site_id = reservation.site_id WHERE ? >= reservation.from_date AND WHERE ? <= reservation.to_date " +
		"LIMIT 5;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlStatement, inputStartDate, inputEndDate);
		while (results.next()) {
			Site site = mapRowToSite(results);
			listOfSites.add(site);
		}
		return listOfSites;
	}
	
	
	
	
	
	
	
	

private Site mapRowToSite(SqlRowSet results) {
	Site theSite;
	theSite = new Site();
	theSite.setSite_id(results.getLong("site_id"));
	theSite.setCampground_id(results.getLong("campground_id"));
	theSite.setSite_number(results.getLong("site_number"));
	theSite.setMax_occupancy(results.getInt("max_occupancy"));
	theSite.setAccessible(results.getBoolean("accessible"));
	theSite.setMax_rv_length(results.getInt("max_rv_length"));
	theSite.setUtilities(results.getBoolean("utilities"));
	return theSite;
	}
}
