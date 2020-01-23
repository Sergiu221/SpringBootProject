package com.sergiu.model;


import java.util.ArrayList;
import java.util.List;

public class CandidateModel {

    private Long cnp;

    private String firstName;

    private String lastName;

    private CategoryModel categoryModel;

    private String highSchool;

    private Double bacGrade;

    private Double bacBestGrade;

    private List<GradeModel> gradeModelList;

    private HallModel hallModel;

    public CandidateModel() {
        this.gradeModelList = new ArrayList<>();
    }

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

    public List<GradeModel> getGradeModelList() {
        return gradeModelList;
    }

    public void setGradeModelList(List<GradeModel> gradeModelList) {
        this.gradeModelList = gradeModelList;
    }

    public HallModel getHallModel() {
        return hallModel;
    }

    public void setHallModel(HallModel hallModel) {
        this.hallModel = hallModel;
    }

    public Double getAverageOnWriteTest() {
        Double firstGrade = this.gradeModelList.get(0).getGrade();
        Double secondGrade = this.gradeModelList.get(1).getGrade();
        return (firstGrade + secondGrade) / 2;
    }
}
