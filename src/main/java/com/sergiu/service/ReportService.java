package com.sergiu.service;

import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sergiu.exception.MyFileNotFoundException;

@Service
public class ReportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

	@Autowired
	FileService fileService;

	public Resource generatePDFDistibution() {
		LOGGER.info("Generate PDF Distribution");

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("raport.pdf"));

			document.open();

			PdfPTable table = new PdfPTable(3);
			addTableHeader(table);
			addRows(table);

			document.add(table);
			document.close();
		} catch (Exception e) {
			System.err.println("My PDF have problems ");
		}
		try {
			Resource resource = new UrlResource(Paths.get("raport.pdf").toUri());
			System.out.println(resource);
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + "raport.pdf");
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + "raport.pdf", ex);
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
		table.addCell("Volocaru");
		table.addCell("Sergiu");
		table.addCell("Adrian");
	}
}
