package bean;

import java.io.Serializable;

public class Subject implements Serializable{


	//変数名
	private String subject_cd;
	private String name;
	private School school;


	//ゲッター、セッター
	public String getSubject_cd() {
		return subject_cd;
	}
	public void setSubject_cd(String subject_cd) {
		this.subject_cd = subject_cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

}
