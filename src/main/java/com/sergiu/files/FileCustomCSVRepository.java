package com.sergiu.files;

public interface FileCustomCSVRepository {

	void save(FileCustomCSV fileCustomCSV);

	void update(FileCustomCSV fileCustomCSV);

	FileCustomCSV findById(String id);

}
