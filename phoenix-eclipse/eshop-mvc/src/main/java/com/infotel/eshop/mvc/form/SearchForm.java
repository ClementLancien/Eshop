package com.infotel.eshop.mvc.form;

public class SearchForm {

	private String keyword;
	private int familyId;
	
	@Override
	public String toString() {
		return "SearchForm [keyword=" + keyword + ", familyId=" + familyId + "]";
	}

	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}
	
}
