package com.sergiu.transformer;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sergiu.entity.SupervisorEntity;
import com.sergiu.dto.SupervisorDTO;

@Component
public class SupervisorsTransformer {

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

    public SupervisorEntity toEntity(@Valid SupervisorDTO supervisorDTO) {
        return modelMapper.map(supervisorDTO, SupervisorEntity.class);
    }
}
