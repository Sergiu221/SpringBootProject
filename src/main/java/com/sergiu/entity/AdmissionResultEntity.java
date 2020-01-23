package com.sergiu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sergiu.util.ListAllocationType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admission_results")
public class AdmissionResultEntity implements Serializable {

    @Id
    @Column(name = "cnp_candidate")
    private Long candidateCnp;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "bac_grade")
    private Double bacGrade;

    @Column(name = "test_grade")
    private Double testGrade;

    @Column(name = "bac_best_Grade")
    private Double bacBestGrade;

    @Column(name = "final_Grade")
    private Double finalGrade;

    @Enumerated(EnumType.STRING)
    private ListAllocationType listName;

    public Long getCandidateCnp() {
        return candidateCnp;
    }

    public void setCandidateCnp(Long candidateCnp) {
        this.candidateCnp = candidateCnp;
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

    public ListAllocationType getListName() {
        return listName;
    }

    public void setListName(ListAllocationType listName) {
        this.listName = listName;
    }
}
