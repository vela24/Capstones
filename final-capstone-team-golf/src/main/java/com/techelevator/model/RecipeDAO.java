package com.techelevator.model;

import java.util.List;

public interface RecipeDAO {
	
	public List<Recipe> getAllRecipes();
	
	public void createRecipe(Recipe recipe);
	
	public List<Recipe> getRecipeByTag(int tagId);
	
	public List<Recipe> getRecipesBySearch(String search);//added this
	
	public List<Recipe> getRecipeByCategory(int categoryId);
	
	public Recipe getRecipeById(long recipeId);
	
	public List<Recipe> getUnapprovedRecipes();
	
	public void approveRecipe(long recipeId);

	Recipe getRecipeByName(String name);

	void updateImagePath(String imagePath, long recipeId);
	


	
}
