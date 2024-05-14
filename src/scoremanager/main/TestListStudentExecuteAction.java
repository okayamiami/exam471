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
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		StudentDao sDao = new StudentDao();//学生Dao
		TestListStudentDao tlsDao=new TestListStudentDao(); //学生別Dao
		Student student = null;//学生
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		String student_no = "";//学生番号
		String entYearStr="";// 選択された入学年度
		String classNum = "";//選択されたクラス番号
		String subject="";//選択された科目フラグ
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();// 科目Daoを初期化
		int entYear = 0;// 入学年度
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化


		student_no = req.getParameter("student_no");//reqから学生番号

		entYearStr = req.getParameter("f1");//reqからf1
		classNum = req.getParameter("f2");//reqからf2
		subject = req.getParameter("f3");//reqからf3

		List<String> clist = cNumDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		//System.out.println("3");
		List<Subject> sublist = subDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとに科目の一覧を取得


		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}

		// 現在を起点に前後10年をリストに追加
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}



		if (sDao.get(student_no)==null) {// 学生番号が入力されていない場合
			errors.put("student", "学生情報が存在しませんでした");
		}else{
			student = sDao.get(student_no);// 学生番号から学生インスタンスを取得
			List<TestListStudent> list = tlsDao.filter(student);// 学生インスタンスで学生別成績の情報取得
			req.setAttribute("tls_set", list);//学生別のlistをセット
			req.setAttribute("student", student);//学生をセット
		}


		// リクエストにセット
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3",subject);
		req.setAttribute("class_num_set", clist);//クラス番号のlistをセット
		req.setAttribute("subject_set", sublist);//科目のlistをセット
		req.setAttribute("ent_year_set", entYearSet);//年度セット


		//errorsが空じゃない場合
		if(!errors.isEmpty()){
			// リクエストにエラーメッセージをセット
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}

}
