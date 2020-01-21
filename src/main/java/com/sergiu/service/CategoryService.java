package com.sergiu.service;

import com.sergiu.dto.CategoryDTO;
import com.sergiu.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> getAllCategoriesWithCandidates();

    List<CategoryDTO> getAllCategories();
}
