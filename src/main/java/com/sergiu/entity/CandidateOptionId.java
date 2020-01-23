package com.sergiu.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateOptionId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "cnp_candidate")
    private Long candidateCnp;

    @Column(name = "name_option")
    private String name_option;

    public CandidateOptionId() {

    }

    public CandidateOptionId(Long candidateCnp, String name_option) {
        this.candidateCnp = candidateCnp;
        this.name_option = name_option;
    }

    public Long getCandidateCnp() {
        return candidateCnp;
    }

    public void setCandidateCnp(Long candidateCnp) {
        this.candidateCnp = candidateCnp;
    }

    public String getName_option() {
        return name_option;
    }

    public void setName_option(String name_option) {
        this.name_option = name_option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CandidateOptionId)) return false;
        CandidateOptionId that = (CandidateOptionId) o;
        return Objects.equals(getCandidateCnp(), that.getCandidateCnp()) &&
                Objects.equals(getName_option(), that.getName_option());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCandidateCnp(), getName_option());
    }
}
