package com.techelevator.npgeek.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.Utility;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.DAO.JDBC.JDBCParkDAO;
import com.techelevator.npgeek.DAO.JDBC.JDBCWeatherDAO;

@Controller
public class DetailsController {
	
	@Autowired
	JDBCParkDAO parkDAO;
	@Autowired
	JDBCWeatherDAO weatherDAO;
	
	
	@RequestMapping("/details")
	public String displayDetails(@RequestParam String parkCode, ModelMap modelHandler, HttpSession session) {
		Park park = parkDAO.getPark(parkCode);
		modelHandler.addAttribute("park", park);
		List<Weather> parkWeather= weatherDAO.getAllWeather(parkCode);
		for (Weather w : parkWeather) {
			if(session.getAttribute("temp").equals("c")) {
				w.setDisplayLowTemp(Utility.convertToCelsius(w.getLowTemp()));
				w.setDisplayHighTemp(Utility.convertToCelsius(w.getHighTemp()));
			}
			if(session.getAttribute("temp").equals("f")) {
				w.setDisplayHighTemp(w.getHighTemp());
				w.setDisplayLowTemp(w.getLowTemp());
			}
		}
		modelHandler.addAttribute("parkWeather", parkWeather);
		return "details";
	}

}
