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
public class UploadFilesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFilesController.class);

    private FilesService filesService;

    @Autowired
    public UploadFilesController(FilesService filesService) {
        this.filesService = filesService;
    }


    @PostMapping(path = "files/load_database")
    public ResponseEntity upLoadFileSetupDataBase(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        try {
            LOGGER.info("File [{}] is loaded.\n", multipartFile.getResource().getFilename());
            filesService.readAndInsertMainResources(multipartFile.getInputStream());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("File [{}] fail to load.", multipartFile.getResource().getFilename());
            throw e;

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("File was successfully loaded");
    }

    @PostMapping(path = "files/load_grades")
    public ResponseEntity<String> uploadGradesIntoDataBase(@RequestParam("file") MultipartFile multipartFile) throws Exception {

        try {
            LOGGER.info("File [{}] with grads is loaded.\n", multipartFile.getResource().getFilename());
            filesService.readAndInsertGradesIntoDataBase(multipartFile.getInputStream());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("File [{}] with grades fail to load.", multipartFile.getResource().getFilename());
            throw e;

        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("File was successfully loaded");

    }
}
