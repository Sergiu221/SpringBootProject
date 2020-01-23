package com.sergiu.transformer;

import com.sergiu.dto.CategoryDTO;
import com.sergiu.entity.CategoryEntity;
import com.sergiu.model.CategoryModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriesTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public List<CategoryDTO> toDTO(List<CategoryEntity> entities) {
        return entities.stream()
                .map(entity -> toDTO(entity))
                .collect(Collectors.toList());
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }

    public CategoryModel toModel(CategoryEntity entity) {
        return modelMapper.map(entity, CategoryModel.class);
    }

    public List<CategoryModel> toModel(List<CategoryEntity> entities) {
        return entities.stream()
                .map(entity -> toModel(entity))
                .collect(Collectors.toList());
    }

    public CategoryDTO toDTO(CategoryEntity entity) {
        return modelMapper.map(entity, CategoryDTO.class);
    }
}
