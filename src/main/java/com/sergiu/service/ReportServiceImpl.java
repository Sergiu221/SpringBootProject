package com.sergiu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import com.sergiu.dto.ReportCandidatesDTO;
import com.sergiu.dto.ReportHallsDTO;
import com.sergiu.entity.CandidateEntity;
import com.sergiu.model.ColumnCandidatesReport;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.util.FieldWidth;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    public static final String CANDIDATES_TITLE = "Candidati";
    public static final String HALLS_TITLE = "Sali";
    public static final String CANDIDATES_PDF = "candidati.pdf";
    public static final String HALLS_PDF = "sali.pdf";

    @Autowired
    private CandidateRepository candidateRepository;


    @Value("classpath:candidates.jrxml")
    Resource resourceFile;

    @Override
    public Resource generatePDFDistibution() {
        //TODO:
        return null;
    }


    @Override
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

    @Override
    public File generateReportCandidates(ReportCandidatesDTO reportCandidatesDTO) {
        try {

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportCandidatesDTO.getSourceList());
            return buildReport(reportCandidatesDTO.getColumnsReport(), jrBeanCollectionDataSource, CANDIDATES_TITLE, CANDIDATES_PDF);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Report fail!", e);
            return null;
        }

    }

    @Override
    public File generateReportHalls(ReportHallsDTO reportHallsDTO) {
        try {

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(reportHallsDTO.getSourceList());
            return buildReport(reportHallsDTO.getColumnsReport(), jrBeanCollectionDataSource, HALLS_TITLE, HALLS_PDF);

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Report fail!", e);
            return null;
        }

    }


    private File buildReport(List<ColumnCandidatesReport> columnsReport, JRBeanCollectionDataSource jrBeanCollectionDataSource, String title, String pdfName) throws JRException, IOException, ClassNotFoundException {
        DynamicReport dr = createJasperDesign(columnsReport, title);
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("createdBy", "Volocaru Sergiu Adrian");


        JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), jrBeanCollectionDataSource);

        System.out.println("Done");

        LOGGER.info("Report successfully generated");

        FileOutputStream out = new FileOutputStream(pdfName);
        out.write(JasperExportManager.exportReportToPdf(jasperPrint));
        out.close();
        return new File(pdfName);
    }

    private DynamicReport createJasperDesign(List<ColumnCandidatesReport> columnCandidatesReports, String title) throws ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        for (ColumnCandidatesReport column : columnCandidatesReports) {
            if (column.isReport()) {
                drb = drb.addColumn(column.getText(), column.getField(), column.getDataType(), FieldWidth.getPredefinedWidth(column.getField()));

            }
        }

        for (int i = 0; i < drb.getColumns().size(); i++) {
            if (drb.getColumn(i).getStyle() == null) {
                drb.getColumn(i).setStyle(new Style());
            }
            drb.getColumn(i).getStyle().setHorizontalAlign(HorizontalAlign.CENTER);
            drb.getColumn(i).getStyle().setBorder(Border.THIN());
        }
        drb.setUseFullPageWidth(true);
        drb.setSubtitle("Acest raport a fost generat la data de" + new Date());
        DynamicReport dr = drb.build();
        dr.setTitle(title);
        return dr;
    }
}