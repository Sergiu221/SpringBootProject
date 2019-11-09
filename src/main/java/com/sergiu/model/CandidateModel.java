package com.sergiu.model;

public class CandidateModel {

    private Long cnp;

    private String firstName;

    private String lastName;

    private CategoryModel category;

    private String highSchool;

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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }
}