package com.sergiu.controller;

import com.sergiu.entity.DistributionEntity;
import com.sergiu.repository.DistributionRepository;
import com.sergiu.service.DistributionServiceImpl;
import com.sergiu.service.ReportServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DistributionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionController.class);

    @Autowired
    private ReportServiceImpl reportSerivice;

    @Autowired
    private DistributionRepository distributionRepository;

    @Autowired
    private DistributionServiceImpl distributionServiceImpl;

    @GetMapping(path = "/distribution/clear")
    public void clear() {
        // TODO:Need to be implemented
        LOGGER.info("Distribution table has bee reset");
        distributionRepository.deleteAll();
    }

    @GetMapping(path = "/distribution/start")
    public void start() {
        // TODO:Need to be implemented
        LOGGER.info("Distribution table has bee filed with data");
        distributionRepository.deleteAll();
        distributionServiceImpl.distributeCandidatesIntoHalls();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/distribution")
    public List<DistributionEntity> getAllData() {
        return distributionRepository.findAll();
    }

    @GetMapping(path = "/testDistribution")
    public void testDistribution() {

        distributionServiceImpl.distributeCandidatesIntoHalls();
    }

}
