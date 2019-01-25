package com.techelevator.npgeek;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeek.dao.JDBCParkDAO;
import com.techelevator.npgeek.dao.JDBCSurveyDAO;
import com.techelevator.npgeek.dao.JDBCWeatherDAO;
import com.techelevator.npgeek.dao.model.Park;
import com.techelevator.npgeek.dao.model.Survey;

@Controller
@SessionAttributes({"temp"})
public class MainController {
	
	@Autowired
	private JDBCParkDAO parkDao;
	
	@Autowired
	private JDBCWeatherDAO weatherDao;
	
	@Autowired
	private JDBCSurveyDAO surveyDao;
	
	/*
	 * Method to display the home page
	 */
	@RequestMapping(path={"/","/home"})
	public String displayHomePage(HttpServletRequest request) {
		request.setAttribute("parks", parkDao.getAllParks());
		
		return "home";
	}
	
	
	/**
	 * Method to display the park detail page, once a img has been clicked from the home page
	 * or the temp setting has been changed from the detail page
	 * @param request - HttpServletRequest object
	 * @param model - ModelMap object
	 * @return - returns the parkDetail page
	 */
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String displayParkDetail(HttpServletRequest request, ModelMap model) {
		String parkCode = request.getParameter("parkcode");
		
		request.setAttribute("park", parkDao.getParkByParkCode(parkCode));
		request.setAttribute("weatherList", weatherDao.getFiveDayForecast(parkCode));
		
		return "parkDetail";
	}
	
	/**
	 * Method used to toggle the temp change button 
	 * @param tempF - variable indicating requested temp display either 'F' or 'C'
	 * @param parkCode - the current parkCode of the park on the detail page
	 * @param model - ModelMap object
	 * @return - redirects back to the parkDetail page with the temp now in 'F'
	 */
	@RequestMapping(path="/changeToFarenheit", method=RequestMethod.POST)
	public String changeToFarenheit(@RequestParam String tempF, @RequestParam String parkCode, ModelMap model){
		model.addAttribute("parkcode", parkCode);
		model.addAttribute("temp", tempF);
		 
		return "redirect:/parkDetail";
	}
	
	/**
	 * Method used to toggle the temp change button
	 * @param tempF - variable indicating requested temp display either 'F' or 'C'
	 * @param parkCode - the current parkCode of the park on the detail page
	 * @param model - ModelMap object
	 * @return - redirects to the detailpage with the temp now 'C'
	 */
	@RequestMapping(path="/changeToCelcius", method=RequestMethod.POST)
	public String changeToCelcius(@RequestParam String tempF, @RequestParam String parkCode,ModelMap model){
		model.addAttribute("parkcode", parkCode);
		model.addAttribute("temp", tempF);
	
		return "redirect:/parkDetail";
	}
	
	/**
	 * Displays the survey page 
	 * @param request - HttpServletRequest object
	 * @return - return the survey page
	 */
	@RequestMapping(path="/survey", method=RequestMethod.GET)
	public String displaySurveyPage(HttpServletRequest request) {
		return "survey";
	}
	
	/**
	 * Method to process the survey results after submitted
	 * @param parkCode - parkcode of favorite park that was selected
	 * @param email - email of survey participant
	 * @param stateOfRes - state of residence
	 * @param activityLevel - activity Level of survey participant
	 * @param model - ModelMap object
	 * @return - redirects to the favoriteParks page
	 */
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String processSurveyInfo(@RequestParam String parkCode,
									@RequestParam String email, 
									@RequestParam String stateOfRes,
									@RequestParam String activityLevel,
									ModelMap model) {
		
		Survey survey = new Survey();
		survey.setParkCode(parkCode);
		survey.setEmail(email);
		survey.setState(stateOfRes);
		survey.setActivityLevel(activityLevel);
		
		surveyDao.submitSurvey(survey);
	
		return "redirect:/favoriteParks";
	}
	
	/**
	 * Method to display the favoriteParks page
	 * @param request - HttpServletRequest object
	 * @return - return the favoriteParks page
	 */
	@RequestMapping("/favoriteParks")
	public String favoriteParksPage(HttpServletRequest request) {
		
		request.setAttribute("favorites", surveyDao.retrieveFavoriteParks());
		return "favoriteParks";
	}

}
