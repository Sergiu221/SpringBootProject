package com.sergiu.service;

import com.sergiu.dto.CategoryDTO;
import com.sergiu.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategoriesWithCandidates();

    List<CategoryDTO> getAllCategories();
}
