package com.techelevator.npgeek.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.DAO.JDBC.JDBCParkDAO;

@Controller
public class HomepageController {
	
	@Autowired
	JDBCParkDAO parkDao;
	
	@RequestMapping("/")
	public String displayHomepage(ModelMap modelHolder, HttpSession session) {
		if(session.getAttribute("temp") == null) {
			session.setAttribute("temp", "f");
		}
		modelHolder.addAttribute("parks", parkDao.getAllParks()); 
		return "homepage";
	}
	
	@RequestMapping(path="/temp", method=RequestMethod.POST)
	public String setTemp(@RequestParam String temp, HttpSession session) {
		if(temp.equals("f")) {
			session.setAttribute("temp", "f");;
		}
		if(temp.equals("c")) {
			session.setAttribute("temp", "c");
		}
		return "redirect:/";
		
		
}

}

