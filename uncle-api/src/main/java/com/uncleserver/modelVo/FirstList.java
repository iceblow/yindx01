package com.uncleserver.modelVo;

import java.util.List;

public class FirstList {

	private int id;

	private String name;

	private List<SecondList> secondList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SecondList> getSecondList() {
		return secondList;
	}

	public void setSecondList(List<SecondList> secondList) {
		this.secondList = secondList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
