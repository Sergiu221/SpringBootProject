package com.sergiu.transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sergiu.entity.CandidateEntity;
import com.sergiu.entity.CategoryEntity;
import com.sergiu.entity.HallEntity;
import com.sergiu.entity.SupervisorEntity;
import com.sergiu.model.CandidateModel;
import com.sergiu.model.CategoryModel;
import com.sergiu.model.HallModel;
import com.sergiu.model.SupervisorDTO;

@Component
public class Transformer {

    @Autowired
    private ModelMapper modelMapper;

    public SupervisorDTO toDTO(SupervisorEntity entity) {
        return modelMapper.map(entity, SupervisorDTO.class);
    }

    public List<SupervisorDTO> toDTO(List<SupervisorEntity> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public HallModel hallFromEntityToModel(HallEntity entity) {
        HallModel model = new HallModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSize(entity.getSize());
        model.setUtilizableSize(entity.getUtilizableSize());
        model.setListCandidates(candidateFromEntityToModel(entity.getListCandidates()));
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
        model.setCnp(entity.getCnp());
        model.setFirstName(entity.getFirstName());
        model.setLastName(entity.getLastName());
        model.setCategory(categoryFromEntityToModel(entity.getCategory()));
        model.setHighSchool(entity.getHighSchool());
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
        entity.setCnp(model.getCnp());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setCategory(categoryFromModelToEntity(model.getCategory()));
        entity.setHighSchool(model.getHighSchool());
        return entity;
    }

    public HallEntity hallFromModelToEntity(@Valid HallModel hallModel) {
        HallEntity entity = new HallEntity();
        entity.setId(hallModel.getId());
        entity.setName(hallModel.getName());
        entity.setUtilizableSize(hallModel.getUtilizableSize());
        entity.setSize(hallModel.getSize());
        return entity;
    }

    public SupervisorEntity toEntity(@Valid SupervisorDTO model) {
        SupervisorEntity entity = new SupervisorEntity();
        entity.setId(model.getId());
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        return entity;
    }

    public CategoryModel categoryFromEntityToModel(CategoryEntity entity) {
        CategoryModel model = new CategoryModel();
        model.setLanguageExam(entity.getLanguage());
        model.setName(entity.getName());
        model.setSubjectExam(entity.getDiscipline());
        model.setTypeExam(entity.getAdmissionType());
        return model;
    }

    public CategoryEntity categoryFromModelToEntity(@Valid CategoryModel model) {
        CategoryEntity entity = new CategoryEntity();
        entity.setLanguage(model.getLanguageExam());
        entity.setName(model.getName());
        entity.setDiscipline(model.getSubjectExam());
        entity.setAdmissionType(model.getTypeExam());
        return entity;
    }
}
