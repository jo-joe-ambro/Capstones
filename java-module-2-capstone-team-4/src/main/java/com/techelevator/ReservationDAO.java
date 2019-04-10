package com.techelevator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
	public Reservation createReservation(String ReservationName, Long site_id, LocalDate from_date, LocalDate to_date);
	public List<Reservation> availableReservation(String campground, LocalDate to_date, LocalDate from_date);
	public List<Reservation> searchByAvailableReservation(String campground, LocalDate from_date, LocalDate to_date);

}
