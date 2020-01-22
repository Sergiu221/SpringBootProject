package com.sergiu.builders;

import com.sergiu.entity.CandidateEntity;
import com.sergiu.entity.GradeEntity;

import java.util.List;

public class GradeBuilder {
    public static GradeEntity build(List<String> fields) {
        GradeEntity gradeEntity = new GradeEntity();
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setCnp(Long.valueOf(fields.get(0)));
        gradeEntity.setCandidateEntity(candidateEntity);
        gradeEntity.setGrade(Double.valueOf(fields.get(1)));
        gradeEntity.setName(fields.get(2));
        return gradeEntity;
    }
}
