package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;


public class TestDao extends Dao{

	private String baseSql = "select * from test";


	public Test get(Student student, Subject subject, School school, int no) throws Exception {

		// testを初期化
		Test test = new Test();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		String condition = " where student_no=? and subject_cd=? and no=?";
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition);
			//プレースホルダにバインド
			statement.setString(1, student.getStudent_no());
			statement.setString(2, subject.getSubject_cd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);
			ResultSet rSet = statement.executeQuery();

			if ( rSet.next()) {

				test.setStudent(student);
				test.setSchool(school);
				test.setSubject(subject);
				test.setNo(no);
				test.setPoint(rSet.getInt("point"));

			} else {
				test = null;
			}

		} catch (Exception e){
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return test;
	}


	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {

		// リストを初期化
		List<Test> list = new ArrayList<>();
		try {
			while(rSet.next()){
				Test test = new Test();
			StudentDao studentDao = new StudentDao();
			SubjectDao subjectDao = new SubjectDao();

			// テストインスタンスに検索結果をセット
			test.setStudent(studentDao.get(rSet.getString("student_no")));
			test.setPoint(rSet.getInt("point"));
			test.setNo(rSet.getInt("no"));
			test.setClassNum(rSet.getString("class_num"));
			test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
			test.setSchool(school);
			//リストに追加
			list.add(test);
			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}


	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

		// リストを初期化
		List<Test> list = new ArrayList<>();

		// コネクションを確立
		Connection connection = getConnection();

		// プリペアードステートメント
		PreparedStatement statement = null;

		// リザルトセット
		ResultSet rSet = null;

		// SQL文の条件
		String condition = " where ent_year=? and student.class_num=? and (subject_cd=? or subject_cd is null) and (test.no=? or test.no is null) and student.school_cd=? ";


		// SQL文のソート
		String order = " order by student_no asc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select ent_year,student.class_num, student.student_no as student_no, isnull(subject_cd, ?)as subject_cd,coalesce(test.no, ?) as no ,coalesce(point,null)as point from test right outer join student on test.student_no = student.student_no"+ condition + order);

			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			statement.setInt(2, num);
			statement.setInt(3, entYear);
			statement.setString(4, classNum);
			statement.setString(5, subject.getSubject_cd());
			statement.setInt(6, num);
			statement.setString(7, school.getCd());

			// プライベートステートメントを実行
			rSet = statement.executeQuery();
			list = postFilter(rSet, school);

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}


	public boolean save(List<Test> list) throws Exception {

		int count =0;
			for(Test test:list){
			// コネクションを確立
				Connection connection = getConnection();
				try{
					boolean bool = save(test,connection);
				if (bool != true){
					count++;
				}
				} catch (Exception e) {
					throw e;
				} finally {

					// コネクションを閉じる
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
				}
			}
		if (count == 0) {
			// 実行件数が一件以上ある場合
			return true;
		} else {
			// 実行件数が０件の場合
			return false;
		}
	}



	private boolean save(Test test, Connection connection) throws Exception {
		PreparedStatement statement = null;

		int count =0;

		try{
			Test old = get(test.getStudent(),test.getSubject(),test.getSchool(), test.getNo());
			//
			if(old == null){
			//
			statement = connection.prepareStatement("insert into test(student_no,subject_cd,school_cd,no,point,class_num)values(?, ?, ?, ?, ?, ?)");

			//プレースホルダ―に値を設定
			statement.setString(1, test.getStudent().getStudent_no());
			statement.setString(2, test.getSubject().getSubject_cd());
			statement.setString(3, test.getSchool().getCd());
			statement.setInt(4, test.getNo());
			statement.setInt(5, test.getPoint());
			statement.setString(6, test.getClassNum());

			}else{
				statement = connection.prepareStatement("update test set point=? where student_no=? and subject_cd=? and no=?");
				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getStudent_no());
				statement.setString(3, test.getSubject().getSubject_cd());
				statement.setString(4, test.getSchool().getCd());
				statement.setInt(5, test.getNo());


			}
		count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}
}
//
//	public boolean delete(List<Test> list) {
//
//	}
//
//	private boolean delete(Test test, Connection connection) {
//
//	}
//
//
//
//
//}
