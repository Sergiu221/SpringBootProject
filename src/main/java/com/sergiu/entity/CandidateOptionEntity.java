package com.sergiu.entity;

import javax.persistence.*;

@Entity
@Table(name = "candidate_options")
public class CandidateOptionEntity {


    @EmbeddedId
    private CandidateOptionId candidateOptionId;

    @Column(name = "priority")
    private Integer priority;

    public CandidateOptionEntity() {

    }

    public CandidateOptionEntity(CandidateOptionId candidateOptionId) {
        this.candidateOptionId = candidateOptionId;

    }

    public CandidateOptionId getCandidateOptionId() {
        return candidateOptionId;
    }

    public void setCandidateOptionId(CandidateOptionId candidateOptionId) {
        this.candidateOptionId = candidateOptionId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
