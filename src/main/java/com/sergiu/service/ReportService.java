package com.sergiu.service;

import com.sergiu.dto.ReportCandidatesDTO;
import com.sergiu.dto.ReportHallsDTO;
import org.springframework.core.io.Resource;

import java.io.File;

public interface ReportService {
    Resource generatePDFDistibution();

    File generateReport();

    File generateReportCandidates(ReportCandidatesDTO reportCandidatesDTO);

    File generateReportHalls(ReportHallsDTO reportHallsDTO);
}
