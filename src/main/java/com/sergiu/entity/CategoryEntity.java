package com.sergiu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "subject_exam")
	private String subjectExam;

	@Column(name = "language_exam")
	private String languageExam;

	@Column(name = "type_exam")
	private String typeExam;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubjectExam() {
		return subjectExam;
	}

	public void setSubjectExam(String subjectExam) {
		this.subjectExam = subjectExam;
	}

	public String getLanguageExam() {
		return languageExam;
	}

	public void setLanguageExam(String languageExam) {
		this.languageExam = languageExam;
	}

	public String getTypeExam() {
		return typeExam;
	}

	public void setTypeExam(String typeExam) {
		this.typeExam = typeExam;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((languageExam == null) ? 0 : languageExam.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subjectExam == null) ? 0 : subjectExam.hashCode());
		result = prime * result + ((typeExam == null) ? 0 : typeExam.hashCode());
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
		CategoryEntity other = (CategoryEntity) obj;
		if (languageExam == null) {
			if (other.languageExam != null)
				return false;
		} else if (!languageExam.equals(other.languageExam))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subjectExam == null) {
			if (other.subjectExam != null)
				return false;
		} else if (!subjectExam.equals(other.subjectExam))
			return false;
		if (typeExam == null) {
			if (other.typeExam != null)
				return false;
		} else if (!typeExam.equals(other.typeExam))
			return false;
		return true;
	}
}
