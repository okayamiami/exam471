package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		StudentDao sDao = new StudentDao();//学生Dao
		TestListStudentDao tlsDao=new TestListStudentDao();
		Student student = null;//学生
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		String student_no = "";//学生番号

		student_no = req.getParameter("student_no");//学生番号

		//DBからデータ取得 3

		//↓これいらない
		if (sDao.get(student_no)==null) {// 学生番号が入力されていない場合
			errors.put("student", "学生情報が存在しませんでした");

		}else{
			student = sDao.get(student_no);// 学生番号から学生インスタンスを取得
			List<TestListStudent> list = tlsDao.filter(student);// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
			System.out.println("a10");
			req.setAttribute("tls_set", list);//学生別のlistをセット
			req.setAttribute("student", student);//学生別のlistをセット
			System.out.println("a11");
		}
		System.out.println("a12");
		if(!errors.isEmpty()){
			// リクエスト属性をセット
			System.out.println("a13-1");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
			return;
		}
		System.out.println("a13");
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}

}
