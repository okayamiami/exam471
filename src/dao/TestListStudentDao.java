package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
	private String baseSql= "select distinct subject.name,subject.subject_cd,test.no,test.point from test inner join subject on subject.subject_cd=test.subject_cd where student_no = ? ";

	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		List<TestListStudent>list=new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while(rSet.next()){
				//学生インスタンス初期化
				//学生情報をセットしていく

				TestListStudent tlstu=new TestListStudent();
				tlstu.setName(rSet.getString("name"));
				tlstu.setSubject_cd(rSet.getString("subject_cd"));
				tlstu.setNo(rSet.getInt("no"));
				tlstu.setPoint(rSet.getInt("point"));

				//リストにセットしていく
				list.add(tlstu);
			}
		}catch(SQLException |NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}


	public List<TestListStudent> filter(Student student) throws Exception{
		List<TestListStudent>list=new ArrayList<>();

		//データベースへのコネクション
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;
		//SQL文の条件追加

		//SQL文のソート
		//String sqls="order by subject_cd asc ";

		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(baseSql);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, student.getStudent_no());
			//プリペアードステートメントを実行
			rSet=statement.executeQuery();
			list=postFilter(rSet);
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
}


