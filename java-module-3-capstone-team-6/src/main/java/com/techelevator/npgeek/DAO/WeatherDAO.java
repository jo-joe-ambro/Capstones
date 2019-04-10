package com.techelevator.npgeek.DAO;

import java.util.List;

import com.techelevator.npgeek.Weather;

public interface WeatherDAO {
	
	List<Weather> getAllWeather(String parkcode);
}
