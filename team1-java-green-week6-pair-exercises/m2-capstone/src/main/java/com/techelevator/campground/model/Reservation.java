package com.techelevator.campground.model;

import java.math.BigDecimal;
import java.util.Date;

public class Reservation {
	private Long reservationId;
	private Long SiteId;
	private Long SiteNumber;

	private String name;
	private Date fromDate;
	private Date toDate;
	private Date createDate;
	
	private boolean accessible;
	private Long maxRVLength;
	private boolean utilities;
	private double dailyFee;
	private Long numberOfDays;
	private BigDecimal totalCost;
	private String campName;
	
	
	/*public String toString() {
	return lastName + " " + firstName;
}*/
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}

	public Long getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Long numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	private Long maxOccupancy;
	public Long getMaxOccupancy() {
		return maxOccupancy;
	}
	public void setMaxOccupancy(Long maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public Long getMaxRVLength() {
		return maxRVLength;
	}
	public void setMaxRVLength(Long max_rv_length) {
		this.maxRVLength = max_rv_length;
	}
	public boolean isUtilities() {
		return utilities;
	}
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	public double getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(double daily_fee) {
		this.dailyFee = dailyFee;
	}

	public Long getSiteNumber() {
		return SiteNumber;
	}
	public void setSiteNumber(Long siteNumber) {
		SiteNumber = siteNumber;
	}
	public Long getSiteId() {
		return SiteId;
	}
	public void setSiteId(Long siteId) {
		SiteId = siteId;
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

	
	
}
