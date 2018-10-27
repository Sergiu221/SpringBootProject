package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergiu.entity.TeacherEntity;

@Repository
public interface TeacherRepository  extends JpaRepository<TeacherEntity, Integer> {
	TeacherEntity findByFirstName(String productName);
}
