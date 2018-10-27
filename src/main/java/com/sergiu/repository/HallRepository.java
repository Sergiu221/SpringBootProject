package com.sergiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sergiu.entity.HallEntity;

public interface HallRepository extends JpaRepository<HallEntity, Integer> {

}
