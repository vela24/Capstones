package com.techelevator.model;

import java.util.List;



public interface TagDao {
	
//	Inserts tags into database
	
	public void submitTag(long tag_id, long recipe_id);
	
	public Tag getTagIdByName(String tagName);
	
	public void createTag(Tag tag);
	
	public boolean searchForTag(String tag);
	
	public List<Tag> getTagsByRecipeId(long recipeId);
	
	
	
//	insert tag and recipe into tag_recipe table
	

}
