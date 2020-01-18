package com.sergiu.entity;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
public class CandidateEntity {

    @Id
    @Column(name = "cnp")
    private Long cnp;

    @Column(name = "first_name")
    private String firstName;


    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @Column(name = "high_school")
    private String highSchool;

    @OneToOne
    @JoinTable(name = "distribution", joinColumns = {@JoinColumn(name = "cnp_candidate")}, inverseJoinColumns = {
            @JoinColumn(name = "id_hall")})
    private HallEntity hall;

    public HallEntity getHall() {
        return hall;
    }

    public void setHall(HallEntity hall) {
        this.hall = hall;
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

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }



    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity category) {
        this.categoryEntity = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnp == null) ? 0 : cnp.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((highSchool == null) ? 0 : highSchool.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CandidateEntity other = (CandidateEntity) obj;
        if (cnp == null) {
            if (other.cnp != null)
                return false;
        } else if (!cnp.equals(other.cnp))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (highSchool == null) {
            if (other.highSchool != null)
                return false;
        } else if (!highSchool.equals(other.highSchool))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CandidateEntity{" +
                "cnp=" + cnp +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", category=" + categoryEntity +
                ", highSchool='" + highSchool + '\'' +
                '}';
    }
}
