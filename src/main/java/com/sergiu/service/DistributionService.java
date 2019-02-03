package com.sergiu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.repository.DistributionRepository;

@Service
public class DistributionService {

	@Autowired
	private DistributionRepository distributionRepository;
	
	
	public void clear() {
		distributionRepository.deleteAllInBatch();
	}
}
