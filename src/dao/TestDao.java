package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao  extends Dao{


	public Test get(String student,Subject subject, School school,int no) throws Exception {
		//成績インスタンス初期化
		Test test=new Test();
		//データベースへのコネクション
		Connection connection =getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;

		try{
			//prepareにsql文セット(
			statement=connection.prepareStatement("select * from test where no=? ");
			//バインド
			statement.setString(1, student);
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);

			//プリペアードステートメント実行
			ResultSet rSet=statement.executeQuery();

			//各DAO初期化
			SchoolDao schoolDao= new SchoolDao();
			StudentDao studentDao = new StudentDao();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				test.setClassNum(rSet.getString("class_num"));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
				test.setSubject(subjectDao.get(rSet.getString("subject")));
				test.setStudent(studentDao.get(rSet.getString("student")));

			}else{
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				student=null;
			}
		}catch(Exception e){
			throw e;
		}finally{
			//プリペア閉じる
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return test;
	}



	private String baseSql = "select * from test where school_cd = ? ";


	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		//空のリスト作成
		List<Test>list=new ArrayList<>();
		try{
			//リザルトセットを全件走査

			while(rSet.next()){
				//学生インスタンス初期化
				//学生情報をセットしていく

				Test test=new Test();

				test.setClassNum(rSet.getString("class_num"));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));

				test.setSchool(school);


				//リストにセットしていく
				list.add(test);

			}
		}catch(SQLException |NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}


	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		//戻り値用のリストを作成
		//new演算子とArrayListで空のListを用意
		List<Test> list = new ArrayList<>();

		//データベースへのコネクション
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;
		//SQL文の条件追加
		String sql2="and ent_year=? and class_num=? ";
		//SQL文のソート
		String sqls="order by student_no asc ";

		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(baseSql+sql2+sqls);
			//プリペアードステートメントに入学年度をバインド
			statement.setInt(1, entYear);
			//プリペアードステートメントにクラス番号をバインド
			statement.setString(2, classNum);
			//プリペアードステートメントに科目コードをバインド
			statement.setSubject(3, subject.getCd());
			//プリペアードステートメントにnumをバインド
			statement.setInt(4, num);

			//プリペアードステートメントを実行
			rSet=statement.executeQuery();
			list=postFilter(rSet,school);
		}catch(Exception e){
			throw e;
		}finally{
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}

	/**
	 * filterメソッド 学校、入学年度、在学フラグを指定して学生の一覧を取得する
	 *
	 * @param school:School
	 *            学校
	 * @param entYear:int
	 *            入学年度
	 * @param isAttend:boolean
	 *            在学フラグ
	 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
		//戻り値用のリストを作成
		//new演算子とArrayListで空のListを用意
		List<Student> list = new ArrayList<>();

		//データベースへのコネクション
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;
		//SQL文の条件追加
		String sql2="and ent_year=? ";
		//SQL文のソート
		String sqls="order by student_no asc ";

		//SQL文の在学フラグ条件
		String sql3="";
		//trueの場合
		if(isAttend){
			sql3="and is_attend=true ";
		}

		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(baseSql+sql2+sql3+sqls);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			//プリペアードステートメントに入学年度をバインド
			statement.setInt(2, entYear);
			//プリペアードステートメントを実行
			rSet=statement.executeQuery();

			list=postFilter(rSet,test);
		}catch(Exception e){
			throw e;
		}finally{
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}

	/**
	 * filterメソッド 学校、在学フラグを指定して学生の一覧を取得する
	 *
	 * @param school:School
	 *            学校
	 * @param isAttend:boolean
	 *            在学フラグ
	 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<Student> filter(School school, boolean isAttend) throws Exception {
		//戻り値用のリストを作成
		//new演算子とArrayListで空のListを用意
		List<Student> list = new ArrayList<>();

		//データベースへのコネクション
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;

		//SQL文のソート
		String sqls="order by student_no asc ";

		//SQL文の在学フラグ条件
		String sql3="";
		//trueの場合
		if(isAttend){
			sql3="and is_attend=true ";
		}

		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(baseSql+sql3+sqls);
			statement.setString(1, school.getCd());

			//プリペアードステートメントを実行
			//System.out.println(statement);救世主

			rSet = statement.executeQuery();

			list=postFilter(rSet,school);

		}catch(Exception e){
			throw e;
		}finally{
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}

	/**
	 * saveメソッド 学生インスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
	 *
	 * @param student：Student
	 *            学生
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean save(Student student) throws Exception {
		//データベースへのコネクション
		Connection connection =getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//実行件数
		int count=0;
		try{
			//データベースから学生取得
			Student old=get(student.getStudent_no());
			if(old==null){
				//学生が存在しなかった場合
				//プリペアにINSERT文セット
				statement=connection.prepareStatement(
						"insert into student(student_no,name,ent_year,class_num,is_attend,school_cd) values(?,?,?,?,?,?)");
				//プリペアにバインド
				statement.setString(1, student.getStudent_no());
				statement.setString(2, student.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.isAttend());
				statement.setString(6, student.getSchool().getCd());
			}else{
				//学生が存在しなかった場合
				//プリペアにUPDATE文セット
				statement=connection.prepareStatement(
						"update student set name=?, ent_year=?, class_num=?, is_attend=? where student_no=? ");
				//プリペアにバインド
				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.isAttend());
				statement.setString(5, student.getStudent_no());
			}
			//プリペア実行
			count=statement.executeUpdate();
		}catch(Exception e){
			throw e;
		}finally{
			if(statement !=null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			if(connection !=null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		if(count>0){
			//1件以上
			return true;
		}else{
			//0の場合
			return false;
		}
	}
}

