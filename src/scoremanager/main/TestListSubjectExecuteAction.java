package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		TestListSubjectDao tlsubDao = new TestListSubjectDao();
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		String subjectStr="";//入力された在学フラグ
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();// 科目Daoを初期化
		int entYear = 0;// 入学年度
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化
		Subject subject = new Subject();
		System.out.println("7");
		entYearStr = req.getParameter("f1");//入学年度取得
		classNum = req.getParameter("f2");//入学年度取得
		subjectStr = req.getParameter("f3");//入学年度取得
		System.out.println(subjectStr);
		System.out.println("8");
		List<String> clist = cNumDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<Subject> sublist = subDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとに科目の一覧を取得

		System.out.println("9");
		subject=subDao.get(subjectStr,teacher.getSchool());


		System.out.println(subject.getName());


		System.out.println("10");
		if (subject != null){
			System.out.println("10-1");
			String subjectName = subject.getName();
			req.setAttribute("subjectName", subjectName);
			req.setAttribute("f3", subjectName);
			System.out.println(subjectName);
			}
		System.out.println("11");
		if (entYearStr != null) {
			// 数値に変換
			System.out.println("11-1");
			entYear = Integer.parseInt(entYearStr);
		}
		System.out.println("12");
		for (int i = year - 10; i < year + 10; i++) {
			System.out.println("12-1");
			entYearSet.add(i);
		}// 現在を起点に前後10年をリストに追加

		System.out.println("13");


		List<TestListSubject> list = tlsubDao.filter(entYear,classNum,subject,teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		if (list==null) {// 学生番号が入力されていない場合
			System.out.println("13-1");
			errors.put("student", "学生情報が存在しませんでした");

		}else{
			System.out.println("13-2");
			//student = sDao.get(student_no);// 学生番号から学生インスタンスを取得

			System.out.println("13-3");
			req.setAttribute("tlsub_set", list);//学生別のlistをセット
			System.out.println(list);
			//req.setAttribute("student", student);//学生別のlistをセット
			req.setAttribute("f1", entYear);
			// リクエストにクラス番号をセット
			req.setAttribute("f2", classNum);
			req.setAttribute("class_num_set", clist);//クラス番号のlistをセット
			req.setAttribute("subject_set", sublist);//のlistをセット
			req.setAttribute("ent_year_set", entYearSet);
			System.out.println("13-4");
		}
		if(!errors.isEmpty()){
			// リクエスト属性をセット
			System.out.println("a14-1");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
			return;
		}
		System.out.println("a15");
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}


