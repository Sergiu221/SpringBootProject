package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiu.entity.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Integer> {

}
