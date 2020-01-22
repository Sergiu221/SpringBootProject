package com.sergiu.service;

import com.sergiu.dto.ReportCandidatesDTO;
import com.sergiu.dto.ReportHallsDTO;

import java.io.File;

public interface ReportService {

    File buildGeneralListDistributedReport();

    File buildCandidatesListWithoutExam();

    File buildCandidatesListFromHall(Integer hallId);

    File buildGeneralListWithGradesReport();

    File generateReportCandidates(ReportCandidatesDTO reportCandidatesDTO);

    File generateReportHalls(ReportHallsDTO reportHallsDTO);
}
