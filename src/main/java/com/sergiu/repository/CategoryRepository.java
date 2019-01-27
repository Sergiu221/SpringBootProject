package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiu.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}
