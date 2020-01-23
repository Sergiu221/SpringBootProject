package com.sergiu.builders;

import com.sergiu.entity.CandidateOptionEntity;
import com.sergiu.entity.CandidateOptionId;

import java.util.List;

public class CandidateOptionBuilder {
    public static CandidateOptionEntity build(List<String> fieldsValue) {
        CandidateOptionEntity candidateOptionEntity = new CandidateOptionEntity();
        CandidateOptionId candidateOptionId = new CandidateOptionId();
        candidateOptionId.setCandidateCnp(Long.valueOf(fieldsValue.get(0)));
        candidateOptionId.setName_option(fieldsValue.get(1));
        candidateOptionEntity.setCandidateOptionId(candidateOptionId);
        candidateOptionEntity.setPriority(Integer.valueOf(fieldsValue.get(2)));
        return candidateOptionEntity;
    }
}
