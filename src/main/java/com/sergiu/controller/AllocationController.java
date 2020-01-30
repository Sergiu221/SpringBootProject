package com.sergiu.controller;

import com.sergiu.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AllocationController {

    private AllocationService allocationService;

    @Autowired
    public void setAllocationService(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @GetMapping("admission/{cnp}")
    public AdmissionResultDTO getCandidateResult(@PathVariable(name = "cnp") Long cnp) {

        return allocationService.getCandidateResult(cnp);
    }

    @GetMapping(path = "/allocation")
    public void startAllocation() {

        allocationService.startAllocateCandidates();
    }

    @GetMapping(path = "/admission/reject/{cnp}")
    public ResponseEntity<?> rejectCandidate(@PathVariable(name = "cnp") Long cnp) {
        allocationService.rejectCandidate(cnp);
        return ResponseEntity.ok().build();
    }
}
