package com.project.DTO;

public class Team {

	private int tno;
	private String name;
	
	public Team() {}
	
	public Team(int tno, String name) {
		this.tno = tno;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Team [tno=" + tno + ", name=" + name + "]";
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
