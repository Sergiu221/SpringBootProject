package com.sergiu.service;

public interface AllocationService {
    void startAllocateCandidates();

    void rejectCandidate(Long cnp);
}
