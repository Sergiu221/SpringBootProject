package com.sergiu.entity;

import org.hibernate.annotations.Type;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "uploaded_document")
public class UploadedDocumentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "mime_type")
	private String mimeType;

	@Column(name = "file_name")
	private String filename;

	@Column(name = "upload_date")
	private Timestamp uploadDate;

	@Lob
	@Column(name = "content")
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] content;

	@Column(name = "document_type")
	private String documentType;

	@ManyToOne
	@JoinColumn(name = "admission_data_id")
	private AdmissionDataEntity admissionData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Timestamp getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public AdmissionDataEntity getAdmissionData() {
		return admissionData;
	}

	public void setAdmissionData(AdmissionDataEntity admissionData) {
		this.admissionData = admissionData;
	}
}