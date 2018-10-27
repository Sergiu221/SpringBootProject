package com.sergiu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.dto.HallDTO;
import com.sergiu.service.HallService;
import com.sergiu.transformer.Transformer;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HallController {

	@Autowired
	private HallService hallService;

	@Autowired
	private Transformer transformer;

	@RequestMapping("/halls")
	public List<HallDTO> halls() {
		return transformer.hallFromModelToDTO(hallService.retrieveAllHalls());
	}
}
