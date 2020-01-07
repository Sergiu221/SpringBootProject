package com.sergiu.controller;

import com.sergiu.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

import java.io.*;

@CrossOrigin
@RestController
public class ReportsController {

    @Autowired
    private ReportServiceImpl reportServiceImpl;


    @GetMapping("/report")
    public ResponseEntity<Resource> report() throws IOException {

        File file = reportServiceImpl.generateReport();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=candidati.pdf")
                .body(resource);
    }

}
