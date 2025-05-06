package com.sergiu.transformer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sergiu.entity.Supervisor;
import com.sergiu.dto.SupervisorDTO;
import org.springframework.validation.annotation.Validated;

@Component
public class SupervisorsTransformer {

    public SupervisorDTO toDTO(Supervisor entity) {
        SupervisorDTO supervisorDTO = new SupervisorDTO();
        supervisorDTO.setId(entity.getId());
        supervisorDTO.setFirstName(entity.getFirstName());
        supervisorDTO.setLastName(entity.getLastName());
        return supervisorDTO;
    }

    public List<SupervisorDTO> toDTO(List<Supervisor> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public Supervisor toEntity(@Validated SupervisorDTO supervisorDTO) {
        Supervisor entity = new Supervisor();
        entity.setId(supervisorDTO.getId());
        entity.setFirstName(supervisorDTO.getFirstName());
        entity.setLastName(supervisorDTO.getLastName());
        return entity;
    }
}
