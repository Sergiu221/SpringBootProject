package com.sergiu.transformer;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.dto.CategoryDTO;
import com.sergiu.dto.HallDTO;
import com.sergiu.entity.Candidate;
import com.sergiu.model.CandidateModel;
import com.sergiu.model.CandidateResultModel;
import com.sergiu.model.CategoryModel;
import com.sergiu.model.GradesModel;
import com.sergiu.util.GradeUtils;
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

    public List<CandidateDTO> toDTO(List<Candidate> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public Candidate toEntity(CandidateDTO candidateDTO) {
        return modelMapper.map(candidateDTO, Candidate.class);
    }

    public Candidate toEntity(CandidateModel candidateModel) {
        return modelMapper.map(candidateModel, Candidate.class);
    }

    public CandidateModel toModel(Candidate candidate) {
        CandidateModel candidateModel = modelMapper.map(candidate, CandidateModel.class);
        if (candidate.getGrades() != null) {
            candidateModel.setGradesModel(modelMapper.map(candidate.getGrades(), GradesModel.class));
        }

        if (candidate.getCategory() != null) {
            candidateModel.setCategoryModel(modelMapper.map(candidate.getCategory(), CategoryModel.class));
        }

        return candidateModel;
    }

    public List<CandidateModel> toModel(List<Candidate> candidateEntities) {
        return candidateEntities.stream()
                .map(entity -> toModel(entity))
                .collect(Collectors.toList());
    }

    public CandidateResultModel toCandidateResultModel(CandidateModel candidateModel) {
        CandidateResultModel candidateResultModel = new CandidateResultModel();
        candidateResultModel.setCnp(candidateModel.getCnp());
        candidateResultModel.setFirstName(candidateModel.getFirstName());
        candidateResultModel.setLastName(candidateModel.getLastName());
        candidateResultModel.setAdmissionType(candidateModel.getCategoryModel().getAdmissionType());
        Double testGrade = candidateModel.getAverageOnWriteTest();
        candidateResultModel.setTestGrade(testGrade);
        candidateResultModel.setBacGrade(candidateModel.getBacGrade());
        candidateResultModel.setBacBestGrade(candidateModel.getBacBestGrade());
        Double finalGrade = GradeUtils.calculateFinalResult(testGrade, candidateModel.getBacBestGrade(), candidateModel.getBacGrade());
        candidateResultModel.setFinalGrade(finalGrade);
        return candidateResultModel;
    }

    public List<CandidateResultModel> toCandidateResultModel(List<CandidateModel> candidateModels) {
        return candidateModels.stream()
                .map(model -> toCandidateResultModel(model))
                .collect(Collectors.toList());
    }

    public CandidateDTO toDTO(Candidate entity) {
        CandidateDTO candidateDTO = modelMapper.map(entity, CandidateDTO.class);
        if (entity.getHallEntity() != null) {
            candidateDTO.setHallDTO(modelMapper.map(entity.getHallEntity(), HallDTO.class));
        }
        if (entity.getCategory() != null) {
            candidateDTO.setCategoryDTO(modelMapper.map(entity.getCategory(), CategoryDTO.class));
        }
        return candidateDTO;
    }
}
