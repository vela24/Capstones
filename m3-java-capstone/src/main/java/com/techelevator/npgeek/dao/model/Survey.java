package com.techelevator.npgeek.dao.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	private Long surveyId;
	private String parkCode, state, activityLevel, parkName;
	private int surveyCount;
	
	//@NotBlank(message="Email address is required")
	//@Email(message="Email must be a valid email address")
	private String email;
	
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	public int getSurveyCount() {
		return surveyCount;
	}
	public void setSurveyCount(int surveyCount) {
		this.surveyCount = surveyCount;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	
}
