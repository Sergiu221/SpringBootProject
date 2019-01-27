package com.sergiu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "category")
public class CategoryEntity {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "subject")
	private String subjectExam;

	@Column(name = "language")
	private String languageExam;

	@Column(name = "type")
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
}
