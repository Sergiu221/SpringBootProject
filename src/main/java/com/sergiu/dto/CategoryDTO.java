package com.sergiu.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO implements Serializable {

    private Integer id;

    private String name;

    private String discipline;

    private String language;

    private String admissionType;

    private List<CandidateDTO> candidateEntities = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public List<CandidateDTO> getCandidateEntities() {
        return candidateEntities;
    }

    public void setCandidateEntities(List<CandidateDTO> candidateEntities) {
        this.candidateEntities = candidateEntities;
    }
}
