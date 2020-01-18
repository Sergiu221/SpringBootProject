package com.sergiu.builders;

import com.sergiu.entity.CandidateEntity;
import com.sergiu.entity.CategoryEntity;

import java.util.List;

public class CandidateBuilder {
    public static CandidateEntity build(List<String> fieldsValue) {
        CandidateEntity candidateEntity = new CandidateEntity();
        CategoryEntity categoryEntity = new CategoryEntity();
        candidateEntity.setCnp(Long.valueOf(fieldsValue.get(0)));
        candidateEntity.setFirstName(String.valueOf(fieldsValue.get(1)));
        candidateEntity.setLastName(String.valueOf(fieldsValue.get(2)));
        categoryEntity.setId(Integer.valueOf(fieldsValue.get(3)));
        candidateEntity.setCategoryEntity(categoryEntity);
        candidateEntity.setHighSchool(String.valueOf(fieldsValue.get(4)));
        return candidateEntity;
    }
}
