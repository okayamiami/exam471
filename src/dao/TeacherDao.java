package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDao extends Dao{

	//getメソッド
	public Teacher get(String id) throws Exception{
		//先生インスタンス初期化
		Teacher teacher = new Teacher();
		SchoolDao scDao = new SchoolDao();
		//データベースへのコネクション
		Connection connection =getConnection();

		//プリペアードステートメント
		PreparedStatement statement=null;

		try{
			//prepareにsql文セット
			statement=connection.prepareStatement("select * from teacher where id=? ");
			//バインド
			statement.setString(1, id);
			//プリペアードステートメント実行
			ResultSet rSet=statement.executeQuery();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				teacher.setSchool(scDao.get(rSet.getString("school_cd")));
			}else{
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				teacher=null;
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
		return teacher;
	}

	//loginメソッド
	public Teacher login(String id, String password) throws Exception{
		//先生インスタンス初期化
		Teacher teacher = new Teacher();
		SchoolDao scDao = new SchoolDao();
		//データベースへのコネクション
		Connection connection =getConnection();

		//プリペアードステートメント
		PreparedStatement statement=null;

		try{
			//prepareにsql文セット
			statement=connection.prepareStatement("select * from teacher where id=? and password=? ");
			//バインド
			statement.setString(1, id);
			statement.setString(2, password);
			//プリペアードステートメント実行
			ResultSet rSet=statement.executeQuery();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				teacher.setId(rSet.getString("id"));
				teacher.setPassword(rSet.getString("password"));
				teacher.setName(rSet.getString("name"));
				teacher.setSchool(scDao.get(rSet.getString("school_cd")));
			}else{
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				teacher=null;
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
		return teacher;
	}
}