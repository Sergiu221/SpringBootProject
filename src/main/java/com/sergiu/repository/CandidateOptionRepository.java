package com.sergiu.repository;

import com.sergiu.entity.CandidateOptionEntity;
import com.sergiu.entity.CandidateOptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateOptionRepository extends JpaRepository<CandidateOptionEntity, CandidateOptionId> {

    List<CandidateOptionEntity> findAllByCandidateOptionId_CandidateCnpOrderByPriority(Long cnp);
}
