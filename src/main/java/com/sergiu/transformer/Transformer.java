package com.sergiu.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sergiu.dto.TeacherDTO;
import com.sergiu.entity.TeacherEntity;
import com.sergiu.model.TeacherModel;

@Component
public class Transformer {

	public TeacherModel teacherFromEntityToModel(TeacherEntity entity) {
		TeacherModel model = new TeacherModel();
		model.setId(entity.getId());
		model.setFirstName(entity.getFirstName());
		model.setMiddleName(entity.getMiddleName());
		model.setLastName(entity.getLastName());
		return model;
	}

	public List<TeacherModel> teacherFromEntityToModel(List<TeacherEntity> entityList) {
		List<TeacherModel> modelList = new ArrayList<>();
		for (TeacherEntity entity : entityList) {
			modelList.add(teacherFromEntityToModel(entity));
		}
		return modelList;
	}

	public TeacherDTO teacherFromModelToDTO(TeacherModel model) {
		TeacherDTO dto = new TeacherDTO();
		dto.setId(model.getId());
		dto.setFirstName(model.getFirstName());
		dto.setMiddleName(model.getMiddleName());
		dto.setLastName(model.getLastName());
		return dto;
	}

	public List<TeacherDTO> teacherFromModelToDTO(List<TeacherModel> modelList) {
		List<TeacherDTO> dtoList = new ArrayList<>();
		for (TeacherModel model : modelList) {
			dtoList.add(teacherFromModelToDTO(model));
		}
		return dtoList;
	}
}
