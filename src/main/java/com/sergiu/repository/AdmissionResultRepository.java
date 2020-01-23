package com.sergiu.repository;

import com.sergiu.entity.AdmissionResultEntity;
import com.sergiu.util.ListAllocationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionResultRepository extends JpaRepository<AdmissionResultEntity, Long> {

    List<AdmissionResultEntity> findAllByListName(ListAllocationType listType);
}
