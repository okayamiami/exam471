package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable{
	private int entYear;
	private String student_no;
	private String student_name;
	private String class_num;
	private Map<Integer,Integer> points;

	public TestListSubject() {
		points=new HashMap<>();
	}
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public String getStudent_no() {
		return student_no;
	}
	public void setStudent_no(String student_no) {
		this.student_no = student_no;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getClass_num() {
		return class_num;
	}
	public void setClass_num(String class_num) {
		this.class_num = class_num;
	}
	public Map<Integer, Integer> getPoints() {
		return points;
	}
	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}


	/**
	 * getPointメソッド　得点マップから値を取得する
	 *
	 * @param key:int
	 * 			回数
	 * @return　得点:String
	 */
	public String getPoint(int key){
		Integer k=points.get(key);
		if (k==null){
			return "-";
		}else{
			return k.toString();
		}
	}
	public Map<Integer,Integer> putPoint(int key,int value){
		points.put(key, value);
		return points;
	}

}
