package com.sergiu.service;

import com.sergiu.dto.CategoryDTO;
import com.sergiu.entity.Category;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.CategoryRepository;
import com.sergiu.transformer.CategoriesTransformer;
import com.sergiu.util.AdmissionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CategoriesTransformer transformer;

    @Override
    public List<Category> getAllCategoriesWithCandidates() {

        List<Category> categories = categoryRepository.findAllByAdmissionType(AdmissionType.ADMITERE.getType());
        List<Category> result = new ArrayList<>();
        for (Category category : categories) {
            if (candidateRepository.findAllByCategory_Id(category.getId()).size() > 0) {
                category.setCandidateEntities(candidateRepository.findAllByCategory_Id(category.getId()));
                result.add(category);
            } else {

                LOGGER.info("Skipped the category with id [{}].", category.getId());
            }
        }
        return result;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return transformer.toDTO(categoryRepository.findAll());
    }

}
