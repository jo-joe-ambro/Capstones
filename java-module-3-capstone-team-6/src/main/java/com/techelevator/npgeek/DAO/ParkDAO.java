package com.techelevator.npgeek.DAO;

import java.util.List;

import com.techelevator.npgeek.Park;

public interface ParkDAO {

	
	List<Park> getAllParks();
	Park getPark(String parkcode);
	
}
