
package com.project.DTO;

public class IGroup {

	private int igno;
	private String name;
	
	@Override
	public String toString() {
		return "IGroup [igno=" + igno + ", name=" + name + "]";
	}
	public int getIgno() {
		return igno;
	}
	public void setIgno(int igno) {
		this.igno = igno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IGroup() {}
	
	public IGroup(int igno, String name) {
		this.igno = igno;
		this.name = name;
	}
	
}
