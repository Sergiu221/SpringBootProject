package com.sergiu.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class CountryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "country", length = 30)
	private String country;

	@OneToMany(mappedBy = "country")
	private List<RegionEntity> regions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<RegionEntity> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionEntity> regions) {
		this.regions = regions;
	}
}