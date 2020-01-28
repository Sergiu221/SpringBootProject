package com.sergiu.service;

import com.sergiu.entity.AdmissionResultEntity;
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

    @Autowired
    private CandidatesTransformer transformer;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateOptionRepository candidateOptionRepository;

    @Autowired
    private AdmissionResultRepository admissionResultRepository;

    @Override
    public void startAllocateCandidates() {
        AllocationModel allocation = new AllocationModel(RO_BUGET, RO_TAXA, EN_BUGET, EN_TAXA, MD_RO_BUGET, MD_EN_TAXA);

        SortedSet<CandidateResultModel> candidates = retrieveAllCandidatesOrderByFinalGrade();
        for (CandidateResultModel candidateResultModel : candidates) {
            AdmissionResultEntity resultEntity = new AdmissionResultEntity();
            ListAllocationType list;
            if (candidateResultModel.getAdmissionType().equals("Olimpic")) {
                list = ListAllocationType.L1;
                resultEntity.setFinalGrade(10.0);
                LOGGER.info("Candidatul:" + candidateResultModel.getCnp() + " a fost adaugat in lista" + list);
            } else {
                list = getAllocationListForCandidate(candidateResultModel, allocation);
                Double finalGrade = GradeUtils.calculateFinalResult(candidateResultModel.getTestGrade(), candidateResultModel.getBacGrade(), candidateResultModel.getBacBestGrade());
                resultEntity.setFinalGrade(finalGrade);
                LOGGER.info("Candidatul:" + candidateResultModel.getCnp() + "a fost adaugat in lista" + list);
            }

            resultEntity.setCandidateCnp(candidateResultModel.getCnp());
            resultEntity.setFirstName(candidateResultModel.getFirstName());
            resultEntity.setLastName(candidateResultModel.getLastName());
            resultEntity.setBacBestGrade(candidateResultModel.getBacBestGrade());
            resultEntity.setBacGrade(candidateResultModel.getBacGrade());
            resultEntity.setTestGrade(candidateResultModel.getTestGrade());
            resultEntity.setListName(list);
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
        AdmissionResultEntity admissionResultEntity = admissionResultRepository.findById(cnp).get();
        admissionResultEntity.setListName(ListAllocationType.L8);
        admissionResultRepository.save(admissionResultEntity);
        Candidate candidate = candidateRepository.findByCnp(cnp).get();
        candidate.setStatusExam(StatusExam.RESPINS);
        startAllocateCandidates();
    }
}
