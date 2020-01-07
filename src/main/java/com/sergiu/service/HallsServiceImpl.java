package com.sergiu.service;

import com.sergiu.dto.HallDTO;
import com.sergiu.entity.HallEntity;
import com.sergiu.exception.ResourceNotConsistentData;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.repository.HallRepository;
import com.sergiu.transformer.HallsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallsServiceImpl implements HallsService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private HallsTransformer hallsTransformer;

    @Override
    public List<HallDTO> getAllHalls() {
        return hallsTransformer.toDTO(hallRepository.findAll());
    }

    @Override
    public void createHall(HallDTO hallDTO) {
        hallRepository.save(hallsTransformer.toEntity(hallDTO));
    }

    @Override
    public HallDTO getHallById(Integer id) {
        return hallsTransformer.toDTO(hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id)));
    }

    @Override
    public HallDTO updateHall(Integer id, HallDTO hallDTO) {

        if (id != hallDTO.getId()) {
            throw new ResourceNotConsistentData("Hall", "id", id, hallDTO.getId());
        }

        hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));
        return hallsTransformer.toDTO(hallRepository.save(hallsTransformer.toEntity(hallDTO)));
    }

    @Override
    public void deleteHall(Integer id) {
        HallEntity entity = hallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));
        hallRepository.delete(entity);
    }
}
