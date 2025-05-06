package com.sergiu.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "distribution")
public class Distribution {

    @EmbeddedId
    private DistributionId distributionId;

    public Distribution() {

    }

    public Distribution(DistributionId distributionId) {
        this.distributionId = distributionId;
    }

    public DistributionId getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(DistributionId distributionId) {
        this.distributionId = distributionId;
    }
}
