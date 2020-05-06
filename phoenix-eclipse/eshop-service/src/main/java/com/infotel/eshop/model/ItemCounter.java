package com.infotel.eshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity @Table(name="item_counter")
public class ItemCounter implements Serializable {
	@Id 
	private String item;
	
	@Id 
	private String subset;
	
	@Column(name="next_value")
	private int nextValue;
	
	public void increment() {
		nextValue++;
	}

	@Override
	public String toString() {
		return "ItemCounter [item=" + item + ", subset=" + subset + ", nextValue=" + nextValue + "]";
	}

	public String getItem() {
		return item;
	}

	public String getSubset() {
		return subset;
	}

	public int getNextValue() {
		return nextValue;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public void setSubset(String subset) {
		this.subset = subset;
	}

	public void setNextValue(int nextValue) {
		this.nextValue = nextValue;
	}
	
	
}
