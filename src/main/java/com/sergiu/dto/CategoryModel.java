package com.sergiu.dto;

public class CategoryModel {

	private String name;

	private String subjectExam;

	private String languageExam;

	private String typeExam;

	private int nrCandidates;

	public CategoryModel() {
	}

	public CategoryModel(String name, String subjectExam, String languageExam, String typeExam, int nrCandidates) {
		super();
		this.name = name;
		this.subjectExam = subjectExam;
		this.languageExam = languageExam;
		this.typeExam = typeExam;
		this.nrCandidates = nrCandidates;
	}

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

	public int getNrCandidates() {
		return nrCandidates;
	}

	public void setNrCandidates(int nrCandidates) {
		this.nrCandidates = nrCandidates;
	}
}
