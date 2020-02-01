package com.sergiu.service;

import com.sergiu.controller.AdmissionResultDTO;
import com.sergiu.entity.AdmissionResult;
import com.sergiu.entity.Candidate;
import com.sergiu.entity.CandidateOptionEntity;
import com.sergiu.model.AllocationModel;
import com.sergiu.repository.AdmissionResultRepository;
import com.sergiu.repository.CandidateOptionRepository;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.util.AdmissionType;
import com.sergiu.util.ListAllocationType;
import com.sergiu.util.StatusExam;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class AllocationServiceImpl implements AllocationService, AllocationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllocationServiceImpl.class);

    private ModelMapper modelMapper;
    private CandidateRepository candidateRepository;
    private CandidateOptionRepository candidateOptionRepository;
    private AdmissionResultRepository admissionResultRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Autowired
    public void setCandidateOptionRepository(CandidateOptionRepository candidateOptionRepository) {
        this.candidateOptionRepository = candidateOptionRepository;
    }

    @Autowired
    public void setAdmissionResultRepository(AdmissionResultRepository admissionResultRepository) {
        this.admissionResultRepository = admissionResultRepository;
    }

    @Override
    public void startAllocateCandidates() {
        AllocationModel allocation = new AllocationModel(RO_BUGET, RO_TAXA, EN_BUGET, EN_TAXA, MD_RO_BUGET, MD_EN_TAXA);
        SortedSet<AdmissionResult> admissionResults = new TreeSet<>(admissionResultRepository.findAllByListNameIsNullOrListNameIsNot(ListAllocationType.L8));

        for (AdmissionResult admissionResult : admissionResults) {
            if (isOlympic(admissionResult)) {
                admissionResult.setFinalGrade(10.0);
                admissionResult.setListName(ListAllocationType.L1);
            } else {

                ListAllocationType listAllocationType = getAllocationListForCandidate(admissionResult, allocation);
                admissionResult.setListName(listAllocationType);
            }
            updateAdmissionResult(admissionResult);
            LOGGER.info("Candidatul:" + admissionResult.getCnp() + "a fost adaugat in lista" + admissionResult.getListName());
        }
    }

    private boolean isOlympic(AdmissionResult admissionResult) {
        return AdmissionType.OLIMPIC.equals(admissionResult.getCandidate().getCategory().getAdmissionType());
    }

    private ListAllocationType getAllocationListForCandidate(AdmissionResult admissionResult, AllocationModel allocation) {
        Long cnp = admissionResult.getCnp();

        List<CandidateOptionEntity> candidateOptionEntities = candidateOptionRepository.findAllByCandidateOptionId_CandidateCnpOrderByPriority(cnp);
        for (CandidateOptionEntity candidateOptionEntity : candidateOptionEntities) {

            String option = candidateOptionEntity.getCandidateOptionId().getName_option();
            switch (option) {
                case "RO-BUGET":
                    if (allocation.getRoBuget() > 0) {
                        allocation.decrementRoBuget();
                        return ListAllocationType.L3;
                    }
                case "EN-BUGET": {
                    if (allocation.getEnBuget() > 0) {
                        allocation.decrementEnBuget();
                        return ListAllocationType.L4;
                    }
                }

                case "RO-TAXA":
                    if (allocation.getRoTaxa() > 0) {
                        allocation.decrementRoTaxa();
                        return ListAllocationType.L5;
                    }
                case "EN-TAXA": {
                    if (allocation.getEnTaxa() > 0) {
                        allocation.decrementEnTaxa();
                        return ListAllocationType.L6;
                    }
                }
                case "MD-RO-BUGET": {
                    if (allocation.getMdRoBuget() > 0) {
                        allocation.decrementMdRoBuget();
                        return ListAllocationType.L2;
                    }
                }
                case "MD-EN-BUGET": {
                    if (allocation.getMdEnBuget() > 0) {
                        allocation.decrementMdEnBuget();
                        return ListAllocationType.L2;
                    }
                }
            }

            if (admissionResult.getFinalGrade() > 5) {
                return ListAllocationType.L7;
            }

        }
        return ListAllocationType.L8;
    }

    @Override
    public void rejectCandidate(Long cnp) {
        AdmissionResult admissionResult = admissionResultRepository.findById(cnp).get();
        admissionResult.setListName(ListAllocationType.L8);
        updateAdmissionResult(admissionResult);
        startAllocateCandidates();
    }

    @Override
    public void updateAdmissionResult(AdmissionResult admissionResult) {
        admissionResultRepository.save(admissionResult);
        triggerUpdateIntoCandidate(admissionResult);
    }

    private void triggerUpdateIntoCandidate(AdmissionResult admissionResult) {
        Candidate candidate = candidateRepository.findByCnp(admissionResult.getCnp()).get();

        if (admissionResult.getListName() == ListAllocationType.L8) {
            candidate.setStatusExam(StatusExam.RESPINS);
        } else {
            candidate.setStatusExam(StatusExam.ADMIS);
        }
        candidateRepository.save(candidate);
        LOGGER.info("Trigger update for candidate with cnp" + candidate.getCnp() + " with status update to: " + candidate.getStatusExam());
    }

    @Override
    public AdmissionResultDTO getCandidateResult(Long cnp) {
        AdmissionResult admissionResult = admissionResultRepository.findById(cnp).get();
        return modelMapper.map(admissionResult, AdmissionResultDTO.class);
    }
}
