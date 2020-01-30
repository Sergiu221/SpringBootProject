package com.sergiu.service;

import com.sergiu.controller.AdmissionResultDTO;
import com.sergiu.entity.AdmissionResult;
import com.sergiu.entity.Candidate;
import com.sergiu.entity.CandidateOptionEntity;
import com.sergiu.model.AllocationModel;
import com.sergiu.model.CandidateResultModel;
import com.sergiu.repository.AdmissionResultRepository;
import com.sergiu.repository.CandidateOptionRepository;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.transformer.CandidatesTransformer;
import com.sergiu.util.GradeUtils;
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
    private CandidatesTransformer transformer;
    private CandidateRepository candidateRepository;
    private CandidateOptionRepository candidateOptionRepository;
    private AdmissionResultRepository admissionResultRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setTransformer(CandidatesTransformer transformer) {
        this.transformer = transformer;
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

        SortedSet<CandidateResultModel> candidates = retrieveAllCandidatesOrderByFinalGrade();
        for (CandidateResultModel candidateResultModel : candidates) {
            Candidate candidate = candidateRepository.findByCnp(candidateResultModel.getCnp()).get();

            AdmissionResult resultEntity = new AdmissionResult();
            ListAllocationType list;
            if (candidateResultModel.getAdmissionType().equals("Olimpic")) {
                list = ListAllocationType.L1;
                resultEntity.setFinalGrade(10.0);
                candidate.setStatusExam(StatusExam.ADMIS);
                LOGGER.info("Candidatul:" + candidateResultModel.getCnp() + " a fost adaugat in lista" + list);
            } else {
                list = getAllocationListForCandidate(candidateResultModel, allocation);
                Double finalGrade = GradeUtils.calculateFinalResult(candidateResultModel.getTestGrade(), candidateResultModel.getBacGrade(), candidateResultModel.getBacBestGrade());
                resultEntity.setFinalGrade(finalGrade);
                LOGGER.info("Candidatul:" + candidateResultModel.getCnp() + "a fost adaugat in lista" + list);
            }

            resultEntity.setCnp(candidateResultModel.getCnp());
            resultEntity.setTestGrade(candidateResultModel.getTestGrade());
            resultEntity.setListName(list);
            if (list == ListAllocationType.L8) {
                candidate.setStatusExam(StatusExam.RESPINS);
            } else {
                candidate.setStatusExam(StatusExam.ADMIS);
            }
            candidateRepository.saveAndFlush(candidate);
            admissionResultRepository.saveAndFlush(resultEntity);
        }
    }

    private ListAllocationType getAllocationListForCandidate(CandidateResultModel candidateResult, AllocationModel allocation) {

        List<CandidateOptionEntity> candidateOptionEntities = candidateOptionRepository.findAllByCandidateOptionId_CandidateCnpOrderByPriority(candidateResult.getCnp());
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

            if (candidateResult.getFinalGrade() > 5) {
                return ListAllocationType.L7;
            }

        }
        return ListAllocationType.L8;
    }

    private SortedSet<CandidateResultModel> retrieveAllCandidatesOrderByFinalGrade() {
        List<Candidate> candidateEntities = candidateRepository.findAllByStatusExamIsNullOrStatusExamNot(StatusExam.RESPINS);
        List<CandidateResultModel> candidateResultModels = transformer.toCandidateResultModel(transformer.toModel(candidateEntities));
        SortedSet<CandidateResultModel> result = new TreeSet<>();
        for (CandidateResultModel candidateResultModel : candidateResultModels) {
            result.add(candidateResultModel);
        }
        return result;
    }

    @Override
    public void rejectCandidate(Long cnp) {
        AdmissionResult admissionResult = admissionResultRepository.findById(cnp).get();
        admissionResult.setListName(ListAllocationType.L8);
        admissionResultRepository.save(admissionResult);
        Candidate candidate = candidateRepository.findByCnp(cnp).get();
        candidate.setStatusExam(StatusExam.RESPINS);
        candidateRepository.save(candidate);
        startAllocateCandidates();
    }

    @Override
    public AdmissionResultDTO getCandidateResult(Long cnp) {
        AdmissionResult admissionResult = admissionResultRepository.findById(cnp).get();
        return modelMapper.map(admissionResult, AdmissionResultDTO.class);
    }
}
