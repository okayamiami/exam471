package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{


	public Subject get(String subject_cd, School school)throws Exception {
		//科目インスタンス初期化
		Subject subject = new Subject();
		SchoolDao scDao = new SchoolDao();
		//データベースへのコネクション
		Connection connection =getConnection();

		//プリペアードステートメント
		PreparedStatement statement=null;

		try{
			//prepareにsql文セット
			statement=connection.prepareStatement("select * from subject where subject_cd=? ");
			//バインド
			statement.setString(1, subject_cd);
			//プリペアードステートメント実行
			ResultSet rSet=statement.executeQuery();
			//科目DAO初期化
			SubjectDao subjectDao= new SubjectDao();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				subject.setSubject_cd(rSet.getString("subject_cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(scDao.get(rSet.getString("school_cd")));
			}else{
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject=null;
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
		return subject;
	}

	private List<Subject> filter(School school) throws Exception {

		//戻り値用のリストを作成
		//new演算子とArrayListで空のListを用意
		List<Subject> list = new ArrayList<>();

		//データベースへのコネクション
		Connection connection=getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//リザルトセット
		ResultSet rSet=null;
		//SQL文の条件を新規に作成
		String sql="select * from subject where SCHOOL_CD=? and SUBJECT_CD=? and NAME=? ";
		//SQL文のソート
		String sqls="order by SUBJECT_CD asc ";

		//SQL文の在学フラグ条件
		//String sql3="";
		//trueの場合
		//if(isAttend){
		//	sql3="and is_attend=true ";
		//}

		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(sql+sqls);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			//プリペアードステートメントに入学年度をバインド
			statement.setString(2, rSet.getString("subject_cd"));
			//プリペアードステートメントにクラス番号をバインド
			statement.setString(3, rSet.getString("name"));
			//プリペアードステートメントを実行
			rSet=statement.executeQuery();
			//list=postFilter(rSet,school);
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

	public boolean save(Subject subject) throws Exception {
		//データベースへのコネクション
		Connection connection =getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//実行件数
		int count=0;
		try{
			//データベースから科目情報取得
			Subject old= get(subject.getSubject_cd());
			if(old==null){
				//科目が存在しなかった場合
				//プリペアにINSERT文セットし新たな科目を作る
				statement=connection.prepareStatement(
						"insert into subject(subject_cd,name,school_cd) values(?,?,?)");
				//プリペアにバインド
				statement.setString(1, subject.getSubject_cd());
				statement.setString(2, subject.getName());
				statement.setString(6, subject.getSchool().getCd());
			}else{
				//科目が存在しなかった場合
				//プリペアにUPDATE文セットし更新する
				statement=connection.prepareStatement(
						"update student set name=?, school_cd=? where subject_cd=? ");
				//プリペアにバインド
				statement.setString(2, subject.getName());
				statement.setString(3, subject.getSchool());
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


	public boolean delete(Subject subject)throws Exception{
		//falseを入れておく
		return false;
	}



}
