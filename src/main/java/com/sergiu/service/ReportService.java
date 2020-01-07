package com.sergiu.service;

import org.springframework.core.io.Resource;

import java.io.File;

public interface ReportService {
    Resource generatePDFDistibution();

    File generateReport();
}
