package com.sergiu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.dto.TeacherDTO;
import com.sergiu.service.TeacherService;
import com.sergiu.transformer.Transformer;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private Transformer transformer;

	@RequestMapping("/teachers")
	public List<TeacherDTO> greeting() {
		return transformer.teacherFromModelToDTO(teacherService.retrieveAllTeachers());
	}
}
