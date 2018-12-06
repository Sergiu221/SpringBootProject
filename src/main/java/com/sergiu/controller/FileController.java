package com.sergiu.controller;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.sergiu.files.FileCustomCSV;
import com.sergiu.files.FileCustomCSV.TypeFile;
import com.sergiu.files.FileCustomCSVRepository;
import com.sergiu.service.FileService;
import com.sergiu.service.ReportService;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class FileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileCustomCSVRepository sessionFiles;

	@Autowired
	private FileService fileService;

	@Autowired
	private ReportService reportSerivice;

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, path = "/upload_supervisors")
	public void supervisorsFileUpload(@RequestParam("file") MultipartFile file) {

		System.out.printf("File name=%s\n", file.getOriginalFilename());
		FileCustomCSV fileCustomCSV = new FileCustomCSV();
		fileCustomCSV.setId("0");
		fileCustomCSV.setFileType(TypeFile.SUPERVISORS);
		fileCustomCSV.setFileName(file.getOriginalFilename());
		fileCustomCSV.setFile(fileService.convert(file));
		sessionFiles.save(fileCustomCSV);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, path = "/upload_candidates")
	public void candidatesFileUpload(@RequestParam("file") MultipartFile file) {

		System.out.printf("File name=%s\n", file.getOriginalFilename());
		FileCustomCSV fileCustomCSV = new FileCustomCSV();
		fileCustomCSV.setId("1");
		fileCustomCSV.setFileType(TypeFile.CANDIDATES);
		fileCustomCSV.setFileName(file.getOriginalFilename());
		fileCustomCSV.setFile(fileService.convert(file));
		sessionFiles.save(fileCustomCSV);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST, path = "/upload_halls")
	public void hallsFileUpload(@RequestParam("file") MultipartFile file) {

		System.out.printf("File name=%s\n", file.getOriginalFilename());
		FileCustomCSV fileCustomCSV = new FileCustomCSV();
		fileCustomCSV.setId("2");
		fileCustomCSV.setFileType(TypeFile.HALLS);
		fileCustomCSV.setFileName(file.getOriginalFilename());
		fileCustomCSV.setFile(fileService.convert(file));
		sessionFiles.save(fileCustomCSV);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(path = "/generate_final_report", method = RequestMethod.GET)
	public ResponseEntity<Resource> getAllFilesFromSession(HttpServletRequest request) {

		// Load file as Resource
		Resource resource = reportSerivice.generatePDFDistibution(new HashSet<>(), new HashSet<>(), new HashSet<>());

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			LOGGER.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/pdf";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
