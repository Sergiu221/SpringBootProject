package com.sergiu.transformer;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.dto.CategoryDTO;
import com.sergiu.dto.HallDTO;
import com.sergiu.entity.Candidate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CandidatesTransformer {

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<CandidateDTO> toDTO(List<Candidate> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public Candidate toEntity(CandidateDTO candidateDTO) {
        return modelMapper.map(candidateDTO, Candidate.class);
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
