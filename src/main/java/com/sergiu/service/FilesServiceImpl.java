package com.sergiu.service;

import com.sergiu.builders.CandidateBuilder;
import com.sergiu.builders.CategoryBuilder;
import com.sergiu.builders.HallBuilder;
import com.sergiu.builders.SupervisorBuilder;
import com.sergiu.repository.CandidateRepository;
import com.sergiu.repository.CategoryRepository;
import com.sergiu.repository.HallRepository;
import com.sergiu.repository.SupervisorRepository;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FilesServiceImpl implements FilesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilesServiceImpl.class);

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Override
    public void readAndPrintCandidatesFrom(InputStream inputStream) throws Exception {

        SpreadsheetDocument document = SpreadsheetDocument.loadDocument(inputStream);

        LOGGER.debug("Incarca categoriile!");
        insertCategoriesIntoDataBase(document);

        LOGGER.debug("Incarca candidatii!");
        insertCandidatesIntoDataBase(document);

        LOGGER.debug("Incarca salile!");
        insertHallsIntoDataBase(document);

        LOGGER.debug("Incarca supraveghetorii!");
        insertSupervisorsIntoDataBase(document);

    }

    private void insertSupervisorsIntoDataBase(SpreadsheetDocument document) throws Exception {
        Table table = document.getSheetByName("Supraveghetori");
        if (table == null) {
            throw new Exception("Supraveghetori nu sunt prezenti in fisierul uploadat");
        }

        List<List<String>> listCategories = getListOfFiledFromTable(table);

        for (List<String> fields : listCategories) {
            supervisorRepository.save(SupervisorBuilder.build(fields));
        }
    }

    private void insertCategoriesIntoDataBase(SpreadsheetDocument document) throws Exception {

        Table table = document.getSheetByName("Categori");
        if (table == null) {
            throw new Exception("Categoriile nu sunt prezenti in fisierul uploadat");
        }

        List<List<String>> listCategories = getListOfFiledFromTable(table);

        for (List<String> fields : listCategories) {
            categoryRepository.save(CategoryBuilder.build(fields));
        }
    }

    private void insertCandidatesIntoDataBase(SpreadsheetDocument document) throws Exception {
        Table table = document.getSheetByName("Candidati");
        if (table == null) {
            throw new Exception("Candidatii nu sunt prezenti in fisierul uploadat");
        }

        List<List<String>> listCandidates = getListOfFiledFromTable(table);

        for (List<String> fields : listCandidates) {
            candidateRepository.save(CandidateBuilder.build(fields));
        }
    }

    private void insertHallsIntoDataBase(SpreadsheetDocument document) throws Exception {

        Table table = document.getSheetByName("Sali");
        if (table == null) {
            throw new Exception("Salile nu sunt prezenti in fisierul uploadat");
        }

        List<List<String>> listHalls = getListOfFiledFromTable(table);

        for (List<String> fields : listHalls) {
            hallRepository.save(HallBuilder.build(fields));
        }
    }

    private List<List<String>> getListOfFiledFromTable(Table table) {
        List<List<String>> result = new ArrayList<>();
        int columnNr = table.getColumnCount();
        int rowsNr = table.getRowCount();
        List<Row> rows = table.getRowList();

        for (int i = 1; i < rowsNr; i++) {
            Row row = rows.get(i);
            List<String> fields = new ArrayList<>();

            for (int j = 0; j < columnNr; j++) {
                fields.add(row.getCellByIndex(j).getDisplayText());
            }
            result.add(fields);
        }
        return result;
    }
}
