package com.sergiu.service;

import com.sergiu.entity.CategoryEntity;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public List<CategoryEntity> getAllCategoriesWithCandidates() {

        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryEntity> result = new ArrayList<>();
        for (CategoryEntity category : categories) {
            if (candidateRepository.findAllByCategory_Id(category.getId()).size() > 0) {
                category.setCandidateEntities(candidateRepository.findAllByCategory_Id(category.getId()));
                result.add(category);
            } else {

                LOGGER.info("Skipped the category with id [{}].", category.getId());
            }
        }
        return result;
    }

}
