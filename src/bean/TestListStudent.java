package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable{
	private String subjectName;
	private String subject_Cd;
	private int num;
	private int point;


	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubject_Cd() {
		return subject_Cd;
	}
	public void setSubject_Cd(String subject_Cd) {
		this.subject_Cd = subject_Cd;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}



}
