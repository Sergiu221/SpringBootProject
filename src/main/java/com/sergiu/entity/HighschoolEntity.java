package com.sergiu.entity;

import javax.persistence.*;

@Entity
@Table(name = "highschools")
public class HighschoolEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 100)
	private String highSchoolName;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private RegionEntity region;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHighSchoolName() {
		return highSchoolName;
	}

	public void setHighSchoolName(String highSchoolName) {
		this.highSchoolName = highSchoolName;
	}

	public RegionEntity getRegion() {
		return region;
	}

	public void setRegion(RegionEntity region) {
		this.region = region;
	}
}