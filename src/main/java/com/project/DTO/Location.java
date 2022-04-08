
package com.project.DTO;

public class Location {

	private int lno;
	private String name;
	
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Location [lno=" + lno + ", name=" + name + "]";
	}
	public Location() {}
	
	public Location(int lno, String name) {
		this.lno = lno;
		this.name = name;
	}
	
	
	
	
}
