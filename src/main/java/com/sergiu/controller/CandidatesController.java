package com.sergiu.controller;

import java.util.List;

import javax.validation.Valid;

import com.sergiu.service.CandidatesServiceImpl;
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

import com.sergiu.dto.CandidateDTO;

@CrossOrigin
@RestController
public class CandidatesController {

    @Autowired
    private CandidatesServiceImpl candidatesServiceImpl;

    @GetMapping("/candidates")
    public List<CandidateDTO> getAllCandidates() {
        return candidatesServiceImpl.getAllCandidates();
    }

    @PostMapping("/candidates")
    public void createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        candidatesServiceImpl.createCandidate(candidateDTO);
    }

    @GetMapping("/candidates/{cnp}")
    public CandidateDTO getCandidateByCnp(@PathVariable(value = "cnp") Long cnp) {
        return candidatesServiceImpl.getCandidateByCnp(cnp);
    }

    @PutMapping("/candidates/{cnp}")
    public CandidateDTO updateCandidate(@PathVariable(value = "cnp") Long cnp,
                                        @Valid @RequestBody CandidateDTO candidateDTO) {
        return candidatesServiceImpl.updateCandidate(cnp, candidateDTO);
    }

    @DeleteMapping("/candidates/{cnp}")
    public ResponseEntity<?> deleteCandidate(@PathVariable(value = "cnp") Long cnp) {
        candidatesServiceImpl.deleteCandidate(cnp);
        return ResponseEntity.ok().build();
    }
}
