package com.sergiu.service;

import com.sergiu.controller.AdmissionResultDTO;

public interface AllocationService {
    void startAllocateCandidates();

    void rejectCandidate(Long cnp);

    AdmissionResultDTO getCandidateResult(Long cnp);
}
