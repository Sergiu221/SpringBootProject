package com.sergiu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "candidates")
public class CandidateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "cnp")
	private String cnp;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "exam_language")
	private String examLanguage;

	@Column(name = "exam_field")
	private String examField;

	@Column(name = "exam_type")
	private String examType;

	@Column(name = "high_school")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnp == null) ? 0 : cnp.hashCode());
		result = prime * result + ((examField == null) ? 0 : examField.hashCode());
		result = prime * result + ((examLanguage == null) ? 0 : examLanguage.hashCode());
		result = prime * result + ((examType == null) ? 0 : examType.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((highSchool == null) ? 0 : highSchool.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateEntity other = (CandidateEntity) obj;
		if (cnp == null) {
			if (other.cnp != null)
				return false;
		} else if (!cnp.equals(other.cnp))
			return false;
		if (examField == null) {
			if (other.examField != null)
				return false;
		} else if (!examField.equals(other.examField))
			return false;
		if (examLanguage == null) {
			if (other.examLanguage != null)
				return false;
		} else if (!examLanguage.equals(other.examLanguage))
			return false;
		if (examType == null) {
			if (other.examType != null)
				return false;
		} else if (!examType.equals(other.examType))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (highSchool == null) {
			if (other.highSchool != null)
				return false;
		} else if (!highSchool.equals(other.highSchool))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
