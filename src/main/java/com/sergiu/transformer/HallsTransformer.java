package com.sergiu.transformer;

import com.sergiu.dto.HallDTO;
import com.sergiu.entity.Hall;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HallsTransformer {

    public HallDTO toDTO(Hall entity) {
        HallDTO hallDTO = new HallDTO();
        hallDTO.setId(entity.getId());
        hallDTO.setName(entity.getName());
        hallDTO.setSize(entity.getSize());
        hallDTO.setUtilizableSize(entity.getUtilizableSize());
        return hallDTO;
    }

    public List<HallDTO> toDTO(List<Hall> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Hall toEntity(@Validated HallDTO hallDTO) {
        Hall entity = new Hall();
        entity.setId(hallDTO.getId());
        entity.setName(hallDTO.getName());
        entity.setSize(hallDTO.getSize());
        entity.setUtilizableSize(hallDTO.getUtilizableSize());
        return entity;
    }
}
