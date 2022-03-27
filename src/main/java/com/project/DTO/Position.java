package com.project.DTO;

public class Position {

	private int pno;
	private String name;
	private int seq;
	
	public Position() {}
	
	public Position(int pno, String name, int seq) {
		this.pno = pno;
		this.name = name;
		this.seq = seq;
	}
	
	@Override
	public String toString() {
		return "Position [pno=" + pno + ", name=" + name + ", seq=" + seq + "]";
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
}
