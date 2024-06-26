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


	//getメソッド
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
			//SubjectDao subjectDao= new SubjectDao();

			if(rSet.next()){
				//リザルトセットが存在する場合
				//科目インスタンスに検索結果をセット
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


	private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
		//空のリスト作成
		List<Subject>list=new ArrayList<>();
		try{
			//リザルトセットを全件走査
			while(rSet.next()){
				//科目インスタンス初期化
				//科目情報をセットしていく

				Subject subject=new Subject();

				subject.setSubject_cd(rSet.getString("subject_cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
				//リストにセットしていく
				list.add(subject);

			}
		}catch(SQLException |NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}




	//filterメソッド
	public List<Subject> filter(School school) throws Exception {

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
		String sql="select * from subject where SCHOOL_CD=? ";

		//SQL文のソート
		//String sqls="order by SUBJECT_CD asc ";

		try{
			//プリペアードステートメントにSQLセット
			statement=connection.prepareStatement(sql);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());

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

	//saveメソッド
	public boolean save(Subject subject) throws Exception {
		//データベースへのコネクション
		Connection connection =getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//実行件数
		int count=0;
		try{
			//データベースから科目情報取得
			Subject old= get(subject.getSubject_cd(), subject.getSchool());
			if(old==null){
				//科目が存在しなかった場合
				//プリペアにINSERT文セットし新たな科目を作る
				System.out.println("追加sql開始");
				statement=connection.prepareStatement(
						"insert into subject(subject_cd,name,school_cd) values(?,?,?)");
				//プリペアにバインド
				statement.setString(1, subject.getSubject_cd());
				statement.setString(2, subject.getName());
				statement.setString(3, subject.getSchool().getCd());
				System.out.println("追加sql終了");
			}else{
				//科目が存在した場合
				//プリペアにUPDATE文セットし更新する
				System.out.println("更新sql開始");
				statement=connection.prepareStatement(
						"update subject set name=? where subject_cd=? and school_cd=? ");
				//プリペアにバインド
				statement.setString(1, subject.getName());
				statement.setString(2,subject.getSubject_cd());
				statement.setString(3, subject.getSchool().getCd());
				System.out.println("更新sql完了");

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


	//deleteメソッド
	public boolean delete(Subject subject)throws Exception{
		//falseを入れておく

		//データベースへのコネクション
		Connection connection =getConnection();
		//プリペアードステートメント
		PreparedStatement statement=null;
		//実行件数
		int count=0;
		try{
			//データベースから科目情報取得
			System.out.println("デリートsql開始");
			Subject old= get(subject.getSubject_cd(), subject.getSchool());
			if(old !=null){
				//科目と学校コードがnullじゃなければ科目コードが?の科目を削除
				statement=connection.prepareStatement(
						"delete from subject where subject_cd=? and school_cd=? ");
						//H2で使用　delete from subject where subject_cd='DIE' and school_cd='knz'
				//プリペアにバインド
				statement.setString(1, subject.getSubject_cd());
				statement.setString(2, subject.getSchool().getCd());
				System.out.println("デリートsql終了");
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
