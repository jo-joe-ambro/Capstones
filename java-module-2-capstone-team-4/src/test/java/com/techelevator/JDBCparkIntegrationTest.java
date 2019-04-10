package com.techelevator;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class JDBCparkIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCparkDAO dao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/nationalpark");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new JDBCparkDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void park_by_name() {
		LocalDate establishDate = new Date(3/11/89).toLocalDate();
		Park newPark = getPark("Little Big Horn", "AL", establishDate, 100, 100, "a pleasant place" );
		
		List<Park> testResults = dao.searchParkByName("Little Big Horn");
		
		Assert.assertNotNull(testResults);
		Assert.assertEquals(1, testResults.size());
		Park savedPark = testResults.get(0);
		Assert.assertEquals(newPark, savedPark);
	}
	
	@Test
	public void get_park() {
		LocalDate newDate = new Date(3/8/20).toLocalDate();
		Long newLong = new Long(5);
		Park newPark = dao.createpark(newLong, "CA", "CA", newDate, 5, 5, "CA park");
		
		List<Park> listOfParks = dao.getPark();
		
		Assert.assertEquals(newPark, listOfParks.get(listOfParks.size()-1));
	}
	
	
	
	private Park getPark(String name, String location, LocalDate establish_date, int area, int visitors, String description) {
		Park thePark = new Park();
		thePark.setName(name);
		thePark.setLocation(location);
		thePark.setEstablish_date(establish_date);
		thePark.setArea(area);
		thePark.setVisitors(visitors);
		thePark.setDescription(description);
		return thePark;
	}
	
}
