package com.infotel.eshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Figure {
	@Id
	private int id;
	private String name;
	
	@Override
	public String toString() {
		return "Figure [id=" + id + ", name=" + name + "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + id;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if(obj == null) return false;
//		Figure fig = (Figure)obj;
//		return this.id == fig.getId() && this.getName().equals(fig.getName()) ;
//	}
	
}
