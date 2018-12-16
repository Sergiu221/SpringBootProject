package com.sergiu.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.dto.HallDTO;
import com.sergiu.dto.SupervisorDTO;
import com.sergiu.entity.CandidateEntity;
import com.sergiu.entity.HallEntity;
import com.sergiu.entity.SupervisorEntity;
import com.sergiu.model.CandidateModel;
import com.sergiu.model.HallModel;
import com.sergiu.model.SupervisorModel;

@Component
public class Transformer {

	public SupervisorModel supervisorFromEntityToModel(SupervisorEntity entity) {
		SupervisorModel model = new SupervisorModel();
		model.setId(entity.getId());
		model.setFirstName(entity.getFirstName());
		model.setMiddleName(entity.getMiddleName());
		model.setLastName(entity.getLastName());
		return model;
	}

	public List<SupervisorModel> supervisorFromEntityToModel(List<SupervisorEntity> entityList) {
		List<SupervisorModel> modelList = new ArrayList<>();
		for (SupervisorEntity entity : entityList) {
			modelList.add(supervisorFromEntityToModel(entity));
		}
		return modelList;
	}

	public SupervisorDTO supervisorFromModelToDTO(SupervisorModel model) {
		SupervisorDTO dto = new SupervisorDTO();
		dto.setId(model.getId());
		dto.setFirstName(model.getFirstName());
		dto.setMiddleName(model.getMiddleName());
		dto.setLastName(model.getLastName());
		return dto;
	}

	public List<SupervisorDTO> supervisorFromModelToDTO(List<SupervisorModel> modelList) {
		List<SupervisorDTO> dtoList = new ArrayList<>();
		for (SupervisorModel model : modelList) {
			dtoList.add(supervisorFromModelToDTO(model));
		}
		return dtoList;
	}

	public HallModel hallFromEntityToModel(HallEntity entity) {
		HallModel model = new HallModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setSize(entity.getSize());
		return model;
	}

	public List<HallModel> hallFromEntityToModel(List<HallEntity> entityList) {
		List<HallModel> modelList = new ArrayList<>();
		for (HallEntity entity : entityList) {
			modelList.add(hallFromEntityToModel(entity));
		}
		return modelList;
	}

	public HallDTO hallFromModelToDTO(HallModel model) {
		HallDTO dto = new HallDTO();
		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setSize(model.getSize());
		return dto;
	}

	public List<HallDTO> hallFromModelToDTO(List<HallModel> modelList) {
		List<HallDTO> dtoList = new ArrayList<>();
		for (HallModel model : modelList) {
			dtoList.add(hallFromModelToDTO(model));
		}
		return dtoList;
	}

	public CandidateModel candidateFromEntityToModel(CandidateEntity entity) {
		CandidateModel model = new CandidateModel();
		model.setId(entity.getId());
		model.setFirstName(entity.getFirstName());
		model.setLastName(entity.getLastName());
		return model;
	}

	public List<CandidateModel> candidateFromEntityToModel(List<CandidateEntity> entityList) {
		List<CandidateModel> modelList = new ArrayList<>();
		for (CandidateEntity entity : entityList) {
			modelList.add(candidateFromEntityToModel(entity));
		}
		return modelList;
	}

	public CandidateDTO candidateFromModelToDTO(CandidateModel model) {
		CandidateDTO dto = new CandidateDTO();
		dto.setId(model.getId());
		dto.setFirstName(model.getFirstName());
		dto.setLastName(model.getLastName());
		return dto;
	}

	public List<CandidateDTO> candidateFromModelToDTO(List<CandidateModel> modelList) {
		List<CandidateDTO> dtoList = new ArrayList<>();
		for (CandidateModel model : modelList) {
			dtoList.add(candidateFromModelToDTO(model));
		}
		return dtoList;
	}
}
