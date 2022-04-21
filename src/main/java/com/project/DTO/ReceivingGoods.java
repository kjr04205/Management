package com.project.DTO;

public class ReceivingGoods {

	private int rgno;
	private String gname;
	private String gmember;
	private String ggroup;
	private int gcount;
	private String gdate;
	
	public ReceivingGoods() {
		
	}
	
	public ReceivingGoods(int rgno, String gname, String gmember, String ggroup, int gcount, String gdate) {
		super();
		this.rgno = rgno;
		this.gname = gname;
		this.gmember = gmember;
		this.ggroup = ggroup;
		this.gcount = gcount;
		this.gdate = gdate;
	}

	public int getRgno() {
		return rgno;
	}

	public void setRgno(int rgno) {
		this.rgno = rgno;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGmember() {
		return gmember;
	}

	public void setGmember(String gmember) {
		this.gmember = gmember;
	}

	public String getGgroup() {
		return ggroup;
	}

	public void setGgroup(String ggroup) {
		this.ggroup = ggroup;
	}

	public int getGcount() {
		return gcount;
	}

	public void setGcount(int gcount) {
		this.gcount = gcount;
	}

	public String getGdate() {
		return gdate;
	}

	public void setGdate(String gdate) {
		this.gdate = gdate;
	}

	@Override
	public String toString() {
		return "ReceivingGoods [rgno=" + rgno + ", gname=" + gname + ", gmember=" + gmember + ", ggroup=" + ggroup
				+ ", gcount=" + gcount + ", gdate=" + gdate + "]";
	}
	
	
}
