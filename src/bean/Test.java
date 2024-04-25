package bean;

import java.io.Serializable;

import javax.security.auth.Subject;

public class Test implements Serializable {

	/**
	 * Student:Student
	 */
	private Student student;

	/**
	 * クラス番号:String
	 */
	private String classNum;

	/**
	 * 科目:Subject
	 */
	private Subject subject;

	/**
	 * 所属校:School
	 */
	private School school;

	/**
	 * 回数:String
	 */
	private int no;

	/**
	 * 得点:Point
	 */
	private int point;

	//以下ゲッターセッター

	public Student getStudent() {
		return student;
	}

	public String getClassNum() {
		return classNum;
	}

	public Subject getSubject() {
		return subject;
	}

	public School getSchool() {
		return school;
	}

	public int getNo() {
		return no;
	}

	public int getPoint() {
		return point;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}