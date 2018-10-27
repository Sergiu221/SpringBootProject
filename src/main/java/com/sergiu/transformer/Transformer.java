package com.sergiu.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sergiu.dto.HallDTO;
import com.sergiu.dto.TeacherDTO;
import com.sergiu.entity.HallEntity;
import com.sergiu.entity.TeacherEntity;
import com.sergiu.model.HallModel;
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

	public HallModel teacherFromEntityToModel(HallEntity entity) {
		HallModel model = new HallModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setSize(entity.getSize());
		return model;
	}

	public List<HallModel> hallFromEntityToModel(List<HallEntity> entityList) {
		List<HallModel> modelList = new ArrayList<>();
		for (HallEntity entity : entityList) {
			modelList.add(teacherFromEntityToModel(entity));
		}
		return modelList;
	}

	public HallDTO teacherFromModelToDTO(HallModel model) {
		HallDTO dto = new HallDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setSize(model.getSize());
		return dto;
	}

	public List<HallDTO> hallFromModelToDTO(List<HallModel> modelList) {
		List<HallDTO> dtoList = new ArrayList<>();
		for (HallModel model : modelList) {
			dtoList.add(teacherFromModelToDTO(model));
		}
		return dtoList;
	}
}
