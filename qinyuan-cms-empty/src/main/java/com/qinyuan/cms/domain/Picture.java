package com.qinyuan.cms.domain;

public class Picture {
	private String picture;
	private String describe;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public Picture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Picture(String picture, String describe) {
		super();
		this.picture = picture;
		this.describe = describe;
	}
	@Override
	public String toString() {
		return "Picture [picture=" + picture + ", describe=" + describe + "]";
	}
	
	
}
