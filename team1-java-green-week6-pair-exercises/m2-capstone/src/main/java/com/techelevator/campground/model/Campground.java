package com.techelevator.campground.model;

public class Campground {

	private Long id;
	private Long setKey;
	private String name;
	private String openFrom;
	private String openTo;
	private double dailyFee;
	private Long parkID;
	

	public Long getParkID() {
		return parkID;
	}
	public void setParkID(Long parkID) {
		this.parkID = parkID;
	}

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
	public String getOpenFrom() {
		return openFrom;
	}
	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}
	public String getOpenTo() {
		return openTo;
	}
	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}
	public double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(double dailyFee) {
		this.dailyFee = dailyFee;
	}
	public void setKey(long setKey) {
		// TODO Auto-generated method stub
		this.setKey = setKey;	
	}

	public Long getSetKey() {
		return setKey;
	}
	
	public Long getID(long setkey) {
		return this.id;
	}		
}
