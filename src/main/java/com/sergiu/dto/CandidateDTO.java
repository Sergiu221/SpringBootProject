package com.sergiu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CandidateDTO implements Serializable {

    private Long cnp;

    private String firstName;

    private String lastName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CategoryDTO categoryDTO;

    private String highSchool;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private HallDTO hallDTO;

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
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

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public HallDTO getHallDTO() {
        return hallDTO;
    }

    public void setHallDTO(HallDTO hallDTO) {
        this.hallDTO = hallDTO;
    }
}