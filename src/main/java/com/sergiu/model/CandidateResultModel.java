package com.sergiu.model;

public class CandidateResultModel implements Comparable {
    private Long cnp;

    private String firstName;

    private String lastName;

    private String admissionType;

    private Double bacGrade;

    private Double testGrade;

    private Double bacBestGrade;

    private Double finalGrade;

    private String listName;

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

    public String getAdmissionType() {
        return admissionType;
    }

    public void setAdmissionType(String admissionType) {
        this.admissionType = admissionType;
    }

    public Double getBacGrade() {
        return bacGrade;
    }

    public void setBacGrade(Double bacGrade) {
        this.bacGrade = bacGrade;
    }

    public Double getTestGrade() {
        return testGrade;
    }

    public void setTestGrade(Double testGrade) {
        this.testGrade = testGrade;
    }

    public Double getBacBestGrade() {
        return bacBestGrade;
    }

    public void setBacBestGrade(Double bacBestGrade) {
        this.bacBestGrade = bacBestGrade;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    @Override
    public int compareTo(Object o) {
        CandidateResultModel obj = (CandidateResultModel) o;
        if (this.finalGrade > obj.getFinalGrade()) {
            return -1;
        }

        if (this.finalGrade < obj.getFinalGrade()) {
            return 1;
        }

        if (this.testGrade > obj.getTestGrade()) {
            return -1;
        }

        if (this.testGrade > obj.getTestGrade()) {
            return 1;
        }

        return 0;
    }
}
