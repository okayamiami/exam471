package bean;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 学生番号:String
	 */
	private String student_no;

	/**
	 * 氏名:String
	 */
	private String name;

	/**
	 * 入学年度:int
	 */
	private int entYear;

	/**
	 * クラス番号:String
	 */
	private String classNum;

	/**
	 * 在学中フラグ:boolean true:在学中
	 */
	private boolean isAttend;

	/**
	 * 所属校:School
	 */
	private School school;


	/**
	 * ゲッター、セッター
	 */
	public String getStudent_no() {
		return student_no;
	}

	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public boolean isAttend() {
		return isAttend;
	}

	public void setAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}
