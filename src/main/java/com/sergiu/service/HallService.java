package com.sergiu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.model.HallModel;
import com.sergiu.repository.HallRepository;
import com.sergiu.transformer.Transformer;

@Service
public class HallService {

	@Autowired
	private HallRepository hallRepository;

	@Autowired
	private Transformer transformer;

	public List<HallModel> retrieveAllHalls() {
		return transformer.hallFromEntityToModel(hallRepository.findAll());
	}
}
