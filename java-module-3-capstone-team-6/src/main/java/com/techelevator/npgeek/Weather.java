package com.techelevator.npgeek;

public class Weather {
	
	private String parkCode;
	private int day;
	private int lowTemp;
	private int highTemp;
	private String forecast;
	private String recommendation;
	private String forecastImage;
	private int displayHighTemp;
	private int displayLowTemp;
	
	public int getDisplayHighTemp() {
		return displayHighTemp;
	}


	public void setDisplayHighTemp(int displayHighTemp) {
		this.displayHighTemp = displayHighTemp;
	}


	public int getDisplayLowTemp() {
		return displayLowTemp;
	}


	public void setDisplayLowTemp(int displayLowTemp) {
		this.displayLowTemp = displayLowTemp;
	}


	public Weather(String parkCode, int day, int lowTemp,int highTemp, String forecast) {
		this.parkCode = parkCode;
		this.day = day;
		this.lowTemp = lowTemp;
		this.highTemp = highTemp;
		this.forecast = forecast;
		switch(forecast) {
		case "partly cloudy": 
			forecastImage = "partly_cloudy";
			break;
		default:
			forecastImage = forecast;	
		}
		
		switch(forecast) {
		case "snow" :
			recommendation = "Pack Snowshoes!";
			break;
		case "partly cloudy" :
			recommendation = "";
			break;
		case "cloudy" :
			recommendation = "";
			break;
		case "rain" :
			recommendation = "Pack rain gear and wear waterproof shoes";
			break;
		case "thunderstorms" :
			recommendation = "Seek shelter and avoid hiking on exposed ridges";
			break;
		case "sunny" :
			recommendation = "Pack sunblock";
			break;
		default:
			break;
		}
		if(highTemp > 75) {
			if(recommendation.length() > 0) {
				recommendation += "\n";
			}
			recommendation += "Bring an extra gallon of water";
		}
		if(lowTemp < 20) {
			if(recommendation.length() > 0) {
				recommendation += "\n";
			}
			recommendation += "Be careful of frigid temperatures";
		}
		if(highTemp - lowTemp > 20) {
			if(recommendation.length() > 0) {
				recommendation += "\n";
			}
			recommendation += "Wear breathable layers";
		}
	}
	
	
	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}


	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}


	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}


	public void setForecastImage(String forecastImage) {
		this.forecastImage = forecastImage;
	}


	public String getForecastImage() {
		return forecastImage;
	}


	public String getRecommendation() {
		return recommendation;
	}
	public String getParkCode() {
		return parkCode;
	}
	public int getDay() {
		return day;
	}
	public int getLowTemp() {
		return lowTemp;
	}
	public int getHighTemp() {
		return highTemp;
	}
	public String getForecast() {
		return forecast;
	}
}
