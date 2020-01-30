package com.sergiu.repository;

import com.sergiu.entity.AdmissionResult;
import com.sergiu.util.ListAllocationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionResultRepository extends JpaRepository<AdmissionResult, Long> {

    List<AdmissionResult> findAllByListName(ListAllocationType listType);
}
