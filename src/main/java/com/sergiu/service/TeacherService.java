package com.sergiu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.model.TeacherModel;
import com.sergiu.repository.TeacherRepository;
import com.sergiu.transformer.Transformer;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private Transformer transformer;

	public List<TeacherModel> retrieveAllTeachers() {

		teacherRepository.findById(1);
		return transformer.teacherFromEntityToModel(teacherRepository.findAll());
	}
}
