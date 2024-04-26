package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();//セッション
		StudentDao sDao = new StudentDao();//学生Dao
		TestListStudentDao tlsDao=new TestListStudentDao();
		Student student = null;//学生
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		String student_no = "";//学生番号

		student_no = req.getParameter("student_no");//学生番号

	}

}
