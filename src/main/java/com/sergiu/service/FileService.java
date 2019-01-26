package com.sergiu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.sergiu.model.CandidateModel;
import com.sergiu.model.FileCSV;
import com.sergiu.model.HallModel;
import com.sergiu.model.SupervisorModel;
import com.sergiu.repository.FileRepository;
import com.sergiu.util.TypeFile;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

	public Set<CandidateModel> retrieveFromCSVlistOfCandidates() {
		Set<CandidateModel> result = new HashSet<CandidateModel>();
		LOGGER.info("Started retriving listOfCandidates");
		FileCSV csv = fileRepository.findByType(TypeFile.CANDIDATES);
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(csv.getFile().getAbsolutePath()));
			String[] line;
			while ((line = reader.readNext()) != null) {
				System.out.println("Candidate [CNP= " + line[0] + ", prenume= " + line[1] + " , nume=" + line[2] + "]");
				CandidateModel model = new CandidateModel();
				model.setFirstName(line[1]);
				model.setLastName(line[2]);
				model.setCnp(line[0]);
				model.setHighSchool(line[3]);
				result.add(model);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Set<SupervisorModel> retrieveFromCSVlistOfSupervisor(File file) {
		LOGGER.info("Started retriving listOfSupervisor");

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
		LOGGER.info("Started retriving listOfHalls");

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

	private File convert(MultipartFile multipartFile) {

		try {
			LOGGER.info("Start converting file with name:" + multipartFile.getName());
			File contentFile = new File(multipartFile.getOriginalFilename());
			contentFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(contentFile);
			fos.write(multipartFile.getBytes());
			fos.close();
			return contentFile;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("I can't read the content of this file");
		}
	}

	public void saveCSVInSession(MultipartFile file, String type) {
		FileCSV model = new FileCSV();
		model.setFileType(TypeFile.valueOf(type));
		model.setFileName(file.getOriginalFilename());
		model.setFile(convert(file));
		fileRepository.save(model);
	}
}
