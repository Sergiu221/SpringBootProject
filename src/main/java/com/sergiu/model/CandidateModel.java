package com.sergiu.model;

public class CandidateModel {

	private int id;

	private String cnp;

	private String firstName;

	private String lastName;

	private String liceu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getLiceu() {
		return liceu;
	}

	public void setLiceu(String liceu) {
		this.liceu = liceu;
	}
}