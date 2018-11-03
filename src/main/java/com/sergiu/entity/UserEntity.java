package com.sergiu.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jboss.aerogear.security.otp.api.Base32;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "app_user")
public class UserEntity {

	public UserEntity() {
		this.secret = Base32.random();
		this.registerDate = new Timestamp(new Date().getTime());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_name", length = 30)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 30)
	private String lastName;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "admission_status", length = 30)
	private String admissionStatus;

	@JsonIgnore
	@OneToOne(mappedBy = "user")
	private AdmissionDataEntity admissionData;

	@Column(name = "secret")
	private String secret;

	@Column(name = "register_date")
	private Timestamp registerDate;

	@Column(name = "register_number")
	private Long registerNumber;

	@Column(name = "has2FA")
	private Boolean has2FA;

	/**
	 * Roles are being eagerly loaded here because they are a fairly small
	 * collection of items for this example.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private RoleEntity role;

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public Long getRegisterNumber() {
		return registerNumber;
	}

	public void setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(String admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public AdmissionDataEntity getAdmissionData() {
		return admissionData;
	}

	public void setAdmissionData(AdmissionDataEntity admissionData) {
		this.admissionData = admissionData;
	}

	public String getSecret() {
		return secret;
	}

	public Boolean getHas2FA() {
		return has2FA;
	}

	public void setHas2FA(Boolean has2FA) {
		this.has2FA = has2FA;
	}
}
