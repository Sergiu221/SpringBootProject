package com.sergiu.controller;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.service.CandidatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<?> createCandidate(@Valid @RequestBody CandidateDTO candidateDTO) {
        candidatesServiceImpl.createCandidate(candidateDTO);
        return ResponseEntity.ok().build();
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
