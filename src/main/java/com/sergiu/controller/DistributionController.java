package com.sergiu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sergiu.service.DistributionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sergiu.entity.DistributionEntity;
import com.sergiu.repository.DistributionRepository;
import com.sergiu.service.FileService;
import com.sergiu.service.ReportService;

@CrossOrigin
@RestController
public class DistributionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private ReportService reportSerivice;

    @Autowired
    private DistributionRepository distributionRepository;

    @Autowired
    private DistributionService distributionService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, path = "/distribution/clear")
    public void clear() {
        // TODO:Need to be implemented
        LOGGER.info("Distribution table has bee reset");
        distributionRepository.deleteAll();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, path = "/distribution/start")
    public void start() {
        // TODO:Need to be implemented
        LOGGER.info("Distribution table has bee filed with data");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/distribution")
    public List<DistributionEntity> getAllData() {
        return distributionRepository.findAll();
    }


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST, path = "/upload_file/{type}")
    public void upLoadFile(@RequestParam("file") MultipartFile file, @PathVariable("type") String type) {

        System.out.printf("File name=%s\n", file.getOriginalFilename());

        System.out.println("Typul este:" + type);
        fileService.saveCSVInSession(file, type);
    }

    @GetMapping(path = "/generate_final_report")
    public ResponseEntity<Resource> getAllFilesFromSession(HttpServletRequest request) {

        Resource resource = reportSerivice.generatePDFDistibution();

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            LOGGER.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/pdf";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(path = "/testDistribution")
    public void testDistribution() {

        distributionService.distributeCandidatesIntoHalls();
    }

}
