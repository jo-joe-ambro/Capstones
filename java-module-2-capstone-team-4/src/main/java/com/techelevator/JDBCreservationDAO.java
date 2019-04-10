package com.techelevator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



public class JDBCreservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCreservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Reservation> searchByAvailableReservation(String campground, LocalDate from_date, LocalDate to_date) {
		List<Reservation> theReservation = new ArrayList<>();

		String sqlGetAllReservations = "SELECT * FROM reservation JOIN site ON site.site_id = reservation.site_id "
				+ "JOIN site ON campground.campground_id = site.campground_id"
				+ "  WHERE campground.name LIKE ? AND from_date=? AND to_date=?";
		Date startDate = Date.valueOf(to_date);
		Date endDate = Date.valueOf(from_date);
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllReservations, "%"+campground+"%", startDate, endDate);
		
		while(results.next()) {
		Reservation rev = new Reservation();
		rev.setReservation_id(results.getLong("reservation_id"));
		rev.setSite_id(results.getLong("site_id"));
		rev.setName(results.getString("name"));
		rev.setFrom_date(results.getDate("from_date").toLocalDate());
		rev.setTo_date(results.getDate("to_date").toLocalDate());
		rev.setCreate_date(results.getDate("create_date").toLocalDate());
		theReservation.add(rev);

		}
		return theReservation;
	}

	public Reservation createReservation(String ReservationName, Long site_id, LocalDate from_date, LocalDate to_date) {

		Reservation newReservation = new Reservation();

		String sqlGetNextInt = "SELECT nextval('reservation_reservation_id_seq')";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetNextInt);
		result = jdbcTemplate.queryForRowSet(sqlGetNextInt);
		result.next();
		Long id = result.getLong(1);

		String sqlCreateReservation = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date, create_date)"
				+ "VALUES (?,?,?,?,?,?)";

		newReservation.setReservation_id(id);
		newReservation.setSite_id(site_id);
		newReservation.setName(ReservationName);
		newReservation.setTo_date(to_date);
		newReservation.setFrom_date(from_date);
		newReservation.setCreateDate(LocalDate.now());
		return newReservation;
		
	}
	public List<Reservation> availableReservation(String campground, LocalDate to_date, LocalDate from_date){
		List<Reservation> site = new ArrayList<>();
		
		String sqlAvailableReservation = "SELECT site.*, campground.daily_fee FROM site JOIN campground ON site.campground_id = campground.campground_id "
				+ "WHERE site_id NOT IN ( "
				+ " SELECT reservation.site FROM reservation" 
				+ "WHERE (reservation.from_date <= ? AND reservation.start_date >=?)"
				+"		OR (reservation.from_date >= ? AND reservation.start_date >=?)"
				+"		OR (reservation.from_date <= ? AND reservation.start_date <=?)"
				+" OR (reservation.from_date >= ? AND reservation.start_date <= ?)"
				+ ") AND ("
				+ "SELECT campground.campground_id FROM campground WHERE campground.name LIKE ?) = site.campground_id ORDER BY site.site_id LIMIT 5;";
		Date StartDate = Date.valueOf(to_date);
		Date EndDate = Date.valueOf(from_date);
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlAvailableReservation, StartDate, EndDate, "%"+campground+"%");
		
		while(rows.next()) {
			Reservation rev = new Reservation();
			
			rev.setSite_id(rows.getLong("site_id"));
			rev.setCampground_id(rows.getLong("campground_id"));
			rev.setSiteNumber(rows.getLong("site_number"));
			rev.setOccupancy(rows.getLong("max_occupancy"));
			rev.setAccessible(rows.getBoolean("accessible"));
			rev.setMaxRVLength(rows.getLong("max_rv_length"));
			rev.setUtilites(rows.getBoolean("utilities"));
			rev.setDailyFee(rows.getString("daily_fee"));
			site.add(rev);
			
			
		}
		return site;		
	}

	
	
}
