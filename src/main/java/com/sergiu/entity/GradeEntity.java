package com.sergiu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "grades")
public class GradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @JsonManagedReference("grade")
    @ManyToOne
    @JoinColumn(name = "cnp")
    private CandidateEntity candidateEntity;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "profersor_name")
    private String firstName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public CandidateEntity getCandidateEntity() {
        return candidateEntity;
    }

    public void setCandidateEntity(CandidateEntity candidateEntity) {
        this.candidateEntity = candidateEntity;
    }
}
