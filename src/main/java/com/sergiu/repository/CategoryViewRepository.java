package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiu.entity.CategoryViewEntity;

public interface CategoryViewRepository extends JpaRepository<CategoryViewEntity, String> {

}
