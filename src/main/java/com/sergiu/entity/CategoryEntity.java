package com.sergiu.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class CategoryEntity implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "discipline")
    private String discipline;

    @Column(name = "language")
    private String language;

    @Column(name = "admission_type")
    private String admissionType;

    @OneToMany(mappedBy = "categoryEntity")
    private List<CandidateEntity> candidateEntities = new ArrayList<>();

    public CategoryEntity() {

    }

    public CategoryEntity(Integer id, String name, String discipline, String language, String admissionType) {
        this.id = id;
        this.name = name;
        this.discipline = discipline;
        this.language = language;
        this.admissionType = admissionType;
    }

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

    public List<CandidateEntity> getCandidateEntities() {
        return candidateEntities;
    }

    public void setCandidateEntities(List<CandidateEntity> candidateEntities) {
        this.candidateEntities = candidateEntities;
    }


    @Override
    public int compareTo(Object o) {

        CategoryEntity categoryEntity = (CategoryEntity) o;
        int sizeCompare = categoryEntity.getCandidateEntities().size() - this.candidateEntities.size();
        if (sizeCompare == 0) {
            return this.id.compareTo(categoryEntity.getId());
        }
        return sizeCompare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryEntity)) return false;
        CategoryEntity that = (CategoryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
