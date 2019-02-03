package com.sergiu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DistributionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_candidate")
	private Long candidateId;

	@Column(name = "id_hall")
	private Long hallId;

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getHallId() {
		return hallId;
	}

	public void setHallId(Long hallId) {
		this.hallId = hallId;
	}
}
