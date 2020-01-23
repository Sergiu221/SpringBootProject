package com.sergiu.controller;

import com.sergiu.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AllocationController {

    @Autowired
    private AllocationService allocationService;

    @GetMapping(path = "/allocation")
    public void startAllocation() {

        allocationService.startAllocateCandidates();
    }
}
