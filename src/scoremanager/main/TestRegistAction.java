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
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		String subjectStr="";// 入力された科目コード
		String numStr="";// 入力された回数
		String pointStr="";// 入力された得点
		int num = 0;//回数
//		int point = 0;//得点
		int entYear = 0;// 入学年度
		List<Test> tests = null;// 学生リスト
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化

		SubjectDao subDao = new SubjectDao();// 科目Daoを初期化
		TestDao tDao = new TestDao();// テストDaoを初期化
		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		System.out.println("1");

		Subject subject = new Subject();
		//リクエストパラメータ―の取得 2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subjectStr = req.getParameter("f3");
		numStr = req.getParameter("f4");

		System.out.println("2");
		//DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> clist = cNumDao.filter(teacher.getSchool());
		List<Subject> slist = subDao.filter(teacher.getSchool());

		System.out.println("2-1");
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}

		System.out.println("2-2");
		if (numStr != null) {
			// 数値に変換
			num = Integer.parseInt(numStr);
		}
		System.out.println(subjectStr);

//		System.out.println("2-3");
//		if (pointStr != null) {
//			// 数値に変換
//			point = Integer.parseInt(pointStr);
//		}

		subject.setSubject_cd(subjectStr);

		System.out.println("2-4");
		if (entYear != 0 && !classNum.equals("0") && !subject.equals("0")&& num != 0) {
			// 入学年度、クラス番号、回数を指定
			System.out.println("3");
			tests = tDao.filter(entYear, classNum, subject , num, teacher.getSchool());
			System.out.println("4");
		} else {
			System.out.println("2-5");
			errors.put("f1", "指定してください");
			req.setAttribute("errors", errors);
		}

		//ビジネスロジック 4
		System.out.println("5");
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}

		//DBへデータ保存 5
		//なし

		//レスポンス値をセット 6
		// リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		// リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);
		// リクエストに科目をセット


		if (subjectStr != null){
		String subjectName = subject.getName();
		req.setAttribute("subjectName", subjectName);
		req.setAttribute("f3", subjectName);
		}

		req.setAttribute("f4", num);

		// リクエストにテストリストをセット
		req.setAttribute("tests", tests);
		// リクエストにデータをセット
		req.setAttribute("class_num_set", clist);
		System.out.println(clist);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("subject_set", slist);
		req.setAttribute("num", num);
		req.setAttribute("ent_year", entYear);
		//JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
		System.out.println("a");
	}



	//private void setRequestData(HttpServletRequest req, HttpServletResponse res) {

	//}
}
