package model;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = -6864392464226802950L;

	public Category(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
