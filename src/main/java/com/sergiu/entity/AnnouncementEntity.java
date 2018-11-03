package com.sergiu.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "announcements")
public class AnnouncementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "info")
	private String info;

	@Column(name = "expiry_date")
	private Timestamp expiry_date;

	@Column(name = "publish_date")
	private Timestamp publish_date;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public AnnouncementEntity() {
		this.publish_date = new Timestamp(new Date().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Timestamp getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(Timestamp publish_date) {
		this.publish_date = publish_date;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Timestamp getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(Timestamp expiry_date) {
		this.expiry_date = expiry_date;
	}

}
