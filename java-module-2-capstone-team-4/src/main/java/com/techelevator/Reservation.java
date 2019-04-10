package com.techelevator;

import java.sql.Date;
import java.time.LocalDate;

public class Reservation {
	
	private Long reservation_id;
	private Long site_id;
	private String name;
	private LocalDate from_date;
	private LocalDate to_date;
	private LocalDate create_date;
	private String dailyFee;
	private Long maxCapicity;
	private Boolean accessible;
	private Long maxRVLength;
	private Boolean utilites;
	private Long campground_id;
	private Long siteNumber;
	private Long occupancy;
	private LocalDate createDate;
	
	
	public Long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public Long getSite_id() {
		return site_id;
	}
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getFrom_date() {
		return from_date;
	}
	public void setFrom_date(LocalDate from_date) {
		this.from_date = from_date;
	}
	public LocalDate getTo_date() {
		return to_date;
	}
	public void setTo_date(LocalDate to_date) {
		this.to_date = to_date;
	}
	public LocalDate getCreate_date() {
		return create_date;
	}
	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
	}
	
	public String getDailyFee() {
		return dailyFee;
	}
	public void setDailyFee(String dailyFee) {
		this.dailyFee = dailyFee;
	}
	public Long getMaxCapicity() {
		return maxCapicity;
	}
	public void setMaxCapicity(Long maxCapicity) {
		this.maxCapicity = maxCapicity;
	}
	public Boolean getAccessible() {
		return accessible;
	}
	public void setAccessible(Boolean accessible) {
		this.accessible = accessible;
	}
	public Long getMaxRVLength() {
		return maxRVLength;
	}
	public void setMaxRVLength(Long maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	public Boolean getUtilites() {
		return utilites;
	}
	public void setUtilites(Boolean utilites) {
		this.utilites = utilites;
	}
	public Long getCampground_id() {
		return campground_id;
	}
	public void setCampground_id(Long campground_id) {
		this.campground_id = campground_id;
	}
	public Long getSiteNumber() {
		return siteNumber;
	}
	public void setSiteNumber(Long siteNumber) {
		this.siteNumber = siteNumber;
	}
	public Long getOccupancy() {
		return occupancy;
	}
	public void setOccupancy(Long occupancy) {
		this.occupancy = occupancy;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	
}
