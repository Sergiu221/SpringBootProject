package com.sergiu.entity;

import javax.persistence.*;

@Entity
@Table(name = "candidate_options")
public class CandidateOption {

    @EmbeddedId
    private CandidateOptionId candidateOptionId;

    @Column
    private Integer priority;

    @ManyToOne(optional = false)
    @MapsId("cnp")
    @JoinColumn(name = "cnp", insertable = false)
    private Candidate candidate;

    public CandidateOption() {
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

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
