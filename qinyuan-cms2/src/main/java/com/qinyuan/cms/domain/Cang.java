package com.qinyuan.cms.domain;

public class Cang {
	private int id;
	private int aid;
	private int uid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Cang [id=" + id + ", aid=" + aid + ", uid=" + uid + "]";
	}
	public Cang(int id, int aid, int uid) {
		super();
		this.id = id;
		this.aid = aid;
		this.uid = uid;
	}
	public Cang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
