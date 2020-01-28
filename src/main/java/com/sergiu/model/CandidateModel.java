package com.sergiu.model;


public class CandidateModel {

    private Long cnp;

    private String firstName;

    private String lastName;

    private CategoryModel categoryModel;

    private String highSchool;

    private Double bacGrade;

    private Double bacBestGrade;

    private GradesModel gradesModel;

    private HallModel hallModel;

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

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public Double getBacGrade() {
        return bacGrade;
    }

    public void setBacGrade(Double bacGrade) {
        this.bacGrade = bacGrade;
    }

    public Double getBacBestGrade() {
        return bacBestGrade;
    }

    public void setBacBestGrade(Double bacBestGrade) {
        this.bacBestGrade = bacBestGrade;
    }

    public GradesModel getGradesModel() {
        return gradesModel;
    }

    public void setGradesModel(GradesModel gradesModel) {
        this.gradesModel = gradesModel;
    }

    public HallModel getHallModel() {
        return hallModel;
    }

    public void setHallModel(HallModel hallModel) {
        this.hallModel = hallModel;
    }

    public Double getAverageOnWriteTest() {
        if (this.gradesModel == null) {
            return null;
        }
        Double firstGrade = this.gradesModel.getFirstGrade();
        Double secondGrade = this.gradesModel.getSecondGrade();
        return (firstGrade + secondGrade) / 2;
    }
}
