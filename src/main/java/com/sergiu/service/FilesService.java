package com.sergiu.service;

import java.io.InputStream;

public interface FilesService {

    void readAndPrintCandidatesFrom(InputStream inputStream) throws Exception;
}
