package com.sergiu.dto;

import java.io.File;
import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.sergiu.util.TypeFile;

@RedisHash("FileCSV")
public class FileCSV implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fileName;
	private TypeFile fileType;
	private File file;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TypeFile getFileType() {
		return fileType;
	}

	public void setFileType(TypeFile fileType) {
		this.fileType = fileType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
