package com.sergiu.controller;

import com.sergiu.service.FilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FilesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilesController.class);

    private FilesService filesService;

    @Autowired
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }


    @PostMapping(path = "files/load_database")
    public ResponseEntity upLoadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        try {
            LOGGER.info("File [{}] is loaded.\n", multipartFile.getResource().getFilename());
            filesService.readAndPrintCandidatesFrom(multipartFile.getInputStream());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("File [{}] fail to load.", multipartFile.getResource().getFilename());
            throw e;

        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("File was successfully loaded");
    }
}
