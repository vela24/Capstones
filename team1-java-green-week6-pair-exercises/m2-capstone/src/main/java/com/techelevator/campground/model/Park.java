package com.techelevator.campground.model;

import java.util.Date;

public class Park {

	private Long id;
	private String name;
	private String location;
	private Date date;
	private Date establishedDate;
	private int area;
	private int visitors;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Date getDate() {
		return date;
	}
	
	public Date getestablishedDate() {
		return establishedDate;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getVisitors() {
		return visitors;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
		
	}
	public void setAnnualVisitorsCount(int visitors) {
		this.visitors = visitors;
		
	}
	
	
}
