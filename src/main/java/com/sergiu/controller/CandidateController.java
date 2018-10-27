package com.sergiu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.service.CandidateService;
import com.sergiu.transformer.Transformer;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private Transformer transformer;

	@RequestMapping("/candidates")
	public List<CandidateDTO> halls() {
		return transformer.candidateFromModelToDTO(candidateService.retrieveAllHalls());
	}
}