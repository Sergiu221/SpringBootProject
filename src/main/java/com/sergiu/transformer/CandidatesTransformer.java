package com.sergiu.transformer;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.dto.CategoryDTO;
import com.sergiu.dto.HallDTO;
import com.sergiu.entity.CandidateEntity;
import com.sergiu.entity.GradeEntity;
import com.sergiu.model.CandidateModel;
import com.sergiu.model.CandidateResultModel;
import com.sergiu.model.GradeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidatesTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public CandidatesTransformer() {

    }

    public List<CandidateDTO> toDTO(List<CandidateEntity> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public CandidateEntity toEntity(CandidateDTO candidateDTO) {
        return modelMapper.map(candidateDTO, CandidateEntity.class);
    }

    public CandidateEntity toEntity(CandidateModel candidateModel) {
        return modelMapper.map(candidateModel, CandidateEntity.class);
    }

    public CandidateModel toModel(CandidateEntity candidateEntity) {
        CandidateModel candidateModel = modelMapper.map(candidateEntity, CandidateModel.class);
        if (candidateEntity.getGradeEntity().size() > 0) {
            for (GradeEntity gradeEntity : candidateEntity.getGradeEntity()) {
                candidateModel.getGradeModelList().add(modelMapper.map(gradeEntity, GradeModel.class));
            }
        }
        return candidateModel;
    }

    public List<CandidateModel> toModel(List<CandidateEntity> candidateEntities) {
        return candidateEntities.stream()
                .map(entity -> toModel(entity))
                .collect(Collectors.toList());
    }

    public CandidateResultModel toCandidateResultModel(CandidateModel candidateModel) {
        CandidateResultModel candidateResultModel = new CandidateResultModel();
        candidateResultModel.setCnp(candidateModel.getCnp());
        candidateResultModel.setFirstName(candidateModel.getFirstName());
        candidateResultModel.setLastName(candidateModel.getLastName());
        Double testGrade = (candidateModel.getGradeModelList().get(0).getGrade() + candidateModel.getGradeModelList().get(1).getGrade()) / 2;
        candidateResultModel.setTestGrade(testGrade);
        return candidateResultModel;
    }

    public List<CandidateResultModel> toCandidateResultModel(List<CandidateModel> candidateModels) {
        return candidateModels.stream()
                .map(model -> toCandidateResultModel(model))
                .collect(Collectors.toList());
    }

    public CandidateDTO toDTO(CandidateEntity entity) {
        CandidateDTO candidateDTO = modelMapper.map(entity, CandidateDTO.class);
        if (entity.getHallEntity() != null) {
            candidateDTO.setHallDTO(modelMapper.map(entity.getHallEntity(), HallDTO.class));
        }
        if (entity.getCategoryEntity() != null) {
            candidateDTO.setCategoryDTO(modelMapper.map(entity.getCategoryEntity(), CategoryDTO.class));
        }
        return candidateDTO;
    }
}
