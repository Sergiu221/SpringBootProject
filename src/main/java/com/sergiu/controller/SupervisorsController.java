package com.sergiu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sergiu.entity.SupervisorEntity;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.model.SupervisorModel;
import com.sergiu.repository.SupervisorRepository;
import com.sergiu.transformer.Transformer;

@CrossOrigin
@RestController
public class SupervisorsController {
	@Autowired
	private SupervisorRepository supervisorRepository;
	@Autowired
	private Transformer transformer;

	@GetMapping("/supervisors")
	public List<SupervisorModel> getAllSupervisors() {
		return transformer.supervisorFromEntityToModel(supervisorRepository.findAll());
	}

	@PostMapping("/supervisors")
	public void createSupervisor(@Valid @RequestBody SupervisorModel supervisorModel) {
		supervisorRepository.save(transformer.supervisorFromModelToEntity(supervisorModel));
	}

	@GetMapping("/supervisors/{id}")
	public SupervisorModel getSupervisorById(@PathVariable(value = "id") Integer id) {
		return transformer.supervisorFromEntityToModel(supervisorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id)));
	}

	@PutMapping("/supervisors/{id}")
	public SupervisorModel updateSupervisor(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody SupervisorModel supervisor) {

		SupervisorEntity entity = supervisorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

		entity.setFirstName(supervisor.getFirstName());
		entity.setLastName(supervisor.getLastName());
		entity.setMiddleName(supervisor.getMiddleName());
		return transformer.supervisorFromEntityToModel(supervisorRepository.save(entity));
	}

	@DeleteMapping("/supervisors/{id}")
	public ResponseEntity<?> deleteSupervisor(@PathVariable(value = "id") Integer id) {
		SupervisorEntity entity = supervisorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Supervisor", "id", id));

		supervisorRepository.delete(entity);
		return ResponseEntity.ok().build();
	}
}
