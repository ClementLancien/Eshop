package com.infotel.eshop.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Families")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductFamilyListDto {
	
	@XmlElement(name="Family")
	private List<ProductFamilyDto> families;

	public List<ProductFamilyDto> getFamilies() {
		return families;
	}

	public void setFamilies(List<ProductFamilyDto> families) {
		this.families = families;
	}
	
	

}
