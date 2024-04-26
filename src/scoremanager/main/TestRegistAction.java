package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		TestDao sDao = new TestDao();//学生Dao
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao subDao = new SubjectDao();// 科目Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメータ―の取得 2
		String student_no = req.getParameter("student_no");//学番
		String subject_cd = req.getParameter("subject_cd");//科目

		//DBからデータ取得 3
		Student student = sDao.get(student_no);//学生番号から学生インスタンスを取得
		List<String> clist = cNumDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> slist = sDao.filter(teacher.getSchool());// ログインユーザーの科目コードをもとに科目の一覧を取得


		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//条件で手順4~6の内容が分岐
		req.setAttribute("class_num_set", list);
		if (student != null) {// 成績が存在していた場合
			System.out.println("1");
			req.setAttribute("ent_year", student.getEntYear());
			req.setAttribute("class_num", student.getClassNum());
			req.setAttribute("student_no", student.getStudent_no());
			req.setAttribute("name", student.getName());

		} else {// 学生が存在していなかった場合
			errors.put("student_no", "学生が存在していません");
			req.setAttribute("errors", errors);
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}