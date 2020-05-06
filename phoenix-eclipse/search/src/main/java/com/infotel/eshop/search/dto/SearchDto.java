package com.infotel.eshop.search.dto;

public class SearchDto {

	private String keyword;
	
	private int familyId;

	private String tag;

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
