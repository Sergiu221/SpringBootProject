package com.sergiu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category_view")
public class CategoryViewEntity {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "subject_exam")
	private String subjectExam;

	@Column(name = "language_exam")
	private String category_view;

	@Column(name = "type_exam")
	private String typeExam;

	@Column(name = "nr_candidates")
	private String nrCandidates;

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

	public String getCategory_view() {
		return category_view;
	}

	public void setCategory_view(String category_view) {
		this.category_view = category_view;
	}

	public String getTypeExam() {
		return typeExam;
	}

	public void setTypeExam(String typeExam) {
		this.typeExam = typeExam;
	}

	public String getNrCandidates() {
		return nrCandidates;
	}

	public void setNrCandidates(String nrCandidates) {
		this.nrCandidates = nrCandidates;
	}
}
