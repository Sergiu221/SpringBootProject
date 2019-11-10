package com.sergiu.dto;

import java.util.List;

public class HallDTO {

	private int id;

	private String name;

	private int utilizableSize;

	private int size;

	private List<CandidateDTO> listCandidates;

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

	public List<CandidateDTO> getListCandidates() {
		return listCandidates;
	}

	public void setListCandidates(List<CandidateDTO> listCandidates) {
		this.listCandidates = listCandidates;
	}
}
