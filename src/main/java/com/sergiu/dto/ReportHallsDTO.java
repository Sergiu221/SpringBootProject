package com.sergiu.dto;

import com.sergiu.model.ColumnCandidatesReport;

import java.io.Serializable;
import java.util.List;

public class ReportHallsDTO implements Serializable {
    List<HallDTO> sourceList;

    List<ColumnCandidatesReport> columnsReport;

    public List<HallDTO> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<HallDTO> sourceList) {
        this.sourceList = sourceList;
    }

    public List<ColumnCandidatesReport> getColumnsReport() {
        return columnsReport;
    }

    public void setColumnsReport(List<ColumnCandidatesReport> columnsReport) {
        this.columnsReport = columnsReport;
    }
}
