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
public class RecipeController {
	
	@Autowired
	private RecipeDAO recipeDAO;
	
	@Autowired
	private TagDao tagDAO;
	
	@Autowired
	ServletContext servletContext;
	
	
	@RequestMapping(path="/recipes", method=RequestMethod.GET) 
	public String displayRecipeTypes(HttpServletRequest request) {
		
		request.setAttribute("recipes", recipeDAO.getAllRecipes());
		
		return "recipes";
	}
	
	@RequestMapping(path="/recipeDetails", method=RequestMethod.GET) 
	public String displayRecipeDetails(HttpServletRequest request) {
		String recipeId = request.getParameter("recipeId");
		Long recipeId1 = Long.valueOf(request.getParameter("recipeId"));
		for(Recipe meals : recipeDAO.getAllRecipes()) {
			if((Long.parseLong(recipeId) == meals.getRecipeId())) {
				request.setAttribute("recipe", meals);
				request.setAttribute("tags", tagDAO.getTagsByRecipeId(recipeId1));
			}
		}
		return "recipeDetails";
	}
	
	@RequestMapping(path="/recipeCreationPage", method=RequestMethod.GET)
	public String displayRecipeTable(ModelMap modelHolder) {
		if( ! modelHolder.containsAttribute("recipe")) {
			modelHolder.addAttribute("recipe", new Recipe());
		}
		return "recipeCreationPage";
	}
	
	@RequestMapping(path="/categoryAndTagAndNameSearch", method=RequestMethod.GET) 
	public String displayRecipeDetailsBySearch(HttpServletRequest request,@RequestParam String search) {
		request.setAttribute("recipes", recipeDAO.getRecipesBySearch(search));
		return "categorySearch";
	}//matt added this
	
	@RequestMapping(path="/recipeCreationPage", method=RequestMethod.POST)
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
		myRecipe.setAdminCreated(false);
		myRecipe.setUserCreated(true);
		myRecipe.setUserUploadedImage(false);
		recipeDAO.createRecipe(myRecipe);
		separateTags(tags, myRecipe);
	
		
			return "redirect:/uploadForm?recipeId="+myRecipe.getRecipeId();
	}
	
	@RequestMapping(path="/uploadForm", method=RequestMethod.GET) 
	public String displayUploadForm(HttpServletRequest request,@RequestParam String recipeId) {
		return "uploadForm";
	}
	
	@RequestMapping(path="/uploadFile", method=RequestMethod.POST)
	public String handleFileUpload(@RequestParam MultipartFile file, ModelMap map,@RequestParam Long recipeId) {
		
		File imagePath = getImageFilePath();
		String imageName = imagePath + File.separator + recipeId;//File.separator just puts in a '/'
		
		
		if (file.isEmpty()) {
			map.addAttribute("message", "File Object empty");
		} else {
			createImage(file, imageName);
			recipeDAO.updateImagePath(String.valueOf(recipeId), recipeId);
			
		}
		map.addAttribute("message", "uploaded to: " + imageName);
		
		
		
		return "redirect:/recipes";
	}
	@RequestMapping(path="/image/{imageName}", method=RequestMethod.GET)
	@ResponseBody
	public byte[] getImage(@PathVariable(value="imageName") String imageName) {
		String imagePath = getServerContextPath() + File.separator + imageName;
		File image = new File(imagePath);
		try {
			return Files.readAllBytes(image.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	private File getImageFilePath() {
		String serverPath = getServerContextPath();
		File filePath = new File(serverPath);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		return filePath;
	}
	
	private String getServerContextPath() {
		return servletContext.getRealPath("/") + "uploads";
	}
	
	private void createImage(MultipartFile file, String name) {
		File image = new File(name);
		try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(image))) {
	
			stream.write(file.getBytes());
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	


