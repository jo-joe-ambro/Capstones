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
import org.springframework.jdbc.support.rowset.SqlRowSet;





public class JDBCcampgroundIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCcampgroundDAO dao;
	
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
		dao = new JDBCcampgroundDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void campground_by_name () {
		String fromdate = "07";
		String todate = "09";
		Campground newCampground = getCampground("Flat Rock", fromdate, todate, 20.0);
		dao.createCampground(newCampground);
		
		List<Campground> testResults = dao.searchCampgroundByName("Flat Rock");
		
		Assert.assertNotNull(testResults);
		Assert.assertEquals(1, testResults.size());
		Campground savedCampground = testResults.get(0);
		Assert.assertEquals(newCampground, savedCampground);
	}
	
	
	
	private Campground getCampground(String name, String open_from_mm, String open_to_mm, Double fee) {
		Campground theCampground = new Campground();
		theCampground.setName(name);
		theCampground.setOpen_from_mm(open_from_mm);
		theCampground.setOpen_to_mm(open_to_mm);
		theCampground.setDaily_fee(fee);
		return theCampground;
	}

}
