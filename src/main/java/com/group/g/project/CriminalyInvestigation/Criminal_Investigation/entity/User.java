package com.group.g.project.CriminalyInvestigation.Criminal_Investigation.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "Mobile_number")
	private String mobile;

	@Column(name = "work_area")
	private String area;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "cases_users",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "case_id"))
	private Collection<Case> cases;
	@OneToMany(mappedBy="user",
			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private Collection<Evidence> evidence;

	public User() {
	}

	public User(String userName, String password, String firstName, String lastName, String email) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(Long id, String userName, String password, String firstName, String lastName, String email, String mobile, String area, Collection<Role> roles) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.area = area;
		this.roles = roles;
	}

	public User(String userName, String password, String firstName, String lastName, String email,
				Collection<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
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

	public Collection<Case> getCases() {
		return cases;
	}

	public void setCases(Collection<Case> cases) {
		this.cases = cases;
	}

	public Collection<Evidence> getEvidence() {
		return evidence;
	}

	public void setEvidence(Collection<Evidence> evidence) {
		this.evidence = evidence;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", area='" + area + '\'' +
				", roles=" + roles +
				", cases=" + cases +
				", evidence=" + evidence +
				'}';
	}
}
