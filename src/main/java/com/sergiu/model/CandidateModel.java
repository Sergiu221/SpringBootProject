package com.sergiu.model;

public class CandidateModel {

	private int id;

	private String cnp;

	private String firstName;

	private String lastName;

	private String examLanguage;

	private String examField;

	private String examType;

	private String highSchool;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
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

	public String getExamLanguage() {
		return examLanguage;
	}

	public void setExamLanguage(String examLanguage) {
		this.examLanguage = examLanguage;
	}

	public String getExamField() {
		return examField;
	}

	public void setExamField(String examField) {
		this.examField = examField;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}
}