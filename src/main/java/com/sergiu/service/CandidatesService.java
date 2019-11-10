package com.sergiu.service;

import com.sergiu.entity.CandidateEntity;
import com.sergiu.exception.ResourceNotConsistentData;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.dto.CandidateDTO;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.transformer.CandidatesTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatesService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    CandidatesTransformer transformer;

    public List<CandidateDTO> getAllCandidates() {
        return transformer.toDTO(candidateRepository.findAll());
    }

    public void createCandidate(CandidateDTO candidateDTO) {
        candidateRepository.save(transformer.toEntity(candidateDTO));
    }

    public CandidateDTO getCandidateByCnp(Long cnp) {
        return transformer.toDTO(candidateRepository.findByCnp(cnp)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "cnp", cnp)));
    }

    public CandidateDTO updateCandidate(Long cnp, CandidateDTO candidateDTO) {

        if (cnp != candidateDTO.getCnp()) {
            throw new ResourceNotConsistentData("Candidate", "cnp", cnp, candidateDTO.getCnp());
        }
        candidateRepository.findByCnp(cnp)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "cnp", cnp));
        CandidateEntity entity = candidateRepository.findByCnp(cnp).get();

        return transformer.toDTO(candidateRepository.save(entity));

    }

    public void deleteCandidate(Long cnp) {
        CandidateEntity entity = candidateRepository.findByCnp(cnp)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "cnp", cnp));

        candidateRepository.delete(entity);
    }
}
