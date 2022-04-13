package com.project.DTO;

public class Inventory {
	private int ino;
	private String name;
	private String code;
	private int count;
	private String lno;
	private String igno;
	
	public Inventory() {}
	public Inventory(int ino, String name, String code, int count, String lno, String igno) {
		super();
		this.ino = ino;
		this.name = name;
		this.code = code;
		this.count = count;
		this.lno = lno;
		this.igno = igno;
	}
	@Override
	public String toString() {
		return "Inventory [ino=" + ino + ", name=" + name + ", code=" + code + ", count=" + count + ", lno=" + lno
				+ ", igno=" + igno + "]";
	}
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLno() {
		return lno;
	}
	public void setLno(String lno) {
		this.lno = lno;
	}
	public String getIgno() {
		return igno;
	}
	public void setIgno(String igno) {
		this.igno = igno;
	}
	
}
