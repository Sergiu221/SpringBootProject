package com.sergiu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.dto.SupervisorDTO;
import com.sergiu.service.SupervisorService;
import com.sergiu.transformer.Transformer;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SupervisorController {

	@Autowired
	private SupervisorService supervisorService;

	@Autowired
	private Transformer transformer;

	@RequestMapping("/supervisors")
	public List<SupervisorDTO> supervisors() {
		return transformer.supervisorFromModelToDTO(supervisorService.retrieveAllSupervisors());
	}
}
