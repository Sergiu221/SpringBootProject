package com.sergiu.repository;

import com.sergiu.util.StatusExam;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiu.entity.CandidateEntity;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {

    List<CandidateEntity> findAllByOrderByFirstNameAsc();

    List<CandidateEntity> findAllByCategoryEntity_Id(Integer categoryId);

    Optional<CandidateEntity> findByCnp(Long cnp);

    void deleteByCnp(Long cnp);

    List<CandidateEntity> findAllByCategoryEntity_AdmissionType(String type);

    List<CandidateEntity> findAllByCategoryEntity_AdmissionTypeNot(String type);

    List<CandidateEntity> findAllByStatusExamIsNullOrStatusExamNot(StatusExam statusExam);
}
