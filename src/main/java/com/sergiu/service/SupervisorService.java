package com.sergiu.service;

import java.util.List;

import com.sergiu.entity.SupervisorEntity;
import com.sergiu.exception.ResourceNotConsistentData;
import com.sergiu.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sergiu.model.SupervisorDTO;
import com.sergiu.repository.SupervisorRepository;
import com.sergiu.transformer.Transformer;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private Transformer transformer;

    public List<SupervisorDTO> getAllSupervisors() {
        return transformer.toDTO(supervisorRepository.findAll());
    }

    public void createSupervisor(SupervisorDTO supervisorDTO) {
        supervisorRepository.save(transformer.toEntity(supervisorDTO));
    }

    public SupervisorDTO getSupervisorById(Integer id) {
        return transformer.toDTO(supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id)));
    }

    public SupervisorDTO updateSupervisor(Integer id, SupervisorDTO supervisorDTO) {
        if (id != supervisorDTO.getId()) {
            throw new ResourceNotConsistentData("Supervisor", "id", id, supervisorDTO.getId());
        }
        supervisorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

        return transformer.toDTO(supervisorRepository.save(transformer.toEntity(supervisorDTO)));
    }

	public void deleteSupervisor(Integer id) {
		SupervisorEntity entity = supervisorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

		supervisorRepository.delete(entity);
	}
}
