package com;

import java.util.ArrayList;
import java.util.List;

public class Jclass {
	String path;
	int numOfAddition;
	int numOfDeletion;
	List<Statment> Statments;

	public Jclass() {
		path = "";
		Statments = new ArrayList<Statment>();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
