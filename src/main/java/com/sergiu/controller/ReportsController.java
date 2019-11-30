package com.sergiu.controller;

import com.sergiu.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Paths;

@CrossOrigin
@RestController
public class ReportsController {

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

}
