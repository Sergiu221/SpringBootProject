package com.sergiu.dto;

import com.sergiu.model.ColumnCandidatesReport;

import java.io.Serializable;
import java.util.List;

public class ReportCandidatesDTO implements Serializable {

    List<CandidateDTO> sourceList;

    List<ColumnCandidatesReport> columnsReport;

    public List<CandidateDTO> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<CandidateDTO> sourceList) {
        this.sourceList = sourceList;
    }

    public List<ColumnCandidatesReport> getColumnsReport() {
        return columnsReport;
    }

    public void setColumnsReport(List<ColumnCandidatesReport> columnsReport) {
        this.columnsReport = columnsReport;
    }
}
