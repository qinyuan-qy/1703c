package com.bw.bean;

public class Moive {
	private int mid;
	private String mname;
	private String author;
	private int price;
	private String mdate;
	private String mtype;
	private int mstatus;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public int getMstatus() {
		return mstatus;
	}
	public void setMstatus(int mstatus) {
		this.mstatus = mstatus;
	}
	@Override
	public String toString() {
		return "Moive [mid=" + mid + ", mname=" + mname + ", author=" + author
				+ ", price=" + price + ", mdate=" + mdate + ", mtype=" + mtype
				+ ", mstatus=" + mstatus + "]";
	}
	public Moive(int mid, String mname, String author, int price, String mdate,
			String mtype, int mstatus) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.author = author;
		this.price = price;
		this.mdate = mdate;
		this.mtype = mtype;
		this.mstatus = mstatus;
	}
	public Moive() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
