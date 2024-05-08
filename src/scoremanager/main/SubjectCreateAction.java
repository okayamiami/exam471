package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得

		SubjectDao sbDao = new SubjectDao();//科目Dao
		//リクエストパラメータ―の取得 2
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		// ログインユーザーの学校コードをもとに科目の一覧を取得
		List<Subject> list = sbDao.filter(teacher.getSchool());

		//DBからデータ取得 3

		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにデータをセット
		req.setAttribute("sblist", list);


		req.getRequestDispatcher("subject_create.jsp").forward(req, res);

	}

}
