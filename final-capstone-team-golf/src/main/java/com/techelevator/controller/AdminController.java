package com.techelevator.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDAO;
import com.techelevator.model.Tag;
import com.techelevator.model.TagDao;


@Controller
public class AdminController {
	
	@Autowired
	private RecipeDAO recipeDAO;
	
	@Autowired
	private TagDao tagDAO;
	

	
	@RequestMapping(path="/recipeAdminCreationPage", method=RequestMethod.GET)
	public String displayRecipeTable(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("recipe")) {
			modelHolder.addAttribute("recipe", new Recipe());
		}
		return "recipeAdminCreationPage";
	}
	
	@RequestMapping(path="/recipeAdminCreationPage", method=RequestMethod.POST)
	public String createRecipe(HttpServletRequest request,@RequestParam (name="recipeName") String recipeName, 
						@RequestParam (name="ingredients") String ingredients,
						@RequestParam (name="instructions") String instructions,
						@RequestParam (name="prepTime") int prepTime,
						@RequestParam (name="servingSize") int servingSize,@RequestParam(name="tags") String tags,
						ModelMap model) {
		Recipe myRecipe = new Recipe();
		myRecipe.setRecipeName(recipeName);
		myRecipe.setServingSize(servingSize);
		myRecipe.setPrepTime(prepTime);
		myRecipe.setIngredients(ingredients);
		myRecipe.setInstructions(instructions);
		myRecipe.setAdminCreated(true);
		myRecipe.setUserCreated(false);
		myRecipe.setUserUploadedImage(false);
		recipeDAO.createRecipe(myRecipe);
		separateTags(tags, myRecipe);
		
		
			return "redirect:/uploadForm?recipeId="+myRecipe.getRecipeId();
	}
	
	@RequestMapping(path="/approveRecipes", method=RequestMethod.GET) 
	public String displayRecipeTypes(HttpServletRequest request) {
		request.setAttribute("recipes", recipeDAO.getUnapprovedRecipes());
		return "approveRecipes";
	}
	@RequestMapping(path="/approveRecipes", method=RequestMethod.POST) 
	public String displayAFterApprove(HttpServletRequest request,@RequestParam long recipeId) {
		recipeDAO.approveRecipe(recipeId);
		return "redirect:/approveRecipes";
	}
	
	private void separateTags(String tags,Recipe recipe) {
		StringTokenizer tokenizer=new StringTokenizer(tags,",");
		while(tokenizer.hasMoreTokens()) {
			String newTag = tokenizer.nextToken();
			if(tagDAO.searchForTag(newTag)== true) {
				Recipe myNewRecipe = recipeDAO.getRecipeByName(recipe.getRecipeName());
				Tag myTag = tagDAO.getTagIdByName(newTag);
				tagDAO.submitTag(myTag.getTagId(), myNewRecipe.getRecipeId());
			}
			else {
				Tag myNewTag = new Tag();
				myNewTag.setTagName(newTag);
				tagDAO.createTag(myNewTag);
				Recipe myNewRecipe = recipeDAO.getRecipeByName(recipe.getRecipeName());
				Tag myTag = tagDAO.getTagIdByName(newTag);
				tagDAO.submitTag(myTag.getTagId(), myNewRecipe.getRecipeId());
			}
		}
	}
	
}
