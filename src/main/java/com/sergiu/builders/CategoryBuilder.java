package com.sergiu.builders;

import com.sergiu.entity.CategoryEntity;

import java.util.List;

public class CategoryBuilder {
    public static CategoryEntity build(List<String> fields) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(String.valueOf(fields.get(0)));
        categoryEntity.setDiscipline(String.valueOf(fields.get(1)));
        categoryEntity.setLanguage(String.valueOf(fields.get(2)));
        categoryEntity.setAdmissionType(String.valueOf(fields.get(3)));
        return categoryEntity;
    }
}
