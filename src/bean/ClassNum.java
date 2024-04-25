package bean;

import java.io.Serializable;

public class ClassNum implements Serializable {

	/**
	 * 学校:School
	 */
	private School school;

	/**
	 * クラス番号:String
	 */
	private String class_num;


	/**
	 * ゲッター、セッター
	 */
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getNum() {
		return class_num;
	}

	public void setNum(String class_num) {
		this.class_num = class_num;
	}

}
