package com.sergiu.model;

public class GradeModel {

    private Integer id;

    private CandidateModel candidateModel;

    private Double grade;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CandidateModel getCandidateModel() {
        return candidateModel;
    }

    public void setCandidateModel(CandidateModel candidateModel) {
        this.candidateModel = candidateModel;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
