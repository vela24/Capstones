package com.techelevator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.model.RecipeDAO;

@Controller 
public class CategoryController {
	
//	@Autowired
//	private CategoryDAO categoryDAO;
	
	@Autowired
	private RecipeDAO recipeDAO;
	
	
//	@RequestMapping(path="/categorySearch", method=RequestMethod.GET) 
//	public String displayRecipeDetails(HttpServletRequest request,@RequestParam int categoryId) {
//		request.setAttribute("recipes", recipeDAO.getRecipeByCategory(categoryId));
//		return "categorySearch";
//	}
	
}
