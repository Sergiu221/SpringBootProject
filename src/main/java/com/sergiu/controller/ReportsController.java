package com.sergiu.controller;

import com.sergiu.dto.CandidateDTO;
import com.sergiu.dto.ReportCandidatesDTO;
import com.sergiu.dto.ReportHallsDTO;
import com.sergiu.model.ColumnCandidatesReport;
import com.sergiu.service.ReportService;
import com.sergiu.service.ReportServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import javax.validation.Valid;
import java.io.*;

@CrossOrigin
@RestController
public class ReportsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsController.class);
    @Autowired
    private ReportService reportService;


    @GetMapping("/report")
    public ResponseEntity<Resource> report() throws IOException {

        File file = reportService.generateReport();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=candidati.pdf")
                .body(resource);
    }

    @PostMapping("reports/candidates")
    public ResponseEntity<Resource> getReportsWithCandidates(@Valid @RequestBody ReportCandidatesDTO reportCandidatesDTO) throws FileNotFoundException {

        LOGGER.info("Start generate report for candidates!");
        File file = reportService.generateReportCandidates(reportCandidatesDTO);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=candidati.pdf")
                .body(resource);
    }

    @PostMapping("reports/halls")
    public ResponseEntity<Resource> getReportsWithHalls(@Valid @RequestBody ReportHallsDTO hallsDTO) throws FileNotFoundException {

        LOGGER.info("Start generate report for candidates!");
        File file = reportService.generateReportHalls(hallsDTO);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sali.pdf")
                .body(resource);
    }
}
