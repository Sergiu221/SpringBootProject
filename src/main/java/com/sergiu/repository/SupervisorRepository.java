package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sergiu.entity.SupervisorEntity;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<SupervisorEntity, Integer> {

    Optional<SupervisorEntity> findByFirstName(String firstName);

    void deleteByFirstName(String firstName);
}
