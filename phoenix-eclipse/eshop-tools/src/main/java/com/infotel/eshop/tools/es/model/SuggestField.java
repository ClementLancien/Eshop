package com.infotel.eshop.tools.es.model;

public class SuggestField {
	private String input;
	private int weight;
	
	public SuggestField(String input, int weight) {
		super();
		this.input = input;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
}
