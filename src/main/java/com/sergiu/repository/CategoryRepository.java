package com.sergiu.repository;

import com.sergiu.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

    List<CategoryEntity > findAllByAdmissionType(String admissionType);
}
