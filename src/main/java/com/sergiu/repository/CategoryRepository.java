package com.sergiu.repository;

import com.sergiu.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByAdmissionType(String admissionType);
}
