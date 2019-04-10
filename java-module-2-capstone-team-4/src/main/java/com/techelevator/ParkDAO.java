package com.techelevator;

import java.util.List;

public interface ParkDAO {
	public List<Park> searchParkByName(String nameSearch);
	public List<Park> getPark();

}
