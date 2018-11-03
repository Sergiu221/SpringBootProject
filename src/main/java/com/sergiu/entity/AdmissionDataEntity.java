package com.sergiu.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "admission_data")
public class AdmissionDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "cnp", length = 13)
	private String cnp;

	@Column(name = "address", length = 200)
	private String address;

	@Column(name = "exam_subject", length = 30)
	private String examSubject;

	@Column(name = "telephone", length = 13)
	private String telephone;

	@Column(name = "bac_grade", length = 10)
	private Float bacGrade;

	@Column(name = "general_grade", length = 10)
	private Float generalGrade;

	@OneToOne
	@JoinColumn(name = "highschool_id")
	private HighschoolEntity highschool;

	@Column(name = "civil_state", length = 30)
	private String civil_state;

	@Column(name = "language", length = 20)
	private String language;

	@Column(name = "hasDisabilities", length = 2)
	private Boolean hasDisabilities;

	@Column(name = "additionalInformation")
	private String additionalInformation;

	@Column(name = "admissionType", length = 50)
	private String admissionType;

	@OneToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;

	@OneToOne
	@JoinColumn(name = "regiony_id")
	private RegionEntity region;

	@OneToOne
	@JoinColumn(name = "city_id")
	private CityEntity city;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Column(name = "create_date")
	private Timestamp createDate;

	@OneToMany(mappedBy = "admissionData")
	private List<UploadedDocumentEntity> documents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExamSubject() {
		return examSubject;
	}

	public void setExamSubject(String examSubject) {
		this.examSubject = examSubject;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Float getBacGrade() {
		return bacGrade;
	}

	public void setBacGrade(Float bacGrade) {
		this.bacGrade = bacGrade;
	}

	public Float getGeneralGrade() {
		return generalGrade;
	}

	public void setGeneralGrade(Float generalGrade) {
		this.generalGrade = generalGrade;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getHasDisabilities() {
		return hasDisabilities;
	}

	public void setHasDisabilities(Boolean hasDisabilities) {
		this.hasDisabilities = hasDisabilities;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAdmissionType() {
		return admissionType;
	}

	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}

	public HighschoolEntity getHighschool() {
		return highschool;
	}

	public void setHighschool(HighschoolEntity highschool) {
		this.highschool = highschool;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public RegionEntity getRegion() {
		return region;
	}

	public void setRegion(RegionEntity region) {
		this.region = region;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public String getCivil_state() {
		return civil_state;
	}

	public void setCivil_state(String civil_state) {
		this.civil_state = civil_state;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<UploadedDocumentEntity> getDocuments() {
		return documents;
	}

	public void setDocuments(List<UploadedDocumentEntity> documents) {
		this.documents = documents;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
