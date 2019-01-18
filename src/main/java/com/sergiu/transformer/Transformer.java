package com.sergiu.transformer;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

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

	public CandidateEntity candidateFromModelToEntity(CandidateModel model) {
		CandidateEntity entity = new CandidateEntity();
		entity.setId(model.getId());
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		return entity;
	}

	public HallEntity hallFromModelToEntity(@Valid HallModel hallModel) {
		HallEntity entity = new HallEntity();
		entity.setId(hallModel.getId());
		entity.setName(hallModel.getName());
		entity.setSize(hallModel.getSize());
		return entity;
	}

	public SupervisorEntity supervisorFromModelToEntity(@Valid SupervisorModel model) {
		SupervisorEntity entity = new SupervisorEntity();
		entity.setId(model.getId());
		entity.setFirstName(model.getFirstName());
		entity.setLastName(model.getLastName());
		return entity;
	}
}
