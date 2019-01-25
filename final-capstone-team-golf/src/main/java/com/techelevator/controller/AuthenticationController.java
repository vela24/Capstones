package com.techelevator.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {

	private UserDAO userDAO;

	@Autowired
	public AuthenticationController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String displayLoginForm(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("login")) {
			modelHolder.addAttribute("login", new User());
		}
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String login(@RequestParam String userName, 
						@RequestParam String password, 
						HttpSession session,ModelMap model,HttpSession request) {
		if(userDAO.searchForUsernameAndPassword(userName, password)) {
			session.setAttribute("currentUser", userDAO.getUserByUserName(userName));
			model.addAttribute("currentUser", userDAO.getUserByUserName(userName));
			request.setAttribute("userName", userDAO.getUserByUserName(userName));
			
			return "redirect:/";
		}else {
		return "redirect:/login?success=false";
		}
	}

	@RequestMapping(path="/logout", method=RequestMethod.POST)
	public String logout(ModelMap model, HttpSession session) {
		model.remove("currentUser");
		session.removeAttribute("currentUser");
		session.invalidate();
		return "redirect:/";
	}
}
