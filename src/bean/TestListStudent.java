package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable{
	private String name;
	private String subject_cd;
	private int no;
	private int point;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getsubject_cd() {
		return subject_cd;
	}
	public void setSubject_cd(String subject_cd) {
		this.subject_cd = subject_cd;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}



}
