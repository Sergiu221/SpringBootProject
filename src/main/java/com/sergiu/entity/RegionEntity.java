package com.sergiu.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "regions")
public class RegionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "region", length = 30)
	private String region;

	@OneToMany(mappedBy = "region")
	private List<CityEntity> cities;

	@OneToMany(mappedBy = "region")
	private List<HighschoolEntity> highschools;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<CityEntity> getCities() {
		return cities;
	}

	public void setCities(List<CityEntity> cities) {
		this.cities = cities;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
	}

	public List<HighschoolEntity> getHighschools() {
		return highschools;
	}

	public void setHighschools(List<HighschoolEntity> highschools) {
		this.highschools = highschools;
	}
}