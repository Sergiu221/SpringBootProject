package com.sergiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.sergiu.files.FileCustomCSV;
import com.sergiu.files.FileCustomCSV.TypeFile;
import com.sergiu.service.FileService;
import com.sergiu.files.FileCustomCSVRepository;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class FileController {

	@Autowired
	private FileCustomCSVRepository sessionFiles;

	@Autowired
	private FileService fileService;
	
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
	@RequestMapping(method = RequestMethod.GET, path = "/generate_final_report")
	public void getAllFilesFromSession() {

		fileService.retrieveFromCSVlistOfCandidates(sessionFiles.findById("0").getFile());
		fileService.retrieveFromCSVlistOfSupervisor(sessionFiles.findById("1").getFile());
		fileService.retrieveFromCSVlistOfHalls(sessionFiles.findById("2").getFile());
		System.out.printf("----------Start generate report!----------");
		FileCustomCSV supervisors = sessionFiles.findById("0");
		FileCustomCSV candidates = sessionFiles.findById("1");
		FileCustomCSV halls = sessionFiles.findById("2");
		System.out.println("Cu succes am obtinut:" + supervisors.getFileName() + ", " + candidates.getFileName() + ", "
				+ halls.getFileName());
	}

}