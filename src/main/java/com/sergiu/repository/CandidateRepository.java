package com.sergiu.repository;

import com.sergiu.entity.Candidate;
import com.sergiu.util.StatusExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findAllByOrderByFirstNameAsc();

    List<Candidate> findAllByCategory_Id(Integer categoryId);

    Optional<Candidate> findByCnp(Long cnp);

    void deleteByCnp(Long cnp);

    List<Candidate> findAllByCategory_AdmissionType(String type);

    List<Candidate> findAllByCategory_AdmissionTypeNot(String type);

    List<Candidate> findAllByStatusExamIsNullOrStatusExamNot(StatusExam statusExam);
}
