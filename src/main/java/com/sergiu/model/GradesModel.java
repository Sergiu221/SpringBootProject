package com.sergiu.model;

import com.sergiu.entity.Candidate;

public class GradesModel {

    private Long cnp;

    private Candidate candidate;

    private Double firstGrade;

    private String nameProfessorOne;

    private Double secondGrade;

    private String nameProfessorTwo;

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Double getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(Double firstGrade) {
        this.firstGrade = firstGrade;
    }

    public String getNameProfessorOne() {
        return nameProfessorOne;
    }

    public void setNameProfessorOne(String nameProfessorOne) {
        this.nameProfessorOne = nameProfessorOne;
    }

    public Double getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(Double secondGrade) {
        this.secondGrade = secondGrade;
    }

    public String getNameProfessorTwo() {
        return nameProfessorTwo;
    }

    public void setNameProfessorTwo(String nameProfessorTwo) {
        this.nameProfessorTwo = nameProfessorTwo;
    }
}
