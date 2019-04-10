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

public class JDBCreservationIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCreservationDAO dao;
	
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
		dao = new JDBCreservationDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test 
	public void get_search_available_reservation() {
			Date fromdate = new Date(5/1/94);
			Date todate = new Date(3/11/89);
			Date createdate = new Date(4/3/86);
			Long newLong = new Long(46);
			dao.createReservation("Old Custer", newLong, fromdate.toLocalDate(), todate.toLocalDate());
			Reservation newReservation = getReservation(newLong, "Old Custer", fromdate.toLocalDate(), todate.toLocalDate(), createdate.toLocalDate());
			
			List<Reservation> testResults = dao.searchByAvailableReservation("Old Custer", fromdate.toLocalDate(), todate.toLocalDate());
			
			Assert.assertNotNull(testResults);
			Assert.assertEquals(1, testResults.size());
			Reservation savedCampground = testResults.get(0);
			Assert.assertEquals(newReservation, savedCampground);
	}
	
	
	
	private Reservation getReservation(Long site_id, String name, LocalDate from_date, LocalDate to_date, LocalDate create_date) {

		Reservation theReservation = new Reservation();
		theReservation.setSite_id(site_id);
		theReservation.setName(name);
		theReservation.setFrom_date(from_date);
		theReservation.setTo_date(to_date);
		theReservation.setCreate_date(create_date);
		return theReservation;
	}
}
