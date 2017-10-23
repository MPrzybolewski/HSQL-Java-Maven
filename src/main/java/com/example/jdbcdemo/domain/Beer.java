package com.example.jdbcdemo.domain;

public class Beer {
	
	private long id;
	
	private String name;
	private String type;
	private double percentOfAlcohol;
	private double price;
	
	public Beer() {
	}
	
	public Beer(String name, int yob) {
		super();
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPercentOfAlcohol() {
		return percentOfAlcohol;
	}

	public void setPercentOfAlcohol(double percentOfAlcohol) {
		this.percentOfAlcohol = percentOfAlcohol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
