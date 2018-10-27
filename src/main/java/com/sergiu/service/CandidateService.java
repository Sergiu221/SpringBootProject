package com.sergiu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.model.CandidateModel;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.transformer.Transformer;

@Service
public class CandidateService {

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private Transformer transformer;

	public List<CandidateModel> retrieveAllHalls() {
		return transformer.candidateFromEntityToModel(candidateRepository.findAll());
	}
}
