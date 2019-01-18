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

import com.sergiu.entity.HallEntity;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.model.HallModel;
import com.sergiu.repository.HallRepository;
import com.sergiu.transformer.Transformer;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HallsController {
	@Autowired
	private HallRepository hallRepository;
	@Autowired
	private Transformer transformer;

	@GetMapping("/halls")
	public List<HallModel> getAllHalls() {
		return transformer.hallFromEntityToModel(hallRepository.findAll());
	}

	@PostMapping("/halls")
	public void createHall(@Valid @RequestBody HallModel hallModel) {
		hallRepository.save(transformer.hallFromModelToEntity(hallModel));
	}

	@GetMapping("/halls/{id}")
	public HallModel getHallById(@PathVariable(value = "id") Integer id) {
		return transformer.hallFromEntityToModel(
				hallRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id)));
	}

	@PutMapping("/halls/{id}")
	public HallModel updateHall(@PathVariable(value = "id") Integer id, @Valid @RequestBody HallModel hall) {

		HallEntity entity = hallRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));

		entity.setName(hall.getName());
		entity.setSize(hall.getSize());

		return transformer.hallFromEntityToModel(hallRepository.save(entity));
	}

	@DeleteMapping("/halls/{id}")
	public ResponseEntity<?> deleteHall(@PathVariable(value = "id") Integer id) {
		HallEntity entity = hallRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hall", "id", id));

		hallRepository.delete(entity);
		return ResponseEntity.ok().build();
	}
}
