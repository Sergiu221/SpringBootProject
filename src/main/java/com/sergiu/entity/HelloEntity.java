package com.sergiu.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HelloEntity {

	@Id
	private int id;

	private String message;

	public String getMessage() {
		return message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
