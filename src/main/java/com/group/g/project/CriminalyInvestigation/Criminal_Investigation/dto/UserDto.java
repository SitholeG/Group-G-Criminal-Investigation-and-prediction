package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity.Evidence;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.validation.FieldMatch;
import com.group.g.project.CriminalyInvestigation.Criminal_Investigation.validation.ValidEmail;

import java.util.Collection;


@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserDto {

	private long id;
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String mobile;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String area;

	private Collection<Evidence> evidence;

	public UserDto() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Collection<Evidence> getEvidence() {
		return evidence;
	}

	public void setEvidence(Collection<Evidence> evidence) {
		this.evidence = evidence;
	}

	@Override
	public String toString() {
		return "UserDto{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", matchingPassword='" + matchingPassword + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", area='" + area + '\'' +
				", evidence=" + evidence +
				'}';
	}
}
