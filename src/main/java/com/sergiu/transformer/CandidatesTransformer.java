package com.sergiu.transformer;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.dto.CategoryDTO;
import com.sergiu.dto.HallDTO;
import com.sergiu.entity.CandidateEntity;
import com.sergiu.model.CandidateModel;
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
