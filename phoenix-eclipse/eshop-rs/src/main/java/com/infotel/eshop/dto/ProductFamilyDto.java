package com.infotel.eshop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProductFamily")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductFamilyDto {
	@XmlAttribute
	private int id;
	
	@XmlAttribute
	private String name;
	
	@Override
	public String toString() {
		return "ProductFamilyDto [id=" + id + ", name=" + name + "]";
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
	
	

}
