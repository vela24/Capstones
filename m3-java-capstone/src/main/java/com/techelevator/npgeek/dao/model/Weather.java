package com.techelevator.npgeek.dao.model;

public class Weather {
	
	private String parkcode, forecast;
	private double highTemp, lowTemp;
	private int day;
	
	public String getParkcode() {
		return parkcode;
	}
	public void setParkcode(String parkcode) {
		this.parkcode = parkcode;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public double getHighTemp() {
		return highTemp;
	}
	public void setHighTemp(double highTemp) {
		this.highTemp = highTemp;
	}
	public double getLowTemp() {
		return lowTemp;
	}
	public void setLowTemp(double lowTemp) {
		this.lowTemp = lowTemp;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
}
