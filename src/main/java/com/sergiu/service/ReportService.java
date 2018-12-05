package com.sergiu.service;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sergiu.model.CandidateModel;
import com.sergiu.model.HallModel;
import com.sergiu.model.SupervisorModel;

@Service
public class ReportService {
	Logger LOGGGER =new L
	public void generatePDFDistibution(Set<CandidateModel> listCandidates, Set<SupervisorModel> listSupervisors,
			Set<HallModel> listHalls) {
		System.out.println("Generate PDF Distribution");

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("raport.pdf"));

			document.open();

			PdfPTable table = new PdfPTable(3);
			addTableHeader(table);
			addRows(table);

			document.add(table);
			document.close();

			Path dowlnoadFile = Paths.get("raport.pdf");
			System.out.println(dowlnoadFile.getFileName());
		} catch (Exception e) {
			System.err.println("My PDF have problems ");
		}
		
		 String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("column header 1", "column header 2", "column header 3").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table) {
		table.addCell("row 1, col 1");
		table.addCell("row 1, col 2");
		table.addCell("row 1, col 3");
	}
}
