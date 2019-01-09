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

import com.sergiu.entity.CandidateEntity;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.model.CandidateModel;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.transformer.Transformer;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Candidates {
	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private Transformer transformer;

	@GetMapping("/candidates")
	public List<CandidateModel> getAllCandidates() {
		return transformer.candidateFromEntityToModel(candidateRepository.findAll());
	}

	// Create a new Note
	@PostMapping("/candidates")
	public void createCandidate(@Valid @RequestBody CandidateModel candidateModel) {
		candidateRepository.save(transformer.candidateFromModelToEntity(candidateModel));
	}

	// Get a Single Note
	@GetMapping("/candidates/{id}")
	public CandidateModel getNoteById(@PathVariable(value = "id") Integer id) {
		return transformer.candidateFromEntityToModel(candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id)));
	}

	// Update a Note
	@PutMapping("/notes/{id}")
	public CandidateModel updateNote(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody CandidateModel candidate) {

		CandidateEntity entity = candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));

		entity.setFirstName(candidate.getFirstName());
		entity.setLastName(candidate.getLastName());

		return transformer.candidateFromEntityToModel(candidateRepository.save(entity));
	}
	// Delete a Note

	@DeleteMapping("/candidates/{id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable(value = "id") Integer id) {
		CandidateEntity entity = candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));

		candidateRepository.delete(entity);
		return ResponseEntity.ok().build();
	}
}
