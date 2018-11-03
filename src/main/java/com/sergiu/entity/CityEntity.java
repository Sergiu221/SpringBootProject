package com.sergiu.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class CityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "city", length = 30)
	private String city;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private RegionEntity region;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public RegionEntity getRegion() {
		return region;
	}

	public void setRegion(RegionEntity region) {
		this.region = region;
	}
}