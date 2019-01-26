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
public class CandidatesController {

	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private Transformer transformer;

	@GetMapping("/candidates")
	public List<CandidateModel> getAllCandidates() {
		return transformer.candidateFromEntityToModel(candidateRepository.findAll());
	}

	@PostMapping("/candidates")
	public void createCandidate(@Valid @RequestBody CandidateModel candidateModel) {
		candidateRepository.save(transformer.candidateFromModelToEntity(candidateModel));
	}

	@GetMapping("/candidates/{id}")
	public CandidateModel getCandidateById(@PathVariable(value = "id") Integer id) {
		return transformer.candidateFromEntityToModel(candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id)));
	}

	@PutMapping("/candidates/{id}")
	public CandidateModel updateCandidate(@PathVariable(value = "id") Integer id,
			@Valid @RequestBody CandidateModel candidate) {

		CandidateEntity entity = candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));

		entity.setCnp(candidate.getCnp());
		entity.setFirstName(candidate.getFirstName());
		entity.setLastName(candidate.getLastName());
		entity.setExamLanguage(candidate.getExamLanguage());
		entity.setExamField(candidate.getExamField());
		entity.setExamType(candidate.getExamType());
		entity.setHighSchool(candidate.getHighSchool());

		return transformer.candidateFromEntityToModel(candidateRepository.save(entity));
	}

	@DeleteMapping("/candidates/{id}")
	public ResponseEntity<?> deleteCandidate(@PathVariable(value = "id") Integer id) {
		CandidateEntity entity = candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate", "id", id));

		candidateRepository.delete(entity);
		return ResponseEntity.ok().build();
	}
}
