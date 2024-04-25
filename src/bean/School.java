package bean;

import java.io.Serializable;

public class School implements Serializable {

	/**
	 * 学校コード:String
	 */
	private String school_cd;

	/**
	 * 学校名:String
	 */
	private String name;

	/**
	 * ゲッター、セッター
	 */
	public String getCd() {
		return school_cd;
	}
	public void setCd(String school_cd) {
		this.school_cd = school_cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
