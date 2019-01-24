package com.sergiu.model;

public class HallModel {

	private int id;

	private String name;

	private int utilizableSize;
	
	private int size;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getUtilizableSize() {
		return utilizableSize;
	}

	public void setUtilizableSize(int utilizableSize) {
		this.utilizableSize = utilizableSize;
	}
}
