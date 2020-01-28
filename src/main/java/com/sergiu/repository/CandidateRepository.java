package com.sergiu.repository;

import com.sergiu.entity.Candidate;
import com.sergiu.util.StatusExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByOrderByFirstNameAsc();

    List<Candidate> findAllByCategoryEntity_Id(Integer categoryId);

    Optional<Candidate> findByCnp(Long cnp);

    void deleteByCnp(Long cnp);

    List<Candidate> findAllByCategoryEntity_AdmissionType(String type);

    List<Candidate> findAllByCategoryEntity_AdmissionTypeNot(String type);

    List<Candidate> findAllByStatusExamIsNullOrStatusExamNot(StatusExam statusExam);
}
