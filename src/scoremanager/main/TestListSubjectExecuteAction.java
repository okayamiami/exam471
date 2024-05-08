package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		StudentDao sDao = new StudentDao();//学生Dao
		TestListSubjectDao tlsubDao = new TestListSubjectDao();
		Student student = null;//学生
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		String student_no = "";//学生番号
		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		String subject="";//入力された在学フラグ
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();// 科目Daoを初期化
		int entYear = 0;// 入学年度
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化

		entYearStr = req.getParameter("f1");//入学年度取得
		entYearStr = req.getParameter("f1");//入学年度取得
		entYearStr = req.getParameter("f1");//入学年度取得
		//ここまで

	}

}
