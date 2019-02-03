package com.sergiu.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "distribution")
public class DistributionEntity {

	@EmbeddedId
	private DistributionId distributionId;

	public DistributionId getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(DistributionId distributionId) {
		this.distributionId = distributionId;
	}
}
