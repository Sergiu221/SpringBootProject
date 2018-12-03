package com.sergiu.files;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("FileCustomCSV")
public class FileCustomCSV implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TypeFile {
		SUPERVISORS, CANDIDATES, HALLS
	}

	private String id;
	private String fileName;
	private TypeFile fileType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
}
