package com.infotel.eshop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Search")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchDto {

	@XmlElement(name="Keyword")
	private String keyword;
	
	@XmlElement(name="FamilyId")
	private int familyId;
	
	@XmlElement(name="Tag")
	private String tag;
	
	@XmlElement(name="Randomize")
	private boolean randomize;

	public String getKeyword() {
		return keyword;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isRandomize() {
		return randomize;
	}

	public void setRandomize(boolean randomize) {
		this.randomize = randomize;
	}

	@Override
	public String toString() {
		return "SearchDto [keyword=" + keyword + ", familyId=" + familyId + ", tag=" + tag + ", randomize=" + randomize
				+ "]";
	}
	
}
