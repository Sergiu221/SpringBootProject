package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergiu.entity.SupervisorEntity;

@Repository
public interface SupervisorRepository extends JpaRepository<SupervisorEntity, Integer> {

}
