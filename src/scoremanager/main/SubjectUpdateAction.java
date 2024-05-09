package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		SchoolDao scDao = new SchoolDao();
		SubjectDao sbDao = new SubjectDao();//科目DAOをインスタンス化

		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得
		String subject_cd = req.getParameter("subject_cd");//科目コード
		String school_cd = req.getParameter("school_cd");//学校コード
		String subject_name = req.getParameter("subject_name");
		School sc = scDao.get(school_cd);

		Subject subject = sbDao.get(subject_cd, sc);//科目コードから科目インスタンスを取得
		List<Subject> list = sbDao.filter(teacher.getSchool());

		//ビジネスロジック
		//科目コードから学生インスタンスを取得
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//条件で手順4~6の内容が分岐
		req.setAttribute("subject_name", subject_name);
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);

	}

}
