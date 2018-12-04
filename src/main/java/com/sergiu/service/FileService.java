package com.sergiu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.sergiu.model.CandidateModel;
import com.sergiu.model.HallModel;
import com.sergiu.model.SupervisorModel;

@Service
public class FileService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

	public Set<CandidateModel> retrieveFromCSVlistOfCandidates(File file) {
		LOGGER.info("To be implemented=>FileSerice:retrieveFromCSVlistOfCandidates");

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(file.getPath()));
			String[] line;
			while ((line = reader.readNext()) != null) {
				System.out.println("Candidate [CNP= " + line[0] + ", prenume= " + line[1] + " , nume=" + line[2]+"]");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new HashSet<CandidateModel>();
	}

	public Set<SupervisorModel> retrieveFromCSVlistOfSupervisor(File file) {
		LOGGER.info("To be implemented=>FileSerice:retrieveFromCSVlistOfSupervisor");

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(file.getPath()));
			String[] line;
			while ((line = reader.readNext()) != null) {
				System.out.println("Supervisor[CNP= " + line[0] + ", Prenume= " + line[1] + " , Nume=" + line[2] + "]");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new HashSet<SupervisorModel>();
	}

	public Set<HallModel> retrieveFromCSVlistOfHalls(File file) {
		LOGGER.info("To be implemented=>FileSerice:retrieveFromCSVlistOfHalls");

		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(file.getPath()));
			String[] line;
			while ((line = reader.readNext()) != null) {
				System.out.println("Hall [Nume= " + line[0] + ", Locuri= " + line[1] + "]");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new HashSet<HallModel>();
	}

	public File convert(MultipartFile multipartFile) {

		try {
			LOGGER.info("Start converting file with name:" + multipartFile.getName());
			File contentFile = new File(multipartFile.getOriginalFilename());
			contentFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(contentFile);
			fos.write(multipartFile.getBytes());
			fos.close();
			return contentFile;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("I can't read the content of this file");
		}
	}
}
