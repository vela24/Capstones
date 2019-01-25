package com.techelevator.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {
	
	//@Email(message="Must be a valid email address")
	@NotBlank(message=" * Username/Email required") @Email(message="Must be a valid email address")
	private String userName;
	
	@Size(min=10, message="Password too short, must be at least 10")
//	@Pattern.List({
//		@Pattern(regexp=".*[a-z].*", message="Must have a lower case"),
//		@Pattern(regexp=".*[A-Z].*", message="Must have a capital")
//	})
	private String password;
	private String role;
	private String confirmPassword;
	private boolean admin;
	
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getUserName() {
		return userName;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String toString() { 
		return this.userName; 
	}
}
