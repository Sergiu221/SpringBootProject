package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiu.entity.CandidateEntity;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    Optional<CandidateEntity> findByCnp(Long cnp);

    void deleteByCnp(Long cnp);
}
