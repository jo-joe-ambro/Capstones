package com.techelevator;

import java.util.List;

public interface CampgroundDAO {

	public List<Campground> getCampgrounds(String campSearch);
	public List<Campground> searchCampgroundByName(String nameSearch);
}
