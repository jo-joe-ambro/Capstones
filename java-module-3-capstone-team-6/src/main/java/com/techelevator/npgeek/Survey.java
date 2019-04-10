package com.techelevator.npgeek;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	private Long surveyID;
	private String parkCode;
	
	@NotBlank(message="Email is Required")
	@Email(message="Email must be a valid Email address")
	private String email;
	private String emailConfirmation;
	private String state;
	private String activityLevel;
	
	public Long getSurveyID() {
		return surveyID;
	}
	public void setSurveyID(Long surveyID) {
		this.surveyID = surveyID;
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
	public String getEmailConfirmation() {
		return emailConfirmation;
	}
	public void setEmailConfirmation(String emailConfirmation) {
		this.emailConfirmation = emailConfirmation;
	}
	
	private boolean emailMatching;
	public boolean isEmailMatching() {
		if(email != null) {
			return email.equals(emailConfirmation);
		}
		return true;
	}

}
