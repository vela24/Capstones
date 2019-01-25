package com.techelevator.model;

import java.util.Collections;
import java.util.List;


public class Recipe {
	
	private String recipeName,recipeCreator,ingredients,instructions,imagePath;
	private boolean userCreated,adminCreated,userUploadedImage;
	private Integer prepTime,servingSize;
	private long recipeId;
//	private String recipeType;   

	public long getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public Integer getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}
	public String getRecipeCreator() {
		return recipeCreator;
	}
	public void setRecipeCreator(String recipeCreator) {
		this.recipeCreator = recipeCreator;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public boolean isUserCreated() {
		return userCreated;
	}
	public void setUserCreated(boolean userCreated) {
		this.userCreated = userCreated;
	}
	public boolean isAdminCreated() {
		return adminCreated;
	}
	public void setAdminCreated(boolean adminCreated) {
		this.adminCreated = adminCreated;
	}
	public Integer getServingSize() {
		return servingSize;
	}
	public void setServingSize(Integer servingSize) {
		this.servingSize = servingSize;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public boolean isUserUploadedImage() {
		return userUploadedImage;
	}
	public void setUserUploadedImage(boolean userUploadedImage) {
		this.userUploadedImage = userUploadedImage;
	}
	
	
		

}
