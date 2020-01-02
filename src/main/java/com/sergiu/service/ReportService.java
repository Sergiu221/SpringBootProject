package com.sergiu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.sergiu.entity.CandidateEntity;
import com.sergiu.repository.CandidateRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sergiu.exception.MyFileNotFoundException;
import com.sergiu.dto.CandidateDTO;

@Service
public class ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private CandidateRepository candidateRepository;


    @Value("classpath:candidates.jrxml")
    Resource resourceFile;

    public Resource generatePDFDistibution() {
        //TODO:
        return null;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Nume", "Prenume", "CNP", "Liceul").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    private void addRow(PdfPTable table, CandidateDTO candidateDTO) {
        table.addCell(candidateDTO.getLastName());
        table.addCell(candidateDTO.getFirstName());
        table.addCell(candidateDTO.getCnp().toString());
        table.addCell(candidateDTO.getHighSchool());
    }

    private void addRows(PdfPTable table, Set<CandidateDTO> candidatesDTO) {
        for (CandidateDTO model : candidatesDTO) {
            addRow(table, model);
        }
    }

    public File generateReport() {
        try {

            byte[] bytes = null;


            InputStream inputStream = resourceFile.getInputStream();

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            List<CandidateEntity> candidates = candidateRepository.findAll();
            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(candidates);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "Websparrow.org");

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            // Export the report to a PDF file
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);

            System.out.println("Done");

            LOGGER.info("Report successfully generated");

            FileOutputStream out = new FileOutputStream("candidati.pdf");
            out.write(bytes);
            out.close();
            return new File("candidati.pdf");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
