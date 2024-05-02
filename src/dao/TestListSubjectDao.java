package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao{

	private String baseSql;

	private List<TestListSubject> postFilter(ResultSet rSet)throws Exception{
		List<TestListSubject>list=new ArrayList<>();
		try{
			//リザルトセットを全件走査

			while(rSet.next()){
				//学生インスタンス初期化
				//学生情報をセットしていく

				TestListSubject tlsub=new TestListSubject();

				tlsub.setStudent_no(rSet.getString("student_no"));
				tlsub.setStudent_name(rSet.getString("student_name"));
				tlsub.setClass_num(rSet.getString("class_num"));
				tlsub.setPoints(tlsub.putPoint(rSet.getInt("no"),rSet.getInt("point")));

				//リストにセットしていく
				list.add(tlsub);

			}
		}catch(SQLException |NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}
	public List<TestListSubject> filter(int entYear,String classNum,Subject subject,School school) throws Exception{

		List<TestListSubject>list=new ArrayList<>();
		//データベースへのコネクション
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;
		//SQL文の条件追加
		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(baseSql);

			//プレースホルダー未完　statement.setString(1, );

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
