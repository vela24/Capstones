package com.techelevator.npgeek.dao.model;

import java.util.List;

public class Park {

	private String parkCode, parkName, state, climate, quote, quoteSource, description;
	
	private Long acreage, elevationInFeet, numOfCampsites, yearFounded, annualVisitorCount, entryFee, numOfAnimalSpecies;
	
	private Double  milesOfTrail;

	public String getParkCode() {
		return parkCode;
	}

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getQuoteSource() {
		return quoteSource;
	}

	public void setQuoteSource(String quoteSource) {
		this.quoteSource = quoteSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAcreage() {
		return acreage;
	}

	public void setAcreage(Long acreage) {
		this.acreage = acreage;
	}

	public Long getElevationInFeet() {
		return elevationInFeet;
	}

	public void setElevationInFeet(Long elevationInFeet) {
		this.elevationInFeet = elevationInFeet;
	}

	public Double getMilesOfTrail() {
		return milesOfTrail;
	}

	public void setMilesOfTrail(Double milesOfTrail) {
		this.milesOfTrail = milesOfTrail;
	}

	public Long getNumOfCampsites() {
		return numOfCampsites;
	}

	public void setNumOfCampsites(Long numOfCampsites) {
		this.numOfCampsites = numOfCampsites;
	}

	public Long getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(Long yearFounded) {
		this.yearFounded = yearFounded;
	}

	public Long getAnnualVisitorCount() {
		return annualVisitorCount;
	}

	public void setAnnualVisitorCount(Long annualVisitorCount) {
		this.annualVisitorCount = annualVisitorCount;
	}

	public Long getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(Long entryFee) {
		this.entryFee = entryFee;
	}

	public Long getNumOfAnimalSpecies() {
		return numOfAnimalSpecies;
	}

	public void setNumOfAnimalSpecies(Long numOfAnimalSpecies) {
		this.numOfAnimalSpecies = numOfAnimalSpecies;
	}
	
}
