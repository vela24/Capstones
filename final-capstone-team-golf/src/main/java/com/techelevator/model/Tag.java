package com.techelevator.model;

public class Tag {
	
	private Long tagId;
	private int recipeId;
	private String tagName;
	
	

	public Long getTag() {
		return tagId;
	}
	public void setTag(Long id) {
		this.tagId = id;
	}
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


}
