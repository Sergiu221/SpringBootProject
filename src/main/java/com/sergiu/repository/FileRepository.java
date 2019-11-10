package com.sergiu.repository;

import com.sergiu.dto.FileCSV;
import com.sergiu.util.TypeFile;

public interface FileRepository {

	void save(FileCSV fileCustomCSV);

	void update(FileCSV fileCustomCSV);

	FileCSV findByType(TypeFile typeFile);
}
