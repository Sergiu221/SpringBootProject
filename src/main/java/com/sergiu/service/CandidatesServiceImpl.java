package com.sergiu.service;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.entity.Candidate;
import com.sergiu.exception.ResourceNotFoundException;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.transformer.CandidatesTransformer;
import com.sergiu.util.AdmissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidatesServiceImpl implements CandidatesService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    CandidatesTransformer transformer;

    @Override
    public List<CandidateDTO> getAllCandidates() {
        return transformer.toDTO(candidateRepository.findAll());
    }

    @Override
    public void createCandidate(CandidateDTO candidateDTO) {
        candidateRepository.save(transformer.toEntity(candidateDTO));
    }

    @Override
    public CandidateDTO getCandidateByCnp(Long cnp) {
        return transformer.toDTO(candidateRepository.findByCnp(cnp)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "cnp", cnp)));
    }

    @Override
    public CandidateDTO updateCandidate(Long cnp, CandidateDTO candidateDTO) {
        candidateRepository.findByCnp(cnp)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate", "cnp", cnp));

        Candidate entity = candidateRepository.save(transformer.toEntity(candidateDTO));
        return transformer.toDTO(candidateRepository.save(entity));
    }

    @Override
    public void deleteCandidate(Long cnp) {
          candidateRepository.deleteById(cnp);
    }

    @Override
    public Integer totalCandidates() {
        return candidateRepository.findAllByCategory_AdmissionTypeNot(AdmissionType.OLIMPIC).size();
    }
}
