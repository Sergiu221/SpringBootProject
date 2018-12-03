package com.sergiu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.model.SupervisorModel;
import com.sergiu.repository.SupervisorRepository;
import com.sergiu.transformer.Transformer;

@Service
public class SupervisorService {

	@Autowired
	private SupervisorRepository supervisorRepository;

	@Autowired
	private Transformer transformer;

	public List<SupervisorModel> retrieveAllSupervisors() {
		return transformer.supervisorFromEntityToModel(supervisorRepository.findAll());
	}
}
