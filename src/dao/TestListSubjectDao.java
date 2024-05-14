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

	private String baseSql = "select student.student_no, ent_year, test.subject_cd, student.name, test.no, student.class_num, test.point from student left outer join test on student.student_no = test.student_no where test.point is not null and test.subject_cd = ? and student.ent_year = ? and student.class_num = ? ";
//SELECT distinct STUDENT.ENT_YEAR , SUBJECT.SUBJECT_CD,STUDENT.CLASS_NUM ,STUDENT.STUDENT_NO , STUDENT.NAME , TEST.NO,TEST.POINT  FROM STUDENT LEFT OUTER JOIN (TEST INNER JOIN  SUBJECT ON TEST.SUBJECT_CD = SUBJECT.SUBJECT_CD  ) ON STUDENT.STUDENT_NO = TEST.STUDENT_NO
	//where test.point is not null

	private List<TestListSubject> postFilter(ResultSet rSet)throws Exception{
		List<TestListSubject>list=new ArrayList<>();
		try{
			//回数、人数判定用
			String student = "";
			//科目別インスタンス
			TestListSubject tlsub=new TestListSubject();
			//リザルトセットを全件走査
			while(rSet.next()){
				//もし判定用に同じ番号が入っていたら
				if(student.equals(rSet.getString("student_no"))){
					tlsub.setPoints(tlsub.putPoint(rSet.getInt("no"),rSet.getInt("point")));
				}else{
					//判定用が空じゃないなら
					if(!(student.equals(""))){
						list.add(tlsub);
					}
					//リストにセットしていく
					tlsub = new TestListSubject();
					tlsub.setEntYear(rSet.getInt("ent_year"));
					tlsub.setStudent_no(rSet.getString("student_no"));
					tlsub.setStudent_name(rSet.getString("name"));
					tlsub.setClass_num(rSet.getString("class_num"));
					tlsub.setPoints(tlsub.putPoint(rSet.getInt("no"),rSet.getInt("point")));
					student=rSet.getString("student_no");
				}

			}
			//最後の１人をセットするためのやつ
			if (!(student.equals(""))){
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

		try{
			statement=connection.prepareStatement(baseSql);
			statement.setString(1, subject.getSubject_cd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);


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
